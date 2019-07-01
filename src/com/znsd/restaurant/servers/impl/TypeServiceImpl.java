package com.znsd.restaurant.servers.impl;

import com.znsd.restaurant.dao.impl.TypeDaoImpl;
import com.znsd.restaurant.servers.TypeService;

public class TypeServiceImpl implements TypeService{

	@Override
	public String select(String line, String pageSize, int first) {
		// TODO Auto-generated method stub
		return new TypeDaoImpl().select(line, pageSize, first);
	}

}
