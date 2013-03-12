package com.sc.auth.action.userlogon;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sc.auth.action.userlogon.service.UserLoginService;
import com.sc.auth.core.Action;
import com.sc.auth.util.ParamUtils;

public class LoginAction extends Action {

	@Override
	public String excute(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String action = ParamUtils.getString(request, "action", "");
		if("create".equals(action)){
			return excuteSql(request,response);
		}else{
			checkUserExist(request,response);
			return null;
		}
		
	}

	private String excuteSql(HttpServletRequest request,
			HttpServletResponse response) {
		String sql = ParamUtils.getString(request, "sql", "");
		
		if(!getUserLoginService().excuteSql(sql)){
			return "failure";
		}		
		return "success";
	}

	private String checkUserExist(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String userName = ParamUtils.getString(request, "userName", "");
		String password = ParamUtils.getString(request, "password", "");
		
		try {
			BaseUser user = getUserLoginService().getUser(userName, password);
			if(null == user){
				response.getWriter().print("{success:true,msg:'error'}");
			}else{
				response.getWriter().print("{success:true,msg:'ok'}");				
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			//return "failure";
			//response.getWriter().print("failure");
			response.getWriter().print("{success:false,msg:'exception'}");
		}
		return null;
		//return "success";
		
	}
	
	private UserLoginService getUserLoginService(){
		return new UserLoginService();
	}
}
