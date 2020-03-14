package itx.examples.consulapp.backend.controller;

import itx.examples.consulapp.backend.config.ConsulConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/services")
public class BackendController {

    private final ConsulConfig consulConfig;

    public BackendController(@Autowired ConsulConfig consulConfig) {
        this.consulConfig = consulConfig;
    }

    @GetMapping("/version")
    public String home() {
        return "{ \"version\": \"1.0.0-BE\" }";
    }

    @GetMapping("/data")
    public String data() {
        return "{ \"data\": \"Backend: " + consulConfig. getTestData() + "\" }";
    }

}
