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
import java.util.List;
import java.math.BigInteger;
import java.io.Serializable;

public class Packet<T extends Serializable> implements Serializable
{
  private ArrayList< Tuple<String, T> > data;
  private int packetType;

  /**
   *  @brief Default constructor
   */
  public Packet(int type)
  {
    data = new ArrayList< Tuple<String, T> >();
    packetType = type;
  }

  /**
   *  @brief Getter for packet type
   *  @return int packet type
   */
  public int getType()
  {
    return packetType;
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
   *  @return T message
   */
  public T getMessage(int index)
  {
    return data.get(index).getSecond();
  }

  /**
   *  @brief Getter for number of elements in packet
   *  @return int elements in packet
   */
  public int getSize()
  {
    return data.size();
  }

  /**
   *  @brief Checks if packet is empty
   *  @return boolean is empty
   */
  public boolean isEmpty()
  {
    return data.isEmpty();
  }

  /**
   *  @brief Adds a new entry to the packet
   *  @param String name
   *  @param T message
   */
  public void add(String name, T newData)
  {
    data.add( new Tuple<String, T>(name, newData) );
  }

  /**
   *  @brief Appends data to existing packet
   *  @param List<Tuple<String,T>> data to add
   */
  public void add(List<Tuple<String,T>> newData)
  {
    data.addAll(newData);
  }
}
