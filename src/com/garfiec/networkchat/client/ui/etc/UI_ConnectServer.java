package com.garfiec.networkchat.client.ui.etc;

import com.garfiec.networkchat.client.ui.UI_Constants;
import com.garfiec.networkchat.client.ui.UI_Strings;
import com.garfiec.networkchat.client.util.Chat_Client_Config;

import javax.swing.*;
import java.awt.*;

public class UI_ConnectServer extends JFrame {
    private Chat_Client_Config settings;

    private JTextField server_ip_input;
    private JTextField server_port_input;

    private JTextField cipher_p_input;
    private JTextField cipher_q_input;

    public UI_ConnectServer(Chat_Client_Config settings) {
        super("Connection Settings");
        this.settings = settings;
        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(createUI());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 350);
        setVisible(true);
    }

    private JPanel createUI() {
        JPanel panel = new JPanel();
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        panel.setBorder(UI_Constants.STANDARD_PADDING);

        /////////////////////////////////////////
        // Connection Settings

        JPanel connection_settings_panel = new JPanel();
        SpringLayout connection_settings_layout = new SpringLayout();
        connection_settings_panel.setLayout(connection_settings_layout);
        connection_settings_panel.setBorder(BorderFactory.createTitledBorder("Connection Settings"));

        // IP
        JPanel server_ip_panel = new JPanel(new BorderLayout());
        server_ip_panel.setBorder(UI_Constants.HORIZONTAL_PADDING);
        JLabel server_ip_label = new JLabel("Server IP ");
        this.server_ip_input = new JTextField(this.settings.server_ip);
        server_ip_panel.add(server_ip_label, BorderLayout.WEST);
        server_ip_panel.add(this.server_ip_input, BorderLayout.CENTER);
        connection_settings_panel.add(server_ip_panel);

        // Port
        JPanel server_port_panel = new JPanel(new BorderLayout());
        server_port_panel.setBorder(UI_Constants.HORIZONTAL_PADDING);
        JLabel server_port_label = new JLabel("Server Port ");
        this.server_port_input = new JTextField(Integer.toString(this.settings.port));
        server_port_panel.add(server_port_label, BorderLayout.WEST);
        server_port_panel.add(this.server_port_input, BorderLayout.CENTER);
        connection_settings_panel.add(server_port_panel);

        // Connect Button
        JPanel connect_server_panel = new JPanel(new BorderLayout());
        connect_server_panel.setBorder(UI_Constants.RIGHT_PADDING);
        JButton connect_server_bttn = new JButton("Connect to Server");
        connect_server_bttn.addActionListener(e -> {
            // Todo: Save settings
            // Todo: Tell client to connect to server
        });
        connect_server_panel.add(connect_server_bttn);
        connection_settings_panel.add(connect_server_panel);

        // Constraints
        connection_settings_layout.putConstraint(SpringLayout.WEST, server_ip_panel, UI_Constants.STD_PAD, SpringLayout.WEST, connection_settings_panel);
        connection_settings_layout.putConstraint(SpringLayout.NORTH, server_ip_panel, UI_Constants.STD_PAD, SpringLayout.NORTH, connection_settings_panel);
        connection_settings_layout.putConstraint(SpringLayout.EAST, server_ip_panel, UI_Constants.STD_PAD, SpringLayout.EAST, connection_settings_panel);
        connection_settings_layout.putConstraint(SpringLayout.WEST, server_port_panel, UI_Constants.STD_PAD, SpringLayout.WEST, connection_settings_panel);
        connection_settings_layout.putConstraint(SpringLayout.NORTH, server_port_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, server_ip_panel);
        connection_settings_layout.putConstraint(SpringLayout.EAST, server_port_panel, UI_Constants.STD_PAD, SpringLayout.EAST, connection_settings_panel);

        connection_settings_layout.putConstraint(SpringLayout.EAST, connect_server_panel, UI_Constants.STD_PAD, SpringLayout.EAST, connection_settings_panel);
        connection_settings_layout.putConstraint(SpringLayout.NORTH, connect_server_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, server_port_panel);
        connection_settings_layout.putConstraint(SpringLayout.SOUTH, connection_settings_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, connect_server_panel);


        /////////////////////////////////////////
        // Advanced settings

        JPanel advanced_settings_panel = new JPanel();
        SpringLayout advanced_settings_layout = new SpringLayout();
        advanced_settings_panel.setLayout(advanced_settings_layout);
        advanced_settings_panel.setBorder(BorderFactory.createTitledBorder("Advanced Settings (Leave alone if you do not know what you are doing)"));

        // Cipher settings
        JLabel cipher_settings_label = new JLabel("<html><h3>Cipher Settings</h3></html>");
        advanced_settings_panel.add(cipher_settings_label);

        JPanel cipher_p_panel = new JPanel(new BorderLayout());
        cipher_p_panel.setBorder(UI_Constants.HORIZONTAL_PADDING);
        JLabel cipher_p_label = new JLabel("p ");
        this.cipher_p_input = new JTextField(Long.toString(this.settings.cipher_p));
        cipher_p_panel.add(cipher_p_label, BorderLayout.WEST);
        cipher_p_panel.add(this.cipher_p_input, BorderLayout.CENTER);
        advanced_settings_panel.add(cipher_p_panel);

        JPanel cipher_q_panel = new JPanel(new BorderLayout());
        cipher_q_panel.setBorder(UI_Constants.HORIZONTAL_PADDING);
        JLabel cipher_q_label = new JLabel("q ");
        this.cipher_q_input = new JTextField(Long.toString(this.settings.cipher_q));
        cipher_q_panel.add(cipher_q_label, BorderLayout.WEST);
        cipher_q_panel.add(this.cipher_q_input, BorderLayout.CENTER);
        advanced_settings_panel.add(cipher_q_panel);

        JPanel cipher_note_panel = new JPanel(new BorderLayout());
        cipher_note_panel.setBorder(UI_Constants.RIGHT_PADDING);
        JLabel cipher_note_label = new JLabel(UI_Strings.CIPHER_USE_NOTE);
        cipher_note_panel.add(cipher_note_label, BorderLayout.CENTER);
        advanced_settings_panel.add(cipher_note_panel);

        // Constraints
        advanced_settings_layout.putConstraint(SpringLayout.WEST, cipher_settings_label, UI_Constants.STD_PAD, SpringLayout.WEST, advanced_settings_panel);
        advanced_settings_layout.putConstraint(SpringLayout.NORTH, cipher_settings_label, UI_Constants.STD_PAD, SpringLayout.NORTH, advanced_settings_panel);
        advanced_settings_layout.putConstraint(SpringLayout.WEST, cipher_p_panel, UI_Constants.STD_PAD, SpringLayout.WEST, advanced_settings_panel);
        advanced_settings_layout.putConstraint(SpringLayout.NORTH, cipher_p_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, cipher_settings_label);
        advanced_settings_layout.putConstraint(SpringLayout.EAST, cipher_p_panel, UI_Constants.STD_PAD, SpringLayout.EAST, advanced_settings_panel);
        advanced_settings_layout.putConstraint(SpringLayout.WEST, cipher_q_panel, UI_Constants.STD_PAD, SpringLayout.WEST, advanced_settings_panel);
        advanced_settings_layout.putConstraint(SpringLayout.NORTH, cipher_q_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, cipher_p_panel);
        advanced_settings_layout.putConstraint(SpringLayout.EAST, cipher_q_panel, UI_Constants.STD_PAD, SpringLayout.EAST, advanced_settings_panel);
        advanced_settings_layout.putConstraint(SpringLayout.NORTH, cipher_note_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, cipher_q_panel);
        advanced_settings_layout.putConstraint(SpringLayout.EAST, cipher_note_panel, UI_Constants.STD_PAD, SpringLayout.EAST, advanced_settings_panel);

        advanced_settings_layout.putConstraint(SpringLayout.SOUTH, advanced_settings_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, cipher_note_panel);

        /////////////////////////////////////////
        // UI Config

        // Add elements
        panel.add(connection_settings_panel);
        panel.add(advanced_settings_panel);

        // Define constraints
        layout.putConstraint(SpringLayout.WEST, connection_settings_panel, UI_Constants.STD_PAD, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, connection_settings_panel, UI_Constants.STD_PAD, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, connection_settings_panel, UI_Constants.STD_PAD, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.WEST, advanced_settings_panel, UI_Constants.STD_PAD, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, advanced_settings_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, connection_settings_panel);
        layout.putConstraint(SpringLayout.EAST, advanced_settings_panel, UI_Constants.STD_PAD, SpringLayout.EAST, panel);

        return panel;
    }
}
