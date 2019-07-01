package com.znsd.restaurant.dao;

import com.znsd.restaurant.bean.VegetableTypeBean;

import java.util.List;

public interface CookingDao {
	public String add(String name);
	public void delete(String name);
	public List<VegetableTypeBean> query();
	public void mod(String name);
}
