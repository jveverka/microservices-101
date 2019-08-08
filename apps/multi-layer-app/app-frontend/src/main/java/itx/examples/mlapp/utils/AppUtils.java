package itx.examples.mlapp.utils;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import itx.examples.mlapp.service.BackendId;
import itx.examples.mlapp.service.BackendInfo;
import itx.examples.mlapp.service.BackendInfos;
import itx.examples.mlapp.service.ConnectRequest;
import itx.examples.mlapp.service.DataRequest;
import itx.examples.mlapp.service.DataResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public final class AppUtils {

    private AppUtils() {
    }

    public static String renderToJson(Collection<BackendInfo> backendInfoCollection) throws InvalidProtocolBufferException {
        BackendInfos.Builder backendInfosBuilder = BackendInfos.newBuilder();
        backendInfosBuilder.addAllInfos(backendInfoCollection);
        return JsonFormat.printer().print(backendInfosBuilder.build());
    }

    public static DataRequest createDataRequest(InputStream inputStream) throws InvalidProtocolBufferException {
        DataRequest.Builder builder = DataRequest.newBuilder();
        String result = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        JsonFormat.parser().merge(result, builder);
        return builder.build();
    }

    public static String renderToJson(DataResponse response) throws InvalidProtocolBufferException {
        return JsonFormat.printer().print(response);
    }

    public static String filterCapabilitiesAndRenderToJson(Collection<BackendInfo> status) {
        Set<String> capabilitiesString = status.stream().map(BackendInfo::getCapability).collect(Collectors.toSet());
        return "[ " + capabilitiesString.stream().collect(Collectors.joining(", ")) + " ]";
    }

    public static ConnectRequest createConnectRequest(InputStream inputStream) throws InvalidProtocolBufferException {
        ConnectRequest.Builder builder = ConnectRequest.newBuilder();
        String result = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        JsonFormat.parser().merge(result, builder);
        return builder.build();
    }

    public static String backendIdToJson(BackendId id) throws InvalidProtocolBufferException {
        return JsonFormat.printer().print(id);
    }

    public static BackendId createBackendId(InputStream inputStream) throws InvalidProtocolBufferException {
        BackendId.Builder builder = BackendId.newBuilder();
        String result = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        JsonFormat.parser().merge(result, builder);
        return builder.build();
    }

}
