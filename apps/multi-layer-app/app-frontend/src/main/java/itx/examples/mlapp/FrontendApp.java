package itx.examples.mlapp;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import io.undertow.Undertow;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.BlockingHandler;
import itx.examples.mlapp.http.BlockingExecHandler;
import itx.examples.mlapp.http.HttpStatusHandler;
import itx.examples.mlapp.services.BackendServiceImpl;
import itx.examples.mlapp.services.ConnectionFactory;
import itx.examples.mlapp.services.ConnectionFactoryImpl;
import itx.examples.mlapp.services.NotificationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

import static itx.examples.mlapp.apis.Constants.DEFAULT_BIND_ADDRESS;
import static itx.examples.mlapp.apis.Constants.FRONTEND_DEFAULT_GRPC_PORT;
import static itx.examples.mlapp.apis.Constants.FRONTEND_DEFAULT_HTTP_PORT;

public class FrontendApp {

    private static final Logger LOG = LoggerFactory.getLogger(FrontendApp.class);

    public static void main(String[] args) throws InterruptedException, IOException {

        LOG.info("FrontendApp started ...");

        ConnectionFactory connectionFactory = new ConnectionFactoryImpl();
        BackendServiceImpl backendService = new BackendServiceImpl(connectionFactory);
        NotificationServiceImpl notificationService = new NotificationServiceImpl(backendService);

        Server server = NettyServerBuilder.forAddress(
                new InetSocketAddress(DEFAULT_BIND_ADDRESS, FRONTEND_DEFAULT_GRPC_PORT))
                .addService(notificationService)
                .build();

        RoutingHandler routingHandler = new RoutingHandler()
                .get("/status", new HttpStatusHandler(backendService))
                .post("/exec", new BlockingHandler(new BlockingExecHandler(backendService)));

        Undertow undertowServer = Undertow.builder()
                .addHttpListener(FRONTEND_DEFAULT_HTTP_PORT, DEFAULT_BIND_ADDRESS)
                .setHandler(routingHandler).build();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                LOG.info("FrontendApp shutting down ...");
                try {
                    backendService.close();
                } catch (Exception e) {
                    LOG.warn("Closing of backendService has failed !");
                }
                server.shutdown();
                LOG.info("FrontendApp shutdown.");
            }
        });

        server.start();
        LOG.info("FrontendApp, grpc listening on {}:{}", DEFAULT_BIND_ADDRESS, FRONTEND_DEFAULT_GRPC_PORT);
        LOG.info("FrontendApp, http listening on {}:{}", DEFAULT_BIND_ADDRESS, FRONTEND_DEFAULT_HTTP_PORT);
        undertowServer.start();
        server.awaitTermination();
    }

}
