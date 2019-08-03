package itx.examples.mlapp.services;

import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import itx.examples.mlapp.service.DataContainer;
import itx.examples.mlapp.service.DataRequest;
import itx.examples.mlapp.service.DataResponse;
import itx.examples.mlapp.service.DataServiceGrpc;

import java.nio.charset.Charset;

public class DataServiceImpl extends DataServiceGrpc.DataServiceImplBase {

    private final String capability;

    public DataServiceImpl(String capability) {
        this.capability = capability;
    }

    public void getData(DataRequest request, StreamObserver<DataResponse> responseObserver) {
        ByteString byteString = ByteString.copyFrom("response", Charset.forName("UTF-8"));
        DataContainer dataContainer = DataContainer.newBuilder()
                .setTypeId("java.lang.String")
                .setData(byteString)
                .build();
        DataResponse dataResponse = DataResponse.newBuilder()
                .setData(dataContainer)
                .setRequestId(request.getId())
                .build();
        responseObserver.onNext(dataResponse);
        responseObserver.onCompleted();
    }
}
