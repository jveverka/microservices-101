package itx.examples.mlapp;

import com.beust.jcommander.JCommander;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import itx.examples.mlapp.config.Arguments;
import itx.examples.mlapp.services.DataServiceImpl;
import itx.examples.mlapp.services.ManagerConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.UUID;

public class BackendApp {

    private static final Logger LOG = LoggerFactory.getLogger(BackendApp.class);

    public static final int FRONTEND_DEFAULT_PORT = 5556;
    public static final int BACKEND_DEFAULT_PORT = 5555;

    public static void main(String[] args) throws InterruptedException, IOException {
        Arguments arguments = new Arguments();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        String id = UUID.randomUUID().toString();
        LOG.info("BackendApp started with capability: {} {} {}:{} ...",
                id, arguments.getCapability(), arguments.getSelfAddress(), arguments.getPort());

        DataServiceImpl dataService =
                new DataServiceImpl(id, arguments.getSelfAddress(), arguments.getPort(), arguments.getCapability());
        ManagerConnector managerConnector =
                new ManagerConnector(id, arguments.getManagerAddress(), FRONTEND_DEFAULT_PORT, arguments.getCapability(),
                        arguments.getSelfAddress(), BACKEND_DEFAULT_PORT);
        managerConnector.startManagerConnectionLoop();

        Server server = NettyServerBuilder.forAddress(
                new InetSocketAddress(arguments.getSelfAddress(), BACKEND_DEFAULT_PORT))
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
        server.awaitTermination();
    }

}
