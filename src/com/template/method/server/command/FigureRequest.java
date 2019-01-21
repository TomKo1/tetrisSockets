package com.template.method.server.command;

import com.template.method.client.command.FigureResponse;
import com.template.method.gui.figure.Figure;
import com.template.method.server.TetrisServer;
import com.template.method.server.ServerOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Request send when client wants new figure.
 */
public class FigureRequest implements ServerRequestable {

    private String playerName;

    public FigureRequest(String clientName) {
        this.playerName = clientName;
    }

    public void execute(TetrisServer tetrisServer) {

        tetrisServer.getLog().info("The player \"" + playerName + "\" requests new figures.");

        List<Figure> figures = new ArrayList<Figure>();

        // Creates a figure list with 10 figures.
        for (int i = 0; i < 10; i++) {
            figures.add(new Figure(100, 0, 20, 20));
        }

        for (ServerOutput serverOutput : tetrisServer.getServerOutputs()) {
            serverOutput.addSendable(new FigureResponse(figures));

            synchronized (serverOutput.getTickDummy()) {
                serverOutput.getTickDummy().notifyAll();
            }
        }
    }
}
