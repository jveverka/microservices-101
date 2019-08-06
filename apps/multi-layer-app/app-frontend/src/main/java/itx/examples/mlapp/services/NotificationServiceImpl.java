package itx.examples.mlapp.services;

import io.grpc.stub.StreamObserver;
import itx.examples.mlapp.service.BackendInfo;
import itx.examples.mlapp.service.Empty;
import itx.examples.mlapp.service.NotificationServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationServiceImpl extends NotificationServiceGrpc.NotificationServiceImplBase {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final BackendServiceImpl backendService;

    public NotificationServiceImpl(BackendServiceImpl backendService) {
        this.backendService = backendService;
    }

    public void onNewBackend(BackendInfo request, StreamObserver<Empty> responseObserver) {
        LOG.info("onNewBackend: {} {}:{}", request.getId(), request.getHostname(), request.getPort());
        try {
            backendService.connect(request.getId(), request.getHostname(), request.getPort());
        } catch (Exception e) {
            LOG.error("Backend service connection has failed !");
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

}
