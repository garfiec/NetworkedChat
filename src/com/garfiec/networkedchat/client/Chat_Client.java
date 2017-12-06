package com.garfiec.networkedchat.client;

import com.garfiec.networkedchat.client.ui.Client_Display;

public class Chat_Client {
    public static void main(String[] args) {
        System.out.println("Running chat client");
        Chat_Client chat_client = new Chat_Client();
        new Client_Display();
    }
}
