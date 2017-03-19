package com.assignment1.bbm488;

import java.util.List;

public class RegisterController {

	// 0 : if correctly added to the list
	// 1 : username exists
	// 2 : password or username empty
	public static int reqisterUser(List<User> userList, String username, String password)
	{
		
		//make sure given username and password is not empty 
		if(username == null || password == null)
		{
			return 2;
		}
		
		if(username.isEmpty() || password.isEmpty())
		{
			return 2;
		}
		
		// make sure the user isn't already exists
		boolean userExist = UserListController.isUserExists(userList, username);
		if(userExist)
		{
			return 1;
		}
		
		// add the user to the list
		userList.add(new User(username, password));
		return 0;
	}
	
}
