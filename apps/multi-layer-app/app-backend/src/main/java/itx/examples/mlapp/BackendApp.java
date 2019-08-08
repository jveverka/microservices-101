package itx.examples.mlapp;

import com.beust.jcommander.JCommander;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import itx.examples.mlapp.config.Arguments;
import itx.examples.mlapp.service.BackendId;
import itx.examples.mlapp.services.DataServiceImpl;
import itx.examples.mlapp.services.ManagerConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.UUID;

import static itx.examples.mlapp.apis.Constants.BACKEND_DEFAULT_GRPC_PORT;

public class BackendApp {

    private static final Logger LOG = LoggerFactory.getLogger(BackendApp.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        Arguments arguments = new Arguments();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        BackendId id =  BackendId.newBuilder()
                .setId(UUID.randomUUID().toString())
                .build();
        LOG.info("BackendApp started id={} capability={} {}:{} ...",
                id, arguments.getCapability(), arguments.getSelfAddress(), arguments.getPort());

        for(InetAddress addr : InetAddress.getAllByName(arguments.getManagerAddress())) {
            LOG.info("Resolved manager address: {}", addr.getHostAddress());
        }

        DataServiceImpl dataService =
                new DataServiceImpl(id, arguments.getSelfAddress(), arguments.getPort(), arguments.getCapability());
        ManagerConnector managerConnector =
                new ManagerConnector(id, arguments.getManagerAddress(), arguments.getManagerPort(), arguments.getCapability(),
                        arguments.getSelfAddress(), BACKEND_DEFAULT_GRPC_PORT);

        Server server = NettyServerBuilder.forAddress(
                new InetSocketAddress(arguments.getSelfAddress(), BACKEND_DEFAULT_GRPC_PORT))
                .addService(dataService)
                .build();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                LOG.info("BackendApp shutting down ...");
                try {
                    managerConnector.close();
                } catch (Exception e) {
                    LOG.warn("Frontend connection loop shutdown error!");
                }
                server.shutdown();
                LOG.info("BackendApp shutdown.");
            }
        });

        LOG.info("BackendApp, listening on {}:{}", arguments.getSelfAddress(), arguments.getPort());
        server.start();
        managerConnector.startManagerConnectionLoop();
        server.awaitTermination();
    }

}
