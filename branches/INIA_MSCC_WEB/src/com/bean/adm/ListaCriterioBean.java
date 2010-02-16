package com.bean.adm;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.ListaCriterioSeleccion;
import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;

public class ListaCriterioBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private SelectItem[] listasCriterioCombo;
	private List<ListaCriterioSeleccion> listasCriterio;
	private ListaCriterioSeleccion listaCriterio;
	private String listaCriterioSeleccionada;

	private SelectItem[] valoresSeleccion;
	private List<ValorSeleccion> listaValorSeleccion;
	private ValorSeleccion valorSeleccion;
	private String valorSeleccionado;
	
	private String codigoValor;
	private String descripcionValor;
	private String unidadMedidaValor;
	
	public ListaCriterioBean() {
	}
	
	public void takeSelectionLista() {
		try {
			listaCriterio = new ListaCriterioSeleccion();
			listaCriterio.set_codigo(getListaCriterioSeleccionada());
			valoresSeleccion = new SelectItem[listaValorSeleccion.size() + 1];
			valoresSeleccion[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int l = 1;
			for (ValorSeleccion v : listaValorSeleccion) {
				SelectItem si = new SelectItem(v.get_descripcion());
				valoresSeleccion[l] = si;
				l++;
			}
			valorSeleccionado = "";
		
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void takeSelectionValorSelecion() {
		try {
			valorSeleccion = new ValorSeleccion();
			valorSeleccion.set_codigo(getValorSeleccionado());
			for (ValorSeleccion valor : listaValorSeleccion) {
				if (valor.get_codigo().equals(valorSeleccion.get_codigo())){
					
				}
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}


	public String getValorSeleccionado() {
		return valorSeleccionado;
	}

	public void setValorSeleccionado(String valorSeleccionado) {
		this.valorSeleccionado = valorSeleccionado;
	}

	public SelectItem[] getListasCriterioCombo() {
		return listasCriterioCombo;
	}

	public void setListasCriterioCombo(SelectItem[] listasCriterioCombo) {
		this.listasCriterioCombo = listasCriterioCombo;
	}

	public List<ListaCriterioSeleccion> getListasCriterio() {
		return listasCriterio;
	}

	public void setListasCriterio(List<ListaCriterioSeleccion> listasCriterio) {
		this.listasCriterio = listasCriterio;
	}

	public ListaCriterioSeleccion getListaCriterio() {
		return listaCriterio;
	}

	public void setListaCriterio(ListaCriterioSeleccion listaCriterio) {
		this.listaCriterio = listaCriterio;
	}

	public String getListaCriterioSeleccionada() {
		return listaCriterioSeleccionada;
	}

	public void setListaCriterioSeleccionada(String listaCriterioSeleccionada) {
		this.listaCriterioSeleccionada = listaCriterioSeleccionada;
	}

	public SelectItem[] getValoresSeleccion() {
		return valoresSeleccion;
	}

	public void setValoresSeleccion(SelectItem[] valoresSeleccion) {
		this.valoresSeleccion = valoresSeleccion;
	}

	public List<ValorSeleccion> getListaValorSeleccion() {
		return listaValorSeleccion;
	}

	public void setListaValorSeleccion(List<ValorSeleccion> listaValorSeleccion) {
		this.listaValorSeleccion = listaValorSeleccion;
	}

	public ValorSeleccion getValorSeleccion() {
		return valorSeleccion;
	}

	public void setValorSeleccion(ValorSeleccion valorSeleccion) {
		this.valorSeleccion = valorSeleccion;
	}

	public String getCodigoValor() {
		return codigoValor;
	}

	public void setCodigoValor(String codigoValor) {
		this.codigoValor = codigoValor;
	}

	public String getDescripcionValor() {
		return descripcionValor;
	}

	public void setDescripcionValor(String descripcionValor) {
		this.descripcionValor = descripcionValor;
	}

	public String getUnidadMedidaValor() {
		return unidadMedidaValor;
	}

	public void setUnidadMedidaValor(String unidadMedidaValor) {
		this.unidadMedidaValor = unidadMedidaValor;
	}

}
