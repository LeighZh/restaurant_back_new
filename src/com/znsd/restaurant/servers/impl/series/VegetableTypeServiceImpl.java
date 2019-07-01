package com.znsd.restaurant.servers.impl.series;

import com.znsd.restaurant.dao.impl.series.VegetableTypeDaoImpl;
import com.znsd.restaurant.servers.series.VegetableTypeService;

public class VegetableTypeServiceImpl implements VegetableTypeService {

	@Override
	public String select(String line, String pageSize, int first) {
		// TODO Auto-generated method stub
		return new VegetableTypeDaoImpl().select(line, pageSize, first);
	}

	public int count() {
		return new VegetableTypeDaoImpl().count();
	}

}
