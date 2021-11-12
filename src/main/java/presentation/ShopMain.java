package presentation;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Iterator;
//import java.util.logging.*;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.ApplicationException;
import pojo.EmployePoJo;
import pojo.ItemsPoJo;
import pojo.OfferPoJo;
import dao.DBUtil;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import service.CustomerService;
import service.CustomerServiceImpl;

public class ShopMain {

	private static final Logger logger = LogManager.getLogger(ShopMain.class);

	public static void main(String[] args) {
		logger.info("Book Management System Application started...");

		Scanner userInput = new Scanner(System.in);
		String username = "";
		String password = "";
		String passCon = "";
		boolean resume = true;
		int option = 0;

		EmployeeService employeServ = new EmployeeServiceImpl();
		CustomerService customerServ = new CustomerServiceImpl();
		ItemsPoJo itemsPoJo = new ItemsPoJo();

		while (resume) {
			
			System.out.println("**********************************");
			System.out.println("Welcome to Some Kind of Shop App");
			System.out.println("**********************************");
			System.out.println("1. Employee Login ");
			System.out.println("2. Customer Login ");
			System.out.println("3. Regerister Account ");
			System.out.println("4. List all Items.");
			System.out.println("5. Exit.");
			System.out.println("**********************************");
			System.out.print("Enter your option:");
			option = Integer.parseInt(userInput.nextLine());
			System.out.println("**********************************");

			// Connection conn = DBUtil.makeConnection();

			switch (option) {

			case 1:
				System.out.println("Welcome back Employee!");
				System.out.print("Enter your username : ");
				username = userInput.nextLine();
				System.out.print("Enter your password : ");
				password = userInput.nextLine();

				try {
					if (employeServ.employeLogin(username, password) == true) {
						try {
							employeeMenu(username, employeServ, itemsPoJo);
						} catch (ApplicationException e) {
							System.out.println("**********************************");
							System.out.println("Sorry!! There is some issue with the database...");
							System.out.println("Please try after sometime....");
							System.out.println("**********************************");
							logger.error(e.getMessage());
						}
					} else
						System.out.println("Either incorrect Username or Password. Please try again");

				} catch (ApplicationException e) {
					System.out.println("**********************************");
					System.out.println("Sorry!! There is some issue with the database...");
					System.out.println("Please try after sometime....");
					System.out.println("**********************************");
					logger.error(e.getMessage());
				}
				break;
			case 2:
				System.out.println("Welcome valued Customer!");
				System.out.print("Enter your username : ");
				username = userInput.nextLine();
				System.out.print("Enter your password : ");
				password = userInput.nextLine();

				try {
					if(customerServ.customerLogin(username, password) == true) {
						try {
							customerMenu(username, customerServ, itemsPoJo);
						} catch (ApplicationException e) {
							System.out.println("**********************************");
							System.out.println("Sorry!! There is some issue with the database...");
							System.out.println("Please try after sometime....");
							System.out.println("**********************************");
							logger.error(e.getMessage());
						}
					} else
						System.out.println("Either incorrect Username or Password. Please try again");
					
				} catch (ApplicationException e) {
					System.out.println("**********************************");
					System.out.println("Sorry!! There is some issue with the database...");
					System.out.println("Please try after sometime....");
					System.out.println("**********************************");
					logger.error(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Welcome!");
				System.out.println("Enter your username : ");
				username = userInput.nextLine();
				System.out.print("Enter your password : ");
				password = userInput.nextLine();
				System.out.print("Re-Enter your password : ");
				passCon = userInput.nextLine();
				System.out.println(password+" "+passCon);
				if(password.equals(passCon)==true)
				{
					try {
						if(customerServ.makeAccount(username, password)==true)
							System.out.println("Account successfully made!" +username+", Please login to continue");
						else
							System.out.println("There's already an account with that username. Please choose a new one.");
					}catch (ApplicationException e) {
						System.out.println("**********************************");
						System.out.println("Sorry!! There is some issue with the database...");
						System.out.println("Please try after sometime....");
						System.out.println("**********************************");
						logger.error(e.getMessage());
					}
				}
				else
				{
					System.out.println("Password not the same. Please re-enter password");
				}
				break;
			case 4:
				List<ItemsPoJo> listItems;
				try {
					listItems = customerServ.getAllItems();
				}catch (ApplicationException e) {
					System.out.println("**********************************");
					System.out.println("Sorry!! There is some issue with the database...");
					System.out.println("Please try after sometime....");
					System.out.println("**********************************");
					logger.error(e.getMessage());
					break;
				}
				Iterator<ItemsPoJo> itr2 = listItems.iterator();		
				System.out.println("Item Name\tItem Price\t\tItem ID\t\"");
				System.out.println("*************************************************************");
				while(itr2.hasNext())
				{
					ItemsPoJo myItems = itr2.next();
					System.out.println("");
					System.out.print(myItems.getName()+"\t\t"+myItems.getPrice() +"\t\t"+myItems.getId()+"\t"+myItems.isRemoved()
									+"\t"+myItems.isBought()+"\t");
				}
				break;
			case 5:
				System.exit(0);
			}

		}

	}

	public static void employeeMenu(String user, EmployeeService employeServ, ItemsPoJo itemsPoJo)
			throws ApplicationException {
		ItemsPoJo addItems = new ItemsPoJo();
		EmployePoJo info = new EmployePoJo();
		Scanner userInput = new Scanner(System.in);
		int option = 0;
		int id = 0;
		String name;
		float price;
		// Connection conn = DBUtil.makeConnection();
		// Statement stmt;
while(true) {
		System.out.println("**********************************");
		System.out.println("Welcome Back, " + user + "!");
		System.out.println("**********************************");
		System.out.println("1. Add Item ");
		System.out.println("2. Remove Item ");
		System.out.println("3. List Pending Offers");
		System.out.println("4. Decide On Offers");
		System.out.println("5. List Items");
		System.out.println("6. Exit.");
		System.out.println("**********************************");
		System.out.print("Enter your option:");
		option = Integer.parseInt(userInput.nextLine());

		
		switch (option) {
		case 1:
			System.out.println("**********************************");
			System.out.print("Enter name of Item : ");
			name = userInput.nextLine();
			System.out.print("Enter price of Item : ");
			price = Integer.parseInt(userInput.nextLine());
			addItems = new ItemsPoJo(name, price, 0, false, false);
			try {
				employeServ.addItem(addItems);
				System.out.println("Successfully added a item!");
				break;
			} catch (ApplicationException e) {
				System.out.println("**********************************");
				System.out.println("Sorry!! There is some issue with the database...");
				System.out.println("Please try after sometime....");
				System.out.println("**********************************");
				logger.error(e.getMessage());
				break;
			}
		case 2:
			System.out.println("**********************************");
			System.out.print("Enter ID of item to be removed: ");
			id = Integer.parseInt(userInput.nextLine());
			try {
				if(employeServ.deleteItems(id,user)==true)
				{
					System.out.println("Successfully removed Item!");
					break;
				}
				else
					System.out.println("Please re-enter another ID.");
			}catch (ApplicationException e) {
				System.out.println("**********************************");
				System.out.println("Sorry!! There is some issue with the database...");
				System.out.println("Please try after sometime....");
				System.out.println("**********************************");
				logger.error(e.getMessage());
				break;
			}
			break;
		case 3:
			List<OfferPoJo> listOffers;			
			try{
				listOffers = employeServ.getOffers();
			}catch (ApplicationException e) {
				System.out.println("**********************************");
				System.out.println("Sorry!! There is some issue with the database...");
				System.out.println("Please try after sometime....");
				System.out.println("**********************************");
				logger.error(e.getMessage());
				break;
			}
			Iterator<OfferPoJo> itr = listOffers.iterator();		
			System.out.println("Offer ID\tItem ID\t\tItem Name\t\tItem Price"
					+ "\t\tBuyer\t\tBuyer ID\t\tOffer Status\t\tEmployee ID\t\t");
			System.out.println("*****************************************************************************************");
			while(itr.hasNext())
			{
				OfferPoJo myOffers = itr.next();
				System.out.println("");
				System.out.print(myOffers.getOfferID()+"\t"+myOffers.getItemID() +"\t\t"+myOffers.getItemName()+"\t"+myOffers.getName()
								+"\t"+myOffers.getId()+"\t"+myOffers.getOfferStatus()+"\t");
			}
			break;
		case 4:
			System.out.println("**********************************");
			System.out.println("1. Reject Offer");
			System.out.println("2. Accept Offer");
			System.out.println("**********************************");
			System.out.print("Enter option : ");
			int choice = Integer.parseInt(userInput.nextLine());
			if(choice == 1)
			{
				System.out.print("Enter offer ID to be removed : ");
				id = Integer.parseInt(userInput.nextLine());
			}
			else if(choice == 2)
			{
				System.out.print("Enter offer ID to be Accepted : ");
				id = Integer.parseInt(userInput.nextLine());
			}
			try {
				if(employeServ.decideOffers(user,choice,id)==true)
				{
					System.out.println("Offer ID "+id+" Has been updated!");
					break;
				}
				else
					System.out.println("Offer is either bought or ID is invalid. Please try re-entering another Offer ID...");
				break;
			}catch (ApplicationException e) {
				System.out.println("**********************************");
				System.out.println("Sorry!! There is some issue with the database...");
				System.out.println("Please try after sometime....");
				System.out.println("**********************************");
				logger.error(e.getMessage());
				break;
			}
		case 5:
			List<ItemsPoJo> listItems;
			try {
				listItems = employeServ.getAllItems();
			}catch (ApplicationException e) {
				System.out.println("**********************************");
				System.out.println("Sorry!! There is some issue with the database...");
				System.out.println("Please try after sometime....");
				System.out.println("**********************************");
				logger.error(e.getMessage());
				break;
			}
			Iterator<ItemsPoJo> itr2 = listItems.iterator();		
			System.out.println("Item Name\t\t\tItem Price\t\tItem ID\t\tItem Price"
					+ "\t\tBuyer\t\tBuyer ID\t\tOffer Status\t\tEmployee ID\t\t");
			System.out.println("*****************************************************************************************");
			while(itr2.hasNext())
			{
				ItemsPoJo myItems = itr2.next();
				System.out.println("");
				System.out.print(myItems.getName()+"\t\t\t"+myItems.getPrice() +"\t\t"+myItems.getId()+"\t"+myItems.isRemoved()
								+"\t"+myItems.isBought()+"\t");
			}
		case 6:
			System.exit(0);
		}
		
		}
		
	}

	public static void customerMenu(String user, CustomerService customerServ, ItemsPoJo itemsPoJo)
			throws ApplicationException {
		ItemsPoJo offer = new ItemsPoJo();
		Scanner userInput = new Scanner(System.in);
		String name;
		//List<OfferPoJo> listOffers;
		int option = 0;
		int id;

		while (true) {
		System.out.println("**********************************");
		System.out.println("Welcome Back, " + user + "!");
		System.out.println("**********************************");
		System.out.println("1. Make Offer ");
		System.out.println("2. Remove Offer ");
		System.out.println("3. List Pending Offers");
		System.out.println("4. List all Items.");
		System.out.println("5. Show inventory");
		System.out.println("6. Exit.");
		System.out.println("**********************************");
		System.out.print("Enter your option:");
		option = Integer.parseInt(userInput.nextLine());

		switch (option) {
		case 1:
			System.out.println("**********************************");
			System.out.print("Enter ID of Item : ");
			id = Integer.parseInt(userInput.nextLine());
			offer=customerServ.getItem(id);
			try {
				if(customerServ.makeOffer(offer,user)==true)
				{
					System.out.println("An offer has been made for offer id: "+id);
					break;
				}
				else
					System.out.println("That offer either has been taken, or doesn't exist...Please enter another ID");
			} catch (ApplicationException e) {
				System.out.println("**********************************");
				System.out.println("Sorry!! There is some issue with the database...");
				System.out.println("Please try after sometime....");
				System.out.println("**********************************");
				logger.error(e.getMessage());
				break;
			}
		case 2:
			System.out.println("**********************************");
			System.out.print("Enter ID of Offer made : ");
			id = Integer.parseInt(userInput.nextLine());
			try {
				if (customerServ.removeOffer(id,user) == true)
					{
					 System.out.println("Successfully removed offer!");
					 break;
					}
				else
					System.out.println("That offer isn't listed in your current offers.");
			} catch (ApplicationException e) {
				System.out.println("**********************************");
				System.out.println("Sorry!! There is some issue with the database...");
				System.out.println("Please try after sometime....");
				System.out.println("**********************************");
				logger.error(e.getMessage());
				break;
			}
		case 3:			
			List<OfferPoJo> listOffers;			
			try{
				listOffers = customerServ.getOffers(user);
			}catch (ApplicationException e) {
				System.out.println("**********************************");
				System.out.println("Sorry!! There is some issue with the database...");
				System.out.println("Please try after sometime....");
				System.out.println("**********************************");
				logger.error(e.getMessage());
				break;
			}
			Iterator<OfferPoJo> itr = listOffers.iterator();		
			System.out.println("Offer ID\tItem ID\t\tItem Name\t\tItem Price"
					+ "\t\tBuyer\t\tBuyer ID\t\tOffer Status\t\tEmployee ID\t\t");
			System.out.println("*****************************************************************************************");
			while(itr.hasNext())
			{
				OfferPoJo myOffers = itr.next();
				System.out.println("");
				System.out.print(myOffers.getOfferID()+"\t"+myOffers.getItemID() +"\t\t"+myOffers.getItemName()+"\t"+myOffers.getName()
								+"\t"+myOffers.getId()+"\t"+myOffers.getOfferStatus()+"\t");
			}
			break;
		case 4:
			List<ItemsPoJo> listItems;
			try {
				listItems = customerServ.getAllItems();
			}catch (ApplicationException e) {
				System.out.println("**********************************");
				System.out.println("Sorry!! There is some issue with the database...");
				System.out.println("Please try after sometime....");
				System.out.println("**********************************");
				logger.error(e.getMessage());
				break;
			}
			Iterator<ItemsPoJo> itr2 = listItems.iterator();		
			System.out.println("Item Name\tItem Price\t\tItem ID\t\tItem Price");
			System.out.println("*****************************************************************************************");
			while(itr2.hasNext())
			{
				ItemsPoJo myItems = itr2.next();
				System.out.println("");
				System.out.print(myItems.getName()+"\t\t"+myItems.getPrice() +"\t\t"+myItems.getId()+"\t"+myItems.getPrice());
			}
			break;
		case 5:
			List<OfferPoJo> listInventory;
			try {
				listInventory = customerServ.getInventory(user);
			}catch (ApplicationException e) {
				System.out.println("**********************************");
				System.out.println("Sorry!! There is some issue with the database...");
				System.out.println("Please try after sometime....");
				System.out.println("**********************************");
				logger.error(e.getMessage());
				break;			
		}
			Iterator<OfferPoJo> itr3 = listInventory.iterator();		
			System.out.println("Offer ID \tItem ID\t\tItem Name\t\tItem Price");
			System.out.println("*****************************************************************************************");
			while(itr3.hasNext())
			{
				OfferPoJo myItems = itr3.next();
				System.out.println("");
				System.out.print(myItems.getOfferID()+"\t\t"+myItems.getItemID() +"\t\t"+myItems.getItemName()+"\t"+myItems.getItemPrice());
			}
			break;
		case 6:
			System.exit(0);
	
	}
		}
	
}
	
}
