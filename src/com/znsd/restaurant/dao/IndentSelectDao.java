package com.znsd.restaurant.dao;

public interface IndentSelectDao {
	public String select(String line, String pageSize, int first);
	public int count();
}
