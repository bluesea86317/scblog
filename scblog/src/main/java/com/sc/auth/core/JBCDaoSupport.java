package com.sc.auth.core;

import java.io.IOException;
import java.io.Reader;
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

public class JBCDaoSupport {
	
	private static SqlMapClient sqlMapClient;
	
	private static Reader reader;

	public SqlMapClient getSqlMapClient() throws SQLException{	
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataSourceInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sqlMapClient;
	}
	
	/**
	 * Sql statement of insert
	 * @param sqlMapConfig
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	protected int insert(String sqlMapConfig, Object param) throws SQLException{
		SqlMapClient sqlClient = getSqlMapClient();
//		返回自增主键
		int the_first_autocrement_primaryKey = 0;
		try{
			sqlClient.startTransaction();
			Object object = sqlClient.insert(sqlMapConfig, param);
			if(object != null){
				the_first_autocrement_primaryKey = (Integer)object;
			}		
			sqlClient.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			sqlClient.endTransaction();
		}
		return the_first_autocrement_primaryKey;
		
	}
	
	/**
	 * Sql statement of update
	 * @param sqlMapConfig
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	protected int update(String sqlMapConfig, Object param) throws SQLException{
		SqlMapClient sqlClient = getSqlMapClient();
		int updateCount = 0;
		try{
			sqlClient.startTransaction();
			updateCount = sqlClient.update(sqlMapConfig, param);
			sqlClient.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			sqlClient.endTransaction();
	
		}
		return updateCount;
	}
	
	@SuppressWarnings("rawtypes")
	protected List queryForList(String sqlMapConfig, Object param) throws SQLException{
		List resultList = new ArrayList<Object>();
		SqlMapClient sqlClient = getSqlMapClient();
		try {

			sqlClient.startTransaction();
			resultList = sqlClient.queryForList(sqlMapConfig, param);
			sqlClient.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			sqlClient.endTransaction();

		}
		return resultList;
	}
	
	@SuppressWarnings("rawtypes")
	protected List queryForList(String sqlMapConfig) throws SQLException{
		return queryForList(sqlMapConfig, null);
	}
	
	protected Object queryForObject(String sqlMapConfig, Object param) throws SQLException{
		SqlMapClient sqlClient = getSqlMapClient();
		Object object = null;
		try{
			sqlClient.startTransaction();
			object = sqlClient.queryForObject(sqlMapConfig, param);
			sqlClient.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			sqlClient.endTransaction();
		}
		return object;
	}
	
	protected Object queryForObject(String sqlMapConfig) throws SQLException{
		return queryForObject(sqlMapConfig, null);
	}
	
	protected int delete(String sqlMapConfig, Object param) throws SQLException{
		SqlMapClient sqlClient = getSqlMapClient();
		int deleteCount = 0;
		try{
			sqlClient.startTransaction();
			deleteCount = sqlClient.delete(sqlMapConfig, param);
			sqlClient.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			sqlClient.endTransaction();
		}
		return deleteCount;		
	}

//	protected int delete(String sqlMapConfig) throws SQLException{
//		return getSqlMapClient().delete(sqlMapConfig);
//	}
	
//	private static DataSource getDataSource() throws DataSourceInitException {
//		return DataSourceFactory.getDataSource();
//	}
	
//	private Connection createConnection() throws SQLException, DataSourceInitException{
//		return getDataSource().getConnection();
//	}
}
