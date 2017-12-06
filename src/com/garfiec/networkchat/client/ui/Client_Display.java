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

        ArrayList<String> mock_list = new ArrayList<>();
        mock_list.add("Lobby");
        for (int i = 0; i < 20; i++) {
            mock_list.add(Integer.toString(i));
        }

        // List of rooms
        JPanel list_panel = new JPanel(new BorderLayout());
        list_panel.setBorder(new LineBorder(new Color(147, 147, 147), 1));
        JList room_list = new JList(mock_list.toArray());
        room_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list_panel.add(room_list, BorderLayout.CENTER);

        // Controls
        JPanel roomCtrls = new JPanel();
        BoxLayout ctrlsLayout = new BoxLayout(roomCtrls, BoxLayout.LINE_AXIS);
        roomCtrls.setLayout(ctrlsLayout);
        roomCtrls.setBorder(new EmptyBorder(10, 0, 0, 0));

        JButton addRoomButton = new JButton("Add Room");
        roomCtrls.add(addRoomButton);

        panel.add(list_panel, BorderLayout.CENTER);
        panel.add(roomCtrls, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createRoomUI() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel room_name = new JLabel("<html><h2>Room CSBS</h2></html>");
        panel.add(room_name, BorderLayout.NORTH);

        JPanel community_messages_panel = new JPanel(new BorderLayout());
        community_messages_panel.setBorder(new EmptyBorder(0, 0, 5, 0));
        String sampleMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius, felis at semper convallis, nisi leo auctor nunc, eu sagittis magna purus semper nunc. Mauris gravida faucibus dapibus. Aliquam eleifend tortor non tincidunt lacinia. Aliquam sed magna sed quam suscipit posuere. Nullam sodales tempus massa in iaculis. In nec mi quis nibh dictum congue. Maecenas auctor ornare libero quis pulvinar.\n\n" +
                "Vivamus ut ullamcorper tortor. Nam id lacus nec risus ullamcorper suscipit volutpat eu lacus. Curabitur dui nunc, rutrum id felis et, rhoncus luctus risus. Donec eu turpis interdum, lacinia libero sit amet, dictum eros. Duis euismod vitae massa ultricies dictum. Quisque hendrerit elit turpis, eu faucibus lorem consequat vel. Donec in ligula justo. Etiam sed placerat neque, quis iaculis enim. Vestibulum pretium orci ut nibh feugiat ornare. Vivamus iaculis nibh quis efficitur vulputate. Phasellus dolor tortor, mollis quis neque ut, posuere laoreet felis. Donec vel nunc aliquet, luctus magna non, aliquet erat. Vivamus condimentum quam at eros lobortis luctus.\n\n" +
                "In hac habitasse platea dictumst. Pellentesque metus magna, vehicula at magna aliquet, pharetra ullamcorper nisl. Fusce placerat ac quam nec finibus. Sed gravida, dui quis auctor ultrices, elit massa sollicitudin leo, et ultricies nisl erat non magna. Nam tristique eu dui a semper. Fusce in metus vestibulum, aliquet purus id, mattis felis. Nullam dignissim leo vel finibus fermentum. Nunc maximus dui id tellus elementum, a luctus mauris congue. Proin a ex cursus, venenatis nisl vel, tristique lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed dictum quam interdum mi vehicula ullamcorper. Phasellus ac egestas ante. Integer lobortis scelerisque dui, a tempor eros placerat cursus.\n";

        JTextArea community_messages = new JTextArea(sampleMessage);
        community_messages.setEditable(false);
        community_messages.setLineWrap(true);
        community_messages_panel.add(community_messages, BorderLayout.CENTER);

        JPanel input_panel = new JPanel(new BorderLayout());
        input_panel.setBorder(new EmptyBorder(5, 0, 0, 0));
        JTextField user_input = new JTextField("Your message here");
        input_panel.add(user_input);

        panel.add(community_messages_panel, BorderLayout.CENTER);
        panel.add(input_panel, BorderLayout.SOUTH);
        return panel;
    }
}
