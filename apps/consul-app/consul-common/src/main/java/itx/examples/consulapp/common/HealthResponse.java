package itx.examples.consulapp.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HealthResponse {

    private final String health;

    @JsonCreator
    public HealthResponse(@JsonProperty("health") String health) {
        this.health = health;
    }

    public String getHealth() {
        return health;
    }

}
