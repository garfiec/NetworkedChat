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
import com.garfiec.networkchat.common.Crypt_RSA.Keys;

public class Packet implements Serializable
{
  private int packetType;
  private String source;
  private ArrayList< Tuple<String, Keys> > keyData;
  private ArrayList< Tuple<String, ArrayList<BigInteger>> > messageData;

  /**
   *  @brief Default constructor
   */
  public Packet(int type)
  {
    keyData = new ArrayList< Tuple<String, Keys> >();
    messageData = new ArrayList< Tuple<String, ArrayList<BigInteger>> >();
    packetType = type;
    source = null;
  }

  /**
   *  @brief Default constructor
   */
  public Packet(int type, String name)
  {
    keyData = new ArrayList< Tuple<String, Keys> >();
    messageData = new ArrayList< Tuple<String, ArrayList<BigInteger>> >();
    packetType = type;
    source = name;
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
   *  @brief Getter for source name
   *  @return String source name
   */
  public String getSource()
  {
    return source;
  }

  /**
   *  @brief Gets name at given index
   *  @param int index
   *  @return String name
   */
  public String getName(int index)
  {
    if ( !keyData.isEmpty() ) {
      return keyData.get(index).getFirst();
    } else {
      return messageData.get(index).getFirst();
    }
  }

  /**
   *  @brief Gets all keys
   *  @return ArrayList<Tuple<String,Keys>> keys
   */
  public ArrayList<Tuple<String,Keys>> getKey()
  {
    return keyData;
  }

  /**
   *  @brief Gets key at given index
   *  @param int index
   *  @return Keys key
   */
  public Keys getKey(int index)
  {
    return keyData.get(index).getSecond();
  }

  /**
   *  @brief Gets all messages
   *  @return ArrayList messages
   */
  public ArrayList<Tuple<String,ArrayList<BigInteger>>> getMessage()
  {
    return messageData;
  }

  /**
   *  @brief Gets message at given index
   *  @param int index
   *  @return ArrayList<BigInteger> message
   */
  public ArrayList<BigInteger> getMessage(int index)
  {
    return messageData.get(index).getSecond();
  }

  /**
   *  @brief Getter for number of elements in packet
   *  @return int elements in packet
   */
  public int getSize()
  {
    if ( !keyData.isEmpty() ) {
      return keyData.size();
    } else {
      return messageData.size();
    }
  }

  /**
   *  @brief Adds a new entry to the packet
   *  @param String name
   *  @param Keys key
   */
  public void add(String name, Keys neyKey)
  {
    keyData.add( new Tuple<String, Keys>(name, neyKey) );
  }

  /**
   *  @brief Adds a new entry to the packet
   *  @param String name
   *  @param ArrayList<BigInteger> message
   */
  public void add(String name, ArrayList<BigInteger> message)
  {
    messageData.add( new Tuple<String, ArrayList<BigInteger>>(name, message) );
  }
}
