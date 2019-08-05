package itx.examples.mlapp.services;

import itx.examples.mlapp.service.DataRequest;
import itx.examples.mlapp.service.DataResponse;

import java.util.concurrent.Future;

public interface TaskManager {

    Future<DataResponse> execute(DataRequest dataRequest);

}
