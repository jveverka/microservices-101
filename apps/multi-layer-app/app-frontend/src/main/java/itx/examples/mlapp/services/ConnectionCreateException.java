package itx.examples.mlapp.services;

public class ConnectionCreateException extends Exception {

    public ConnectionCreateException(Exception e) {
        super(e);
    }

    public ConnectionCreateException(String message) {
        super(message);
    }

}
