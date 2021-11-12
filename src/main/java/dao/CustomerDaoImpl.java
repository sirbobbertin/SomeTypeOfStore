package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.ApplicationException;
import passwordHashing.BCrypt;
import pojo.ItemsPoJo;
import pojo.OfferPoJo;
import pojo.CustomerPoJo;

public class CustomerDaoImpl implements CustomerDao{
	
	private static final Logger logger = LogManager.getLogger(CustomerDaoImpl.class);
	
	CustomerDao customerDao;
	
	public CustomerDaoImpl() {
		super();
	}

	public boolean makeOffer(ItemsPoJo itemsPojo, String customerName) throws ApplicationException{
		//logger.info("Entered makeOffer() in dao.");
		boolean flag = true;
		int rowsAffected = 0;
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		try {
			 stmt = conn.createStatement();
			 String query1 = "insert into offer_details(item_id,item_name,item_price,name,offer_status)"
			 											+ " values("+ itemsPojo.getId()+", '"+itemsPojo.getName()+"', "+ itemsPojo.getPrice()+", '"
			 											+ customerName +"','PENDING')";								
			  rowsAffected = stmt.executeUpdate(query1);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rowsAffected == 0)
			flag = false;
		//logger.info("Exited makeOffer() in dao.");
		return flag;
	}

	public boolean removeOffer(int id,String name) throws ApplicationException {
		//logger.info("Entered removeOffer() in service.");
		boolean flag = true;
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		int rowsAffected = 0;
		try {
			stmt = conn.createStatement();
			String query = "DELETE FROM offer_details WHERE offer_id = "+id+" AND name = '"+name+"'";
			rowsAffected = stmt.executeUpdate(query);
			//System.out.println(rowsAffected);
			
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		if(rowsAffected == 0)
			flag = false;
		
		//logger.info("Exited removeOffer() in dao.");
		return flag;
	}

	public List<OfferPoJo> getOffers(String name)throws ApplicationException
	{
		//logger.info("Entered getOffers() in dao.");
		List<OfferPoJo> allOffers = new ArrayList<OfferPoJo>();
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		try {
			 stmt = conn.createStatement();
			 String query = "select * from offer_details where name = '"+name+"'";
			 ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				OfferPoJo offerPoJo = new OfferPoJo(rs.getInt(1),rs.getInt(2),
													rs.getString(3),rs.getFloat(4),
													rs.getString(5),rs.getInt(6),rs.getString(7));
				allOffers.add(offerPoJo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//logger.info("Exited getOffers() in dao.");
		return allOffers;
	}

	public boolean makeAccount(String user, String password) throws ApplicationException{
		//logger.info("Entered makeAccount() in dao.");
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		Statement stmt2;
		boolean flag = false;
		int rowAffected = 0;
		//String genPassword = BCrypt.hashpw(password, BCrypt.gensalt(5));
		try {
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			String check = "select name from customer_details where name = '"+user+"'";
			
			
			ResultSet exist = stmt.executeQuery(check);
			if(exist.next())
				return false;
			
			String query = "Insert into customer_details(name,password) values('"+user+"','"+password+"')";
			rowAffected = stmt2.executeUpdate(query);
			
		}catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		return flag;
	}
	
	public boolean customerLogin(String user, String password) throws ApplicationException
	{
		//logger.info("Entered customerLogin() in dao.");
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		boolean flag = false;
		//String genPassword = BCrypt.hashpw(password, BCrypt.gensalt(5));
		try {
			 stmt = conn.createStatement();
			 String query = "select name, password from customer_details where name = '" + user + "' and password = '" + password +"'";
			 ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				return flag = true;
			}
			else
				return flag;
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
	}

	public ItemsPoJo getItem(int itemID) throws ApplicationException {
		//logger.info("Entered getItem in dao.");
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		ItemsPoJo itemsPojo = null;
		try {
			stmt = conn.createStatement();
			String query = "select * from item_details where item_id="+itemID
							+ "and removed=false";
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				itemsPojo = new ItemsPoJo(rs.getString(1),rs.getFloat(2),rs.getInt(3),false,false);
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		//logger.info("Exited getItem() in dao.");
		return itemsPojo;
	}

	public List<OfferPoJo> getInventory(String name) throws ApplicationException{
		//logger.info("Entered getInventory() in dao.");
		List<OfferPoJo> inventory = new ArrayList<OfferPoJo>();
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select offer_id,item_name,item_id,item_price from offer_details where offer_status = 'APPROVED' and name = '"+name+"'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				OfferPoJo offerPojo = new OfferPoJo(rs.getInt(1),rs.getInt(3),rs.getString(2),rs.getFloat(4)," ", 0, null);
				inventory.add(offerPojo);
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		//logger.info("Exited getInventory() in dao.");
		return inventory;
	}
	
	public List<ItemsPoJo> getAllItems()throws ApplicationException 
	{
		List<ItemsPoJo> allItems = new ArrayList<ItemsPoJo>();
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		try {
			 stmt = conn.createStatement();
			 String query = "select item_name,item_price,item_id,removed,bought from item_details where removed=false and bought = false";
			 ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				ItemsPoJo itemsPoJo = new ItemsPoJo(rs.getString(1),rs.getFloat(2),
													rs.getInt(3),rs.getBoolean(4),
													rs.getBoolean(5));
				allItems.add(itemsPoJo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allItems;
	}
}
