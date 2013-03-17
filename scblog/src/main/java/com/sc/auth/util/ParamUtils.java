package com.sc.auth.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sc.auth.vo.BaseUser;


public class ParamUtils {
	
	public static String getString(HttpServletRequest request, String paramName, String defaultValue){
		String value = request.getParameter(paramName);
		if(StringUtils.isNullOrEmpty(value)){
			return defaultValue;			
		}
		return value;
	}
	
	public static String upperCaseMethodName(String fieldName){		
		return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	
	public static String lowerCaseMethodName(String fieldName){		
		return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
	}
	
	/**
	 * 将配置文件中获取到的参数转换成其他类型的参数，int,boolean,long等
	 * @param value
	 * @param type
	 * @return
	 */
	public static Object convertString(String value, Class<?> type){
			if(String.class.getName().equals(type.getName())){
				return value;
			}
			if(Integer.class.getName().equals(type.getName()) || int.class.getName().equals(type.getName())){
				return Integer.parseInt(value);
			}
			if(Long.class.getName().equals(type.getName()) || long.class.getName().equals(type.getName())){
				return Long.parseLong(value);
			}
			if(Short.class.getName().equals(type.getName()) || short.class.getName().equals(type.getName())){
				return Short.parseShort(value);
			}
			if(Boolean.class.getName().equals(type.getName()) || boolean.class.getName().equals(type.getName())){
				return Boolean.parseBoolean(value);
			}
			if(Byte.class.getName().equals(type.getName()) || byte.class.getName().equals(type.getName())){
				return Byte.parseByte(value);
			}
			if(Float.class.getName().equals(type.getName()) || float.class.getName().equals(type.getName())){
				return Float.parseFloat(value);
			}			
			if(BigDecimal.class.getName().equals(type.getName())){
				return new BigDecimal(value);
			}
			return value;
	}
	
	@SuppressWarnings("unchecked")
	public static String getSql(String sql, Object param){
		try {
			if(param.getClass().getName().indexOf("Map") != -1){
				Map<String,Object> paramMap = (HashMap<String,Object>)param;
				Iterator<?> itr = paramMap.keySet().iterator();
				while(itr.hasNext()){
					String key = (String)itr.next();
					Object value = paramMap.get(key);
					if(sql.indexOf("#"+ key +"#") != -1){
						if(String.class.getName() == value.getClass().getName()){
							sql = sql.replaceAll("#"+ key +"#", "'" + String.valueOf(value) + "'");					
						}else{
							sql = sql.replaceAll("#"+ key +"#", String.valueOf(value));
						}
					}
				}
			}else{				
				Method[] methods = param.getClass().getMethods();				
				for(Method method : methods){
					if(method.getName().indexOf("get") != -1){
						Object value = method.invoke(param);
						String property = ParamUtils.lowerCaseMethodName(method.getName().substring(3));
						if(sql.indexOf("#"+ property +"#") != -1){
							if(String.class.getName() == value.getClass().getName()){
								sql = sql.replace("#" + property + "#","'" + String.valueOf(value) + "'");
							}else{
								sql = sql.replaceAll("#"+ property +"#", String.valueOf(value));
							}
						}
					}
				}	
			}
			System.out.println("The sql is : " + sql);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sql;
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = new LinkedHashMap<String,Object>();
		map.put("userName", "Stephen");
		map.put("id", 1111);
		map.put("password", "888888");
		System.out.println(ParamUtils.getSql("insert into #userName#,#id#,password(#password#)", map));
	}
}
