package dao;

import java.util.List;

import exception.ApplicationException;
import pojo.CustomerPoJo;
import pojo.ItemsPoJo;
import pojo.OfferPoJo;

public interface CustomerDao {
	
	boolean makeOffer(ItemsPoJo itemsPojo,String customerName) throws ApplicationException;
	boolean removeOffer(int id,String name) throws ApplicationException;
	List<OfferPoJo> getOffers(String name) throws ApplicationException;
	List<ItemsPoJo> getAllItems()throws ApplicationException;
	List<OfferPoJo> getInventory(String name) throws ApplicationException;
	boolean customerLogin(String user, String password) throws ApplicationException;
	boolean makeAccount(String user, String password) throws ApplicationException;
	ItemsPoJo getItem(int itemID) throws ApplicationException;

}
