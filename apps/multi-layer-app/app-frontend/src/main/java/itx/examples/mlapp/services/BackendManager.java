package itx.examples.mlapp.services;

import itx.examples.mlapp.service.BackendId;
import itx.examples.mlapp.service.BackendInfo;
import itx.examples.mlapp.service.ConnectRequest;

import java.util.Collection;

public interface BackendManager {

    BackendId connect(ConnectRequest request) throws Exception;

    Collection<BackendInfo> getStatus();

    void disconnect(BackendId id);

}
