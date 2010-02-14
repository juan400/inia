package com.bean.gem;

import java.io.Serializable;
import java.util.Date;

import javax.faces.model.SelectItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
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

	public CultivoBean() {
		estados = new SelectItem[Estado.values().length];
		SelectItem si = new SelectItem(Estado.Activo.name());
		estados[0] = si;
		si = new SelectItem(Estado.Activo.name());
		estados[1] = si;
		estado = estados[1].getValue().toString();
		cultivo = null;
	}

	public String Registrar() {
		String retorno = "GEM001";
		cultivo = new Cultivo();
		cultivo.set_descripcion(getDescripcion());
		cultivo.set_nombre(getNombre());
		cultivo.set_estado(Estado.valueOf(estado));
		this.setExito("Se registro exitosamente el cultivo");
		return retorno;
	}

	public String Propiedades() {
		String retorno = "GEM003";
		if (cultivo != null) {
			this.setSesion(Cultivo.class.toString(), cultivo);
		}
		else{

			this.setError("Se registro exitosamente el cultivo");
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

}
