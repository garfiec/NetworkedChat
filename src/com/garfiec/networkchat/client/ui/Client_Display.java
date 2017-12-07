package com.garfiec.networkchat.client.ui;

import com.garfiec.networkchat.client.ui.etc.UI_Settings;
import com.garfiec.networkchat.client.util.Chat_Client_Settings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class Client_Display extends JFrame {
    private Chat_Client_Settings settings;

    JTextArea community_messages;

    public Client_Display() {
        super(UI_Strings.GUI_TITLE);
        getContentPane().setLayout(new BorderLayout());

        createMenu();
        createUI();


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

        // Todo: Connect to Server Dialog
        menuItem = new JMenuItem("Connect to Server");
        menuItem.addActionListener(e -> System.out.println("TODO: Connect to server"));
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Program Settings");
        menuItem.addActionListener(e -> new JDialog(new UI_Settings(settings)));
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(e -> System.exit(0));
        menu.add(menuItem);

        // Help menu
        menu = new JMenu("Help");
        menuBar.add(menu);

        // TODO: How to use dialog
        menuItem = new JMenuItem("How to use");
        menuItem.addActionListener(e -> System.out.println("TODO: How to use dialog"));
        menu.add(menuItem);

        menu.addSeparator();

        // TODO: About dialog
        menuItem = new JMenuItem("About");
        menuItem.addActionListener(e -> System.out.println("TODO: About dialog"));
        menu.add(menuItem);

        this.setJMenuBar(menuBar);
    }

    private void createUI() {
        JPanel ui_panel = new JPanel(new BorderLayout());

        // Chat rooms control
        JPanel rooms_ctrl_panel = new JPanel(new BorderLayout());
        rooms_ctrl_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        rooms_ctrl_panel.add(createChatRoomCtrls());
        ui_panel.add(rooms_ctrl_panel, BorderLayout.WEST);

        // Chat room itself
        JPanel rooms_panel = new JPanel(new BorderLayout());
        rooms_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        rooms_panel.add(createRoomUI());
        ui_panel.add(rooms_panel, BorderLayout.CENTER);

        this.add(ui_panel, BorderLayout.CENTER);
    }

    private JPanel createChatRoomCtrls() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Title Label for List of Connected Users
        JPanel listLabelPanel = new JPanel(new BorderLayout());
        listLabelPanel.setBorder(new EmptyBorder(0, 0, 5, 0));
        JLabel userListLabel = new JLabel(UI_Strings.USER_LIST_LABEL_TEXT);
        listLabelPanel.add(userListLabel, BorderLayout.CENTER);

        ArrayList<String> mock_list = new ArrayList<>();
        mock_list.add("Lobby");
        for (int i = 0; i < 20; i++) {
            mock_list.add(Integer.toString(i));
        }

        // List of Connected Users
        JPanel list_panel = new JPanel(new BorderLayout());
        list_panel.setBorder(new LineBorder(new Color(147, 147, 147), 1));
        JList users_list = new JList(mock_list.toArray());
        users_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list_panel.add(users_list, BorderLayout.CENTER);

        panel.add(listLabelPanel, BorderLayout.NORTH);
        panel.add(list_panel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createRoomUI() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // User name
        JLabel user_name = new JLabel("<html><h3>Guest</h3></html>");
        panel.add(user_name, BorderLayout.NORTH);

        // Community Messages
        JPanel community_messages_panel = new JPanel(new BorderLayout());
        community_messages_panel.setBorder(new EmptyBorder(0, 0, 5, 0));
        String sampleMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius, felis at semper convallis, nisi leo auctor nunc, eu sagittis magna purus semper nunc. Mauris gravida faucibus dapibus. Aliquam eleifend tortor non tincidunt lacinia. Aliquam sed magna sed quam suscipit posuere. Nullam sodales tempus massa in iaculis. In nec mi quis nibh dictum congue. Maecenas auctor ornare libero quis pulvinar.\n\n" +
                "Vivamus ut ullamcorper tortor. Nam id lacus nec risus ullamcorper suscipit volutpat eu lacus. Curabitur dui nunc, rutrum id felis et, rhoncus luctus risus. Donec eu turpis interdum, lacinia libero sit amet, dictum eros. Duis euismod vitae massa ultricies dictum. Quisque hendrerit elit turpis, eu faucibus lorem consequat vel. Donec in ligula justo. Etiam sed placerat neque, quis iaculis enim. Vestibulum pretium orci ut nibh feugiat ornare. Vivamus iaculis nibh quis efficitur vulputate. Phasellus dolor tortor, mollis quis neque ut, posuere laoreet felis. Donec vel nunc aliquet, luctus magna non, aliquet erat. Vivamus condimentum quam at eros lobortis luctus.\n\n" +
                "In hac habitasse platea dictumst. Pellentesque metus magna, vehicula at magna aliquet, pharetra ullamcorper nisl. Fusce placerat ac quam nec finibus. Sed gravida, dui quis auctor ultrices, elit massa sollicitudin leo, et ultricies nisl erat non magna. Nam tristique eu dui a semper. Fusce in metus vestibulum, aliquet purus id, mattis felis. Nullam dignissim leo vel finibus fermentum. Nunc maximus dui id tellus elementum, a luctus mauris congue. Proin a ex cursus, venenatis nisl vel, tristique lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed dictum quam interdum mi vehicula ullamcorper. Phasellus ac egestas ante. Integer lobortis scelerisque dui, a tempor eros placerat cursus.\n" +
                "Pellentesque venenatis luctus sapien, a tristique odio imperdiet in. Donec ac condimentum erat, vel sagittis ante. Vestibulum aliquet nisl at metus ultricies semper. Aliquam a ultrices felis. Vivamus tristique justo sit amet orci pharetra, ut volutpat tortor euismod. Suspendisse blandit rutrum erat, sit amet fringilla enim elementum in. Ut tincidunt nisl et augue finibus, at cursus nunc sagittis. Proin non convallis est, ut volutpat metus. Duis aliquet erat ac risus porta imperdiet.\n" +
                "In in consectetur dolor. Morbi eu est auctor, ullamcorper velit quis, vulputate urna. Praesent vitae lorem congue magna gravida mollis. Suspendisse aliquam sodales metus, eget elementum nisi mollis vel. Maecenas lobortis lectus et elit malesuada laoreet. Quisque placerat cursus nibh molestie malesuada. Integer pharetra rhoncus imperdiet. Donec dapibus laoreet purus, at tincidunt mi ornare eu. Aenean non posuere mi, a eleifend odio. Maecenas sagittis molestie commodo. Cras a consequat diam. Nulla nec lacinia nisi.\n" +
                "Aliquam porttitor augue eleifend, consectetur dolor quis, bibendum dolor. Maecenas tempor lacus ornare lacinia pretium. Duis vulputate massa enim, quis tempus erat elementum eget. Curabitur eget turpis tincidunt, pretium diam in, pharetra tortor. Ut elementum elit magna, sed cursus nulla venenatis vel. Proin condimentum est quis nisi volutpat ornare. Fusce fermentum nisi quis augue congue, vitae finibus sem euismod. Aenean in urna vitae lorem porttitor interdum. Duis maximus dignissim nunc vel semper. Ut sodales orci id venenatis sollicitudin. Morbi vehicula, risus in semper accumsan, arcu arcu consequat enim, vel iaculis elit lectus vel ipsum.\n";

        JTextArea community_messages = new JTextArea(sampleMessage);
        community_messages.setEditable(false);
        community_messages.setLineWrap(true);
        JScrollPane messages_scroll = new JScrollPane(community_messages);

        this.community_messages = community_messages;
        community_messages_panel.add(messages_scroll, BorderLayout.CENTER);

        // Input message
        JPanel input_panel = new JPanel(new BorderLayout());
        input_panel.setBorder(new EmptyBorder(5, 0, 0, 0));
        JTextField user_input = new JTextField("Your message here");
        user_input.addActionListener(e -> System.out.println("Enter pressed with msg: " + e.getActionCommand())); // TODO: Call message sender
        input_panel.add(user_input);

        panel.add(community_messages_panel, BorderLayout.CENTER);
        panel.add(input_panel, BorderLayout.SOUTH);
        return panel;
    }

    // Adds a message to the end of chat room
    public void appendMessage(String msg) {
        this.community_messages.append(msg);
        this.community_messages.setCaretPosition(this.community_messages.getDocument().getLength());
    }
}
