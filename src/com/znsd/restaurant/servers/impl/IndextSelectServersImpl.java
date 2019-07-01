package com.znsd.restaurant.servers.impl;

import com.znsd.restaurant.dao.impl.IndextSelectDaoImpl;
import com.znsd.restaurant.servers.IndextSelectServers;

public class IndextSelectServersImpl implements  IndextSelectServers {

	@Override
	public String select(String line,String first,int pageSize) {
		return new IndextSelectDaoImpl().select(line,first,pageSize);
	}

	public int count() {
		return new IndextSelectDaoImpl().count();
	}
	
}
