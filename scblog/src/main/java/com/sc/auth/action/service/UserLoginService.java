package com.sc.auth.action.service;

import java.sql.SQLException;

import com.sc.auth.action.dao.UserDao;
import com.sc.auth.vo.BaseUser;

public class UserLoginService {

	public UserDao getUserDao() {
		return new UserDao();
	}
	
	public BaseUser getUser(String userName, String password) throws SQLException{
		return getUserDao().getUser(userName, password);
	}
	
	public BaseUser getBaseUser(String userName, String password) throws SQLException{
		return new BaseUser();
	}
	
}
