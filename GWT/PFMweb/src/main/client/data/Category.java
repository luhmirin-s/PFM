package main.client.data;

public class Category {

	
	private int id;
	private int userId;
	private String name;
	

	public Category() {}
	
	public Category(int id, String name, int userID) {
		setId(id);
		setName(name);
		setUserId(userId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}