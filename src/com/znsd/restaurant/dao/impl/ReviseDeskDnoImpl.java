package com.znsd.restaurant.dao.impl;


import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.ReviseDeskDno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviseDeskDnoImpl extends DBUtils implements ReviseDeskDno{//订桌

	@Override
	public String select(String line, String pageSize, int first) {
		Connection con = getConnection();
		PreparedStatement pr =null;
		ResultSet ex = null;
		String[] st = line.split(",");//列表每列
		String tab = "[";
		 try {
			 pr= con.prepareStatement("SELECT tableid,tableName,tableStatus,line,ROW,username,createTime FROM tablezi where sign='正常' ORDER BY createTime LIMIT ?,?");
			 pr.setObject(1, first);
			 pr.setObject(2, Integer.parseInt(pageSize));
			 ex = pr.executeQuery();
			 while(ex.next()){
				tab+="{\""+st[0]+"\":\""+ex.getObject(1)+"\",\""+st[1]+"\":\""+ex.getObject(2)+"\",\""+st[2]+"\":\""+ex.getObject(3)+"\",\""+st[3]+"\":\""+ex.getObject(4)+"\",\""+st[4]+"\":\""+ex.getObject(5)+"\",\""+st[5]+"\":\""+ex.getObject(6)+"\",\""+st[6]+"\":\""+ex.getObject(7)+"\"},";
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
