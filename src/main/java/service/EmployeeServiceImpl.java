package service;

import java.util.List;

import dao.EmployeDao;
import dao.EmployeDaoImpl;
import exception.ApplicationException;
import pojo.ItemsPoJo;
import pojo.OfferPoJo;
import pojo.EmployePoJo;;

public class EmployeeServiceImpl implements EmployeeService{
	
	EmployeDao employDao;
	
	
	public EmployeeServiceImpl() {
		
		this.employDao = new EmployeDaoImpl();
	}
	
	//@Override
    public boolean employeLogin(String user, String password) throws ApplicationException {
		return this.employDao.employeLogin(user, password);
	}
	
	//@Override
	public ItemsPoJo addItem(ItemsPoJo itemsPoJo) throws ApplicationException {
		return this.employDao.addItem(itemsPoJo);
	}
	
	//@Override
	public List<ItemsPoJo> getAllItems()throws ApplicationException
	{
		return this.employDao.getAllItems();
	}
	
	public List<OfferPoJo> getOffers()throws ApplicationException{
		return this.employDao.getOffers();
	}
	//@Override
	public boolean deleteItems(int id,String name)throws ApplicationException
	{
		return this.employDao.deleteItems(id,name);
	}

	
	public boolean decideOffers(String name,int choice,int id) throws ApplicationException {
		return this.employDao.decideOffers(name,choice,id);
	}
}
