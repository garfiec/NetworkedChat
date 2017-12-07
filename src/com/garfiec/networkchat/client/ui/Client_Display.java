package com.garfiec.networkchat.client.ui;

import com.garfiec.networkchat.client.Chat_Client;
import com.garfiec.networkchat.client.ui.etc.UI_ConnectServer;
import com.garfiec.networkchat.client.util.Chat_Client_Config;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class Client_Display extends JFrame {
    private Chat_Client          client;
    private Chat_Client_Config settings;

    private JList     users_list;
    private JTextArea community_messages;
    private JLabel    statusLabel;

    public Client_Display(Chat_Client client, Chat_Client_Config settings) {
        super(UI_Strings.GUI_TITLE);
        getContentPane().setLayout(new BorderLayout());

        this.client   = client;
        this.settings = settings;

        createMenu();
        createUI();
        createStatusBar();

        setStatus("Disconnected");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(UI_Constants.WIDTH, UI_Constants.HEIGHT);
        setVisible(true);
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

        // Vars
        JMenu menu;
        JMenuItem menuItem;

        // File menu
        menu = new JMenu("File");
        menuBar.add(menu);

        menuItem = new JMenuItem("Connect to Server");
        menuItem.addActionListener(e -> new JDialog(new UI_ConnectServer(settings)));
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(e -> System.exit(0));
        menu.add(menuItem);

        // Help menu
        menu = new JMenu("Help");
        menuBar.add(menu);

        menuItem = new JMenuItem("How to use");
        menuItem.addActionListener(e -> messageBox(UI_Strings.HOW_TO_USE_TEXT));
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("About");
        menuItem.addActionListener(e -> messageBox(UI_Strings.ABOUT_TEXT));
        menu.add(menuItem);

        this.setJMenuBar(menuBar);
    }

    private void createUI() {
        JPanel ui_panel = new JPanel(new BorderLayout());

        // Chat rooms control
        JPanel rooms_ctrl_panel = new JPanel(new BorderLayout());
        rooms_ctrl_panel.setBorder(UI_Constants.STANDARD_PADDING);
        rooms_ctrl_panel.add(createUserList());
        ui_panel.add(rooms_ctrl_panel, BorderLayout.WEST);

        // Chat room itself
        JPanel rooms_panel = new JPanel(new BorderLayout());
        rooms_panel.setBorder(UI_Constants.STANDARD_PADDING);
        rooms_panel.add(createRoomUI());
        ui_panel.add(rooms_panel, BorderLayout.CENTER);

        this.add(ui_panel, BorderLayout.CENTER);
    }

    private void createStatusBar() {
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(new BevelBorder(BevelBorder.RAISED));

        JPanel padding = new JPanel(new BorderLayout());
        padding.setBorder(UI_Constants.STANDARD_PADDING);
        statusBar.add(padding, BorderLayout.CENTER);

        this.statusLabel = new JLabel();
        this.statusLabel.setVerticalAlignment(JLabel.CENTER);
        padding.add(this.statusLabel);

        this.add(statusBar, BorderLayout.SOUTH);
    }

    private JPanel createUserList() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(UI_Constants.STANDARD_PADDING);

        // Title Label for List of Connected Users
        JPanel listLabelPanel = new JPanel(new BorderLayout());
        listLabelPanel.setBorder(UI_Constants.VERTICAL_PADDING);
        JLabel userListLabel = new JLabel(UI_Strings.USER_LIST_LABEL_TEXT);
        listLabelPanel.add(userListLabel, BorderLayout.CENTER);

        // List of Connected Users
        JPanel list_panel = new JPanel(new BorderLayout());
        this.users_list = new JList();
        this.users_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane user_list_scroll = new JScrollPane(this.users_list);
        list_panel.add(user_list_scroll, BorderLayout.CENTER);

        panel.add(listLabelPanel, BorderLayout.NORTH);
        panel.add(list_panel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createRoomUI() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(UI_Constants.STANDARD_PADDING);

        // User name
        JPanel name_panel = new JPanel(new BorderLayout());
        name_panel.setBorder(UI_Constants.VERTICAL_PADDING);
        JLabel user_name_label = new JLabel("Name ");
        JTextField user_name_input = new JTextField(settings.user_name);
        user_name_input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                settings.user_name = user_name_input.getText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                settings.user_name = user_name_input.getText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                settings.user_name = user_name_input.getText();
            }
        });
        name_panel.add(user_name_label, BorderLayout.WEST);
        name_panel.add(user_name_input, BorderLayout.CENTER);

        // Community Messages
        JPanel community_messages_panel = new JPanel(new BorderLayout());
        community_messages_panel.setBorder(new EmptyBorder(0, 0, 5, 0));
        this.community_messages = new JTextArea();
        this.community_messages.setEditable(false);
        this.community_messages.setLineWrap(true);
        JScrollPane messages_scroll = new JScrollPane(this.community_messages);
        community_messages_panel.add(messages_scroll, BorderLayout.CENTER);

        // Input message
        JPanel input_panel = new JPanel(new BorderLayout());
        input_panel.setBorder(UI_Constants.TOP_PADDING);
        JTextField user_input = new JTextField("Your message here");
        user_input.addActionListener(e -> {
            if (!e.getActionCommand().equals("")) {
                this.client.sendMessage(e.getActionCommand());
            }
            user_input.setText("");
        });
        input_panel.add(user_input);

        panel.add(name_panel, BorderLayout.NORTH);
        panel.add(community_messages_panel, BorderLayout.CENTER);
        panel.add(input_panel, BorderLayout.SOUTH);
        return panel;
    }

    public void messageBox(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    // Adds a message to the end of chat room
    public void appendMessage(String msg) {
        this.community_messages.append(msg);
        this.community_messages.setCaretPosition(this.community_messages.getDocument().getLength());
    }

    // Updates the list of users online
    public void updateUsers(ArrayList<String> users) {
        users_list.clearSelection();
        users_list.setListData(users.toArray());
    }

    public void setStatus(String statusText) {
        statusLabel.setText(statusText);
    }
}
