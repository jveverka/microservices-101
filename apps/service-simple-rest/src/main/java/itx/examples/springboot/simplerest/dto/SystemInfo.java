package itx.examples.springboot.simplerest.dto;

public class SystemInfo {

    private final String name;
    private final long timestamp;
    private final String instanceId;
    private final long requestCounter;
    private final PodInfo podInfo;

    public SystemInfo(String name, long timestamp, String instanceId, long requestCounter, PodInfo podInfo) {
        this.name = name;
        this.timestamp = timestamp;
        this.instanceId = instanceId;
        this.requestCounter = requestCounter;
        this.podInfo = podInfo;
    }

    public String getName() {
        return name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public long getRequestCounter() {
        return requestCounter;
    }

    public PodInfo getPodInfo() {
        return podInfo;
    }
}
