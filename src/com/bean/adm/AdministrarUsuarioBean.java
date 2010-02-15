package com.bean.adm;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioADM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioSEG;
import com.inia_mscc.modulos.seg.entidades.Perfil;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class AdministrarUsuarioBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	private String apellido;
	private String estado;
	private Perfil perfil;
	private SelectItem[] perfiles;
	private List<Perfil> listaPerfiles;
	private List<Usuario> usuarios;
	private Usuario usuario = new Usuario();
	
	
	public AdministrarUsuarioBean() throws Exception {
		try {
			listaPerfiles = this.getSegFachada(ServicioSEG.Perfil)
					.ObtenerPerfiles();
			perfiles = new SelectItem[listaPerfiles.size() + 1];
			perfiles[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int i = 1;
			for (Perfil p : listaPerfiles) {
				SelectItem si = new SelectItem(p.get_nombre());
				perfiles[i] = si;
				i++;
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public boolean isInit() {
		this.usuarios = this.getSegFachada(ServicioSEG.Usuario)
				.ObtenerUsuarios();
		return false;
	}
	
	public String verUsuario() {

		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String usuarioElegido = (String) paramMap.get("usuarioElegido");
		Iterator<Usuario> it = usuarios.iterator();
		while (it.hasNext()) {
			Usuario usuarioSeleccionado = (Usuario) it.next();
			if (usuarioSeleccionado.get_id() == (long) Long
					.parseLong(usuarioElegido)) {
				usuario = usuarioSeleccionado;

				setNombre(usuario.get_datos().get_nombre());
				setApellido(usuario.get_datos().get_apellido());
				setEstado(usuario.get_datos().get_estado().toString());
				setPerfil(usuario.get_datos().get_perfil());
			}
		}
		return "resultados";
	}

	public String actualizar() throws Exception {
		String retorno = "registro-error";
		try {
			usuario.get_datos().set_nombre(nombre);
			usuario.get_datos().set_apellido(apellido);
			usuario.set_estadoUsuario(Enumerados.EstadoUsuario.valueOf(estado));
			usuario.get_datos().set_perfil(perfil);
			
			this.getSegFachada(ServicioSEG.Usuario).ActualizarUsuario(usuario);
			retorno = "registro-ok";
		} catch (Exception ex) {
			this
					.setError("Se ha producido un error, por favor intente nuevamente.");
		}
		return retorno;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getApellido() {
		return apellido;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfiles(SelectItem[] perfiles) {
		this.perfiles = perfiles;
	}

	public SelectItem[] getPerfiles() {
		return perfiles;
	}

	public void setListaPerfiles(List<Perfil> listaPerfiles) {
		this.listaPerfiles = listaPerfiles;
	}

	public List<Perfil> getListaPerfiles() {
		return listaPerfiles;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
}