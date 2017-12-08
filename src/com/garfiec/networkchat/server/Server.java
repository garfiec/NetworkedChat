/**
 *  @brief Server class file
 *
 *  CS 342 - Project 5
 *  Univeristy of Illinois at Chicago
 *
 *  @author Ammar Subei
 */

package com.garfiec.networkchat.server;

import java.math.BigInteger;
import java.net.*; 
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.nio.charset.Charset;
import com.garfiec.networkchat.common.Crypt_RSA;
import com.garfiec.networkchat.common.Crypt_RSA.Keys;

public class Server
{
  private boolean connected;
  public boolean serverContinue;

  private int portNumber;
  private String hostAddress;
  public ServerSocket serverSocket;
  public ClientsList connectedClients;

  public Server()
  {
    // Set up the shared connectedClients
    connectedClients = new ClientsList();
    connected = false;

    try {
      InetAddress addr = InetAddress.getLocalHost();
      setHostAddress( addr.getHostAddress() );
    } catch (UnknownHostException e) {
      setHostAddress("127.0.0.1");
    }
  }

  /**
   *  @brief Getter for port number
   *  @return int port number
   */
  public int getPortNumber()
  {
    return portNumber;
  }

  /**
   *  @brief Getter for host address
   *  @return String host address
   */
  public String getHostAddress()
  {
    return hostAddress;
  }

  /**
   *  @brief Getter for connected status
   *  @return boolean connected status
   */
  public boolean isConnected()
  {
    return connected;
  }

  /**
   *  @brief Setter for port number
   *  @param int new port number
   */
  public void setPortNumber(int port)
  {
    portNumber = port;
  }

  /**
   *  @brief Setter for host address
   *  @param String new host address
   */
  public void setHostAddress(String address)
  {
    hostAddress = address;
  }

  public static void main( String args[] )
  { 
    Server application = new Server();
    new ConnectionThread(application);
  }
}

class ConnectionThread extends Thread
{
  Server server;

  public ConnectionThread (Server es3)
  {
    server = es3;
    start();
  }

  public void run()
  {
    server.serverContinue = true;

    try {
      server.serverSocket = new ServerSocket(0);
      server.setPortNumber(server.serverSocket.getLocalPort());

      System.out.println ("Connection Socket Created");
      System.out.println (String.format("Server address: %s", server.getHostAddress()));
      System.out.println (String.format("Port number: %d", server.getPortNumber()));

      try { 
        while (server.serverContinue) {
          System.out.println ("Waiting for Connection");
          new CommunicationThread (server.serverSocket.accept(), server, server.connectedClients);
        }
      } catch (IOException e) {
        System.err.println("Accept failed.");
        System.exit(1);
      } 
    } catch (IOException e) {
      System.err.println( String.format("Could not listen on port: %d", server.getPortNumber()) );
      System.exit(1);
    } finally {
      try {
        server.serverSocket.close();
      } catch (IOException e) {
        System.err.println( String.format("Could not close port: %d", server.getPortNumber()) );
        System.exit(1);
      }
    }
  }

}

class CommunicationThread extends Thread
{
  //private boolean serverContinue = true;
  private Socket clientSocket;
  private Server server;
  private ClientsList connectedClients;

  public CommunicationThread(Socket sock, Server s, ClientsList clients)
  {
    clientSocket = sock;
    server = s;
    connectedClients = clients;
    start();
  }

  public void run()
  {
    System.out.println ("New Communication Thread Started");

    // TODO: need to send new client's info to other clients

    String clientName = null;

    try {
      // TODO: need to receive a message from new client to get its info (name, key)
      ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream()); 
      ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream()); 

      Packet<Keys> newClientInfo = (Packet) in.readObject();

      Crypt_RSA a = new Crypt_RSA();
      Keys k = a.makeKeys(256201021L, 256203161L);

      Client cl = new Client(clientSocket, clientName, k);
      connectedClients.add(cl);

      Packet<ArrayList<BigInteger>> clientMessage;

      while ( (clientMessage = (Packet)in.readObject()).isEmpty() ) {
        // Send to specified clients only
        for (int i = 0; i < clientMessage.getSize(); i++) {
          String target = clientMessage.getName(i);
          ArrayList<BigInteger> message = clientMessage.getMessage(i);

          System.out.println ("Sending Message");

          Packet<ArrayList<BigInteger>> sendData = new Packet<>(1);
          sendData.add(target, message);

          Client client = connectedClients.get(target);
          client.sendMessage(sendData);
        }
      } 

      connectedClients.remove(cl.getName());
      out.close(); 
      in.close(); 
      clientSocket.close(); 
    } catch (IOException e) {
      System.err.println("Problem with Communication Server");
      //System.exit(1);
    } catch (ClassNotFoundException e) {
      System.err.println("Problem with packet received");
      //System.exit(1);
    }
  }
}
