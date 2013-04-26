package com.sc.auth.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 动态代理类,为JDBCDaoSupport中的sql操作增加事务管理
 * @author wen_chen
 *
 */
public class TransactionStartInvocationHandler implements InvocationHandler {

	private SqlMapClient sqlMapClient;
	
	private Object target;
	
	public TransactionStartInvocationHandler(Object target, SqlMapClient sqlMapClient){
		this.target = target;
		this.sqlMapClient = sqlMapClient;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object object = new Object();
		try{
			sqlMapClient.startTransaction();
			object = method.invoke(this.target, args);
			sqlMapClient.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			sqlMapClient.endTransaction();
		}
		return object;
	}

}
