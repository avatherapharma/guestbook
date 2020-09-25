package com.guest.book.service;

import java.util.List;

import com.guest.book.model.UserEntry;
import com.guest.book.model.UserModel;

public interface UserService {
	public UserModel validateUser(UserModel model);
	public UserModel addNewUSer(UserModel model);
	public List<UserEntry> pendingadminentrys();
	public UserEntry addUserEntry(UserEntry model);
	public boolean approveEntry(UserEntry model);
	public boolean removeEntry(UserEntry model);
	
}
