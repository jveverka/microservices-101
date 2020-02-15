package itx.examples.consulapp.frontend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/services")
public class FrontendController {

    @RequestMapping("/version")
    public String home() {
        return "{ \"version\": \"1.0.0\" }";
    }

}
