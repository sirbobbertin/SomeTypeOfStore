package dao;

import java.util.List;

import exception.ApplicationException;
import pojo.ItemsPoJo;
import pojo.OfferPoJo;

public interface EmployeDao {
	
    boolean employeLogin(String user, String password) throws ApplicationException;
	ItemsPoJo addItem(ItemsPoJo itemsPoJo) throws ApplicationException;
	List<ItemsPoJo> getAllItems() throws ApplicationException;
	List<OfferPoJo> getOffers()throws ApplicationException;
	boolean decideOffers(String name,int choice,int id) throws ApplicationException;
	boolean deleteItems(int id,String name) throws ApplicationException;

}
