package com.bean.adm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
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
	private String unidadMedida;
	private List<ListaCriterioSeleccion> listaCriterio;
	private SelectItem[] criterio;
	private ListaCriterioSeleccion criterios;
	private String criterioSeleccionado;
	private boolean disableSeleccionCriterio = false;
	private boolean disableAceptarValorSeleccion = true;
	private List<ValorSeleccion> listaValores;
	private ValorSeleccion valor;
	private boolean recargo = true;

	public boolean isInit() {
		try {
			this.setError("");
			this.setExito("");

				List<ListaCriterioSeleccion> listaCriterio = this
						.getAdmFachada(ServicioADM.ListaCriterio).ObtenerListaCriterio();
						
				criterio = new SelectItem[listaCriterio.size() + 1];
				criterio[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				int i = 1;
				for (ListaCriterioSeleccion c : listaCriterio) {
					SelectItem si = new SelectItem(c.get_descripcion());
					criterio[i] = si;
					i++;
				}
				criterioSeleccionado = this
						.getTextBundleKey("combo_seleccione");
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

				this.getCriterios().set_descripcion(
						this.getCriterioSeleccionado());
				this.setCriterios(this.getAdmFachada(ServicioADM.ListaCriterio)
						.ObtenerCriterio(this.getCriterios()));
				if (this.getCriterios().get_listaValores() != null) {
					this
							.setListaValores(this.getCriterios()
									.get_listaValores());
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
				this.setListaValores(new ArrayList<ValorSeleccion>());
				this.setError("");
				this.setExito("");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public String ModificarValorSeleccion() {
		this.setError("");
		this.setExito("");
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String valorElegido = (String) paramMap.get("valorElegido");
		Iterator<ValorSeleccion> it = this.getListaValores().iterator();
		while (it.hasNext()) {
			ValorSeleccion valorSeleccionado = (ValorSeleccion) it.next();
			if (valorSeleccionado.get_codigo().equalsIgnoreCase(valorElegido)) {
				this.setValor(valorSeleccionado);
				this.setCodigo(this.getValor().get_codigo());
				this.setDescripcion(this.getValor().get_descripcion());
				this.setUnidadMedida(this.getValor().get_unidadMedida());
				break;
			}
		}
		return "ADM003";
	}

	public String EliminarValorSeleccion() {
		this.setError("");
		this.setExito("");
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String valorElegido = (String) paramMap.get("valorElegido");
		Iterator<ValorSeleccion> it = this.getListaValores().iterator();
		while (it.hasNext()) {
			ValorSeleccion valorSeleccionado = (ValorSeleccion) it.next();
			if (valorSeleccionado.get_codigo().equalsIgnoreCase(valorElegido)) {
				this.setValor(valorSeleccionado);
				break;
			}
		}
		this.getListaValores().remove(this.getValor());
		this.setExito("Se elimino el Valor de Selección de la lista.");
		this.setValor(null);
		this.setCodigo("");
		this.setDescripcion("");
		this.setUnidadMedida("");

		return "ADM003";
	}

	public String AceptarValorSeleccion() {
		this.setError("");
		this.setExito("");
		if (this.getValor() != null) {
			this.getValor().set_codigo(this.getCodigo());
			this.getValor().set_descripcion(this.getDescripcion());
			this.getValor().set_unidadMedida(this.getUnidadMedida());

			this.setExito("Se modifico correctamente el Valor de Selección.");
			this.setValor(null);
			this.setCodigo("");
			this.setDescripcion("");
			this.setUnidadMedida("");
		} else if (!ExisteValorSeleccion(this.getCodigo())) {
			this.setValor(new ValorSeleccion());
			this.getValor().set_codigo(this.getCodigo());
			this.getValor().set_descripcion(this.getDescripcion());
			this.getValor().set_unidadMedida(this.getUnidadMedida());

			this.getListaValores().add(this.getValor());
			this.setExito("Se agrego correctamente el Valor de Selección.");
			this.setValor(null);
			this.setCodigo("");
			this.setDescripcion("");
			this.setUnidadMedida("");
		} else {
			this
					.setError("Ya existe un Valor de Selección ingresado con igual Código");
		}
		return "ADM003";
	}

	private boolean ExisteValorSeleccion(String pValor) {
		boolean existe = false;
		for (ValorSeleccion val : this.getListaValores()) {
			if (val.get_codigo().equalsIgnoreCase(pValor)) {
				existe = true;
				break;
			}
		}
		return existe;
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

	public void setDisableAceptarValorSeleccion(
			boolean disableAceptarValorSeleccion) {
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
