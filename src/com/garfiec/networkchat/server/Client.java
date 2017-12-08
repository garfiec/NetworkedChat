/**
 *  @brief Client class file
 *
 *  CS 342 - Project 5
 *  Univeristy of Illinois at Chicago
 *
 *  @author Ammar Subei
 */

package com.garfiec.networkchat.server;

import java.io.*;
import java.net.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import com.garfiec.networkchat.common.Crypt_RSA;
import com.garfiec.networkchat.common.Crypt_RSA.Keys;

public class Client
{
  private Socket socket;
  private String name;
  private Keys key;

  /**
   *  @brief Default constructor
   */
  public Client()
  {
    this.socket = null;
    this.name = null;
    this.key = null;
  }

  /**
   *  @brief Secondary constructor
   *
   *  @param Socket client socket
   *  @param String client name
   *  @param Key client key
   */
  public Client(Socket socket, String name, Keys key)
  {
    this.socket = socket;
    this.name = name;
    this.key = key;
  }

  /**
   *  @brief Getter for client socket
   *  @return Socket socket
   */
  public Socket getSocket() { return socket; }

  /**
   *  @brief Getter for name
   *  @return String client name
   */
  public String getName()   { return name; }

  /**
   *  @brief Getter for key
   *  @return Keys client key
   */
  public Keys getKey()      { return key; }

  /**
   *  @brief Checks if object is empty
   *  @return boolean is empty
   */
  public boolean isEmpty()  { return socket == null; }

  /**
   *  @brief Sends a data packet
   *  @param Packet data to send
   */
  public void send(Packet data)
  {
    // TODO
  }
}

