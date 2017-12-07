/**
 *  @brief Packet class file
 *
 *  CS 342 - Project 5
 *  Univeristy of Illinois at Chicago
 *
 *  @author Ammar Subei
 */

package com.garfiec.networkchat.server;

import java.util.ArrayList;
import java.lang.Long;
import java.io.Serializable;

public class Packet implements Serializable
{
  private ArrayList<ClientMessage> data; ///< Data packet

  /**
   *  @brief Default constructor
   */
  public ClientPacket()
  {
    data = new ArrayList< Tuple<String, ArrayList<Long>> >();
  }

  /**
   *  @brief Gets name at given index
   *  @param int index
   *  @return String name
   */
  public String getName(int index)
  {
    return data.get(index).getFirst();
  }

  /**
   *  @brief Gets message at given index
   *  @param int index
   *  @return ArrayList<Long> message
   */
  public ArrayList<Long> getMessage(int index)
  {
    return data.get(index).getSecond();
  }

  /**
   *  @brief Adds a new entry to the packet
   *  @param ArrayList<Long> message
   *  @param String name
   */
  public void add(String targetName, ArrayList<Long> message)
  {
    data.add( new Tuple<String, ArrayList<Long>>(targetName, message) );
  }
}
