package com.template.method.gui;

import javax.swing.*;


public class ChatFrame extends JFrame {


    public ChatFrame() {
        super("Chat frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        initializeUI();
        setVisible(true);
    }


    private void initializeUI() {
        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS); // top to bottom

        setLayout(boxLayout);

        JTextArea jTextArea = new JTextArea();
        jTextArea.setEditable(false);

        add(jTextArea);

        JTextField jTextField = new JTextField();
        jTextField.setSize(500, 50);
        add(jTextField);

        JButton sendBtn = new JButton("Wyślij");

        add(sendBtn);
    }


}
