package com.assignment1.bbm488;

public class Record {

	private String name;
	private String surname;
	private String adress;
	private int telephone;
	private int age;
	private boolean sessionType;
	
	public Record()
	{
		name = "";
		surname = "";
		adress = "";
		telephone = 0;
		age = 0;
		sessionType = true;
	}

	public boolean isSessionType() {
		return sessionType;
	}

	public void setSessionType(boolean sessionType) {
		this.sessionType = sessionType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
}
