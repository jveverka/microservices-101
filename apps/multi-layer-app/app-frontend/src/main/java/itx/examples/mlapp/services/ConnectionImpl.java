package itx.examples.mlapp.services;

import io.grpc.ManagedChannel;
import itx.examples.mlapp.apis.BlockingObserver;
import itx.examples.mlapp.service.BackendId;
import itx.examples.mlapp.service.BackendInfo;
import itx.examples.mlapp.service.DataRequest;
import itx.examples.mlapp.service.DataResponse;
import itx.examples.mlapp.service.DataServiceGrpc;
import itx.examples.mlapp.service.Empty;

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
    public BackendId getId() {
        return backendInfo.getId();
    }

    @Override
    public Future<DataResponse> execute(DataRequest dataRequest) {
        Task task = new Task(dataRequest);
        return executor.submit(task);
    }

    @Override
    public BackendInfo getBackendInfo() {
        return backendInfo;
    }

    @Override
    public Optional<BackendInfo> sendKeepAlive() {
        BlockingObserver<BackendInfo> backendInfoBlockingObserver = new BlockingObserver<>();
        dataServiceStub.getInfo(Empty.newBuilder().build(), backendInfoBlockingObserver);
        try {
            return backendInfoBlockingObserver.awaitForValue(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void close() throws Exception {
        this.managedChannel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
    }

    private class Task implements Callable<DataResponse> {

        private final DataRequest dataRequest;

        private Task(DataRequest dataRequest) {
            this.dataRequest = dataRequest;
        }

        @Override
        public DataResponse call() throws Exception {
            BlockingObserver<DataResponse> blockingObserver = new BlockingObserver<>();
            ConnectionImpl.this.dataServiceStub.getData(dataRequest, blockingObserver);
            Optional<DataResponse> dataResponse = blockingObserver.awaitForValue(10, TimeUnit.SECONDS);
            return dataResponse.orElseThrow();
        }
    }

}
