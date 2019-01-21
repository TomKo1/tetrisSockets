package com.template.method.gui;

import com.template.method.client.ThreadTetrisClient;
import com.template.method.gui.figure.Figure;
import com.template.method.utilities.XMLParser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  Class representing client part of main window
 */
public class ClientFrame extends JFrame {

    private TetrisFrame tetrisFrame;
    private JPanel clientServerPanel = null;
    private JPanel clientPanel = null;
    private JPanel serverPanel = null;
    private JPanel figurePanel = null;
    private JPanel pointsPanel = null;
    private JButton defaultStartServerBtn;
    private JButton startServerBtn;
    private JButton joinGameBtn = null;
    private boolean isServerRunning = false;
    private JTextField clientName = null;
    private JTextField host = null;
    private JTextField port = null;
    private JTextField tfServerPort = null;
    private JTextField tfAbsencePlayer = null;
    private JTextField tfPlayTime = null;
    private int clientPoints = 0;
    private JLabel lPoints = null;
    private Figure lastFigure = null;


    private ActionListener hostAction = e -> {
        if (clientName.getText().equals("") || host.getText().equals("")) {
            joinGameBtn.setEnabled(false);
            JOptionPane.showMessageDialog(tetrisFrame, "", "Blad", 2);
        }
        else {
            joinGameBtn.setEnabled(true);
        }
    };


    private ActionListener startServerAction = e -> {
        Integer serverPort;
        Integer players;
        Integer playTime;
        if(isServerRunning) JOptionPane.showMessageDialog(this.getContentPane(), "Nie można uruchomić dwa razy servera na tej samej maszynie!", "Uwaga!", JOptionPane.INFORMATION_MESSAGE);


        if(((JButton)e.getSource()).getText().equals("Uruchom server z domyślnymi opcjami")) {
            XMLParser.Configuration configuration = XMLParser.parseConfiguration("./src/com/template/method/server/configuration.xml");
            serverPort = configuration.getPort();
            players = configuration.getPlayerCount();
            playTime = configuration.getPlayTime();
        } else {
            serverPort = Integer.parseInt(tfServerPort.getText());
            players = Integer.parseInt(tfAbsencePlayer.getText());
            playTime = Integer.parseInt(tfPlayTime.getText());
        }
        isServerRunning = true;
        defaultStartServerBtn.setEnabled(false);
        startServerBtn.setEnabled(false);

        new ServerFrame(serverPort, players, playTime, tetrisFrame);
    };


