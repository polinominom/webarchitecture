package com.assignment1.bbm488;

import java.util.List;

public class LoginController {
	public static boolean isLoginCorrect(List<User> userList, String username, String password)
	{
		User u = UserListController.getUserbyUsername(userList, username);
		if(u != null) 
		{
			if(u.getPassword().equals(password))
			{
				//correct login if user exists and entered same password
				return true;
			}
		}
		
		// otherwise it is not correct login
		return false;
	}
}
