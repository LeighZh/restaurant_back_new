package com.znsd.restaurant.servers;

import com.znsd.restaurant.bean.VegetableTypeBean;

import java.util.List;

public interface CookingServers {
	public String add(String name);
	public void delete(String name);
	public List<VegetableTypeBean> query();
	public void mod(String name);
}
