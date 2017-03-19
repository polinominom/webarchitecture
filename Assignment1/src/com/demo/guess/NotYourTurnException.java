package com.demo.guess;

public class NotYourTurnException extends Exception {

	private static final long serialVersionUID = 8596530944172233438L;

	public NotYourTurnException(String uname) {
		super("The user with " + uname + " played in other player's turn.");
	}

}
