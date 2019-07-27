package itx.examples.springboot.simplerest.rest;

import itx.examples.springboot.simplerest.config.AppConfig;
import itx.examples.springboot.simplerest.dto.ComputeResult;
import itx.examples.springboot.simplerest.dto.PodInfo;
import itx.examples.springboot.simplerest.dto.SystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path = "/data")
public class DataServiceRest {

    private static final String NAME_VERSION = "simple-rest:1.0.0";

    private final String instanceId;
    private final AtomicLong requestCounter;

    @Autowired
    private AppConfig appConfig;

    public DataServiceRest() {
        this.instanceId = UUID.randomUUID().toString();
        this.requestCounter = new AtomicLong(0);
    }

    @GetMapping(path = "/info", produces = MediaType.APPLICATION_JSON_VALUE )
    public SystemInfo getSystemInfo() {
        PodInfo podInfo = new PodInfo(appConfig.getPodIP(), appConfig.getPodName(), appConfig.getNodeName());
        return new SystemInfo(NAME_VERSION, System.currentTimeMillis(), instanceId, requestCounter.incrementAndGet(), podInfo);
    }

    @GetMapping(path = "/compute/pi/{precision}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ComputeResult getComputation(@PathVariable("precision") int precision) {
        //This algorithm calculates PI by summing partial results
        // PI = (4/1) - (4/3) + (4/5) - (4/7) + (4/9) - (4/11) + (4/13) - (4/15) ...
        long timeStamp = System.nanoTime();
        int max = 1000;
        if (precision <= 0) {
            max = 1000;
        } else if (precision >= 10) {
            max = 2_147_483_647;
        } else if (precision > 0 && precision < 10) {
            max = 2_147_483_647 / (10 - precision);
        }
        double pi = 4F;
        boolean operation = false;
        for (int i=2; i<=max; i++) {
            if ( (i%2) == 0) {
                continue;
            }
            if (operation) {
                pi = pi + 4F/i;
            } else {
                pi = pi - 4F/i;
            }
            operation = !operation;
        }
        float duration = (System.nanoTime() - timeStamp) / 1_000_000F;
        return new ComputeResult(pi, "PI", duration, max, appConfig.getPodName());
    }

}
