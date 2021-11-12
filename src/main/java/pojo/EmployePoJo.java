package pojo;

public class EmployePoJo {
	
	private String username;
	private String password;
	transient private int employID;
	
	public EmployePoJo() {
		super();
	}
	
	public EmployePoJo(String username, String password, int employID )
	{
		super();
		this.username = username;
		this.password = password;
		this.employID = employID;
		
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

	public int getEmployID() {
		return employID;
	}

	public void setEmployID(int employID) {
		this.employID = employID;
	}

}
