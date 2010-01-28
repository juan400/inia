package com.bean.seg;

import java.io.IOException;
import java.io.Serializable;

import javax.mail.MessagingException;
import javax.naming.NamingException;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.seg.SEGFachada;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class LoginBean extends MaestroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private SEGFachada segFachada = new SEGFachada(Enumerados.Servicio.Usuario);

	private String loginName;
	private String password;
	private String error;

	public boolean isInit() {
		return false;
	}

	public String login() throws IOException, NamingException,
			MessagingException {
		// MaestroBean.getInstance().getTextBundle();
		Usuario u = segFachada.Login(loginName, password);
		if (u != null) {
			MaestroBean maestro = MaestroBean.getInstance();
			maestro.setLogged(true);
			maestro.setUsuario(u);
			error = "";
			// MaestroBean.getInstance().setOpcion("/Servicios/SEG/menuRich.jsp");
			//TODO validar que el usuario estte activo y no este en otro estado que no sea activo.
			return "login-ok";
		} else {
			error = "El nombre de usuario y password no conciden";
			return "login-error";
		}
	}

	public String logout() throws IOException, NamingException,
			MessagingException {
		if (!isLogged()) {
			// MaestroBean.getInstance().setOpcion("/Servicios/SEG/menuRich.jsp");
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

}
