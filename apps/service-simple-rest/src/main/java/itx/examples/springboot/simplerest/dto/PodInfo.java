package itx.examples.springboot.simplerest.dto;

public class PodInfo {

    private final String podIP;
    private final String podName;
    private final String nodeName;

    public PodInfo(String podIP, String podName, String nodeName) {
        this.podIP = podIP;
        this.podName = podName;
        this.nodeName = nodeName;
    }

    public String getPodIP() {
        return podIP;
    }

    public String getPodName() {
        return podName;
    }

    public String getNodeName() {
        return nodeName;
    }

}
