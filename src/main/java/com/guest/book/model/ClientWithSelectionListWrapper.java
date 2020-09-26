package com.guest.book.model;

import java.util.List;

public class ClientWithSelectionListWrapper {

   private List<UserEntry> clientList;

   public List<UserEntry> getClientList() {
      return clientList;
   }
   public void setClientList(List<UserEntry> list) {
      this.clientList = list;
   }
}