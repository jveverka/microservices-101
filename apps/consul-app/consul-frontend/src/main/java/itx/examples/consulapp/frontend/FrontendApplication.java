package itx.examples.consulapp.frontend;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class FrontendApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FrontendApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

}