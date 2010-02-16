package com.bean.adm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.ListaCriterioSeleccion;
import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;
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
	private List<ListaCriterioSeleccion> listaCriterio;
	private SelectItem[] criterio;
	private ListaCriterioSeleccion criterios;
	private String criterioSeleccionado;
	private boolean disableSeleccionCriterio = false;
	private boolean disableAceptarValorSeleccion = true;
	private List<ValorSeleccion> listaValores;
	private ValorSeleccion valor;

	
	public boolean isInit() {
		try {
			this.setError("");
			this.setExito("");
			
			this.setCriterios(null);
			this.setCriterios((ListaCriterioSeleccion) this.getSesion(ListaCriterioSeleccion.class.toString()));
			if (this.getCriterios() != null) {
				criterio = new SelectItem[1];
				criterio[0] = new SelectItem(this.getCriterios().get_descripcion());
				this.setCriterioSeleccionado(this.getCriterios().get_descripcion());
				if (this.getCriterios().get_listaValores() != null
						&& !this.getCriterios().get_listaValores().isEmpty()) {
					this.setListaValores(this.getCriterios().get_listaValores());
				} else {
					this.setListaValores(new ArrayList<ValorSeleccion>());
				}
				criterioSeleccionado = this.getCriterios().get_descripcion();
				this.setDisableSeleccionCriterio(true);
			} else {
				List<ListaCriterioSeleccion> listaCriterios = this.getAdmFachada(ServicioADM.ListaCriterio).ObtenerListaCriterio(null);
				criterio = new SelectItem[listaCriterio.size() + 1];
				criterio[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				int i = 1;
				for (ListaCriterioSeleccion c : listaCriterio) {
					SelectItem si = new SelectItem(c.get_descripcion());
					criterio[i] = si;
					i++;
				}
				criterioSeleccionado = this.getTextBundleKey("combo_seleccione");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return false;
	}

	public ValorSeleccionBean() {
		try {
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}
	
	public void TakeSelectionListaCriterio() {
		try {
			criterios = new ListaCriterioSeleccion();
			if (!this.getCriterioSeleccionado().isEmpty()
					&& !this.getCriterioSeleccionado().equals(
							this.getTextBundleKey("combo_seleccione"))) {

				this.getCriterios().set_descripcion(this.getCriterioSeleccionado());
				this.setCriterios(this.getAdmFachada(ServicioADM.ListaCriterio).ObtenerCriterio(this.getCriterios()));
				if (this.getCriterios().get_listaValores()!= null) {
					this.setListaValores(this.getCriterios().get_listaValores());
					this.setDisableAceptarValorSeleccion(false);
				} else {
					this.setListaValores(new ArrayList<ValorSeleccion>());
				}
				this.setError("");
				this.setExito("");
			} else {
				this.setDisableAceptarValorSeleccion(true);
				this.setCodigo("");
				this.setDescripcion("");
				this.setUnidadMedida("");
				this.setEstado("");
				this.setListaValores(new ArrayList<ValorSeleccion>());
				this.setError("");
				this.setExito("");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
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

	public List<ListaCriterioSeleccion> getListaCriterio() {
		return listaCriterio;
	}

	public void setListaCriterio(List<ListaCriterioSeleccion> listaCriterio) {
		this.listaCriterio = listaCriterio;
	}

	public SelectItem[] getCriterio() {
		return criterio;
	}

	public void setCriterio(SelectItem[] criterio) {
		this.criterio = criterio;
	}

	public ListaCriterioSeleccion getCriterios() {
		return criterios;
	}

	public void setCriterios(ListaCriterioSeleccion criterios) {
		this.criterios = criterios;
	}

	public String getCriterioSeleccionado() {
		return criterioSeleccionado;
	}

	public void setCriterioSeleccionado(String criterioSeleccionado) {
		this.criterioSeleccionado = criterioSeleccionado;
	}

	public boolean isDisableSeleccionCriterio() {
		return disableSeleccionCriterio;
	}

	public void setDisableSeleccionCriterio(boolean disableSeleccionCriterio) {
		this.disableSeleccionCriterio = disableSeleccionCriterio;
	}

	public boolean isDisableAceptarValorSeleccion() {
		return disableAceptarValorSeleccion;
	}

	public void setDisableAceptarValorSeleccion(boolean disableAceptarValorSeleccion) {
		this.disableAceptarValorSeleccion = disableAceptarValorSeleccion;
	}

	public List<ValorSeleccion> getListaValores() {
		return listaValores;
	}

	public void setListaValores(List<ValorSeleccion> listaValores) {
		this.listaValores = listaValores;
	}

	public ValorSeleccion getValor() {
		return valor;
	}

	public void setValor(ValorSeleccion valor) {
		this.valor = valor;
	}

	
}
