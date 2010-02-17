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
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioADM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioSEG;
import com.inia_mscc.modulos.seg.entidades.Perfil;

public class PerfilBean extends MaestroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String descripcion;
	private String estado;
	private String fijo;
	private List<Perfil> perfiles;
	private Perfil perfil = new Perfil();
	private List<Transaccion> transaccionesActivas;

	public String alta() {
		this.setError("");
		this.setExito("");
		return "Alta";
	}

	public String eliminar() {
		String retorno = "";
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
					perfil
							.set_transaccionesSistema(new ArrayList<Transaccion>());
				}
			}
			if (this.getSegFachada(ServicioSEG.Perfil).EliminarPerfil(perfil)
					.isEmpty()) {
				this.setExito("Se ha eliminado exitosamente el perfil.");
				retorno = "eliminado";
			} else {
				this.setError("No se puedo eliminar el perfil, porque "
						+ "está asignado en uno o mas usuarios del sistema.");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return retorno;
	}

	public String verConsulta() {
		this.setError("");
		this.setExito("");
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String consultaElegida = (String) paramMap.get("consultaElegida");
		Iterator<Perfil> it = perfiles.iterator();
		while (it.hasNext()) {
			Perfil perfilSeleccionado = (Perfil) it.next();
			if (perfilSeleccionado.get_id() == (long) Long
					.parseLong(consultaElegida)) {
				perfil = perfilSeleccionado;
				perfil = this.getSegFachada(ServicioSEG.Perfil)
						.ObtenerPerfilConTransAsociadas(perfilSeleccionado);
				nombre = perfil.get_nombre();
				descripcion = perfil.get_descripcion();
				estado = perfil.get_estado().toString();
				for (Transaccion transa : transaccionesActivas) {
					for (Transaccion transaPerfil : perfil
							.get_transaccionesSistema()) {
						if (transa.get_id() == transaPerfil.get_id()) {
							transa.set_asociada(true);
							break;
						}
					}
				}
			}
		}
		return "desplegarResultados";
	}

	public PerfilBean() {
		this.estado = Estado.Activo.name();
	}

	public boolean isInit() {
		this.limpiarBean();
		this.perfiles = this.getSegFachada(ServicioSEG.Perfil)
				.ObtenerPerfiles();
		this.transaccionesActivas = this.getAdmFachada(ServicioADM.Transaccion)
				.ObtenerTransaccionesActiva();
		return false;
	}

	public String actualizar() throws Exception {
		String retorno = "registro-error";
		try {
			perfil.set_nombre(nombre);
			perfil.set_descripcion(descripcion);
			perfil.set_estado(Enumerados.Estado.valueOf(estado));
			List<Transaccion> asociadas = new ArrayList<Transaccion>();
			for (int i = 0; i < transaccionesActivas.toArray().length; i++) {
				Transaccion transaccion = (Transaccion) transaccionesActivas
						.toArray()[i];
				if (transaccion != null) {
					if (transaccion.get_asociada()) {
						asociadas.add(transaccion);
					}
				}
			}
			List<Transaccion> lista = asociadas;
			perfil.set_transaccionesSistema(lista);
			this.getSegFachada(ServicioSEG.Perfil).ActualizarPerfil(perfil);
			retorno = "registro-ok";
		} catch (Exception ex) {
			this
					.setError("Se ha producido un error, por favor intente nuevamente.");
		}
		return retorno;
	}

	public String registrar() throws Exception {
		Boolean transaccionAsociada = false;
		String retorno = "";
		try {
			List<Transaccion> asociadas = new ArrayList<Transaccion>();
			for (int i = 0; i < transaccionesActivas.toArray().length; i++) {
				Transaccion transaccion = (Transaccion) transaccionesActivas
						.toArray()[i];
				if (transaccion != null) {

					if (transaccion.get_asociada()) {
						asociadas.add(transaccion);
						transaccionAsociada = true;
					}
				}
			}
			List<Transaccion> lista = asociadas;
			Perfil datosPerfil = new Perfil();
			datosPerfil.set_transaccionesSistema(lista);
			datosPerfil.set_nombre(nombre);
			datosPerfil.set_descripcion(descripcion);
			datosPerfil.set_estado(Enumerados.Estado.valueOf(estado));

			Perfil per = this.getSegFachada(ServicioSEG.Perfil)
					.ComprobarPerfil(datosPerfil);
			if (transaccionAsociada) {
				if (per == null) {
					setError("");
					Perfil p = this.getSegFachada(ServicioSEG.Perfil)
							.RegistrarPerfil(datosPerfil);
					if (p != null) {
						this.setError("");
						MaestroBean.getInstance().setOpcion(
								"/Servicios/SEG/SEG009.jsp");
						retorno = "registro-ok";
						limpiarBean();
					} else {
						this
								.setError("No ha sido posible crear el perfil, revise los datos ingresados e intentelo nuevamente.");
						retorno = "";
					}
				} else {
					this
							.setError("Ya existe un Perfil con igual nombre, Por favor ingrese otro nombre.");
					retorno = "";
				}
			} else {
				this.setError("Debe asociar como mínimo una transacción.");
				retorno = "";
			}
		} catch (Exception ex) {
			setError(ex.getMessage());
		}
		return retorno;
	}

	private void limpiarBean() {
		nombre = "";
		descripcion = "";
		estado = "";
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

	public void setTransaccionesActivas(List<Transaccion> transaccionesActivas) {
		this.transaccionesActivas = transaccionesActivas;
	}

	public List<Transaccion> getTransaccionesActivas() {
		return transaccionesActivas;
	}

}