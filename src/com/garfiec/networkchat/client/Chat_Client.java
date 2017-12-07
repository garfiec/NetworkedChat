package com.garfiec.networkchat.client;

import com.garfiec.networkchat.client.ui.Client_Display;
import com.garfiec.networkchat.client.util.Chat_Client_Settings;

public class Chat_Client {
    private Chat_Client_Settings settings;

    public Chat_Client() {
        settings = new Chat_Client_Settings();

        new Client_Display(settings);
    }

    public static void main(String[] args) {
        System.out.println("Running chat client");
        Chat_Client chat_client = new Chat_Client();
    }
}
