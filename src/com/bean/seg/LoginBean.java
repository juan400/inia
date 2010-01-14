package com.bean.seg;

import java.io.IOException;
import java.io.Serializable;

import javax.mail.MessagingException;
import javax.naming.NamingException;

import com.bean.comun.BeanMaestro;
import com.inia_mscc.modulos.seg.SEGFachada;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private SEGFachada segFachada = new SEGFachada();

	public boolean isInit() {
		return false;
	}

	public boolean isLogged() {
		return BeanMaestro.getInstance().isLogged();
	}

	private String loginName;
	private String password;
	private String error;

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
		// BeanMaestro.getInstance().getTextBundle();
		Usuario u = segFachada.login(loginName, password);

		if (u != null) {
			BeanMaestro maestro = BeanMaestro.getInstance();
			maestro.setLogged(true);
			maestro.setUsuarioId(u.get_id());

			error = "";
			BeanMaestro.getInstance().setOpcion("/Servicios/SEG/menuRich.jsp");
			return "login-ok";
		} else {
			error = "El nombre de usuario y password no concuerdan";
			return "login-error";
		}
	}

	public String logout() {
		if (!isLogged())
			return "";

		BeanMaestro maestro = BeanMaestro.getInstance();
		maestro.setLogged(false);
		maestro.setUsuarioId(-1);

		error = "";
		return "";
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
