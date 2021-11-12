package pojo;

public class ManagerPoJo {

	String username;
	String password;
	int managerID;
	
	public ManagerPoJo() {
		super();
		}
		
	public ManagerPoJo(String username, String password, int managerID) {
		super();
		this.username = username;
		this.password = password;
		this.managerID = managerID;
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

	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}
}


