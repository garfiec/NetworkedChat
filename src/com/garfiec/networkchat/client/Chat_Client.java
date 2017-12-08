package com.garfiec.networkchat.client;

import com.garfiec.networkchat.client.network.Client_Socket;
import com.garfiec.networkchat.client.ui.Client_Display;
import com.garfiec.networkchat.client.util.Chat_Client_Config;
import com.garfiec.networkchat.common.Crypt_RSA;
import com.garfiec.networkchat.server.Packet;
import com.garfiec.networkchat.server.Tuple;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chat_Client {
    public Chat_Client_Config settings;
    private Client_Display    gui;

    public Crypt_RSA          rsa_cipher;

    private Client_Socket socket = null;
    private HashMap<String, Crypt_RSA.Keys> users;

    private boolean isConnected;

    public Chat_Client() {
        settings = new Chat_Client_Config();
        gui = new Client_Display(this, settings);
		rsa_cipher = new Crypt_RSA();
		isConnected = false;
		this.users = new HashMap<String, Crypt_RSA.Keys>();
    }

    public void setSocket(Client_Socket socket) {
        this.socket = socket;
        isConnected = true;
        gui.setStatus("Connected to server: " + this.settings.server_ip + "@" + this.settings.port);
    }

    public void updateUsersList(ArrayList<Tuple<String, Crypt_RSA.Keys>> users) {
        ArrayList<String> user_name_list = new ArrayList<>();
		this.users = new HashMap<String, Crypt_RSA.Keys>();

		System.out.println(String.format("Received update of %d people", users.size()));

        for (Tuple<String, Crypt_RSA.Keys> user : users) {
			System.out.println(user.getFirst());
			System.out.println(user.getSecond());
            this.users.put(user.getFirst(), user.getSecond());
            user_name_list.add(user.getFirst());
        }

        gui.updateUsers(user_name_list);
    }


    // Message data received from socket
    public void receiveData(ArrayList<BigInteger> data, String name, String source) {
        // Decrypt data
        String message = rsa_cipher.decrypt(data, this.settings.cipher_key);

        gui.appendMessage(source + ": " + message);
    }

    // UI hook to send chat message
    public void sendMessage(String msg, ArrayList<String> selected_users) {
        if (this.socket == null) {
            return;
        }

        if (!this.isConnected) {
            return;
        }

        Packet packet = new Packet(2, this.settings.user_name);
        for (String user : selected_users) {
            Crypt_RSA.Keys k = users.get(user);
            ArrayList<BigInteger> data = this.rsa_cipher.encrypt(msg, k);
            packet.add(user, data);
        }

		socket.sendMessage(packet);
    }

    public static void main(String[] args) {
        System.out.println("Running chat client");
        new Chat_Client();
    }
}
