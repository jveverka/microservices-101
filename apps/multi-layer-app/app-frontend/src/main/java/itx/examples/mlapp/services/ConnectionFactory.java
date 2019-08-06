package itx.examples.mlapp.services;

public interface ConnectionFactory {

    Connection createConnect(String backendId, String host, int port) throws ConnectionCreateException;

}
