package itx.examples.springboot.simplerest.dto;

public class ComputeResult {

    private final double result;
    private final String unit;
    private final float durationMs;
    private final int iterations;
    private final String nodeId;

    public ComputeResult(double result, String unit, float durationMs, int iterations, String nodeId) {
        this.result = result;
        this.unit = unit;
        this.durationMs = durationMs;
        this.iterations = iterations;
        this.nodeId = nodeId;
    }

    public double getResult() {
        return result;
    }

    public String getUnit() {
        return unit;
    }

    public float getDurationMs() {
        return durationMs;
    }

    public int getIterations() {
        return iterations;
    }

    public String getNodeId() {
        return nodeId;
    }
}
