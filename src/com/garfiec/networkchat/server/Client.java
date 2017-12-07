/**
 *  @brief Client class file
 *
 *  CS 342 - Project 5
 *  Univeristy of Illinois at Chicago
 *
 *  @author Ammar Subei
 */
package com.garfiec.networkchat.server;

import java.util.ArrayList;
import java.lang.Long;
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
  private Socket outStream;
  private String name;
  private Keys key;

  /**
   *  @brief Default constructor
   */
  public Client(Socket out, String name, Keys key)
  {
    this.outStream = out;
    this.name = name;
    this.key = key;
  }

  public Socket getOutStream()
  {
    return outStream;
  }

  public String getName()
  {
    return name;
  }

  public Keys getKey()
  {
    return key;
  }
}

