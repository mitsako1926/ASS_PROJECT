package package1;

public class User {

	
	private int userID;
	private String username;
	private String password;
	private String role;

	
	User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	User(String username, String password, String role){
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	//GETTERS AND SETTERS

	public int getUserID() {
	    return userID;
	}

	public void setUserID(int userID) {
	    this.userID = userID;
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

	public String getRole() {
	    return role;
	}

	public void setRole(String role) {
	    this.role = role;
	}
	
	
	//METHODS
	
	@Override
	public boolean equals(Object o) {

	    if (this == o) {
	        return true;
	    }

	    if (!(o instanceof User)) {
	        return false;
	    }

	    User user = (User) o;

	    return username.equals(user.username) && password.equals(user.password);
	}
	
	void verifyPassword() {
		
	}
	
//	void getRole() {
//		
//	}
	
}
