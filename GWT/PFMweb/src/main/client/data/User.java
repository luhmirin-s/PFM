package main.client.data;

public class User {

	private int id;
	private String email;
	private String password;
	private String username;
	
	public User(int id, String username, String password, String email){
		this.id=id;
		this.username=username;
		this.password=password;
		this.email=email;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
