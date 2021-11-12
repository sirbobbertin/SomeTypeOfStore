package dao;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.security.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.ApplicationException;
import passwordHashing.BCrypt;
//import pojo.EmployePoJo;
import pojo.ItemsPoJo;
import pojo.OfferPoJo;



public class EmployeDaoImpl implements EmployeDao {
	
	private static final Logger logger = LogManager.getLogger(EmployeDaoImpl.class);

	public ItemsPoJo addItem(ItemsPoJo itemsPojo) throws ApplicationException {
		
		//logger.info("Entered addItem() in dao.");
		
		itemsPojo.setRemoved(false);
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "insert into item_details(item_name, item_price, removed, bought)" 
							+ "values('"+itemsPojo.getName()+"',"+itemsPojo.getPrice() +","+itemsPojo.isRemoved()
							+","+itemsPojo.isBought()+")"; //UPDATE CODE
			
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		
		//logger.info("Exited addItem() in dao.");
		return itemsPojo;
	}
	
	public boolean deleteItems(int id,String name)throws ApplicationException  {
		//logger.info("Entered deleteItems() in dao.");
		Connection conn = DBUtil.makeConnection();
		int rowsAffected = 0;
		int employeeIDReceived = 0;
		int tableID = 0;
		try {
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();

			String query = "DELETE FROM item_details WHERE item_id = "+id;
			String employeeID="Select employee_id from employee_details where name = '"+name+"'";
			
			
			ResultSet receivedID = stmt.executeQuery(employeeID);
			rowsAffected = stmt2.executeUpdate(query);
			if(receivedID.next())
				tableID = receivedID.getInt(1);
			
			String query2 = "UPDATE offer_details SET offer_status = 'REMOVED', employee_id = "+tableID+" WHERE item_id ="+id;
			 employeeIDReceived = stmt.executeUpdate(query2);
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		if(rowsAffected == 0)
			{logger.info("Entered deleteItems() in dao.");return false;}
		else
		{	logger.info("Exited deleteItems() in dao.");return true;}
	}

	
	public List<ItemsPoJo> getAllItems()throws ApplicationException 
	{
		//logger.info("Entered getAllItems() in dao.");
		List<ItemsPoJo> allItems = new ArrayList<ItemsPoJo>();
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		try {
			 stmt = conn.createStatement();
			 String query = "select * from item_details where removed=false";
			 ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				ItemsPoJo itemsPoJo = new ItemsPoJo(rs.getString(1),rs.getFloat(2),
													rs.getInt(3),rs.getBoolean(4),
													rs.getBoolean(5));
				allItems.add(itemsPoJo);
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		//logger.info("Exited getAllItems() in dao.");
		return allItems;
	}
	
	public List<OfferPoJo> getOffers()throws ApplicationException
	{
		//logger.info("Entered getOffers() in dao.");
		List<OfferPoJo> allOffers = new ArrayList<OfferPoJo>();
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		try {
			 stmt = conn.createStatement();
			 String query = "select * from offer_details";
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
	
	public boolean employeLogin(String user, String password)throws ApplicationException 
	{
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		try {
			 stmt = conn.createStatement();
			// String genPassword = BCrypt.hashpw(password, BCrypt.gensalt(5));
			 String query = "select name, password from employee_details where name = '" + user + "' and password = '" + password +"'";
			 ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
	}

	public boolean decideOffers(String name,int choice,int id) throws ApplicationException {

		//logger.info("Entered decideOffers() in dao");
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		Statement stmt2;
		Statement stmt3;
		Statement stmt4;
		boolean flag = false;
		try {
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			stmt4 = conn.createStatement();
			if(choice == 1)
			{
				String employee = "SELECT employee_id from employee_details where name = '"+name+"'";
				ResultSet rs = stmt4.executeQuery(employee);
				rs.next();
				String query = "UPDATE offer_details SET offer_status = 'REMOVED', employee_id ="+rs.getInt(1)+" where offer_id = "+id;
				stmt.executeUpdate(query);
				return flag = true;
			}
			else if(choice == 2)
			{
				String employee = "SELECT employee_id from employee_details where name = '"+name+"'";
				ResultSet rs2 = stmt4.executeQuery(employee);
				rs2.next();
				String query = "UPDATE offer_details SET offer_status = 'ACCEPTED', employee_id ="+rs2.getInt(1)+" where offer_id = "+id;
				stmt.executeUpdate(query);
				String receive ="Select item_name, item_price, name from offer_details where offer_id = "+id;
				ResultSet rs3 = stmt2.executeQuery(receive);
				rs3.next();
				String insert = "INSERT into payment_details(name,item_name,payment_remaining,purchased_date) values ('"+rs3.getString(3)+"','"+rs3.getString(1)+"', "+rs3.getFloat(2)+", NOW() )";
				stmt3.executeUpdate(insert);
				//String query2 = "UPDATE offer_details SET offer_status = 'REMOVED', employee_id ="+rs2.getInt(1)+" where offer_id = "+id;

				return flag = true;
			}
		}catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		//logger.info("Exited decideOffers() in dao");
		return flag;
	}

}
