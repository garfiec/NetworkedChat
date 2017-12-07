/**
 *  @brief Server class file
 *
 *  CS 342 - Project 5
 *  Univeristy of Illinois at Chicago
 *
 *  @author Ammar Subei
 */

import java.net.*; 
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

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
  Server gui;

  public ConnectionThread (Server es3)
  {
    gui = es3;
    start();
  }

  public void run()
  {
    gui.serverContinue = true;

    try {
      gui.serverSocket = new ServerSocket(0);
      gui.setPortNumber(gui.serverSocket.getLocalPort());

      System.out.println ("Connection Socket Created");
      System.out.println (String.format("Server address: %s", gui.getHostAddress()));
      System.out.println (String.format("Port number: %d", gui.getPortNumber()));

      try { 
        while (gui.serverContinue) {
          System.out.println ("Waiting for Connection");
          new CommunicationThread (gui.serverSocket.accept(), gui, gui.connectedClients);
        }
      } catch (IOException e) {
        System.err.println("Accept failed.");
        System.exit(1);
      } 
    } catch (IOException e) {
      System.err.println( String.format("Could not listen on port: %d", gui.getPortNumber()) );
      System.exit(1);
    } finally {
      try {
        gui.serverSocket.close();
      } catch (IOException e) {
      System.err.println( String.format("Could not close port: %d", gui.getPortNumber()) );
        System.exit(1);
      }
    }
  }

}

class CommunicationThread extends Thread
{
  //private boolean serverContinue = true;
  private Socket clientSocket;
  private Server gui;
  private ClientsList connectedClients;

  public CommunicationThread (Socket clientSoc, Server ec3, 
      ClientsList clients)
  {
    clientSocket = clientSoc;
    gui = ec3;
    connectedClients = clients;
    //gui.history.insert ("Comminucating with Port" + clientSocket.getLocalPort()+"\n", 0);
    start();
  }

  public void run()
  {
    System.out.println ("New Communication Thread Started");

    try {
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
          true); 
      connectedClients.add(out);

      BufferedReader in = new BufferedReader( 
          new InputStreamReader( clientSocket.getInputStream())); 

      String inputLine;  

      while ((inputLine = in.readLine()) != null) {
        System.out.println ("Input: " + inputLine); 
        //gui.history.insert (inputLine+"\n", 0);

        // TODO: send to target clients only
        // Loop through the connectedClients and send to all "active" streams
        for ( PrintWriter out1: connectedClients ) {
          System.out.println ("Sending Message");
          out1.println (inputLine);
        }

        if (inputLine.equals("Bye")) 
          break; 

        if (inputLine.equals("End Server")) 
          gui.serverContinue = false; 
      } 

      connectedClients.remove(out);
      out.close(); 
      in.close(); 
      clientSocket.close(); 
    } catch (IOException e) {
      System.err.println("Problem with Communication Server");
      //System.exit(1); 
    }
  }
}
