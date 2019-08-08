package itx.examples.mlapp.http;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import itx.examples.mlapp.service.BackendId;
import itx.examples.mlapp.service.ConnectRequest;
import itx.examples.mlapp.services.BackendServiceImpl;
import itx.examples.mlapp.utils.AppUtils;

import java.util.concurrent.Future;

public class BlockingConnectHandler implements HttpHandler {

    private final BackendServiceImpl backendService;

    public BlockingConnectHandler(BackendServiceImpl backendService) {
        this.backendService = backendService;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        try {
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            ConnectRequest connectRequest = AppUtils.createConnectRequest(exchange.getInputStream());
            BackendId id = backendService.connect(connectRequest);
            exchange.setStatusCode(200);
            exchange.getResponseSender().send(AppUtils.backendIdToJson(id));
        } catch (Exception e) {
            exchange.getResponseSender().send("{ \"message\": \"" + e.getMessage() + "\"}");
            exchange.setStatusCode(500);
        }
    }

}
