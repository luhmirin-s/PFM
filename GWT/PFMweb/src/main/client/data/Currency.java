package main.client.data;

public class Currency {
	
	private int id;
	private String code;
	

	public Currency() {}
	
	public Currency(int id, String name, int userID) {
		setId(id);
		setCode(code);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
