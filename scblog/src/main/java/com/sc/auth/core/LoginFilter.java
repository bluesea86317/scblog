package com.sc.auth.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sc.auth.util.StringUtils;
import com.sc.auth.vo.BaseUser;

public class LoginFilter implements Filter {
	
	private String LOGIN_PAGE_PATH = "/adminweb/login.jsp";
	
	String[] excludePaths;
	
	public void destroy() {		

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		HttpSession session = request.getSession();
		String servletPath = request.getServletPath();
		if(isExclude(servletPath)){
			chain.doFilter(request, response);
			return;
		}
		if((BaseUser)session.getAttribute("logonUser") == null){
			request.getSession().setAttribute("relayPath", servletPath);
			response.sendRedirect(request.getContextPath() + LOGIN_PAGE_PATH);
		}else{
			chain.doFilter(request, response);
		}	
	}

	public void init(FilterConfig config) throws ServletException {		
		String param = config.getInitParameter("excludePath");
		excludePaths = param.split(",");
	}

	private boolean isExclude(String path){
		if(excludePaths == null) return false;
		for(String excludePath : excludePaths){
			if(path.equals(excludePath)){
				return true;				
			}			
		}
		return false;
	}
}
