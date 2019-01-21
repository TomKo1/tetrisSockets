package com.template.method.gui;

import java.awt.*;
import javax.swing.*;

/**
 * Class representing Tetris GUI
 */
public class TetrisFrame extends JFrame {

    private JTextArea serverLogTextArea = new JTextArea();
    private JPanel panelClientServer = null;
    public ClientFrame clientFrame = null;

    /**
     * Initializes Tetris frame and displays it
     */
    public TetrisFrame() {
        super("Tetris Frame");
        try {
            tetrisFrameInit();
            setSize(590, 1000);
            setBackground(Color.GRAY);
            panelClientServer.setBackground(Color.GRAY);
            clientFrame.setBackground(Color.GRAY);
            setResizable(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Initializes the part responsible for dispaying server logs (only when player is hosting the game)
     */
    public void initServerLog() {

        JPanel serverPanel = new JPanel();
        setSize(1400, 1200);
        serverLogTextArea.append("Server started!\n");

        serverLogTextArea.setPreferredSize(new Dimension(800, 1200));
        serverLogTextArea.setEditable(false);
        serverPanel.add(serverLogTextArea);

        getContentPane().add(serverPanel, BorderLayout.CENTER);

    }

    public void printServerLog(String log) {
        serverLogTextArea.append(log);
    }

    /**
     * Initializes whole frame
     */
    public void tetrisFrameInit() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        getContentPane().setBackground(Color.WHITE);
        setSize(new Dimension(900, 700));
        getContentPane().setLayout(new BorderLayout());

        setResizable(true);
        setLocation(20, 20);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        //initialization of client server panel
        clientFrame = new ClientFrame(this);
        panelClientServer = new JPanel();
        panelClientServer.add(clientFrame.getClientServerPanel());
        getContentPane().add(panelClientServer, BorderLayout.WEST);

        validate();
    }

    /**
     * Implements the start method.
     *
     * @param args String[] not used
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        new TetrisFrame();
    }


    public JTextArea getServerLogTextArea() {
        return serverLogTextArea;
    }
}
