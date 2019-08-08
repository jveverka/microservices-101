package itx.examples.mlapp.services;

import itx.examples.mlapp.service.BackendId;
import itx.examples.mlapp.service.BackendInfo;
import itx.examples.mlapp.service.DataRequest;
import itx.examples.mlapp.service.DataResponse;

import java.util.Optional;
import java.util.concurrent.Future;

public interface Connection extends AutoCloseable {

    BackendId getId();

    Future<DataResponse> execute(DataRequest dataRequest);

    BackendInfo getBackendInfo();

    Optional<BackendInfo> sendKeepAlive();

}
