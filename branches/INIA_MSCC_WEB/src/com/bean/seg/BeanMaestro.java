package com.bean.seg;

import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

	public class BeanMaestro {
		
		private long usuarioId = -1;
		private boolean logged = false;
		private String opcion;
		private ResourceBundle textBundle;
		private ResourceBundle config;
		private boolean reload = false;
		
		private BeanMaestro() {}
		
		public boolean isLogged() {
			return logged;
		}

		public void setLogged(boolean logged) {
			this.logged = logged;
		}

		public long getUsuarioId() {
			return usuarioId;
		}

		public void setUsuarioId(long l) {
			this.usuarioId = l;
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
		public static BeanMaestro getInstance() {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			Map session = context.getSessionMap();
			BeanMaestro g = (BeanMaestro)session.get("BeanMaestro");
			if (g == null) {
				g = new BeanMaestro();
				session.put("BeanMaestro", g);
			}
			return g;
		}
		
}
