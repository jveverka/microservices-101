package itx.examples.mlapp.config;

import com.beust.jcommander.Parameter;
import itx.examples.mlapp.BackendApp;

public class Arguments {

    @Parameter(names = { "-m", "--manager-address" }, description = "IP address of manager service")
    private String managerAddress = "127.0.0.1";

    @Parameter(names = { "-a", "--self-address" }, description = "IP address of machine where this service is running")
    private String selfAddress = "127.0.0.1";

    @Parameter(names = { "-c", "--capability" }, description = "Service's capability")
    private String capability = "default";

    @Parameter(names = { "-p", "--port" }, description = "Self default port")
    private Integer port = BackendApp.BACKEND_DEFAULT_PORT;

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
