package com.znsd.restaurant.dao.series;

public interface VegetableTypeDao {
	public String select(String line, String pageSize, int first);
	public int count();
}
