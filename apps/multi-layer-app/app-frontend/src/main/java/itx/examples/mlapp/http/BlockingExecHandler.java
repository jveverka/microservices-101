package itx.examples.mlapp.http;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import itx.examples.mlapp.service.DataRequest;
import itx.examples.mlapp.service.DataResponse;
import itx.examples.mlapp.services.BackendServiceImpl;
import itx.examples.mlapp.utils.AppUtils;

import java.util.concurrent.Future;

public class BlockingExecHandler implements HttpHandler {

    private final BackendServiceImpl backendService;

    public BlockingExecHandler(BackendServiceImpl backendService) {
        this.backendService = backendService;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        try {
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            DataRequest dataRequest = AppUtils.createDataRequest(exchange.getInputStream());
            Future<DataResponse> execute = backendService.execute(dataRequest);
            DataResponse dataResponse = execute.get();
            exchange.setStatusCode(200);
            exchange.getResponseSender().send(AppUtils.renderToJson(dataResponse));
        } catch (Exception e) {
            exchange.getResponseSender().send("{ \"message\": \"" + e.getMessage() + "\"}");
            exchange.setStatusCode(500);
        }
    }
}
