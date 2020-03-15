package itx.examples.consulapp.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataMessage {

    private final String data;
    private final int status;

    @JsonCreator
    public DataMessage(@JsonProperty("data") String data,
                       @JsonProperty("status") int status) {
        this.data = data;
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

}
