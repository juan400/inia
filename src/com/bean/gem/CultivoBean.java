package com.bean.gem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
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
	private String cultivoElegido;
	private SelectItem[] cultivos;
	private List<Cultivo> listaCultivo;
	private boolean recargo = true;

	public boolean isInit() {
		this.limpiarBean();
		if (recargo) {
			this.setListaCultivo(this.getGEMFachada(ServicioGEM.Cultivo)
					.ObtenerCultivos(null));
			if (this.getListaCultivo() == null) {
				this.setListaCultivo(new ArrayList<Cultivo>());
			}
			cultivos = new SelectItem[listaCultivo.size() + 1];
			cultivos[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int i = 1;
			for (Cultivo c : listaCultivo) {
				SelectItem si = new SelectItem(c.get_nombre());
				cultivos[i] = si;
				i++;
			}
			cultivoElegido = this.getTextBundleKey("combo_seleccione");

			estados = new SelectItem[Estado.values().length];
			SelectItem si = new SelectItem(Estado.Activo.name());
			estados[0] = si;
			si = new SelectItem(Estado.Inactivo.name());
			estados[1] = si;
			estado = estados[0].getValue().toString();
			this.setCultivo(null);
		}
		return false;
	}

	public CultivoBean() {
		this.setListaCultivo(this.getGEMFachada(ServicioGEM.Cultivo)
				.ObtenerCultivos(null));
		this.removerSesion(Cultivo.class.toString());
	}

	public String Registrar() {
		String retorno = "GEM001";
		try {
			this.setCultivo(new Cultivo());
			this.getCultivo().set_descripcion(getDescripcion());
			this.getCultivo().set_nombre(getNombre());
			this.getCultivo().set_estado(Estado.valueOf(estado));

			Cultivo cul = this.getGEMFachada(ServicioGEM.Cultivo)
					.ComprobarCultivo(this.getCultivo());
			if (cul == null) {
				this.getGEMFachada(ServicioGEM.Cultivo).RegistrarCultivo(
						this.getCultivo());
				limpiarBean();
				this.setDisableBtnProp(false);
				this.setExito("Se registro exitosamente el cultivo.");
			} else {
				this
						.setError("Ya existe un Cultivo con igual nombre, Por favor ingrese otro nombre.");
			}
		} catch (Exception ex) {
			this
					.setError("Se ha producido un error, por favor intente nuevamente.");
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

	private void limpiarBean() {
		nombre = "";
		descripcion = "";
		estado = "";
		this.setError("");
		this.setExito("");
	}

	public String Actualizar() {
		String retorno = "GEM002";
		try {
			if (cultivo.get_nombre().equalsIgnoreCase(this.getNombre())) {
				cultivo.set_nombre(nombre);
				cultivo.set_descripcion(descripcion);
				cultivo.set_estado(Enumerados.Estado.valueOf(estado));

				this.getGEMFachada(ServicioGEM.Cultivo).ActualizarCultivo(
						this.getCultivo());
				limpiarBean();
			} else {
				cultivo.set_nombre(nombre);
				cultivo.set_descripcion(descripcion);
				cultivo.set_estado(Enumerados.Estado.valueOf(estado));

				Cultivo cul = this.getGEMFachada(ServicioGEM.Cultivo)
						.ComprobarCultivo(this.getCultivo());
				if (cul == null) {
					setError("");
					this.getGEMFachada(ServicioGEM.Cultivo).ActualizarCultivo(
							this.getCultivo());
					limpiarBean();
				} else {
					this
							.setError("Ya existe un Cultivo con igual nombre, Por favor ingrese otro nombre.");
				}
			}
		} catch (Exception ex) {
			this
					.setError("Se ha producido un error, por favor intente nuevamente.");
		}
		return retorno;
	}

	public void TakeSelectionCultivo() {
		try {
			recargo = false;
			cultivo = new Cultivo();
			if (!this.getCultivoElegido().isEmpty()
					&& !this.getCultivoElegido().equals(
							this.getTextBundleKey("combo_seleccione"))) {

				this.getCultivo().set_nombre(this.getCultivoElegido());

				this.setCultivo(this.BuscarCultivo(this.getCultivo()
						.get_nombre()));

				this.setNombre(this.getCultivo().get_nombre());
				this.setDescripcion(this.getCultivo().get_descripcion());
				this.setEstado(this.getCultivo().get_estado().name());
				this.setError("");
				this.setExito("");
			} else {
				this.setNombre("");
				this.setDescripcion("");
				this.setEstado("");
				this.setError("");
				this.setExito("");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	private Cultivo BuscarCultivo(String pNombre) {
		Cultivo cultivoBuscado = null;
		if (this.getListaCultivo() != null && !this.getListaCultivo().isEmpty()) {
			for (Cultivo cultivo : this.getListaCultivo()) {
				if (cultivo.get_nombre().equals(pNombre)) {
					cultivoBuscado = cultivo;
				}
			}
		}
		return cultivoBuscado;
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

	public String getCultivoElegido() {
		return cultivoElegido;
	}

	public void setCultivoElegido(String cultivoElegido) {
		this.cultivoElegido = cultivoElegido;
	}

	public SelectItem[] getCultivos() {
		return cultivos;
	}

	public void setCultivos(SelectItem[] cultivos) {
		this.cultivos = cultivos;
	}

	public void setListaCultivo(List<Cultivo> listaCultivo) {
		this.listaCultivo = listaCultivo;
	}

	public List<Cultivo> getListaCultivo() {
		return listaCultivo;
	}

}
