package itx.examples.consulapp.frontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import itx.examples.consulapp.common.DataMessage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.List;

@Service
public class BackedAPIServiceImpl implements BackedAPIService {

    private static final Logger LOG = LoggerFactory.getLogger(BackedAPIServiceImpl.class);

    private final DiscoveryClient discoveryClient;
    private final CloseableHttpClient client;
    private final ObjectMapper mapper;
    private String backendUrl;

    @Autowired
    public BackedAPIServiceImpl(DiscoveryClient discoveryClient, ObjectMapper mapper) {
        this.discoveryClient = discoveryClient;
        this.mapper = mapper;
        this.client = HttpClientBuilder.create().build();
    }

    @PostConstruct
    public void init() {
        String serviceId = "backend-myConsulBackendApp";
        List<ServiceInstance> list = discoveryClient.getInstances(serviceId);
        if (list != null && !list.isEmpty() ) {
            URI serviceUri = list.get(0).getUri();
            LOG.info("## ServiceId: {} URI: {}", serviceId, serviceUri);
            backendUrl = serviceUri.toString() + "/services/data";
        } else {
            LOG.warn("## ServiceId: {} not found !", serviceId);
        }
    }

    @Override
    public DataMessage getData() {
        try {
            LOG.info("Requesting data from backend URL: {}", backendUrl);
            CloseableHttpResponse response = client.execute(new HttpGet(backendUrl));
            if (response.getStatusLine().getStatusCode() == 200) {
                return mapper.readValue(response.getEntity().getContent(), DataMessage.class);
            } else {
                return new DataMessage("ERROR: backend http failed ", response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            return new DataMessage("ERROR: backend not available !", -1);
        }
    }

}
