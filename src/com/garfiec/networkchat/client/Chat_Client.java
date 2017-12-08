package com.garfiec.networkchat.client;

import com.garfiec.networkchat.client.network.Client_Socket;
import com.garfiec.networkchat.client.ui.Client_Display;
import com.garfiec.networkchat.client.util.Chat_Client_Config;
import com.garfiec.networkchat.common.Crypt_RSA;
import com.garfiec.networkchat.server.Tuple;

import java.util.ArrayList;
import java.util.HashMap;

public class Chat_Client {
    public Chat_Client_Config settings;
    private Client_Display          gui;

    public Crypt_RSA               rsa_cipher;

    public Client_Socket socket = null;
    private HashMap<String, Crypt_RSA.Keys> users;

    public Chat_Client() {
        settings = new Chat_Client_Config();
        gui = new Client_Display(this, settings);
		rsa_cipher = new Crypt_RSA();
    }

    public void setSocket(Client_Socket socket) {
        this.socket = socket;
    }

    public void updateUsersList(ArrayList<Tuple<String, Crypt_RSA.Keys>> users) {
        ArrayList<String> user_name_list = new ArrayList<>();

        for (Tuple<String, Crypt_RSA.Keys> user : users) {
            this.users.put(user.getFirst(), user.getSecond());
            user_name_list.add(user.getFirst());
        }

        gui.updateUsers(user_name_list);
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
        if (this.socket == null) {
            return false;
        }

//        this.socket.sendMessage();
        // Todo: Find clients to send to
        // Todo: Encrypt data
        // Todo: Transmit data
        return false;
    }

    // Todo: UI hook to send chat message
    public void sendMessage(String msg, ArrayList<String> selected_names) {
        System.out.println("Application received message: " + msg);
        sendPayload();
    }

    public static void main(String[] args) {
        System.out.println("Running chat client");
        new Chat_Client();
    }
}