    public ClientFrame(TetrisFrame tf) {
        tetrisFrame = tf;
        try {
            createPanels();
            initUI();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Creates accurate panels which will be later filled with available options.
     *
     */
    private void createPanels() {
        clientServerPanel = createJPanel(new GridLayout(3, 1, 0, 40), true);
        clientPanel = createJPanel(new GridLayout(6,1), true);
        serverPanel = createJPanel(new GridLayout(6,1), true);
        figurePanel = createJPanel(new GridLayout(2,1), true);
        pointsPanel = createJPanel(new GridLayout(1,2), true);


        JLabel figureLabel = new JLabel("Następny klocek:");

        figurePanel.add(figureLabel);
        figurePanel.setVisible(true);

    }

    /**
     * Creates JPanel using given layout and setting its visibility
     * @param layout layout of JPanel
     * @param isVisible value specifying if the JPanel's visibility
     * @return configured JPanel
     */

    private JPanel createJPanel(LayoutManager layout, boolean isVisible) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(layout);
        jPanel.setVisible(isVisible);

        return jPanel;
    }

    /**
     * Fills panels with available functionalities created by createPanels() method
     */
    //TODO: refactor
    private void initUI() {
        //initialization of panels for client panel
        JPanel pName = createJPanel(new GridLayout(1, 2), true);
        JPanel pIP =  createJPanel(new GridLayout(1, 2), true);
        JPanel pPort = createJPanel(new GridLayout(1, 2), true);
        JPanel pMultiSingleButton = new JPanel(new GridLayout(1, 2));


        //initialization of name label and name text field panel
        JLabel lClientName = new JLabel("Nazwa gracza:");
        clientName = new JTextField();
        clientName.addActionListener( e -> {
            if (clientName.getText().equals("")) {
                joinGameBtn.setEnabled(false);
                JOptionPane.showMessageDialog(tetrisFrame, "Prosze podac nazwe gracza!", "Blad", 2);
            }
            else if (!clientName.getText().equals("") || !host.getText().equals("")) {
                joinGameBtn.setEnabled(true);
            }
            else {
                joinGameBtn.setEnabled(false);
            }
        });
        pName.add(lClientName);
        pName.add(clientName);

        //initialization of ip label and ip address panel
        JLabel lIP = new JLabel("IP hosta:");
        host = new JTextField("localhost");
        host.addActionListener(e -> {

        });


        pIP.add(lIP);
        pIP.add(host);

        //initialization of port label and ip address panel
        JLabel lPort = new JLabel("Port servera:");
        port = new JTextField("8181");
        port.addActionListener(hostAction);
        pPort.add(lPort);
        pPort.add(port);



        //initialization of mutliplayer button
        joinGameBtn = new JButton("Dołącz do gry");
        joinGameBtn.addActionListener(e -> {
            String host = ClientFrame.this.host.getText();
                Integer port = Integer.parseInt(ClientFrame.this.port.getText());
                String clientName = ClientFrame.this.clientName.getText();

                new ThreadTetrisClient(host, port, clientName, ClientFrame.this);
        });
        joinGameBtn.setEnabled(true);
        pMultiSingleButton.add(joinGameBtn);

        //initialization of points panel
        JLabel pointsLabel = new JLabel("Punkty: ");
        pointsLabel.setVisible(true);
        lPoints = new JLabel("0");
        lPoints.setVisible(true);
        pointsPanel.add(pointsLabel);
        pointsPanel.add(lPoints);

        //set panels on client panel
        clientPanel.add(new JLabel("Dołącz do istniejącej gry"));
        clientPanel.add(pName);
        clientPanel.add(pIP);
        clientPanel.add(pPort);
        clientPanel.add(pMultiSingleButton);
        clientPanel.add(pointsPanel);

        //initialization of panels for server panel
        JPanel pServerPort = new JPanel(new GridLayout(1, 2));
        JPanel pAbsencePlayer = new JPanel(new GridLayout(1, 2));
        JPanel pPlayTime = new JPanel(new GridLayout(1, 2));
        JPanel pStartServerButton = new JPanel(new GridLayout(1, 1));

        //initialization of name label and name text field panel
        JLabel lServerPort = new JLabel("Port:");
        tfServerPort = new JTextField("8181");
        pServerPort.add(lServerPort);
        pServerPort.add(tfServerPort);

        //initialization of ip label and ip address panel
        JLabel lAbsencePlayer = new JLabel("Liczba graczy:");
        tfAbsencePlayer = new JTextField("2");
        pAbsencePlayer.add(lAbsencePlayer);
        pAbsencePlayer.add(tfAbsencePlayer);

        //initialization of port label and ip address panel
        JLabel lPlayTime = new JLabel("Szybkość poruszania bloków:");
        tfPlayTime = new JTextField("600000");
        pPlayTime.add(lPlayTime);
        pPlayTime.add(tfPlayTime);

        //initialization of server start button panel
        this.startServerBtn = new JButton("Uruchom server");
        startServerBtn.addActionListener(startServerAction);
        pStartServerButton.add(startServerBtn);


        // initialization of server with from xml
        this.defaultStartServerBtn = new JButton("Uruchom server z domyślnymi opcjami");
        defaultStartServerBtn.addActionListener(startServerAction);
        pStartServerButton.add(defaultStartServerBtn);


        //set panels on server panel
        serverPanel.add(new JLabel("Hostuj grę"));
        serverPanel.add(pServerPort);
        serverPanel.add(pAbsencePlayer);
        serverPanel.add(pPlayTime);
        serverPanel.add(pStartServerButton);

        //set panels on client and server panel
        clientServerPanel.add(clientPanel);
        clientServerPanel.add(serverPanel);
        clientServerPanel.add(figurePanel);
    }



    /**
     * Shows preview of the next figure base on its color
     *
     * @param color color of tetris figure
     */
    public void previewNextFigure(Color color) {
        if (lastFigure != null) {
            figurePanel.remove(lastFigure);
        }

        lastFigure = new Figure();

        if (color.equals(Color.RED)) {
            lastFigure = (Figure) lastFigure.getFigureOne();
        }
        else if (color.equals(Color.GREEN)) {
            lastFigure = (Figure) lastFigure.getFigureTwo();
        }
        else if (color.equals(Color.ORANGE)) {
            lastFigure = (Figure) lastFigure.getFigureThree();
        }
        else if (color.equals(Color.BLUE)) {
            lastFigure = (Figure) lastFigure.getFigureFour();
        }
        else if (color.equals(Color.YELLOW)) {
            lastFigure = (Figure) lastFigure.getFigureFive();
        }
        else if (color.equals(Color.PINK)) {
            lastFigure = (Figure) lastFigure.getFigureSix();
        }
        else if (color.equals(Color.DARK_GRAY)) {
            lastFigure = (Figure) lastFigure.getFigureSeven();
        }
        lastFigure.setLocation(0, 0);
        figurePanel.add(lastFigure);
        figurePanel.validate();
    }

    /**
     * Set client points on points panel.
     *
     * @param points int Points to add with clientPoints and set on points panel
     */
    public void setPoints(int points) {
        pointsPanel.remove(lPoints);
        clientPoints += points;
        lPoints = new JLabel(Integer.toString(clientPoints));
        lPoints.setVisible(true);
        pointsPanel.add(lPoints);
        pointsPanel.validate();
    }


    public ActionListener getHostAction() {
        return hostAction;
    }

    public ActionListener getStartServerAction() {
        return startServerAction;
    }

    public JPanel getClientPanel() {
        return clientPanel;
    }

    public JPanel getServerPanel() {
        return serverPanel;
    }

    public JPanel getFigurePanel() {
        return figurePanel;
    }

    public JButton getDefaultStartServerBtn() {
        return defaultStartServerBtn;
    }

    public JButton getStartServerBtn() {
        return startServerBtn;
    }

    public JButton getJoinGameBtn() {
        return joinGameBtn;
    }

    public JTextField getClientName() {
        return clientName;
    }

    public JTextField getHost() {
        return host;
    }

    public JTextField getPort() {
        return port;
    }

    public JTextField getTfServerPort() {
        return tfServerPort;
    }

    public JTextField getTfAbsencePlayer() {
        return tfAbsencePlayer;
    }

    public JTextField getTfPlayTime() {
        return tfPlayTime;
    }

    public JPanel getClientServerPanel() {
        return clientServerPanel;
    }

    public JPanel getPointsPanel() {
        return pointsPanel;
    }

    public Figure getLastFigure() {
        return lastFigure;
    }

    public JPanel clientServerPanel() {
        return clientServerPanel;
    }
}
