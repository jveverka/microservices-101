package itx.examples.consulapp.backend.controller;

import itx.examples.consulapp.backend.config.ConsulConfig;
import itx.examples.consulapp.common.DataMessage;
import itx.examples.consulapp.common.VersionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/services")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    private final ConsulConfig consulConfig;

    public BackendController(@Autowired ConsulConfig consulConfig) {
        this.consulConfig = consulConfig;
    }

    @GetMapping("/version")
    public ResponseEntity<VersionResponse> home() {
        return ResponseEntity.ok(new VersionResponse("1.0.0-BE"));
    }

    @GetMapping("/data")
    public ResponseEntity<DataMessage> data() {
        LOG.info("data");
        return ResponseEntity.ok(new DataMessage("Backend: " + consulConfig.getTestData(), 200));
    }

}
