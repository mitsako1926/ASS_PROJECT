package package1;

public class Employee {

	//MONADIKO ID GIA TON KATHE IPALILO
	static int ID = 0;
	
	private int userID;
	private String name, username, password, role, userType;

	//TO username kai password einai gia to login 
	
	Employee(String username, String password){// AFTOS O CONSTRUCTOR EINAI GIA NA BROUME EFKOLA AN IPARXEI USER ME TETIO username KAI password
		this.username = username;              // MESW THS EQUALS XORIS NA SIGGRINOUME MIA TO ENA KIA MIA TO ALO
		this.password = password;
	}
	
	Employee(String name, String role,  String userType,  String username , String password){
		ID++;
		this.userID = ID;
		this.name = name;
		this.role = role;
		this.userType = userType;
		this.username = username;
		this.password = password;
	}
	
	//GETTERS AND SETTERS

	public int getUserID() {
	    return userID;
	}

	public void setUserID(int userID) {
	    this.userID = userID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public String getUserType() {
	    return userType;
	}

	public void setUserType(String userType) {
	    this.userType = userType;
	}
	
	
	//METHODS
	
	
	//SIGKRINOUME TOUS IPALILOUS ME BASH TO username KAI password (Mporoume kai me to id)
	@Override
	public boolean equals(Object o) {

	    if (this == o) {
	        return true;
	    }

	    if (!(o instanceof Employee)) {
	        return false;
	    }

	    Employee user = (Employee) o;

	    return username.equals(user.username) && password.equals(user.password);
	}
	
	
	public void printData() {
		System.out.println();
	    System.out.println("════════════════════════════════════");
	    System.out.println("Υπάλληλος      : " + name);
	    System.out.println("Ρόλος          : " + role);
	    System.out.println("Τύπος Χρήστη   : " + userType);
	    System.out.println("════════════════════════════════════");
	}
	
}
