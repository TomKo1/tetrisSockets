package com.template.method.client.command.impl;

import com.template.method.client.command.Clientable;
import com.template.method.client.TetrisClient;

/**
 * TODO: document me!!!
 * <p/>
 * <code>Points</code>.
 * <p/>
 * User: rro
 * Date: 23.03.2006
 * Time: 19:55:40
 *
 * @author Roman R&auml;dle
 * @version $Id: Points.java,v 1.1.1.1 2006/03/23 23:35:56 raedler Exp $
 */
public class Points implements Clientable {

    private int points;

    public Points(int points) {
        this.points = points;
    }

    public void execute(TetrisClient tetrisClient) {
        tetrisClient.setFinalPoints(points);
    }

    public String getMessageKey() {
        return "tetris.client.command.impl.Points";
    }
}
