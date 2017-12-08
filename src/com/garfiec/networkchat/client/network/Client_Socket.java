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

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket sock;

	public Client_Socket(String machineName, int portNum, Chat_Client client) {
		this.client = client;
		this.machineName = machineName;
		this.portNum = portNum;
	}

	public boolean sendKey(String name, Keys k) {
		Packet<Keys> pack = new Packet<Keys>(1);
		pack.add("Andrijko", k);
		try {
			System.out.println("here2");
			out.writeObject(pack);
			Packet<Keys> result = (Packet)in.readObject();
			if (result.getType() == -1) {
				System.out.println("errored but fine");
				return false;
			}
			System.out.println("No error. We gucci");
			return true;
		} catch (Exception e) {
			System.out.println("messed up");
			return false;
		}
	}

	public void sendMessage(String msg, HashMap<String, Keys> users) {
		Packet<ArrayList<BigInteger>> packet = new Packet<ArrayList<BigInteger>>(2);
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
		new CommunicationReadThread(in);
	}

	public boolean connect() {
		try {
			sock = new Socket(machineName, portNum);
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
  private ObjectInputStream in;

  public CommunicationReadThread (ObjectInputStream inparam)
  {
    in = inparam;
    start();
  }

  public void run()
  {
    System.out.println ("Client is listening to server for messages");

    try {
      Packet<ArrayList<BigInteger>> inputLine;

      while ((inputLine = (Packet) in.readObject()) != null)
      {
        System.out.println ("Client received a packet from someone!");
      }
	  System.out.println("Something went wrong. We disconnected"); 
	  in.close();
    }
    catch (IOException e)
    {
      System.err.println("Client encountered problem while reading");
    }
    catch (ClassNotFoundException e) {
	}
  }
}

