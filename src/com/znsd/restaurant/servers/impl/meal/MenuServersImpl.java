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
		return menuDao.select(line, pageSize, first);
	}

	@Override
	public String add(MenuBean ben) {
		// TODO Auto-generated method stub
		return menuDao.add(ben);
	}

	@Override
	public void delete(String name) {
		menuDao.delete(name);
	}

	@Override
	public List<MenuBean> query(MenuBean menu) {
		// TODO Auto-generated method stub
		System.out.println("service!!!!!!!!!!!!!!!!!!!!!!!");
		return menuDao.query(menu);
	}

	@Override
	public void mod(String name,int id) {
		// TODO Auto-generated method stub
		menuDao.mod(name, id);
	}

	public int count() {
		return menuDao.count();
	}

	public int getCount(){
		return menuDao.count();
	}

}
