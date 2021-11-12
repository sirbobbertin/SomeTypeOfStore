package pojo;

import java.util.ArrayList;
import java.util.List;

//import pojo.ItemsPoJo;

public class CustomerPoJo {
	
	private String username;
	private String password;	
	private int customerID;
	private float wallet;
	
	//ItemsPojo item;
	private List<ItemsPoJo> inventory = new ArrayList<ItemsPoJo>();
	
	public CustomerPoJo()
	{
		super();
	}
	
	public CustomerPoJo(String username, String password, int customerID, float wallet, List<ItemsPoJo> inventory)
	{
		this.username = username;
		this.password = password;
		this.customerID = customerID;
		this.wallet = wallet;
		this.inventory = inventory;
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

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public float getWallet() {
		return wallet;
	}

	public void setWallet(float wallet) {
		this.wallet = wallet;
	}

	public List<ItemsPoJo> getInventory() {
		return inventory;
	}

	public void setInventory(List<ItemsPoJo> inventory) {
		this.inventory = inventory;
	}
}
