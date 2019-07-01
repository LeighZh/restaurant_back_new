package com.znsd.restaurant.dao.impl;

import com.znsd.restaurant.bean.system.AuthorityBean;
import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.FuncTionDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FuncTionDaoImpl extends DBUtils implements FuncTionDao {

	@Override
	public ArrayList<AuthorityBean> select(String name) {
		Connection con = getConnection();
		Statement cr= null;
		 ResultSet ex = null;
		 //System.out.println(name);
		 String sql="SELECT * FROM authority WHERE authorityid IN(SELECT authority FROM role_authority WHERE roleid=(SELECT roleid FROM user_role WHERE userid=(SELECT userid FROM USER WHERE userName="+name+")))";
		 //System.out.println(sql);
		 String iSsql="SELECT roleName FROM role WHERE roleid=(SELECT roleid FROM user_role WHERE userid=(SELECT userid FROM USER WHERE userName="+name+"))";
		 ArrayList<AuthorityBean> arr= new ArrayList<AuthorityBean>();
		// System.out.println(iSsql);
		 try {
			 cr = con.createStatement();
			 ex = cr.executeQuery(iSsql);
			 ex.next();
			 String user=ex.getString(1);
			 System.out.println(user);
			 ex = cr.executeQuery(sql);
			 while(ex.next()){
				 arr.add(new AuthorityBean(ex.getString(2),ex.getString(3),"icon-save",user));
			 }
			 
			 return arr;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ex!=null)
					ex.close();
				if(cr!=null)
					cr.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
