package itx.examples.mlapp;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import itx.examples.mlapp.services.NotificationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

public class FrontendApp {

    final private static Logger LOG = LoggerFactory.getLogger(FrontendApp.class);

    public static void main(String[] args) throws InterruptedException, IOException {

        LOG.info("FrontendApp started ...");

        NotificationServiceImpl notificationService = new NotificationServiceImpl();

        Server server = NettyServerBuilder.forAddress(
                new InetSocketAddress("0.0.0.0", 5556))
                .addService(notificationService)
                .build();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                LOG.info("FrontendApp shutting down ...");
                server.shutdown();
                LOG.info("FrontendApp shutdown.");
            }
        });

        LOG.info("FrontendApp, listening on 0.0.0.0:5555");
        server.start();
        server.awaitTermination();
    }

}
