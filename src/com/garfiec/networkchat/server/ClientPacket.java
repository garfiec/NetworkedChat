/**
 *  @brief ClientPacket class file
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

public class ClientPacket implements Serializable
{
  private ArrayList< Tuple<ArrayList<Long>, String> > data; ///< Data packet

  /**
   *  @brief Default constructor
   */
  public ClientPacket()
  {
    data = new ArrayList< Tuple<ArrayList<Long>, String> >();
  }

  /**
   *  @brief Gets message at given index
   *  @param int index
   *  @return ArrayList<Long> message
   */
  public ArrayList<Long> getMessage(int index)
  {
    return data.get(index).getFirst();
  }

  /**
   *  @brief Gets name at given index
   *  @param int index
   *  @return String name
   */
  public String getName(int index)
  {
    return data.get(index).getSecond();
  }

  /**
   *  @brief Adds a new entry to the packet
   *  @param ArrayList<Long> message
   *  @param String name
   */
  public void add(ArrayList<Long> message, String targetName)
  {
    data.add( new Tuple< ArrayList<Long>, String >(message, targetName) );
  }
}
