package itx.examples.mlapp.services;

import io.grpc.stub.StreamObserver;
import itx.examples.mlapp.service.BackendInfo;
import itx.examples.mlapp.service.Confirmation;
import itx.examples.mlapp.service.NotificationServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationServiceImpl extends NotificationServiceGrpc.NotificationServiceImplBase {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final BackendServiceImpl backendService;

    public NotificationServiceImpl(BackendServiceImpl backendService) {
        this.backendService = backendService;
    }

    public void onNewBackend(BackendInfo request, StreamObserver<Confirmation> responseObserver) {
        LOG.info("onNewBackend: {} {}:{}", request.getId(), request.getHostname(), request.getPort());
        try {
            backendService.connect(request.getId(), request.getHostname(), request.getPort());
            Confirmation confirmation = Confirmation.newBuilder()
                    .setStatus(true)
                    .setMessage("connection accepted")
                    .build();
            responseObserver.onNext(confirmation);
        } catch (Exception e) {
            LOG.error("Backend service connection has failed !");
            responseObserver.onError(e);
        }
        responseObserver.onCompleted();
    }

}
