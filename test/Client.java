/**
 *  @brief Client class file
 *
 *  CS 342 - Project 5
 *  Univeristy of Illinois at Chicago
 *
 *  @author Ammar Subei
 */

import java.util.ArrayList;
import java.lang.Long;
import java.io.*;

public class Client
{
  private PrintWriter outStream;
  private String name;
  private Key key;

  /**
   *  @brief Default constructor
   */
  public Client(PrintWriter out, String name, Key key)
  {
    this.outStream = out;
    this.name = name;
    this.key = key;
  }

  public PrintWriter getOutStream()
  {
    return outStream;
  }

  public String getName()
  {
    return name;
  }

  public Key getKey()
  {
    return key;
  }
}

