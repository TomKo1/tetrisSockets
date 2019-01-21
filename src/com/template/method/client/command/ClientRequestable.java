package com.template.method.client.command;

import com.template.method.client.TetrisClient;
import com.template.method.shared.Commandable;


/**
 * Interface implemented by all commands responsible for doing sth on the client side
 */
public interface ClientRequestable extends Commandable {
     void execute(TetrisClient tetrisClient);
     String getMessageKey();
}
