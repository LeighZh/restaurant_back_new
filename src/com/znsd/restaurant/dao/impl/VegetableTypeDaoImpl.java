package com.znsd.restaurant.dao.impl;

import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.VegetableTypeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VegetableTypeDaoImpl  extends DBUtils implements VegetableTypeDao {

	@Override
	public String select(String line, String pageSize, int first) {
		Connection con = getConnection();
		PreparedStatement pr =null;
		ResultSet ex = null;
		//String[] st = line.split(",");//列表每列
		String tab = "[";
		 try {
			 pr= con.prepareStatement("SELECT vegetableid,vegetableName,ADDTIME FROM vegetableType where sign='正常' ORDER BY ADDTIME  LIMIT ?,?");
			 pr.setObject(1, first);
			 pr.setObject(2, Integer.parseInt(pageSize));
			 ex = pr.executeQuery();
			 while(ex.next()){
				tab+="{\"vegetableId\":\""+ex.getObject(1)+"\",\"菜系名称\":\""+ex.getObject(2)+"\",\"增加时间\":\""+ex.getObject(3)+"\"},";
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
	public int count() {
		Connection con = getConnection();
		PreparedStatement pr =null;
		ResultSet ex = null;
		 try {
			 pr= con.prepareStatement("SELECT count(1) FROM vegetableType where sign='正常'");
			 ex = pr.executeQuery();
			 ex.next();
			 return ex.getInt(1);
		} catch (SQLException e) {
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
		return 0;
	}

}
