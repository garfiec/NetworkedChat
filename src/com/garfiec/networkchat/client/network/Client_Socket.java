package com.garfiec.networkchat.client.network;

import com.garfiec.networkchat.client.Chat_Client;
import com.garfiec.networkchat.common.Crypt_RSA.Keys;
import com.garfiec.networkchat.server.Packet;

import java.math.BigInteger;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Client_Socket {
	public Chat_Client client;
	public String machineName;
	public int portNum;

	public ObjectOutputStream out;
	public ObjectInputStream in;
	public Socket sock;

	public Client_Socket(String machineName, int portNum, Chat_Client client) {
		this.client = client;
		this.machineName = machineName;
		this.portNum = portNum;
	}

	public boolean sendKey(String name, Keys k) {
		Packet pack = new Packet(1);
		pack.add("Andrijko", k);
		try {
			System.out.println("here2");
			out.writeObject(pack);
			Packet result = (Packet)in.readObject();
			if (result.getType() == -1) {
				System.out.println("errored but fine");
				return false;
			}
			// use result to update client list
			System.out.println("No error. We gucci");
			return true;
		} catch (Exception e) {
			System.out.println("messed up");
			return false;
		}
	}

	public void sendMessage(ArrayList<BigInteger> data) {
		Packet packet = new Packet(2);
		for (Map.Entry<String, Keys> user: users.entrySet()) {
 		    String uname = user.getKey();
    		Keys key = user.getValue();
			ArrayList<BigInteger> data = client.rsa_cipher.encrypt(msg, key);
			packet.add(uname, data);
		}
		try {
			System.out.println("Sending to users.");
			out.writeObject(packet);
		}
		catch (Exception e) {
			System.out.println("Fuuuuck");
		}
	}

	public void listen() {
		new CommunicationReadThread(this);
	}

	public boolean connect() {
		try {
			sock = new Socket(machineName, portNum);
			this.sock = sock;
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
		} catch (NumberFormatException e) {
			System.out.println("Error connecting");
			return false;
      	} catch (UnknownHostException e) {
			System.out.println("Error connecting");
			return false;
      	} catch (IOException e) {
			System.out.println("Error connecting");
			return false;
      	}
		return true;
	}
}

class CommunicationReadThread extends Thread
{
  private Client_Socket in;
  private ObjectInputStream is;

  public CommunicationReadThread (Client_Socket inparam)
  {
    in = inparam;
    start();
  }

  public void run()
  {
    System.out.println ("Client is listening to server for messages");

    try {
      is = new ObjectInputStream(in.sock.getInputStream());
      Packet inputLine;

      while ((inputLine = (Packet) is.readObject()) != null)
      {
	  	int type = inputLine.getType();
		if (type == 1) {
			System.out.println("Client received updated list");
		} else if (type == 2) {
        	System.out.println("Client received a packet from someone!");
		} else if (type == -1) {
			System.out.println("Client received error");		
		}
      }
	  System.out.println("Something went wrong. We disconnected"); 
	  is.close();
    }
    catch (IOException e)
    {
      System.err.println("Client encountered problem while reading"+e);
	  e.printStackTrace();
    }
    catch (ClassNotFoundException e) {
	}
  }
}

