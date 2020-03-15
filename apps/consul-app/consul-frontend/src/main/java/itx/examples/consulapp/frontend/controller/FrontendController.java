package itx.examples.consulapp.frontend.controller;

import itx.examples.consulapp.common.DataMessage;
import itx.examples.consulapp.common.VersionResponse;
import itx.examples.consulapp.frontend.config.ConsulConfig;
import itx.examples.consulapp.frontend.service.BackedAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/services")
public class FrontendController {

    private final ConsulConfig consulConfig;
    private final BackedAPIService backedAPIService;

    public FrontendController(@Autowired ConsulConfig consulConfig,
                              @Autowired BackedAPIService backedAPIService) {
        this.consulConfig = consulConfig;
        this.backedAPIService = backedAPIService;
    }

    @GetMapping("/version")
    public ResponseEntity<VersionResponse> home() {
        return ResponseEntity.ok(new VersionResponse("1.0.0-FE"));
    }

    @GetMapping("/data")
    public ResponseEntity<DataMessage> data() {
        DataMessage dataMessage = backedAPIService.getData();
        return ResponseEntity.ok(new DataMessage("Frontend: " + consulConfig.getTestData() + " " + dataMessage.getData(), dataMessage.getStatus()));
    }

}
