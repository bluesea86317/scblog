package com.sc.auth.action.userlogon.service;

import java.sql.SQLException;

import com.sc.auth.action.userlogon.BaseUser;
import com.sc.auth.action.userlogon.dao.UserDao;

public class UserLoginService {

	public UserDao getUserDao() {
		return new UserDao();
	}
	
	public BaseUser getUser(String userName, String password) throws SQLException{
		return getUserDao().getUser(userName, password);
	}
	
	public boolean excuteSql(String sql){
		return getUserDao().excuteSql(sql);
	}

	
}
