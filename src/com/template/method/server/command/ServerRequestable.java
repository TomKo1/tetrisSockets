package com.template.method.server.command;

import com.template.method.shared.Commandable;
import com.template.method.server.TetrisServer;


/**
 * Interface implemented by all request objects send to server-side
 */
public interface ServerRequestable extends Commandable {
    void execute(TetrisServer tetrisServer);
}
