package com.sc.auth.core;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.transaction.TransactionConfig;
import com.ibatis.sqlmap.engine.transaction.TransactionManager;
import com.ibatis.sqlmap.engine.transaction.jdbc.JdbcTransactionConfig;
import com.sc.auth.exception.DataSourceInitException;

public class JDBCDaoSupport {
	
private static SqlMapClient sqlMapClient;
	
	private DaoTemplate jdbcDaoTemplate;
	
	private static Reader reader;
	
	private InvocationHandler transactionStartInvocationHandler;
	

	public DaoTemplate getJdbcDaoTemplate() {
		try {
			if(sqlMapClient == null){
				reader = Resources
				.getResourceAsReader("sqlMapConfig.xml");
				sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
				TransactionConfig config = new JdbcTransactionConfig();
				config.setDataSource(DataSourceFactory.getDataSource());
				SqlMapClientImpl sqlMapClientImpl = (SqlMapClientImpl)sqlMapClient;
				sqlMapClientImpl.getDelegate().setTxManager(new TransactionManager(config));
				reader.close();
			}
			jdbcDaoTemplate = new JDBCDaoTemplate(sqlMapClient);
			transactionStartInvocationHandler = new TransactionStartInvocationHandler(jdbcDaoTemplate,sqlMapClient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataSourceInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (DaoTemplate)(Proxy.newProxyInstance(jdbcDaoTemplate.getClass().getClassLoader(), jdbcDaoTemplate.getClass().getInterfaces(), transactionStartInvocationHandler));
	}
	
}
