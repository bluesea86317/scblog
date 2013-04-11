package com.sc.auth.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sc.auth.action.service.UserLoginService;
import com.sc.auth.core.Action;
import com.sc.auth.core.ActionForward;
import com.sc.auth.util.ParamUtils;
import com.sc.auth.util.StringUtils;
import com.sc.auth.vo.BaseUser;

public class LoginAction extends Action {

	private final static String LOGIN_INDEX_PAGE_PATH = "/adminweb/articleManage.do";
	
	@Override
	public String excute(HttpServletRequest request,HttpServletResponse response, ActionForward actionForward) throws IOException {				
		return checkUserExist(request,response);		
	}

	private String checkUserExist(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String userName = ParamUtils.getString(request, "userName", "");
		String password = ParamUtils.getString(request, "password", "");		
		try {
			BaseUser user = getUserLoginService().getUser(userName, password);
			if(null != user){
//			用户名密码验证通过,设置session会话,跳转到登陆前访问的地址
				request.getSession().setAttribute("logonUser", user);
				return_out(response, PROCESS_RESULT_SUCCESS, "");
				String relayPath = (String)request.getSession().getAttribute("relayPath");
				response.sendRedirect(request.getContextPath() + (StringUtils.isNullOrEmpty(relayPath) ? LOGIN_INDEX_PAGE_PATH : relayPath));
			}else{
//				验证不通过
				return_out(response, PROCESS_RESULT_FAILURE, "用户名或密码不正确!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return_out(response,PROCESS_RESULT_FAILURE, e.getMessage());
		}
		return null;
	}
	
	private UserLoginService getUserLoginService(){
		return new UserLoginService();
	}
}
