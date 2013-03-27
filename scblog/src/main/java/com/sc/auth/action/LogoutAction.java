package com.sc.auth.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sc.auth.core.Action;

public class LogoutAction extends Action {

	private final static String LOGIN_PAGE_PATH = "/adminweb/login.jsp";
	
	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + LOGIN_PAGE_PATH);
		return null;
	}

}
