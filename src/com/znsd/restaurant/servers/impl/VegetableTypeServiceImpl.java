package com.znsd.restaurant.servers.impl;

import com.znsd.restaurant.dao.impl.VegetableTypeDaoImpl;
import com.znsd.restaurant.servers.VegetableTypeService;

public class VegetableTypeServiceImpl implements VegetableTypeService{

	@Override
	public String select(String line, String pageSize, int first) {
		// TODO Auto-generated method stub
		return new VegetableTypeDaoImpl().select(line, pageSize, first);
	}

	public int count() {
		return new VegetableTypeDaoImpl().count();
	}

}
