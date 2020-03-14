package itx.examples.consulapp.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/services")
public class HealthCheckController {

    @RequestMapping("/health")
    public String home() {
        return "{ \"status\": \"OK-BE\" }";
    }

}
