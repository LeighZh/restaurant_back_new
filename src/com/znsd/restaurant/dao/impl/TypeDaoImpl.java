package com.znsd.restaurant.dao.impl;

import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.TypeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeDaoImpl extends DBUtils implements TypeDao {

	@Override
	public String select(String line, String pageSize, int first) {
		Connection con = getConnection();
		PreparedStatement pr =null;
		ResultSet ex = null;
		String[] st = line.split(",");//列表每列
		String tab = "[";
		 try {
			 pr= con.prepareStatement("SELECT typeName FROM TYPE LIMIT ?,?");
			 pr.setObject(1, first);
			 pr.setObject(2, Integer.parseInt(pageSize));
			 ex = pr.executeQuery();
			 while(ex.next()){
				tab+="{\""+st[0]+"\":\""+ex.getObject(1)+"\"},";
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
