package com.template.method.server.command.impl;

import com.template.method.client.command.StopClient;
import com.template.method.server.command.Serverable;
import com.template.method.server.TetrisServer;
import com.template.method.server.ServerOutput;


/**
 * TODO: document me!!!
 * <p/>
 * <code>StopGameRequest</code>.
 * <p/>
 * User: rro
 * Date: 23.03.2006
 * Time: 20:23:51
 *
 * @author Roman R&auml;dle
 * @version $Id: StopGameRequest.java,v 1.1.1.1 2006/03/23 23:35:56 raedler Exp $
 */
public class StopGameRequest implements Serverable {

    private String playerName;

    public StopGameRequest() {
    }

    public StopGameRequest(String playerName) {
        this.playerName = playerName;
    }

    public void execute(TetrisServer tetrisServer) {

        if (playerName != null) {
            tetrisServer.getLog().info("The player \"" + playerName + "\" requests to end the game.");
        }

        for (ServerOutput serverOutput : tetrisServer.getServerOutputs()) {
            serverOutput.addSendable(new StopClient());

            synchronized (serverOutput.getTickDummy()) {
                serverOutput.getTickDummy().notifyAll();
            }
        }

        tetrisServer.breakServer = true;
        tetrisServer.getTicker().destroyTickTask();
    }
}
