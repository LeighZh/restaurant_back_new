package com.znsd.restaurant.servers.impl;

import com.znsd.restaurant.dao.impl.RearUserDaoImpl;
import com.znsd.restaurant.servers.RearUserServers;

public class RearUserServersImpl implements  RearUserServers{

	@Override
	public String select(String line, String pageSize, int first) {
		return new RearUserDaoImpl().select(line, pageSize, first);
	}
	
}
