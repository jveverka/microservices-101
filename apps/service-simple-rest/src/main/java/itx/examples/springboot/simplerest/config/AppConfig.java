package itx.examples.springboot.simplerest.config;

import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class AppConfig {

    private final String podIP;
    private final String podName;
    private final String nodeName;

    public AppConfig() {
        this.podIP = Optional.ofNullable(System.getenv("POD_IP")).orElse("DEFAULT");
        this.podName = Optional.ofNullable(System.getenv("POD_NAME")).orElse("DEFAULT");
        this.nodeName = Optional.ofNullable(System.getenv("NODE_NAME")).orElse("DEFAULT");
    }

    public String getPodIP() {
        return podIP;
    }

    public String getPodName() {
        return podName;
    }

    public String getNodeName() {
        return nodeName;
    }

}
