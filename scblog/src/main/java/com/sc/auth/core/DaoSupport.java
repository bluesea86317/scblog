package com.sc.auth.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.sc.auth.exception.DataSourceInitException;
import com.sc.auth.util.ParamUtils;

public abstract class DaoSupport {
		
	/**
	 * Sql statement of insert
	 * @param sqlMapConfig
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	protected int insert(String sqlMapConfig, Object param) throws SQLException{
		Connection conn = null;
		try {
			conn = createConnection();
			String sql = ParamUtils.getSql(sqlMapConfig, param);
			PreparedStatement smt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			smt.executeUpdate();
//			返回自动生成的主键ID
			ResultSet rs = smt.getGeneratedKeys();
			int id;
			if(rs.next()){
				id = rs.getInt(1);
				return id;
			}
		}catch (DataSourceInitException e) {
			e.printStackTrace();
		}finally{
			if(null != conn){
				conn.close();				
			}
		}
		return 0;
	}
	
	/**
	 * Sql statement of update
	 * @param sqlMapConfig
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	protected boolean update(String sqlMapConfig, Map<String,Object> param) throws SQLException{
		boolean flag = true;
		Connection conn = null;
		try {
			conn = createConnection();
			String sql = ParamUtils.getSql(sqlMapConfig, param);
			conn.createStatement().executeUpdate(sql);
			return flag;
		} catch (DataSourceInitException e) {
			flag = false;
			e.printStackTrace();
		}finally{
			if(null != conn){
				conn.close();				
			}
		}
		return flag;
	}
	
	@SuppressWarnings("rawtypes")
	protected List queryForList(String sqlMapConfig, Map<String,Object> param, Class<?> clazz) throws SQLException{
		List<Object> resultList = new ArrayList<Object>();
		ResultSet rs;
		Connection conn = null;
		try {
			conn = createConnection();
			String sql = ParamUtils.getSql(sqlMapConfig, param);
			rs = conn.createStatement().executeQuery(sql);
			while(rs.next()){
				Object object = clazz.newInstance();
//				Field[] fields = clazz.getDeclaredFields();
				Method[] methods = clazz.getMethods();
				for(Method method : methods){
					if(method.getName().startsWith("set")){
						method.invoke(object, ParamUtils.getResultByMethodParam(method.getParameterTypes()[0].getName(), rs, ParamUtils.lowerCaseMethodName(method.getName().substring(3))));
					}
				}
//				for(Field field : fields){
//					Method method = clazz.getMethod("set" + ParamUtils.upperCaseMethodName(field.getName()), field.getType());
//					method.invoke(object, ParamUtils.getResultByMethodParam(method.getParameterTypes()[0].getName(), rs, field.getName()));
//				}
				resultList.add(object);
			}
			
			return resultList;
		} catch (DataSourceInitException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != conn){
				conn.close();				
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T queryForObject(String sqlMapConfig, Map<String,Object> param, Class<?> clazz) throws SQLException{
		Object object = null;
		Connection conn = null;
		try {
			conn = createConnection();
			String sql = ParamUtils.getSql(sqlMapConfig, param);
			ResultSet rs = conn.createStatement().executeQuery(sql);
			Field[] fields = clazz.getDeclaredFields();
			if(rs.next()){
				object = clazz.newInstance();
				for(Field field : fields){
					Method method = clazz.getMethod("set" + ParamUtils.upperCaseMethodName(field.getName()), field.getType());
					method.invoke(object, ParamUtils.getResultByMethodParam(method.getParameterTypes()[0].getName(), rs, field.getName()));
				}				
			}
			return (T)object;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataSourceInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != conn){
				conn.close();				
			}
		}
		return null;
	}
	
	
	
	protected boolean delete(String sqlMapConfig, Map<String,Object> param) throws SQLException{
		boolean flag = true;
		Connection conn = null;
		try {
			conn = createConnection();
			String sql = ParamUtils.getSql(sqlMapConfig, param);
			conn.createStatement().executeUpdate(sql);
			return flag;
		}  catch (DataSourceInitException e) {
			flag = false;
			e.printStackTrace();
		}finally{
			if(null != conn){
				conn.close();				
			}
		}
		return flag;
	}

	protected boolean excuteSql(String sql) throws SQLException{
		boolean flag = true;
		Connection conn = null;
		try {
			createConnection().createStatement().execute(sql);			
		}  catch (DataSourceInitException e) {
			flag = false;
			e.printStackTrace();
		}finally{
			if(null != conn){
				conn.close();				
			}
		}
		
		return flag;
	}
	
	private DataSource getDataSource() throws DataSourceInitException {
		return DataSourceFactory.getDataSource();
	}
	
	private Connection createConnection() throws SQLException, DataSourceInitException{
		return getDataSource().getConnection();
	}
	
}
