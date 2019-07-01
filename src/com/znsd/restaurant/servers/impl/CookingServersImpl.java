package com.znsd.restaurant.servers.impl;

import com.znsd.restaurant.bean.VegetableTypeBean;
import com.znsd.restaurant.dao.CookingDao;
import com.znsd.restaurant.dao.impl.CookingDaoImpl;
import com.znsd.restaurant.servers.CookingServers;

import java.util.List;

public class CookingServersImpl implements CookingServers{
	private CookingDao cook=new CookingDaoImpl();
	@Override
	public String add(String name) {
		// TODO Auto-generated method stub
		return cook.add(name);
	}

	@Override
	public void delete(String name) {
		
		 cook.delete(name);
	}

	@Override
	public List<VegetableTypeBean> query(){
		// TODO Auto-generated method stub
		return cook.query();	
	}

	@Override
	public void mod(String name) {
		// TODO Auto-generated method stub
		
	}

}
