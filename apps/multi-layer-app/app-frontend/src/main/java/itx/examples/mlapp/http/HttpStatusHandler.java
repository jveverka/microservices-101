package itx.examples.mlapp.http;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import itx.examples.mlapp.service.BackendInfo;
import itx.examples.mlapp.services.BackendServiceImpl;
import itx.examples.mlapp.utils.AppUtils;

import java.util.Collection;

public class HttpStatusHandler implements HttpHandler {

    private final BackendServiceImpl backendService;

    public HttpStatusHandler(BackendServiceImpl backendService) {
        this.backendService = backendService;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        Collection<BackendInfo> status = backendService.getStatus();
        exchange.setStatusCode(200);
        exchange.getResponseSender().send(AppUtils.renderToJson(status));
    }
}
