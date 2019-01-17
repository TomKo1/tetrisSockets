package com.template.method.client.command.impl;

import com.template.method.client.TetrisClient;
import com.template.method.client.command.Clientable;

/**
 * TODO: document me!!!
 * <p/>
 * <code>StopClient</code>.
 * <p/>
 * User: rro
 * Date: 23.03.2006
 * Time: 18:17:46
 *
 * @author Roman R&auml;dle
 * @version $Id: StopClient.java,v 1.1.1.1 2006/03/23 23:35:56 raedler Exp $
 */
public class StopClient implements Clientable {

    public void execute(TetrisClient tetrisClient) {
        tetrisClient.stopGame();
    }

    public String getMessageKey() {
        return "tetris.client.command.impl.StopClient";
    }
}
