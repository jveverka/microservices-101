package itx.examples.consulapp.frontend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsulConfig {

    @Value("${my.prop}")
    private String testData;

    public ConsulConfig() {
        this.testData = "NA";
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

}
