package itx.examples.mlapp.services;

import io.grpc.ManagedChannel;
import itx.examples.mlapp.apis.BlockingObserver;
import itx.examples.mlapp.service.BackendInfo;
import itx.examples.mlapp.service.DataRequest;
import itx.examples.mlapp.service.DataResponse;
import itx.examples.mlapp.service.DataServiceGrpc;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ConnectionImpl implements Connection {

    private final BackendInfo backendInfo;
    private final ManagedChannel managedChannel;
    private final DataServiceGrpc.DataServiceStub dataServiceStub;
    private final ExecutorService executor;

    public ConnectionImpl(BackendInfo backendInfo, ManagedChannel managedChannel,
                          DataServiceGrpc.DataServiceStub dataServiceStub) {
        this.backendInfo = backendInfo;
        this.managedChannel = managedChannel;
        this.dataServiceStub = dataServiceStub;
        this.executor = Executors.newCachedThreadPool();
    }

    @Override
    public String getId() {
        return backendInfo.getId();
    }

    @Override
    public Future<DataResponse> execute(DataRequest dataRequest) {
        Task task = new Task(dataServiceStub, dataRequest);
        return executor.submit(task);
    }

    @Override
    public BackendInfo getBackendInfo() {
        return backendInfo;
    }

    @Override
    public void close() throws Exception {
        this.managedChannel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
    }

    private static class Task implements Callable<DataResponse> {

        private final DataServiceGrpc.DataServiceStub dataServiceStub;
        private final DataRequest dataRequest;

        private Task(DataServiceGrpc.DataServiceStub dataServiceStub, DataRequest dataRequest) {
            this.dataServiceStub = dataServiceStub;
            this.dataRequest = dataRequest;
        }

        @Override
        public DataResponse call() throws Exception {
            BlockingObserver<DataResponse> blockingObserver = new BlockingObserver<>();
            dataServiceStub.getData(dataRequest, blockingObserver);
            Optional<DataResponse> dataResponse = blockingObserver.awaitForValue(10, TimeUnit.SECONDS);
            return dataResponse.orElseThrow();
        }
    }

}
