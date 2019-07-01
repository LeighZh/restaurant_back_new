package com.znsd.restaurant.dao.impl.series;

import com.znsd.restaurant.bean.series.VegetableTypeBean;
import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.series.CookingDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CookingDaoImpl implements CookingDao {
	@Override
	public String add(String name) {
		Connection con=DBUtils.getConnection();
		String sql="insert into mealseries(seriesName) values(?)";
		PreparedStatement pre=null;
		try {
			pre=con.prepareStatement(sql);
			pre.setString(1,name);
			pre.execute();
			return "true";
		} catch (SQLException e) {
			System.out.println("6666666");
			return "菜系名称重复";
		}
		
	}

    @Override
    public boolean update(int id, String name) {
        Connection connection=DBUtils.getConnection();
        String sql="update mealseries set seriesName = ? where seriesId = ?";
        PreparedStatement prepare = null;
        boolean res = false;
        try {
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, name);
            prepare.setInt(2, id);
            prepare.execute();
            res = true;
        } catch (SQLException e){
            e.printStackTrace();
            res = false;
        }
        return res;
    }

    @Override
	public void delete(String name) {
		// TODO Auto-generated method stub
		Connection con=DBUtils.getConnection();
		String 	sql="DELETE FROM mealseries WHERE seriesId= ? ";
		PreparedStatement pre=null;
		try {
			pre=con.prepareStatement(sql);
			pre.setString(1,name);
			pre.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<VegetableTypeBean> query() {
		// TODO Auto-generated method stub
		Connection con=DBUtils.getConnection();
		String sql="SELECT seriesID,seriesName FROM mealseries";
		PreparedStatement pre=null;
		List<VegetableTypeBean> list=new ArrayList<VegetableTypeBean>();
		try {
			pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				list.add(new VegetableTypeBean(res.getInt(1),res.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("asd");
		}
		return list;
	}

	@Override
	public void mod(String name) {
		// TODO Auto-generated method stub
		
	}
		
}
