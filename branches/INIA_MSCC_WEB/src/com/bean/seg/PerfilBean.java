package com.bean.seg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Transaccion;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Servicio;
import com.inia_mscc.modulos.seg.entidades.Perfil;

public class PerfilBean extends MaestroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String descripcion;
	private String estado;
	private String fijo;
	private List<Perfil> perfiles;
	private Perfil perfil = new Perfil();
	private List<Transaccion> transacciones;

	private String guardar() {

		return null;
	}

	public String eliminar() throws Exception {
		String retorno = "eliminado";

		try {
			Map paramMap = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap();
			String consultaElegida = (String) paramMap.get("consultaEliminar");
			Iterator<Perfil> it = perfiles.iterator();
			while (it.hasNext()) {
				Perfil object = (Perfil) it.next();
				if ((Long) object.get_id() == Long.parseLong(consultaElegida)) {
					perfil = object;
					nombre = perfil.get_nombre();
					descripcion = perfil.get_descripcion();
					estado = perfil.get_estado().toString();
					perfil.set_transaccionesSistema(new ArrayList<Transaccion>());
				}
			}
			this.getSegFachada(Servicio.Perfil).EliminarPerfil(perfil);
		} catch (Exception ex) {
			this
					.setError("Se ha producido un error, por favor intente nuevamente.");
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public String verConsulta() {
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String consultaElegida = (String) paramMap.get("consultaElegida");
		Iterator<Perfil> it = perfiles.iterator();
		while (it.hasNext()) {
			Perfil perfilSeleccionado = (Perfil) it.next();
			if (perfilSeleccionado.get_id() == (long)Long
					.parseLong(consultaElegida)) {
				perfil = perfilSeleccionado;
				perfil = this.getSegFachada(Servicio.Perfil)
				.ObtenerPerfilConTransAsociadas(perfilSeleccionado);
				nombre = perfil.get_nombre();
				descripcion = perfil.get_descripcion();
				estado = perfil.get_estado().toString();
				for (Transaccion transa : transacciones) {
					for (Transaccion transaPerfil : perfil.get_transaccionesSistema()) {
						if(transa.get_id()==transaPerfil.get_id()){
							transa.set_asociada(true);
							break;
						}
					}
				}
			}
		}
		return "desplegarResultados";
	}

	public boolean isEdicion() {

		return false;
	}

	public PerfilBean() {
		this.estado = Estado.Activo.name();
	}

	public String cancelar() {
		this.LimpiarBean();
		this.perfiles = this.getSegFachada(Servicio.Perfil).ObtenerPerfiles();
		this.transacciones = this.getAdmFachada(Servicio.Transaccion)
				.ObtenerTransacciones();
		return "cancelar";
	}

	public boolean isInit() {
		boolean retorno = false;
		this.LimpiarBean();
		this.perfiles = this.getSegFachada(Servicio.Perfil).ObtenerPerfiles();
		this.transacciones = this.getAdmFachada(Servicio.Transaccion)
				.ObtenerTransacciones();
		return retorno;
	}

	public boolean isLogged() {
		return MaestroBean.getInstance().isLogged();
	}

	public String actualizar() throws Exception {
		String retorno = "registro-error";
		try {
			perfil.set_nombre(nombre);
			perfil.set_descripcion(descripcion);
			perfil.set_estado(Enumerados.Estado.valueOf(estado));
			List<Transaccion> asociadas = new ArrayList<Transaccion>();
			for (int i = 0; i < transacciones.toArray().length; i++) {
				Transaccion transaccion = (Transaccion) transacciones.toArray()[i];
				if (transaccion != null) {
					if (transaccion.get_asociada()) {
						asociadas.add(transaccion);
					}
				}
			}
			List<Transaccion> lista = asociadas;
			perfil.set_transaccionesSistema(lista);
			this.getSegFachada(Servicio.Perfil).ActualizarPerfil(perfil);
			retorno = "registro-ok";
		} catch (Exception ex) {
			this
					.setError("Se ha producido un error, por favor intente nuevamente.");
		}
		return retorno;
	}

	public String registrar() throws Exception {
		String retorno = "";
		try {
			List<Transaccion> asociadas = new ArrayList<Transaccion>();
			for (int i = 0; i < transacciones.toArray().length; i++) {
				Transaccion transaccion = (Transaccion) transacciones.toArray()[i];
				if (transaccion != null) {
					if (transaccion.get_asociada()) {
						asociadas.add(transaccion);
					}
				}
			}
			List<Transaccion> lista = asociadas;
			Perfil datosPerfil = new Perfil();
			datosPerfil.set_transaccionesSistema(lista);
			datosPerfil.set_nombre(nombre);
			datosPerfil.set_descripcion(descripcion);
			datosPerfil.set_estado(Enumerados.Estado.valueOf(estado));

			Perfil per = this.getSegFachada(Servicio.Perfil).ComprobarPerfil(
					datosPerfil);
			if (per == null) {
				setError("");
				Perfil p = this.getSegFachada(Servicio.Perfil).RegistrarPerfil(
						datosPerfil);
				if (p != null) {
					this.setError("");
					MaestroBean.getInstance().setOpcion(
							"/Servicios/SEG/SEG009.jsp");
					retorno = "registro-ok";
					LimpiarBean();
				} else {
					this
							.setError("No ha sido posible crear el perfil, revise los datos ingresados y intentelo nuevamente.");
					MaestroBean.getInstance().setOpcion(
							"/Servicios/SEG/SEG009.jsp");
					retorno = "registro-error";
				}
			} else {
				this
						.setError("Ya existe un Perfil con igual nombre, Por favor ingrese otro nombre.");
				MaestroBean.getInstance()
						.setOpcion("/Servicios/SEG/SEG009.jsp");
				retorno = "registro-error";
			}
		} catch (Exception ex) {
			setError(ex.getMessage());
		}
		return retorno;
	}

	private void LimpiarBean() {
		nombre = null;
		descripcion = null;
		estado = null;
		perfiles = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setFijo(String fijo) {
		this.fijo = fijo;
	}

	public String getFijo() {
		return fijo;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

}