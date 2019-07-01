package com.znsd.restaurant.dao.impl;

import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.IndentSelectDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndextSelectDaoImpl extends DBUtils implements IndentSelectDao{

	@Override
	public String select(String line,String pageSize,int first) {
		
		Connection con = getConnection();
		PreparedStatement pr =null;
		ResultSet ex = null;
		String[] st = line.split(",");//列表每列
		String tab = "[";
		 try {
			 pr= con.prepareStatement("SELECT indentName,indentTime,menuName,status FROM indent where sign='正常' order by indentTime LIMIT ?,? ");
			 pr.setObject(1, first);
			 pr.setObject(2, Integer.parseInt(pageSize));
			 ex = pr.executeQuery();
			 while(ex.next()){
				tab+="{\""+st[0]+"\":\""+ex.getString(1)+"\",\""+st[1]+"\":\""+ex.getTimestamp(2)+"\",\""+st[2]+"\":\""+ex.getString(3)+"\",\""+st[3]+"\":\""+ex.getString(4)+"\"},";
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
			 pr= con.prepareStatement("SELECT count(1) FROM indent where sign='正常'");
			 ex = pr.executeQuery();
			 ex.next();
			 return ex.getInt(1);
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
		return 0;
	}

}
