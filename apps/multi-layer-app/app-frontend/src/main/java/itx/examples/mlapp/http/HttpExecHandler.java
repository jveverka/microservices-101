package itx.examples.mlapp.http;

import com.google.protobuf.InvalidProtocolBufferException;
import io.undertow.io.Receiver;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import itx.examples.mlapp.service.DataRequest;
import itx.examples.mlapp.service.DataResponse;
import itx.examples.mlapp.services.BackendServiceImpl;
import itx.examples.mlapp.utils.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xnio.streams.ChannelInputStream;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpExecHandler implements HttpHandler {

    private static final Logger LOG = LoggerFactory.getLogger(HttpExecHandler.class);

    private final BackendServiceImpl backendService;
    private final ExecutorService executor;

    public HttpExecHandler(BackendServiceImpl backendService) {
        this.backendService = backendService;
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                //exchange.startBlocking();
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                try {
                    exchange.getRequestReceiver().receiveFullBytes((callback, errorCallback) -> {
                        try {
                            DataRequest dataRequest = AppUtils.createDataRequest(callback.getInputStream());
                            Future<DataResponse> execute = backendService.execute(dataRequest);
                            DataResponse dataResponse = execute.get();
                            exchange.getResponseSender().send(AppUtils.renderToJson(dataResponse));
                        } catch (Exception e) {
                            LOG.error("Http error:", e);
                        }
                    });
                    //ChannelInputStream inputStream = new ChannelInputStream(exchange.getRequestChannel());
                    //DataRequest dataRequest = AppUtils.createDataRequest(inputStream);
                    //DataResponse dataResponse = execute.get();
                    //exchange.getResponseSender().send(AppUtils.renderToJson(dataResponse));
                } catch (Exception e) {
                    LOG.error("Http error:", e);
                }
            }
        });
    }
}
