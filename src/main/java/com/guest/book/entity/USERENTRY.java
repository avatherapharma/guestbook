package com.guest.book.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Table(name = "USERENTRY")
@Entity

public class USERENTRY {
	@Id
	@GeneratedValue(generator = "prod-generator")

	@GenericGenerator(name = "prod-generator", parameters = @Parameter(name = "prefix", value = "ENTRY"), strategy = "com.guest.book.entity.IDGenerator")

	private String ID;
	private String ENTRY;
	private String USER;
	private String STATUS;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getENTRY() {
		return ENTRY;
	}
	public void setENTRY(String eNTRY) {
		ENTRY = eNTRY;
	}
	public String getUSER() {
		return USER;
	}
	public void setUSER(String uSER) {
		USER = uSER;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	
}
