package com.znsd.restaurant.servers;

public interface VegetableTypeService {
	public String select(String line, String pageSize, int first);
	public int count();
}
