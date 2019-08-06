package itx.examples.mlapp.services;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import itx.examples.mlapp.apis.BlockingObserver;
import itx.examples.mlapp.service.BackendInfo;
import itx.examples.mlapp.service.DataServiceGrpc;
import itx.examples.mlapp.service.Empty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ConnectionFactoryImpl implements ConnectionFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionFactoryImpl.class);

    @Override
    public Connection createConnect(String backendId, String host, int port) throws ConnectionCreateException {
        try {
            LOG.info("createConnect: {} {}:{}", backendId, host, port);
            ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
            DataServiceGrpc.DataServiceStub dataServiceStub = DataServiceGrpc.newStub(managedChannel);
            BlockingObserver<BackendInfo> blockingObserver = new BlockingObserver<>();
            dataServiceStub.getInfo(Empty.newBuilder().build(), blockingObserver);
            Optional<BackendInfo> backendInfo = null;
            backendInfo = blockingObserver.awaitForValue(10, TimeUnit.SECONDS);
            if (backendInfo.isPresent()) {
                LOG.info("connection created: {} {}:{}", backendId, host, port);
                return new ConnectionImpl(backendInfo.get(), managedChannel, dataServiceStub);
            } else {
                managedChannel.shutdown();
            }
            LOG.info("Connection failed for: {} {}:{}", backendId, host, port);
            throw new ConnectionCreateException("Connection failed");
        } catch (Exception e) {
            throw new ConnectionCreateException(e);
        }
    }

}
