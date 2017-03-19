package com.demo.guess;

import java.util.Date;


public class Guess {
	
	private Date date;
	
	private String heat;
	
	private int value;
	
	private String nick;
	
	public Guess(Date date, String heat, int value, String nick) {
		super();
		this.date = date;
		this.heat = heat;
		this.value = value;
		this.nick = nick;
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHeat() {
		return heat;
	}

	public void setHeat(String heat) {
		this.heat = heat;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}


	

}
