package com.garfiec.networkchat.client.ui.etc;

import com.garfiec.networkchat.client.Chat_Client;
import com.garfiec.networkchat.client.network.Client_Socket;
import com.garfiec.networkchat.client.ui.UI_Constants;
import com.garfiec.networkchat.client.ui.UI_Strings;
import com.garfiec.networkchat.client.util.Chat_Client_Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class UI_ConnectServer extends JFrame {
    private  Chat_Client client;
    private Chat_Client_Config settings;

    private JTextField user_name_input;

    private JTextField server_ip_input;
    private JTextField server_port_input;

    private JTextField cipher_p_input;
    private JTextField cipher_q_input;

    public UI_ConnectServer(Chat_Client client, Chat_Client_Config settings) {
        super("Connection Settings");
        this.client = client;
        this.settings = settings;
        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(createUI());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 375);
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
        connection_settings_panel.setBorder(BorderFactory.createTitledBorder("Server Details"));

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

        // Constraints
        connection_settings_layout.putConstraint(SpringLayout.WEST, server_ip_panel, UI_Constants.STD_PAD, SpringLayout.WEST, connection_settings_panel);
        connection_settings_layout.putConstraint(SpringLayout.NORTH, server_ip_panel, UI_Constants.STD_PAD, SpringLayout.NORTH, connection_settings_panel);
        connection_settings_layout.putConstraint(SpringLayout.EAST, server_ip_panel, UI_Constants.STD_PAD, SpringLayout.EAST, connection_settings_panel);
        connection_settings_layout.putConstraint(SpringLayout.WEST, server_port_panel, UI_Constants.STD_PAD, SpringLayout.WEST, connection_settings_panel);
        connection_settings_layout.putConstraint(SpringLayout.NORTH, server_port_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, server_ip_panel);
        connection_settings_layout.putConstraint(SpringLayout.EAST, server_port_panel, UI_Constants.STD_PAD, SpringLayout.EAST, connection_settings_panel);
        connection_settings_layout.putConstraint(SpringLayout.SOUTH, connection_settings_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, server_port_panel);

        /////////////////////////////////////////
        // User details

        JPanel user_details_panel = new JPanel();
        SpringLayout user_details_layout = new SpringLayout();
        user_details_panel.setLayout(user_details_layout);
        user_details_panel.setBorder(BorderFactory.createTitledBorder("User Details"));

        // User's name
        JPanel user_name_panel = new JPanel(new BorderLayout());
        user_name_panel.setBorder(UI_Constants.HORIZONTAL_PADDING);
        JLabel user_name_label = new JLabel("Name ");
        this.user_name_input = new JTextField(this.settings.user_name);
        user_name_panel.add(user_name_label, BorderLayout.WEST);
        user_name_panel.add(this.user_name_input, BorderLayout.CENTER);
        user_details_panel.add(user_name_panel);

        // Constraints
        user_details_layout.putConstraint(SpringLayout.WEST, user_name_panel, UI_Constants.STD_PAD, SpringLayout.WEST, user_details_panel);
        user_details_layout.putConstraint(SpringLayout.NORTH, user_name_panel, UI_Constants.STD_PAD, SpringLayout.NORTH, user_details_panel);
        user_details_layout.putConstraint(SpringLayout.EAST, user_name_panel, UI_Constants.STD_PAD, SpringLayout.EAST, user_details_panel);
        user_details_layout.putConstraint(SpringLayout.SOUTH, user_details_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, user_name_panel);

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
        // Connect to Server Button

        // Connect Button
        JPanel connect_server_panel = new JPanel(new BorderLayout());
        connect_server_panel.setBorder(UI_Constants.RIGHT_PADDING);
        JButton connect_server_bttn = new JButton("Connect to Server");
        connect_server_bttn.addActionListener(e -> connectServer());
        connect_server_panel.add(connect_server_bttn);

        /////////////////////////////////////////
        // UI Config

        // Add elements
        panel.add(connection_settings_panel);
        panel.add(user_details_panel);
        panel.add(advanced_settings_panel);
        panel.add(connect_server_panel);

        // Define constraints
        layout.putConstraint(SpringLayout.WEST, connection_settings_panel, UI_Constants.STD_PAD, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, connection_settings_panel, UI_Constants.STD_PAD, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, connection_settings_panel, UI_Constants.STD_PAD, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.WEST, user_details_panel, UI_Constants.STD_PAD, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, user_details_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, connection_settings_panel);
        layout.putConstraint(SpringLayout.EAST, user_details_panel, UI_Constants.STD_PAD, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.WEST, advanced_settings_panel, UI_Constants.STD_PAD, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, advanced_settings_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, user_details_panel);
        layout.putConstraint(SpringLayout.EAST, advanced_settings_panel, UI_Constants.STD_PAD, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, connect_server_panel, UI_Constants.STD_PAD, SpringLayout.SOUTH, advanced_settings_panel);
        layout.putConstraint(SpringLayout.EAST, connect_server_panel, UI_Constants.STD_PAD, SpringLayout.EAST, panel);

        return panel;
    }

    private void connectServer() {
        this.settings.server_ip = server_ip_input.getText();
        this.settings.port      = Integer.parseInt(server_port_input.getText());
        this.settings.user_name = user_name_input.getText();
        this.settings.cipher_key = client.rsa_cipher.makeKeys(this.settings.cipher_p, this.settings.cipher_q);

        try {

            boolean worked = settings.setCipherPrimes(Long.parseLong(cipher_p_input.getText()), Long.parseLong(cipher_q_input.getText()));
            if (!worked) {
                JOptionPane.showMessageDialog(this, "Invalid p / q");
                return;
            }

            Client_Socket sock = new Client_Socket(this.settings.server_ip, this.settings.port, this.client);
            if (!sock.connect()) {
                // Failed to connect to server
                return;
            }

			if (sock.sendKey(user_name_input.getText(), this.settings.cipher_key)) {
				sock.listen();
            } else {
                JOptionPane.showMessageDialog(this, "Didn't connect.. Invalid name?");
                return;
            }

            this.client.setSocket(sock);
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        }
        catch (Exception er) {
            System.out.println("Try again");
        }
    }
}
