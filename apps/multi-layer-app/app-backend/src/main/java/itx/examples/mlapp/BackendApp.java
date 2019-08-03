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

public class BackendApp {

    final private static Logger LOG = LoggerFactory.getLogger(BackendApp.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        Arguments arguments = new Arguments();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(args);

        LOG.info("BackendApp started with capability: {} ...", arguments.getCapability());

        DataServiceImpl dataService = new DataServiceImpl(arguments.getCapability());
        ManagerConnector managerConnector = new ManagerConnector("frontent", 5556, arguments.getCapability(), "0.0.0.0", 5555);
        managerConnector.startManagerConnectionLoop();

        Server server = NettyServerBuilder.forAddress(
                new InetSocketAddress("0.0.0.0", 5555))
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

        LOG.info("BackendApp, listening on 0.0.0.0:5555");
        server.start();
        server.awaitTermination();
    }

}
