package itx.examples.mlapp.config;

import com.beust.jcommander.Parameter;

public class Arguments {

    @Parameter(names = { "-c", "--capability" }, description = "Service's capability")
    private String capability = "default";

    public String getCapability() {
        return capability;
    }

}
