package com.assignment1.bbm488;

import java.util.List;

public class UserListController {

	public static boolean isUserExists(List<User> userList, String username)
	{
		if(userList == null)	return false;
		
		for (User u : userList) {
			if(u.getUsername().equals(username))
			{
				return true;
			}
			
		}
		
		return false;
	}
	
	public static User getUserbyUsername(List<User> userList, String username)
	{
		if(userList == null)	return null;
				
		for (User u : userList) {
			if(u.getUsername().equals(username))
			{
				return u;
			}
			
		}
		
		return null;	
	}
}
