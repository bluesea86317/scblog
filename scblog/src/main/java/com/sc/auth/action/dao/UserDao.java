package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sc.auth.core.JDBCDaoSupport;
import com.sc.auth.exception.DataSourceInitException;
import com.sc.auth.vo.BaseUser;

public class UserDao extends SqlMapClientDaoSupport {
	
	public static UserDao getInstance(){
		return new UserDao();
	}
	
	/**
	 * 查询用户，用来验证用户名，密码是否正确
	 * @param userName
	 * @param password
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws DataSourceInitException 
	 * @throws SQLException 
	 */
	public BaseUser getUser(String userName, String password) throws SQLException{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		BaseUser baseUser;
		paramMap.put("userName", userName);
		paramMap.put("password", password);
		baseUser = (BaseUser)getSqlMapClientTemplate().queryForObject("User.getUser", paramMap);
		return baseUser;
	}
	
}
