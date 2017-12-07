/**
 *  @brief ClientsList class file
 *
 *  CS 342 - Project 5
 *  Univeristy of Illinois at Chicago
 *
 *  @author Ammar Subei
 */

import java.util.ArrayList;
import java.io.*;

public class ClientsList
{
  private ArrayList<Client> clients;

  public ClientsList()
  {
    clients = new ArrayList<Client>();
  }

  public boolean contains(String name)
  {
    for (Client client : clients) {
      if (client.getName() == name) {
        return true;
      }
    }

    return false;
  }

  public Client get(int index)
  {
    return clients.get(index);
  }

  public void add(Client newClient)
  {
    clients.add(newClient);
  }
}

