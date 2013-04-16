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
			this.getServletContext().setAttribute("web_host", "http://localhost:7070/scblog");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response){
//		设置请求响应的编码规则
		String servletPath = request.getServletPath();
		String actionPath = servletPath.substring(0,servletPath.lastIndexOf("."));
//		config
		try {
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			ActionConfig actionConfig = configMap.get(actionPath);
			if(null == actionConfig){
				throw new NonActionForRequstException("There is no action config for this request which path is \"" + actionPath + "\"");			
			}			
			
			String filePath = getServletContext().getRealPath("/blog");
			String fileName = "";
			String forwardPath = "";
			int articleId = ParamUtils.getInt(request, "id", 0);
			if(articleId != 0 && "post".equals(actionPath)){
				fileName = articleId + ".html";
				filePath = filePath + "\\" + fileName;
				File file = new File(filePath);
				if(file.exists()){
//					response.sendRedirect(fileName);
					request.getRequestDispatcher(fileName).forward(request, response);
				}else{
					forwardPath = dealForwardPath(request, response, actionConfig);
					StaticHtmlPageCreator.createStaticHtmlPageAndRedirect(request, response, fileName, filePath, forwardPath);
					
				}
			}else{			
				forwardPath = dealForwardPath(request, response, actionConfig);
				if(!StringUtils.isNullOrEmpty(forwardPath)){
					request.getRequestDispatcher(forwardPath).forward(request, response);				
				}
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
	
	private String dealForwardPath(HttpServletRequest request, HttpServletResponse response, ActionConfig actionConfig) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException{		
		String clzName = actionConfig.getActionclass();
		ActionForward forwardMap = actionConfig.getActionForward();
		Action action = (Action)Class.forName(clzName).newInstance();
		return action.excute(request, response, forwardMap);
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
						Method method = clazz.getMethod("set" + ParamUtils.upperCaseMethodName(field.getName()), field.getType());
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
			
			ActionForward forward = new ActionForward();
			for(Object fNode : forwardNodeList){
				Element forewardNode = (Element)fNode;
				String path = forewardNode.attributeValue("path");
				String name = forewardNode.attributeValue("name");
				forward.putForwardMap(name, path);
			}
			actionConfig.setActionForward(forward);
			configMap.put(actionPath,actionConfig);
		}
		System.out.println("初始化actionConfig结束");
	}
}
