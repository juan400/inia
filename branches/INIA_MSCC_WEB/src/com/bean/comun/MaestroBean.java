package com.bean.comun;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.inia_mscc.modulos.seg.entidades.Usuario;

	public class MaestroBean implements Serializable {

		private static final long serialVersionUID = 1L;

		private Usuario usuario = null;
		private boolean logged = false;
		private String opcion;
		private ResourceBundle textBundle;
		private ResourceBundle config;
		private boolean reload = false;
		private boolean activado = false;
		
		
		public void setActivado(boolean activado) {
			this.activado = activado;
		}

		public boolean isActivado() {
			return activado;
		}

		public boolean isInit() {
			return false;
		}
		
		public boolean isLogged() {
			return logged;
		}

		public void setLogged(boolean logged) {
			this.logged = logged;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usu) {
			this.usuario = usu;
		}
		
		
		public String getOpcion() {
			return opcion;
		}

		public void setOpcion(String opcion) {
			this.opcion = opcion;
		}
				
		public ResourceBundle getTextBundle() {
			if (textBundle == null) {
				textBundle = ResourceBundle.getBundle("text", FacesContext.getCurrentInstance().getExternalContext().getRequestLocale());
			}
			return textBundle;
		}
				
		public ResourceBundle getConfig() {
			if (config == null) {
				config = ResourceBundle.getBundle("qgerbil");
			}
			return config;
		}
		
		public boolean isReload() {
			return reload;
		}

		public void setReload(boolean reload) {
			this.reload = reload;
		}

		@SuppressWarnings("unchecked")
		public static MaestroBean getInstance() {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			Map session = context.getSessionMap();
			MaestroBean g = (MaestroBean)session.get("MaestroBean");
			if (g == null) {
				g = new MaestroBean();
				session.put("MaestroBean", g);
			}
			return g;
		}
		
}
