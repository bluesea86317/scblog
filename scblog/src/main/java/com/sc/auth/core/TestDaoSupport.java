package com.sc.auth.core;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.sc.auth.exception.DataSourceInitException;

public class TestDaoSupport {
	
	private SqlMapClient sqlMapClient;
	
	private static Reader reader;

	public SqlMapClient getSqlMapClient() throws SQLException{	
		try {
			if(reader == null){
				reader = Resources
				.getResourceAsReader("sqlMapConfig.xml");
			}
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (IOException e) {
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
		return (Integer)getSqlMapClient().insert(sqlMapConfig, param);
	}
	
	protected int insert(String sqlMapConfig) throws SQLException{
		return (Integer)getSqlMapClient().insert(sqlMapConfig);
	}
	
	/**
	 * Sql statement of update
	 * @param sqlMapConfig
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	protected int update(String sqlMapConfig, Object param) throws SQLException{
		return getSqlMapClient().update(sqlMapConfig, param);
	}
	
	protected int update(String sqlMapConfig) throws SQLException{
		return getSqlMapClient().update(sqlMapConfig);
	}
	
	@SuppressWarnings("rawtypes")
	protected List queryForList(String sqlMapConfig, Object param) throws SQLException{
		List resultList = new ArrayList<Object>();
		SqlMapClient sqlClient = getSqlMapClient();
		Connection conn = null;
		try {
			conn = createConnection();
			sqlClient.setUserConnection(conn);
			//sqlClient.startTransaction();
			resultList = sqlClient.queryForList(sqlMapConfig, param);
			//sqlClient.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSourceInitException e){
			e.printStackTrace();
		}finally{
			//sqlClient.endTransaction();
			sqlClient.getUserConnection().close();
		}
		return resultList;
	}
	
	@SuppressWarnings("rawtypes")
	protected List queryForList(String sqlMapConfig) throws SQLException{
		List resultList = new ArrayList<Object>();
		SqlMapClient sqlClient = getSqlMapClient();
		Connection conn = null;
		try {
			conn = createConnection();
			sqlClient.setUserConnection(conn);
			//sqlClient.startTransaction();
			resultList = sqlClient.queryForList(sqlMapConfig);
			//sqlClient.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSourceInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//sqlClient.endTransaction();
			sqlClient.getUserConnection().close();
		}
		return resultList;
	}
	
	protected Object queryForObject(String sqlMapConfig, Object param) throws SQLException{
		return getSqlMapClient().queryForObject(sqlMapConfig, param);
	}
	
	protected Object queryForObject(String sqlMapConfig) throws SQLException{
		return getSqlMapClient().queryForObject(sqlMapConfig);
	}
	
	protected int delete(String sqlMapConfig, Object param) throws SQLException{
		return getSqlMapClient().delete(sqlMapConfig, param);
	}

	protected int delete(String sqlMapConfig) throws SQLException{
		return getSqlMapClient().delete(sqlMapConfig);
	}
	
	private static DataSource getDataSource() throws DataSourceInitException {
		return DataSourceFactory.getDataSource();
	}
	
	private Connection createConnection() throws SQLException, DataSourceInitException{
		return getDataSource().getConnection();
	}
}
