package com.sc.auth.core;


import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class ApplicationContextListener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		
	}
	
	public void requestInitialized(ServletRequestEvent sre) {
		sre.getServletContext().setAttribute("web_host", "http://localhost:8080/scblog");		
	}
}