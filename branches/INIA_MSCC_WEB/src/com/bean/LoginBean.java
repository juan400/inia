package com.bean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginBean  {


	private static final long serialVersionUID = 1L;

	public String logout(){  
		
		ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
		HttpSession session = (HttpSession)ectx.getSession(false);
		session.invalidate();
		return "go_login_administracion";
		}  
	
}
