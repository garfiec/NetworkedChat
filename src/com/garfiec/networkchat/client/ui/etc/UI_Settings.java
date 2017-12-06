package com.garfiec.networkchat.client.ui.etc;

import com.garfiec.networkchat.client.util.Chat_Client_Settings;

import javax.swing.*;
import java.awt.*;

public class UI_Settings extends JFrame {
    private Chat_Client_Settings settings;

    public UI_Settings(Chat_Client_Settings settings) {
        super("Program Settings");
        this.settings = settings;
        getContentPane().setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }
}
