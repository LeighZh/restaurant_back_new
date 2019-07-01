package com.znsd.restaurant.dao.impl;

import com.znsd.restaurant.bean.meal.MenuBean;
import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.OrderdishesMenuDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderdishesMenuDaoImpl extends DBUtils implements OrderdishesMenuDao{
  
	public List<MenuBean> getSelectMenu(int id,String user) {
 
		Connection conn=super.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select *from menu where menuId=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
	ps=conn.prepareStatement("insert into indent (indentName,userName,menuName,indentTime,status,money,sign) values(1,?,?,Now(),'未结账',?,'正常')");
	ps.setString(1,user);
	ps.setString(2,rs.getString("menuName"));
	ps.setDouble(3,rs.getDouble("price"));
			 ps.execute();
					
			}
			
  
			  
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public void  evaluate(String userId,String menuName,String evaluateStatus,String text){
		
		Object[] obj={userId,menuName,evaluateStatus,text};
		super.executeS("insert into evaluate (userId,menuName,evaluateTime,evaluateStatus,evaluateContent) values(?,?,Now(),?,?)",obj);
		
		Object [] obj1={userId,menuName};
		super.executeS("update indent set status='已评论'  where userName=? and menuName=?",obj1);
		 
		
		
		
	}
	 
	
	
}
