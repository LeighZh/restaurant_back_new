package com.znsd.restaurant.servers.impl;

import com.znsd.restaurant.bean.meal.MenuBean;
import com.znsd.restaurant.dao.OrderdishesMenuDao;
import com.znsd.restaurant.dao.impl.OrderdishesMenuDaoImpl;
import com.znsd.restaurant.servers.OrderdishesMenuService;

import java.util.List;

public class OrderdishesMenuImpl implements OrderdishesMenuService{

	 
	OrderdishesMenuDao omd=new OrderdishesMenuDaoImpl();
	
	public List<MenuBean> getSelectMenu(int id,String user){
		 
		return omd.getSelectMenu(id,user);
	}
	
	public void  evaluate(String userId,String menuName,String evaluateStatus,String text){
			omd.evaluate(userId, menuName, evaluateStatus,text);
			
	}
	
}
