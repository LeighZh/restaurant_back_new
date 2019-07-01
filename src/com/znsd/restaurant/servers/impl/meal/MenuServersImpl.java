package com.znsd.restaurant.servers.impl.meal;

import com.znsd.restaurant.bean.meal.MenuBean;
import com.znsd.restaurant.dao.impl.meal.MenuDaoImpl;
import com.znsd.restaurant.dao.meal.MenuDao;
import com.znsd.restaurant.servers.meal.MenuServers;

import java.util.List;

public class MenuServersImpl implements MenuServers {
	private MenuDao menuDao = new MenuDaoImpl();
	@Override
	public String select(String line, String pageSize, int first) {
		// TODO Auto-generated method stub
		return menu.select(line, pageSize, first);
	}

	private MenuDao menu=new MenuDaoImpl();
	@Override
	public String add(MenuBean ben) {
		// TODO Auto-generated method stub
		return menu.add(ben);
	}

	@Override
	public void delete(String name) {
		menu.delete(name);
	}

	@Override
	public List<MenuBean> query(MenuBean menu) {
		// TODO Auto-generated method stub
		return menuDao.query(menu);
	}

	@Override
	public void mod(String name,int id) {
		// TODO Auto-generated method stub
		menu.mod(name, id);
	}

	public int count() {
		return menu.count();
	}

	public int getCount(){
		return menu.count();
	}


//	public void Typealter(String usName, String id) {
//		menu.Typealter(usName, id);
//	}
}
