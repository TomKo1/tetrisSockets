package com.template.method.server.command;


import com.template.method.server.TetrisServer;

/**
 * Request for server to log message on its console
 * based on enum type inside this object. (INFO/ERROR)
 */
public class ServerLog implements ServerRequestable {

    public enum Type {INFO, ERROR}

    private Type messageType;

    private String message;

    public ServerLog(Type messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }

    public void execute(TetrisServer tetrisServer) {

        Logable log = tetrisServer.getLog();

        switch (messageType) {
            case INFO:
                log.info(message);
                break;
            case ERROR:
                log.error(message);
                break;
        }
    }
}
