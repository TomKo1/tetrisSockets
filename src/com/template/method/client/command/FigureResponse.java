package com.template.method.client.command;



import com.template.method.client.TetrisClient;
import java.util.List;

/**
 * Command responsible for adding new figures to the battlefield
 */
public class FigureResponse implements ClientRequestable {

    private List figures;

    public FigureResponse(List figures) {
        this.figures = figures;
    }

    public void execute(TetrisClient tetrisClient) {
        tetrisClient.getBattleField().addNewFigureList(figures);
    }

    public String getMessageKey() {
        return "tetris.client.command.impl.FigureResponse";
    }
}
