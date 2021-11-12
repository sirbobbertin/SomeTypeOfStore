package service;

import java.util.List;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import exception.ApplicationException;
import pojo.ItemsPoJo;
import pojo.OfferPoJo;
//import pojo.CustomerPoJo;

public class CustomerServiceImpl implements CustomerService {

	CustomerDao customerDao;
	
	public CustomerServiceImpl() {
		this.customerDao = new CustomerDaoImpl();
	}

	public boolean makeOffer(ItemsPoJo itemsPojo,String customerName) throws ApplicationException {
		return this.customerDao.makeOffer(itemsPojo,customerName);
	}

	public boolean removeOffer(int id,String name) throws ApplicationException {
		return this.customerDao.removeOffer(id,name);
	}

	public List<OfferPoJo> getOffers(String name) throws ApplicationException {
		return this.customerDao.getOffers(name);
	}

	public boolean customerLogin(String user, String password) throws ApplicationException {
		return this.customerDao.customerLogin(user, password);
	}

	public ItemsPoJo getItem(int itemID) throws ApplicationException {
		return this.customerDao.getItem(itemID);
	}

	
	public List<ItemsPoJo> getAllItems() throws ApplicationException {
		return this.customerDao.getAllItems();
	}

	@Override
	public boolean makeAccount(String user, String password) throws ApplicationException {
		return this.customerDao.makeAccount(user, password);
	}

	@Override
	public List<OfferPoJo> getInventory(String name) throws ApplicationException {
		return this.customerDao.getInventory(name);
	}


}

	


