

import java.util.ArrayList;
import java.util.List;

public class User {
	private String username;
	private String password;
	private List<Record> records;
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
		this.records = new ArrayList<Record>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}
	
	public void addRecord(Record record){
		records.add(record);
	}
}
