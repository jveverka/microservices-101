package itx.examples.mlapp.services;

import io.grpc.stub.StreamObserver;
import itx.examples.mlapp.service.BackendInfo;
import itx.examples.mlapp.service.Empty;
import itx.examples.mlapp.service.NotificationServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationServiceImpl extends NotificationServiceGrpc.NotificationServiceImplBase {

    final private static Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);

    public void onNewBackend(BackendInfo request, StreamObserver<Empty> responseObserver) {
        LOG.info("onNewBackend: {} {}:{}", request.getId(), request.getHostname(), request.getPort());
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

}
