package itx.examples.consulapp.frontend.controller;

import itx.examples.consulapp.common.HealthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/services")
public class HealthCheckController {

    @GetMapping("/health")
    public ResponseEntity<HealthResponse> home() {
        return ResponseEntity.ok(new HealthResponse("OK-FE"));
    }

}
