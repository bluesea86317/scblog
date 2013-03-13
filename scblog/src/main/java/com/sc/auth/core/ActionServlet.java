package com.sc.auth.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sc.auth.exception.NonActionForRequstException;
import com.sc.auth.util.ParamUtils;
import com.sc.auth.util.StringUtils;

public class ActionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	Map<String,ActionConfig> configMap = new HashMap<String,ActionConfig>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req,resp);
	}
	@Override
	public void destroy() {		
		super.destroy();
	}

	
	@Override
	public void init() throws ServletException {
		String filePath = this.getServletContext().getRealPath("");
		String fileName = this.getInitParameter("config");
		String configPath = filePath + fileName;
		
//		初始化数据库连接		
		try {
			SAXReader reader = new SAXReader();

			Document doc = reader.read(new File(configPath));
			Element rootNode = doc.getRootElement();
//			初始化actionConfig		
			this.initializeActionConfig(rootNode);
//			初始化数据库连接
			this.initializeDataSource(rootNode);
//			设置host
			this.getServletContext().setAttribute("platform_host", "http://localhost:8080/auth_sys");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response){
		String servletPath = request.getServletPath();
		String actionPath = servletPath.substring(0,servletPath.lastIndexOf("."));
//			requestUri.substring(0,requestUri.lastIndexOf("."));
//		config
		ActionConfig actionConfig = configMap.get(actionPath);
		try {
		if(null == actionConfig){
			throw new NonActionForRequstException("There is no action config for this request which the path is \"" + actionPath + "\"");			
		}
		String clzName = actionConfig.getActionclass();
		Map<String,String> forwardMap = actionConfig.getForwardMap();
			Action action = (Action)Class.forName(clzName).newInstance();
			String forwardPath = action.excute(request, response);
			if(!StringUtils.isNullOrEmpty(forwardPath)){
				request.getRequestDispatcher(forwardMap.get(forwardPath)).forward(request, response);				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NonActionForRequstException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化数据库连接
	 * @param rootNode
	 */
	private void initializeDataSource(Element rootNode){
		System.out.println("初始化数据库连接开始");
		List<?> dataSourceNodesList = rootNode.selectNodes("data-sources/data-source");
		Element dataSourceNode = (Element)dataSourceNodesList.get(0);
		String className = dataSourceNode.attributeValue("type");
		Class<?> clazz;
		try {
			clazz = Class.forName(className);
			Object dataSource = clazz.newInstance();
//			获取DB参数
			List<?> dataSourcePropertyNodesList = rootNode.selectNodes("data-sources/data-source/property");
			for(Object node : dataSourcePropertyNodesList){
				Element propertyNode = (Element)node;
				String dataSourceProperty = propertyNode.attributeValue("name");
				String dataSourceValue = propertyNode.attributeValue("value");
				Field[] fields = clazz.getDeclaredFields();
				for(Field field : fields){
					if(field.getName().equals(dataSourceProperty)){
						Method method = clazz.getMethod("set" + ParamUtils.initMethodName(field.getName()), field.getType());
						method.invoke(dataSource, ParamUtils.convertString(dataSourceValue, field.getType()));
					}
				}
			}
//			初始化DB连接
			DataSourceFactory.init((DataSource)dataSource);
			System.out.println("初始化数据库连接结束");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化actionConfig
	 * @param rootNode   config.xml的节点树
	 * @throws DocumentException
	 */
	private void initializeActionConfig(Element rootNode){
		List<?> nodesList = rootNode.selectNodes("action-mapping/action");
		System.out.println("初始化actionConfig开始");
		for(Object node : nodesList){
//			加载actionConfig
			ActionConfig actionConfig = new ActionConfig();
//			设置action的请求路径path,处理类class
			Element actionNode = (Element)node;
			String actionPath = actionNode.attributeValue("path");
			String actionClass = actionNode.attributeValue("class");
			actionConfig.setActionPath(actionPath);
			actionConfig.setActionclass(actionClass);
//			设置action处理完成后的跳转取消，path处理完成后的选择符，value是路径
			List<?> forwardNodeList = actionNode.selectNodes("forward");
			Map<String,String> forwardMap = new HashMap<String,String>();
			for(Object fNode : forwardNodeList){
				Element forewardNode = (Element)fNode;
				String path = forewardNode.attributeValue("path");
				String value = forewardNode.attributeValue("value");
				forwardMap.put(value, path);
			}
			actionConfig.setForwardMap(forwardMap);
			configMap.put(actionPath,actionConfig);
		}
		System.out.println("初始化actionConfig结束");
	}
}
