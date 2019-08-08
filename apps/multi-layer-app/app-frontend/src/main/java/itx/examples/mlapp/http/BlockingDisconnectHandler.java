package itx.examples.mlapp.http;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import itx.examples.mlapp.service.BackendId;
import itx.examples.mlapp.services.BackendServiceImpl;
import itx.examples.mlapp.utils.AppUtils;

public class BlockingDisconnectHandler implements HttpHandler {

    private final BackendServiceImpl backendService;

    public BlockingDisconnectHandler(BackendServiceImpl backendService) {
        this.backendService = backendService;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        try {
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            BackendId id = AppUtils.createBackendId(exchange.getInputStream());
            backendService.disconnect(id);
            exchange.setStatusCode(200);
        } catch (Exception e) {
            exchange.getResponseSender().send("{ \"message\": \"" + e.getMessage() + "\"}");
            exchange.setStatusCode(500);
        }
    }
}
