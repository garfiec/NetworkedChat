package com.garfiec.networkchat.client;

import com.garfiec.networkchat.client.network.Client_Socket;
import com.garfiec.networkchat.client.ui.Client_Display;
import com.garfiec.networkchat.client.util.Chat_Client_Config;
import com.garfiec.networkchat.common.Crypt_RSA;

import java.util.ArrayList;
import java.util.HashMap;

public class Chat_Client {
    private Chat_Client_Config settings;
    private Client_Display          gui;

    private Crypt_RSA               rsa_cipher;

    private HashMap<String, Client_Socket> users;

    public Chat_Client() {
        settings = new Chat_Client_Config();
        gui = new Client_Display(this, settings);
        receiveData(1);
    }

    // Todo: Data received from socket
    public void receiveData(int payload) {
        // Todo: Decrypt data
        // Todo: Read data

        // Placeholder vars
        String type = "userlist";
        String msg = "";
        ArrayList<String> mock_users = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mock_users.add(Integer.toString(i));
        }


        switch(type) {
            case "chatmessage":
                gui.appendMessage(msg);
                break;
            case "userlist":
                gui.updateUsers(mock_users);
                break;
        }
    }

    // Todo: Send data to server
    private boolean sendPayload() {
        // Todo: Find clients to send to
        // Todo: Encrypt data
        // Todo: Transmit data
        return false;
    }

    // Todo: UI hook to send chat message
    public void sendMessage(String msg) {
        System.out.println("Application received message: " + msg);
        sendPayload();
    }

    public static void main(String[] args) {
        System.out.println("Running chat client");
        new Chat_Client();
    }
}
