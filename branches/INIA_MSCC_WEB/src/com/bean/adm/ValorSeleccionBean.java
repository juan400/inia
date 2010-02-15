package com.bean.adm;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioADM;

public class ValorSeleccionBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String codigo;
	private String descripcion;
	private String estado;
	private String unidadMedida;
	private ValorSeleccion valorSelec = new ValorSeleccion();
	private List<ValorSeleccion> valores;

	
	public boolean isInit() {
		this.setValores(this.getAdmFachada(ServicioADM.ValorSeleccion).ObtenerValores());
		return false;
	}

	public ValorSeleccionBean() {
		this.estado = Estado.Activo.name();
	}

	public String actualizar() throws Exception {
		String retorno = "";
		try {
			if (valorSelec.get_descripcion()
					.equalsIgnoreCase(this.getDescripcion())) {
				valorSelec.set_codigo(codigo);
				valorSelec.set_descripcion(descripcion);
				valorSelec.set_estado(Enumerados.Estado.valueOf(estado));
				this.getAdmFachada(ServicioADM.Transaccion)
						.ActualizarValorSeleccion(valorSelec);
				retorno = "registro-ok";
			} else {
				valorSelec.set_codigo(codigo);
				valorSelec.set_descripcion(descripcion);
				valorSelec.set_estado(Enumerados.Estado.valueOf(estado));
				ValorSeleccion val = this.getAdmFachada(ServicioADM.ValorSeleccion)
						.ComprobarValorSeleccion(valorSelec);
				if (val == null) {
					setError("");
					this.getAdmFachada(ServicioADM.ValorSeleccion)
							.ActualizarValorSeleccion(valorSelec);
					retorno = "registro-ok";
				} else {
					this
							.setError("Ya existe un Valor de Selección con igual descripción, Por favor ingrese otro.");
					retorno = "";
				}
			}
		} catch (Exception ex) {
			this
					.setError("Se ha producido un error, por favor intente nuevamente.");
		}
		return retorno;
	}

	public String verValorSeleccion() {
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String valorElegido = (String) paramMap.get("valorElegido");
		Iterator<ValorSeleccion> it = valores.iterator();
		while (it.hasNext()) {
			ValorSeleccion valorSeleccionado = (ValorSeleccion) it.next();
			if (valorSeleccionado.get_id() == (long) Long
					.parseLong(valorElegido)) {
				valorSelec = valorSeleccionado;

				codigo = valorSelec.get_codigo();
				descripcion = valorSelec.get_descripcion();
				estado = valorSelec.get_estado().toString();
				unidadMedida = valorSelec.get_unidadMedida();
				
			}
		}
		return "resultados";
	}

	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public ValorSeleccion getValorSelec() {
		return valorSelec;
	}

	public void setValorSelec(ValorSeleccion valorSelec) {
		this.valorSelec = valorSelec;
	}

	public List<ValorSeleccion> getValores() {
		return valores;
	}

	public void setValores(List<ValorSeleccion> valores) {
		this.valores = valores;
	}	
}
