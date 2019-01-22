package com.template.method.gui;

import com.template.method.client.TetrisClient;
import com.template.method.server.command.BroadcastMessage;

import javax.swing.*;
import java.awt.*;


/**
 *  JFrame for GUI interaction with chat over Socket
 */
public class ChatFrame extends JFrame {

    private JTextArea jTextArea;
    private JTextField inputMessage;
    private TetrisClient tetrisClient;
    private boolean isDisplayed = false;


    public ChatFrame(TetrisClient tetrisClient) {
        super("Chat frame");
        this.tetrisClient = tetrisClient;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500, 500);
        initializeUI();
    }

    public void start() {
        isDisplayed = true;
        setVisible(true);
    }


    private void initializeUI() {
        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS); // top to bottom

        setLayout(boxLayout);

        this.jTextArea = new JTextArea(24, 12);
        JScrollPane jp = new JScrollPane(jTextArea);
        add(jp, BorderLayout.CENTER);

        this.inputMessage = new JTextField();
        inputMessage.setSize(500, 50);
        add(inputMessage);

        JButton sendBtn = new JButton("Wyślij");

        sendBtn.addActionListener(e -> {
            String message = inputMessage.getText();

            if(message != null && !message.isEmpty()) {
                this.inputMessage.setText("");
                String messageFormatted = String.format("%s:> %s\n", tetrisClient.getPlayerName(), message);
                tetrisClient.getOutput().addSendable(new BroadcastMessage(messageFormatted));
                // to make delay in receiving less visible
                tetrisClient.printReceivedMessage(messageFormatted);
            }
        });

        add(sendBtn);
    }

    public void printReceivedMessage(String message) {
        this.jTextArea.append(message);
    }



    public JTextArea getjTextArea() {
        return jTextArea;
    }

    public JTextField getJTextField() {
        return inputMessage;
    }

    public TetrisClient getTetrisClient() {
        return tetrisClient;
    }


    public boolean isDisplayed() {
        return isDisplayed;
    }
}
