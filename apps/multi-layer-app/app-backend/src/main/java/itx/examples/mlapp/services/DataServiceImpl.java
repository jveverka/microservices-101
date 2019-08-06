package itx.examples.mlapp.services;

import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import itx.examples.mlapp.service.BackendInfo;
import itx.examples.mlapp.service.DataContainer;
import itx.examples.mlapp.service.DataRequest;
import itx.examples.mlapp.service.DataResponse;
import itx.examples.mlapp.service.DataServiceGrpc;
import itx.examples.mlapp.service.Empty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class DataServiceImpl extends DataServiceGrpc.DataServiceImplBase {

    private static final Logger LOG = LoggerFactory.getLogger(DataServiceImpl.class);

    private final String id;
    private final String hostname;
    private final int port;
    private final String capability;

    public DataServiceImpl(String id, String hostname, int port, String capability) {
        this.id = id;
        this.hostname = hostname;
        this.port = port;
        this.capability = capability;
    }

    @Override
    public void getData(DataRequest request, StreamObserver<DataResponse> responseObserver) {
        LOG.info("getData: {}", request.getCapability());
        ByteString byteString = ByteString.copyFrom("response", Charset.forName("UTF-8"));
        DataContainer dataContainer = DataContainer.newBuilder()
                .setTypeId("java.lang.String")
                .setData(byteString)
                .build();
        DataResponse dataResponse = DataResponse.newBuilder()
                .setData(dataContainer)
                .setRequestId(request.getId())
                .setProcessorId(id)
                .build();
        responseObserver.onNext(dataResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getInfo(Empty request, StreamObserver<BackendInfo> responseObserver) {
        LOG.info("getInfo: ");
        BackendInfo backendInfo = BackendInfo.newBuilder()
                .setId(id)
                .setHostname(hostname)
                .setPort(port)
                .setCapability(capability)
                .build();
        responseObserver.onNext(backendInfo);
        responseObserver.onCompleted();
    }

}
