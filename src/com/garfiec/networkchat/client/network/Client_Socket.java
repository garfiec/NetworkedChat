package com.garfiec.networkchat.client.network;

import com.garfiec.networkchat.client.Chat_Client;
import com.garfiec.networkchat.common.Crypt_RSA;
import com.garfiec.networkchat.server.Packet;

import java.math.BigInteger;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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

	public void sendMessage(String msg) {
//		out.println("Fuck you.");
		Packet<ArrayList<BigInteger>> pack = new Packet<>(2);
		ArrayList<BigInteger> data = new ArrayList<>();
		data.add(BigInteger.valueOf(500));
		pack.add("GARFIE", data);
		try {
			out.writeObject(msg);
		}
		catch (Exception e) {

		}
	}

	public boolean connect() {
		try {
			sock = new Socket(machineName, portNum);
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());

			new CommunicationReadThread(in);
		} catch (NumberFormatException e) {
			System.out.println("Error connecting");
      	} catch (UnknownHostException e) {
			System.out.println("Error connecting");
      	} catch (IOException e) {
			System.out.println("Error connecting");
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
      Packet<Crypt_RSA.Keys> inputLine;

      while ((inputLine = (Packet) in.readObject()) != null)
      {
        System.out.println ("Client received: " + inputLine);
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

