package itx.examples.consulapp.frontend.controller;

import itx.examples.consulapp.frontend.config.ConsulConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/services")
public class FrontendController {

    private final ConsulConfig consulConfig;

    public FrontendController(@Autowired ConsulConfig consulConfig) {
        this.consulConfig = consulConfig;
    }

    @GetMapping("/version")
    public String home() {
        return "{ \"version\": \"1.0.0\" }";
    }

    @GetMapping("/data")
    public String data() {
        return "{ \"data\": \"" + consulConfig. getTestData() + "\" }";
    }

}
