package com.template.method.gui;

import com.template.method.client.TetrisClient;
import com.template.method.server.command.impl.BroadcastMessage;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class ChatFrame extends JFrame {

    private JTextArea jTextArea;
    private JTextField inputMessage;
    private TetrisClient tetrisClient;



    public ChatFrame(TetrisClient tetrisClient) {
        super("Chat frame");
        this.tetrisClient = tetrisClient;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500, 500);
        initializeUI();
    }

    public void start() {
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
                System.out.println("Message was sent!");
                this.inputMessage.setText("");
                String messageFormatted = String.format("%s:> %s\n", tetrisClient.getPlayerName(), message);
                tetrisClient.getOutput().addSendable(new BroadcastMessage(messageFormatted));
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

}
