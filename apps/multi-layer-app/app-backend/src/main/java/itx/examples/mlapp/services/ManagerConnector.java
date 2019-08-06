package itx.examples.mlapp.services;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import itx.examples.mlapp.apis.BlockingObserver;
import itx.examples.mlapp.service.BackendInfo;
import itx.examples.mlapp.service.Empty;
import itx.examples.mlapp.service.NotificationServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ManagerConnector implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(ManagerConnector.class);
    private static final int MAX_RECONNECTION_ATTEMPTS = 20;

    private final ExecutorService executorService;
    private final String managerHost;
    private final int managerPort;
    private final String capability;
    private final String id;
    private final String selfHostName;
    private final int selfPort;
    private final AtomicInteger counter;

    public ManagerConnector(String id, String managerHost, int managerPort, String capability, String selfHostName, int selfPort) {
        this.executorService = Executors.newSingleThreadExecutor();
        this.managerHost = managerHost;
        this.managerPort = managerPort;
        this.capability = capability;
        this.id = id;
        this.selfHostName = selfHostName;
        this.selfPort = selfPort;
        this.counter = new AtomicInteger(0);
    }

    public void startManagerConnectionLoop() {
        LOG.info("init ...");
        this.executorService.submit(new ManagerConnectorTask());
    }

    @Override
    public void close() throws Exception {
        this.executorService.shutdown();
    }

    private class ManagerConnectorTask implements Runnable {

        @Override
        public void run() {
            ManagedChannel managedChannel = null;
            try {
                LOG.info("Connecting to Frontent manager {}:{}", managerHost, managerPort);
                managedChannel = ManagedChannelBuilder.forAddress(managerHost, managerPort)
                        .usePlaintext()
                        .build();
                NotificationServiceGrpc.NotificationServiceStub notificationServiceGrpc = NotificationServiceGrpc.newStub(managedChannel);
                BackendInfo request = BackendInfo.newBuilder()
                        .setCapability(capability)
                        .setId(id)
                        .setHostname(selfHostName)
                        .setPort(selfPort)
                        .build();
                BlockingObserver<Empty> observer = new BlockingObserver<>();
                notificationServiceGrpc.onNewBackend(request, observer);
                Optional<Empty> confirmation = observer.awaitForValue(5, TimeUnit.SECONDS);
                if (confirmation.isPresent()) {
                    managedChannel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
                    LOG.info("Frontent manager {}:{} worker notification send !", managerHost, managerPort);
                } else {
                    LOG.info("Confirmation not present, rescheduling ...");
                    reschedule();
                }
            } catch (Exception e) {
                LOG.error("Exception: ", e);
                reschedule();
            } finally {
                if (managedChannel != null) {
                    LOG.info("shutting down managed channel");
                    managedChannel.shutdown();
                }
            }
        }

        private void reschedule() {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            int counterValue = counter.getAndIncrement();
            if (counterValue >= MAX_RECONNECTION_ATTEMPTS) {
                LOG.warn("Frontent manager {}:{} / {} too many failed connection attempts, giving up !", managerHost, managerPort, counterValue);
                return;
            }
            LOG.warn("Frontent manager {}:{} / {} connection failed, retry ...", managerHost, managerPort, counterValue);
            executorService.submit(new ManagerConnectorTask());
        }
    }

}
