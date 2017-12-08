package com.garfiec.networkchat.client.network;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Client_Socket {
	
	public String machineName;
	public int portNum;

	private PrintWriter out;
	private BufferedReader in;
	private Socket sock;

	public Client_Socket(String machineName, int portNum) {
		this.machineName = machineName;
		this.portNum = portNum;
	}

	public sendMessage(String msg, ArrayList<String> names) {
		System.out.println("Sending message");
	}

	public boolean connect() {
		try {
			sock = new Socket(machineName, portNum);
			out = new PrintWriter(sock.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader( sock.getInputStream()));

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
  private BufferedReader in;

  public CommunicationReadThread (BufferedReader inparam)
  {
    in = inparam;
    start();
  }

  public void run()
  {
    System.out.println ("Client is listening to server for messages");

    try {
      String inputLine;

      while ((inputLine = in.readLine()) != null)
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
  }
}

