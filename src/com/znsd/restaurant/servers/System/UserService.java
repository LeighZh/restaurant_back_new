package com.znsd.restaurant.servers.System;

import com.znsd.restaurant.bean.order.RecordBean;
import com.znsd.restaurant.bean.system.UserBean;

import java.util.List;

public interface UserService {
	public boolean loginJudge(String name, String password);

    public void resetPassword(String name,String password);

    public void resetUserPassword(String name,String password);

	public boolean updata(UserBean user);

	public boolean delete(int id);

    public boolean insert(UserBean user);

	public UserBean afterRegister(String name, String password);

	public List<UserBean> getUserByLoginName(String name);

	public int getRecordCount(String name);

	public void recordRemove(String record);

	public List<UserBean> afterQuery(UserBean user);
	public List<UserBean> getAdmins(UserBean user);
	
	public List<UserBean> likeAfterQuery(String likeName);
	
	public int afterUserCount();
	
	public int likeAfterCount(String name);
}
