package itx.examples.consulapp.backend;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BackendApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

}