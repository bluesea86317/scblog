package com.sc.auth.core;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sc.auth.core.transaction.Env;

public class JDBCDaoSupport {
	
	private SqlMapClient sqlMapClient;
	
	public JDBCDaoTemplate getJdbcDaoTemplate() {
//		从threadLocal对象中获取sqlMapClient
		return new JDBCDaoTemplate(Env.sqlMapClientLocal.get());
	}


	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}


	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
}
