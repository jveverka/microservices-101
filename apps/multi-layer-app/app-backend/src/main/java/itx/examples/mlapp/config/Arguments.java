package itx.examples.mlapp.config;

import com.beust.jcommander.Parameter;

import static itx.examples.mlapp.apis.Constants.BACKEND_DEFAULT_GRPC_PORT;
import static itx.examples.mlapp.apis.Constants.DEFAULT_HOSTNAME;

public class Arguments {

    @Parameter(names = { "-m", "--manager-address" }, description = "IP address of manager service")
    private String managerAddress = DEFAULT_HOSTNAME;

    @Parameter(names = { "-a", "--self-address" }, description = "IP address of machine where this service is running")
    private String selfAddress = DEFAULT_HOSTNAME;

    @Parameter(names = { "-c", "--capability" }, description = "Service's capability")
    private String capability = "default";

    @Parameter(names = { "-p", "--port" }, description = "Self default port")
    private Integer port = BACKEND_DEFAULT_GRPC_PORT;

    public String getCapability() {
        return capability;
    }

    public String getSelfAddress() {
        return selfAddress;
    }

    public String getManagerAddress() {
        return managerAddress;
    }

    public Integer getPort() {
        return port;
    }

}
