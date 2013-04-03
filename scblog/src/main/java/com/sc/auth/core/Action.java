package com.sc.auth.core;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {
	
	protected final static String PROCESS_RESULT_SUCCESS = "success";
	
	protected final static String PROCESS_RESULT_FAILURE = "failure";
	
	public abstract String excute(HttpServletRequest request, HttpServletResponse response, ActionForward actionForward) throws IOException;	
	
	/**
	 * ajax result return : success
	 * @param response
	 */
	public void return_out(HttpServletResponse response,String result, String msg){
		try {
			response.getWriter().print("{\"resultCode\":\"" + result + "\",\"msg\":\"" + msg + "\"}");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
