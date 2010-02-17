package com.bean.gem;

import java.io.Serializable;
import java.util.Date;

import javax.faces.model.SelectItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioGEM;
import com.inia_mscc.modulos.gem.entidades.Cultivo;

public class CultivoBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date fecha;
	private String nombre;
	private String descripcion;
	private String estado;
	private SelectItem[] estados;
	private Cultivo cultivo;
	private boolean disableBtnProp = true;

	public boolean isInit() {
		estados = new SelectItem[Estado.values().length];
		SelectItem si = new SelectItem(Estado.Activo.name());
		estados[0] = si;
		si = new SelectItem(Estado.Activo.name());
		estados[1] = si;
		estado = estados[1].getValue().toString();
		this.setCultivo(null);
		return false;
	}

	public CultivoBean() {
		this.removerSesion(Cultivo.class.toString());
	}

	public String Registrar() {
		String retorno = "GEM001";
		this.setCultivo(new Cultivo());
		this.getCultivo().set_descripcion(getDescripcion());
		this.getCultivo().set_nombre(getNombre());
		this.getCultivo().set_estado(Estado.valueOf(estado));
		this.setCultivo(this.getGEMFachada(ServicioGEM.Cultivo)
				.RegistrarCultivo(this.getCultivo()));
		if (this.getCultivo() != null) {
			this.setSesion(Cultivo.class.toString(), this.getCultivo());
			nombre = "";
			descripcion = "";
			estado = estados[1].getValue().toString();
			this.setDisableBtnProp(false);
			this
					.setExito("Se registro exitosamente el cultivo.");
		} else {
			this.setError("No se pudo registrar el cultivo, por favor intente nuevamente.");
		}
		return retorno;
	}

	public String Propiedades() {
		String retorno = "";
		this.setCultivo((Cultivo) this.getSesion(Cultivo.class.toString()));
		if (this.getCultivo() == null) {
			this.setError("Debe ingresar los datos del cultivo y registrarlo "
					+ "previamente a realizar el ingreso de la propiedades.");
		} else if (this.getCultivo().get_id() == 0) {
			this.setError("Debe ingresar los datos del cultivo y registrarlo "
					+ "previamente a realizar el ingreso de la propiedades.");
		} else {
			retorno = "GEM003";
		}
		return retorno;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public SelectItem[] getEstados() {
		return estados;
	}

	public void setEstados(SelectItem[] estados) {
		this.estados = estados;
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isDisableBtnProp() {
		return disableBtnProp;
	}

	public void setDisableBtnProp(boolean disableBtnProp) {
		this.disableBtnProp = disableBtnProp;
	}

}
