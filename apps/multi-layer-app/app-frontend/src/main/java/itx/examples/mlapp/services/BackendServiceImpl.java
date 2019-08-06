package itx.examples.mlapp.services;

import com.google.common.util.concurrent.Futures;
import itx.examples.mlapp.service.BackendInfo;
import itx.examples.mlapp.service.DataRequest;
import itx.examples.mlapp.service.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class BackendServiceImpl implements BackendManager, TaskManager, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(BackendServiceImpl.class);

    private final Map<String, Connection> connections;
    private final ConnectionFactory connectionFactory;

    public BackendServiceImpl(ConnectionFactory connectionFactory) {
        this.connections = new ConcurrentHashMap<>();
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void connect(String backendId, String host, int port) throws Exception {
        Connection connection = connectionFactory.createConnect(backendId, host, port);
        LOG.info("connect: id={} capability={} {}:{}", connection.getBackendInfo().getCapability(), backendId, host, port);
        connections.put(backendId, connection);
    }

    @Override
    public Collection<BackendInfo> getStatus() {
        return connections.values().stream().map(Connection::getBackendInfo).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void disconnect(String backendId) {
        LOG.info("disconnect: {}", backendId);
        Connection connection = connections.remove(backendId);
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                LOG.error("Connection close: ", e);
            }
        }
    }

    @Override
    public Future<DataResponse> execute(DataRequest dataRequest) {
        LOG.info("execute: {}", dataRequest.getCapability());
        Optional<Connection> connection = connections.values().stream().filter(c -> c.getBackendInfo().getCapability().equals(dataRequest.getCapability())).findAny();
        if (connection.isPresent()) {
            return connection.get().execute(dataRequest);
        } else {
            return Futures.immediateFailedCheckedFuture(new UnsupportedOperationException("Connection not available !"));
        }
    }

    @Override
    public void close() throws Exception {
        LOG.info("closing ...");
        connections.values().forEach(c->{
            try {
                LOG.info("closing connection {}", c.getId());
                c.close();
            } catch (Exception e) {
                LOG.warn("closing connection {} has failed !", c.getId());
            }
        });
    }

}
