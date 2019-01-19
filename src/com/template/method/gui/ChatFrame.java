package com.template.method.gui;

import com.template.method.client.TetrisClient;
import com.template.method.server.command.impl.BroadcastMessage;

import javax.swing.*;


public class ChatFrame extends JFrame {

    private JTextArea jTextArea;
    private JTextField inputMessage;
    private TetrisClient tetrisClient;



    public ChatFrame(TetrisClient tetrisClient) {
        super("Chat frame");
        this.tetrisClient = tetrisClient;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        initializeUI();
        setVisible(true);
    }


    private void initializeUI() {
        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS); // top to bottom

        setLayout(boxLayout);

        this.jTextArea = new JTextArea();
        jTextArea.setEditable(false);

        add(jTextArea);

        this.inputMessage = new JTextField();
        inputMessage.setSize(500, 50);
        add(inputMessage);

        JButton sendBtn = new JButton("Wyślij");
        sendBtn.addActionListener(e -> {
            String message = inputMessage.getText();
            if(!message.isEmpty())
                tetrisClient.getOutput().addSendable(new BroadcastMessage(message));
        });

        add(sendBtn);
    }


    public void printReceivedMessage(String message) {
        this.jTextArea.append(message);
    }

}
