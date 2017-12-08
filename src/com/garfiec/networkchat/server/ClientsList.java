/**
 *  @brief ClientsList class file
 *
 *  CS 342 - Project 5
 *  Univeristy of Illinois at Chicago
 *
 *  @author Ammar Subei
 */

package com.garfiec.networkchat.server;

import java.util.ArrayList;

public class ClientsList
{
  private ArrayList<Client> clients;

  /**
   *  @brief Default constructor
   */
  public ClientsList()
  {
    clients = new ArrayList<Client>();
  }

  /**
   *  @brief Getter for list size
   *  @return int list size
   */
  public synchronized int getSize()
  {
    return clients.size();
  }

  /**
   *  @brief Checks if list contains the given client name
   *  @param String client name
   */
  public synchronized boolean contains(String name)
  {
    for (Client client : clients) {
      if ( client.getName().equals(name) ) {
        return true;
      }
    }

    return false;
  }

  /**
   *  @brief Gets client at given index
   *  @param int index
   *  @return Client client
   */
  public synchronized Client get(int index)
  {
    return clients.get(index);
  }

  /**
   *  @brief Gets client with the given name
   *  @param String client name
   *  @return Client client
   */
  public synchronized Client get(String name)
  {
    for (Client client : clients) {
      if ( client.getName().equals(name) ) {
        return client;
      }
    }

    return new Client();
  }

  /**
   *  @brief Gets index of given client name
   *  @param String client name
   *  @return int index
   */
  public synchronized int getIndex(String name)
  {
    int i = 0;
    for (Client client : clients) {
      if ( client.getName().equals(name) ) {
        return i;
      }

      i++;
    }

    return -1;
  }

  /**
   *  @brief Adds a new client
   *  @param Client new client to add
   */
  public synchronized void add(Client newClient)
  {
    clients.add(newClient);
  }

  /**
   *  @brief Removes a client
   *  @param String name of client to remove
   */
  public synchronized void remove(String name)
  {
    if ( !clients.contains(name) ) {
      return;
    }

    for (Client client : clients) {
      if ( client.getName().equals(name) ) {
        clients.remove(client);
      }
    }
  }
}

