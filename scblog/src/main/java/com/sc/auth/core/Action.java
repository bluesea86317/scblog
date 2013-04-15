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
		outPut(response,"{\"resultCode\":\"" + result + "\",\"msg\":\"" + msg + "\"}");
	}
	
	/**
	 * ajax输出
	 * @param response
	 * @param result
	 */
	public void outPut(HttpServletResponse response,String result){
		try {
			response.getWriter().print(result);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
