package com.bean.seg;

import java.io.IOException;
import java.io.Serializable;

import javax.mail.MessagingException;
import javax.naming.NamingException;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.seg.SEGFachada;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private SEGFachada segFachada = new SEGFachada();

	private String loginName;
	private String password;
	private String error;

	public boolean isInit() {
		return false;
	}

	public boolean isLogged() {
		return MaestroBean.getInstance().isLogged();
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getError() {
		return error;
	}

	public String login() throws IOException, NamingException,
			MessagingException {
		// MaestroBean.getInstance().getTextBundle();
		
		Usuario u = segFachada.Login(loginName, password);

		if (u != null) {
			MaestroBean maestro = MaestroBean.getInstance();
			maestro.setLogged(true);
			maestro.setUsuario(u);
			StringBuffer p = new StringBuffer();
			for (int i = 0; i < 8; i++) {
				String c = "" + (int)(Math.random() * 10);
				p.append(c);
			}
			error = "";
			MaestroBean.getInstance().setOpcion("/Servicios/SEG/menuRich.jsp");
			return "login-ok";
		} else {
			error = "El nombre de usuario y password no concuerdan";
			return "login-error";
		}
	}

	public String logout() throws IOException, NamingException,
	MessagingException {
		if (!isLogged())
		{
			MaestroBean.getInstance().setOpcion("/Servicios/SEG/menuRich.jsp");
		}
		MaestroBean maestro = MaestroBean.getInstance();
		maestro.setLogged(false);
		maestro.setUsuario(null);
		maestro.setOpcion("/Servicios/SEG/SEG001.jsp");
		error = "";
		return "logout";
	}

	public String registrarse() {
		return "registrarse";
	}

	public void setSegFachada(SEGFachada segFachada) {
		this.segFachada = segFachada;
	}

	public SEGFachada getSegFachada() {
		return segFachada;
	}

}
