package com.sc.auth.core;


import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import com.sc.auth.service.ConfigService;

public class ApplicationContextListener implements ServletRequestListener {

	private ConfigService configService = Env.getBean("configService");
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		
	}
	
	public void requestInitialized(ServletRequestEvent sre) {
		sre.getServletContext().setAttribute("web_host", configService.getProperty("web_host"));		
	}
}