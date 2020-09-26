package com.guest.book.model;
public class ClientWithSelection {
   private Boolean selected;
   private String clientID;
   private String ipAddress;
   private String description;

   public ClientWithSelection() {
   }

   public ClientWithSelection(Boolean selected, String clientID, String ipAddress, String description) {
      super();
      this.selected = selected;
      this.clientID = clientID;
      this.ipAddress = ipAddress;
      this.description = description;
   }

public Boolean getSelected() {
	return selected;
}

public void setSelected(Boolean selected) {
	this.selected = selected;
}

public String getClientID() {
	return clientID;
}

public void setClientID(String clientID) {
	this.clientID = clientID;
}

public String getIpAddress() {
	return ipAddress;
}

public void setIpAddress(String ipAddress) {
	this.ipAddress = ipAddress;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}
}