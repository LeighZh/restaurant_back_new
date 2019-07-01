package com.znsd.restaurant.dao.impl;

import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.EvaluateDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EvaluateDaoImp extends DBUtils implements EvaluateDao{

	@Override
	public String select(String line, String pageSize, int first) {
		Connection con = getConnection();
		PreparedStatement pr =null;
		ResultSet ex = null;
		//String[] st = line.split(",");//列表每列
		String tab = "[";
		 try {
			 pr= con.prepareStatement("SELECT menuName,evaluateContent,evaluateTime FROM evaluate  ORDER BY evaluateTime LIMIT ?,?");
			 pr.setObject(1, first);
			 pr.setObject(2, Integer.parseInt(pageSize));
			 ex = pr.executeQuery();
			 while(ex.next()){
				tab+="{\"评价菜谱名\":\""+ex.getObject(1)+"\",\"评价内容\":\""+ex.getObject(2)+"\",\"评价时间\":\""+ex.getObject(3)+"\"},";
			 } 
			 tab = tab.substring(0,tab.length()-1);
			 tab+="]";
			 return tab;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(ex!=null)
					ex.close();
				if(pr!=null)
					pr.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
}
