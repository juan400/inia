package com.bean.seg;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inia_mscc.modulos.seg.SEGFachada;
import com.inia_mscc.modulos.seg.dao.DAOUsuario;
import com.inia_mscc.modulos.seg.entidades.Usuario;


public class LoginBean implements Serializable  {


	private static final long serialVersionUID = 1L;
	private SEGFachada segFachada =  new SEGFachada();
	
		public boolean isInit() {
			return false;
		}
		
		public boolean isLogged() {
			return BeanMaestro.getInstance().isLogged();
		}
		
		private String nombre;
		private String password;
		private String error;
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
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

		public String login() {
			BeanMaestro.getInstance().getTextBundle();
			Usuario u = segFachada.login(nombre, password);
			
			if (u != null) {
				BeanMaestro maestro = BeanMaestro.getInstance();
				maestro.setLogged(true);
				maestro.setUsuarioId(u.get_id());

				error = "";
				BeanMaestro.getInstance().setOpcion("listado");
				return "login-ok";
			} else {
				error = "El nombre de usuario y password no concuerdan";
				return "login-error";
			}
		}
		
		public String logout() {
			if (!isLogged()) return "";
			
			BeanMaestro maestro = BeanMaestro.getInstance();
			maestro.setLogged(false);
			maestro.setUsuarioId(-1);

			error = "";
			return "";
		}
		
		public String registrarse() {
			return "registrarse";
		}

}
