package com.guest.book.dao;

import java.util.List;

import com.guest.book.entity.USER;
import com.guest.book.entity.USERENTRY;
import com.guest.book.model.UserEntry;
import com.guest.book.model.UserModel;

public interface UserDao {

	public  List<USER>  validateUser(UserModel model);	
	public  List<USERENTRY>  alluserentrys();
	public  boolean  approveEntry(UserEntry model);
	public  boolean  removeentry(UserEntry model);
	
	
}
