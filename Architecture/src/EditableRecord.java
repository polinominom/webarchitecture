
public class EditableRecord {
	private Record record;
	private String scopeType;
	private int id;
	
	public EditableRecord(Record record, String scopeType, int id)
	{
		this.record = record;
		this.scopeType = scopeType;
		this.id = id;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public String getScopeType() {
		return scopeType;
	}

	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
