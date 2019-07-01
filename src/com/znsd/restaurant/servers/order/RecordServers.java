package com.znsd.restaurant.servers.order;

import com.znsd.restaurant.bean.order.RecordBean;

import java.util.List;

public interface RecordServers {

	public List<RecordBean> getOrders(RecordBean record);

	public Boolean deleteOrder(int id);
	
	public List<RecordBean> consumptionQuery(int start, int pageSize);

	public void delete(String data);

	public List<RecordBean> likeQuery(int start, int pageSize, String name);
	
	public int getCount();
	
	public int likeCount(String name);

	public void Deskdelete(String record);

	List<RecordBean> main2(String name, String page, String pageSize);

	String count2();

	String count(String name);



}
