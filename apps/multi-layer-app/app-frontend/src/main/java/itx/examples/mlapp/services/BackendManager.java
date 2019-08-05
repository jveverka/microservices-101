package itx.examples.mlapp.services;

import itx.examples.mlapp.service.BackendInfo;

import java.util.Collection;

public interface BackendManager {

    void connect(String backendId, String host, int port) throws Exception;

    Collection<BackendInfo> getStatus();

    void disconnect(String backendId);

}
