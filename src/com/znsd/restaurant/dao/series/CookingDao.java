package com.znsd.restaurant.dao.series;

import com.znsd.restaurant.bean.series.VegetableTypeBean;

import java.util.List;

public interface CookingDao {
	public String add(String name);
	public boolean update(int id,String name);
	public void delete(String name);
	public List<VegetableTypeBean> query();
	public void mod(String name);
}
