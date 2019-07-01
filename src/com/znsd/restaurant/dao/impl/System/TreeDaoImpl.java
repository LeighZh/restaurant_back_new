package com.znsd.restaurant.dao.impl.System;

import com.znsd.restaurant.bean.system.AuthorityBean;
import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.System.TreeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TreeDaoImpl implements TreeDao{

	@Override
	public List<AuthorityBean> queryAuthority(String name) {
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		ResultSet query = null;
		try {
			prepare = connection.prepareStatement("SELECT text FROM authority where text != ?");
			prepare.setString(1, name);
			query = prepare.executeQuery();
			List<AuthorityBean> list = new ArrayList<AuthorityBean>();
			while(query.next()){
				list.add(new AuthorityBean(query.getString(1)));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(query!=null)
					query.close();
				if(prepare!=null)
					prepare.close();
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
