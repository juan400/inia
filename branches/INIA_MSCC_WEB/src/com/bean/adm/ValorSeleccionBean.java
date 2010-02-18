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
import com.inia_mscc.modulos.comun.entidades.Enumerados.ListaCriterio;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioADM;

public class ValorSeleccionBean extends MaestroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ListaCriterioSeleccion> listaCriterio;
	private SelectItem[] criterios;
	private String criterioSeleccionado;
	private boolean disableSeleccionCriterio = false;
	private boolean disableAceptarValor = true;
	private List<ValorSeleccion> listaValores;
	private List<ValorSeleccion> listaValorEliminadas;
	private ValorSeleccion valor;
	private String codigo;
	private String descripcion;
	private String unidad;
	private boolean recargo;
	private ListaCriterioSeleccion criterio;

	public boolean isInitPantalla() {
		try {
			if (this.getSesion(ListaCriterioSeleccion.class.toString()) == null
					&& this.getCriterio() == null) {
				this.setDisableSeleccionCriterio(false);
				this.setDisableAceptarValor(true);
				List<ListaCriterioSeleccion> listaCriterio = this
						.getAdmFachada(ServicioADM.ListaCriterio)
						.ObtenerListaCriterio();

				criterios = new SelectItem[listaCriterio.size() + 1];
				criterios[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				int i = 1;
				for (ListaCriterioSeleccion c : listaCriterio) {
					SelectItem si = new SelectItem(c.get_descripcion());
					criterios[i] = si;
					i++;
				}
				criterioSeleccionado = this
						.getTextBundleKey("combo_seleccione");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return false;
	}

	public ValorSeleccionBean() {
		try {
			setRecargo(true);
			this.setError("");
			this.setExito("");
			this.setCodigo("");
			this.setDescripcion("");
			this.setUnidad("");
			this.setListaValorEliminadas(null);
			this.setListaValores(null);

			ListaCriterioSeleccion unCriterio = (ListaCriterioSeleccion) this
					.getSesion(ListaCriterioSeleccion.class.toString());
			if (unCriterio != null) {
				this.setCriterio(null);
				this.setCriterio(unCriterio);
				criterios = new SelectItem[1];
				criterios[0] = new SelectItem(this.getCriterio()
						.get_descripcion());
				this.setCriterioSeleccionado(this.getCriterio()
						.get_descripcion());
				if (this.getCriterio().get_listaValores() != null
						&& this.getCriterio().get_listaValores().size() != 0) {
					this.setListaValores(this.getCriterio().get_listaValores());
				} else {
					this.setListaValores(new ArrayList<ValorSeleccion>());
				}
				criterioSeleccionado = this.getCriterio().get_descripcion();
				this.setDisableSeleccionCriterio(true);
				this.setDisableAceptarValor(false);
			} else {
				List<ListaCriterioSeleccion> listaCriterios = this
						.getAdmFachada(ServicioADM.ListaCriterio)
						.ObtenerListaCriterio();

				criterios = new SelectItem[listaCriterios.size() + 1];
				criterios[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				int i = 1;
				for (ListaCriterioSeleccion c : listaCriterios) {
					SelectItem si = new SelectItem(c.get_descripcion());
					criterios[i] = si;
					i++;
				}
				criterioSeleccionado = this
						.getTextBundleKey("combo_seleccione");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void TakeSelectionCriterio() {
		try {
			setRecargo(false);
			criterio = new ListaCriterioSeleccion();
			if (!this.getCriterioSeleccionado().isEmpty()
					&& !this.getCriterioSeleccionado().equals(
							this.getTextBundleKey("combo_seleccione"))) {
				this.setListaValorEliminadas(new ArrayList<ValorSeleccion>());
				this.getCriterio().set_descripcion(
						this.getCriterioSeleccionado());
				this.setCriterio(this.getAdmFachada(ServicioADM.ListaCriterio)
						.ObtenerCriterio(this.getCriterio()));

				this.setDisableAceptarValor(false);

				if (this.getCriterio().get_listaValores() != null) {
					this.setListaValores(this.getCriterio().get_listaValores());
				} else {
					this.setListaValores(new ArrayList<ValorSeleccion>());
				}
				this.setError("");
				this.setExito("");
			} else {
				this.setDisableAceptarValor(true);
				this.setCodigo("");
				this.setDescripcion("");
				this.setUnidad("");

				this.setListaValores(new ArrayList<ValorSeleccion>());
				this.setListaValorEliminadas(new ArrayList<ValorSeleccion>());
				this.setValor(null);
				this.setError("");
				this.setExito("");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public String ModificarValor() {
		setRecargo(false);
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
				this.setUnidad(this.getValor().get_unidadMedida());
				break;
			}
		}
		return "modificar";
	}

	public String EliminarValor() {
		setRecargo(false);
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
		this.getListaValorEliminadas().add(this.getValor());
		this.getListaValores().remove(this.getValor());
		this.setExito("Se elimino el Valor de Selección de la lista.");
		this.setValor(null);
		this.setCodigo("");
		this.setDescripcion("");
		this.setUnidad("");

		return "eliminar";
	}

	public String AceptarValor() {
		try {
			setRecargo(false);
			this.setError("");
			this.setExito("");
			if (!ExisteValor(this.getCodigo())) {
				this.setValor(new ValorSeleccion());
				this.getValor().set_grabada(false);
				this.getValor().set_criterio(this.getCriterio());
				this.getValor().set_codigo(this.getCodigo());
				this.getValor().set_descripcion(this.getDescripcion());
				this.getValor().set_unidadMedida(this.getUnidad());

				this.getListaValores().add(this.getValor());
				this
						.setExito("Se ingresó correctamente el Valor de Selección a la Lista de Criterios.");
				this.setValor(null);
				this.setCodigo("");
				this.setDescripcion("");
				this.setUnidad("");
			} else {
				this
						.setError("Ya Existe un Valor de Selección agregado en la lista con el mismo Código.");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return "aceptar";
	}

	private boolean ExisteValor(String pCodigo) {
		boolean existe = false;
		if (this.getValor() != null) {
			if (!this.getValor().get_codigo().equals(pCodigo)) {
				for (ValorSeleccion val : this.getListaValores()) {
					if (val.get_codigo().equalsIgnoreCase(pCodigo)) {
						existe = true;
						break;
					}
				}
			}
		} else {
			if (this.getListaValores() != null) {
				for (ValorSeleccion val : this.getListaValores()) {
					if (val.get_codigo().equalsIgnoreCase(pCodigo)) {
						existe = true;
						break;
					}
				}
			}
		}
		return existe;
	}

	public String AceptarModificacion() {
		String retorno = "ADM004";
		setRecargo(false);
		this.setError("");
		this.setExito("");
		if (this.getValor() != null) {
			if (!ExisteValor(this.getCodigo())) {
				this.getValor().set_codigo(this.getCodigo());
				this.getValor().set_descripcion(this.getDescripcion());
				this.getValor().set_unidadMedida(this.getUnidad());

				this
						.setExito("Se modifico correctamente el Valor de Seleccion.");
				this.setValor(null);
				this.setCodigo("");
				this.setDescripcion("");
				this.setUnidad("");
			} else {
				this
						.setError("Ya Existe un Valor de Selección agregado en la lista con el mismo Código.");
				retorno = "";
			}
		} else {
			this.setError("Debe seleccionar un Valor para modificar.");
			retorno = "";
		}
		return retorno;
	}

	public String RegistrarValores() {
		try {
			this.getAdmFachada(ServicioADM.ListaCriterio).ActualizarListaCriterio(this.getCriterio());
			
			this.setRecargo(true);
			this
					.setExito("Se ingresó exitosamente la Lista de Criterios y sus Valores de Selección.");
			
			if (this.getSesion(ListaCriterio.class.toString()) !=null){
				this.removerSesion(ListaCriterio.class.toString());
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return "ADM003";
	}
	
	public String ModificarValores() {
		try {
			if (this.getListaValorEliminadas()!= null
					&& !this.getListaValorEliminadas().isEmpty()) {
				
				this.getAdmFachada(ServicioADM.ValorSeleccion).EliminarValores(this.getListaValorEliminadas());
			}
			this.getAdmFachada(ServicioADM.ListaCriterio).ActualizarListaCriterio(this.getCriterio());
			this.setRecargo(true);
			this
					.setExito("Se actualizó correctamente la Lista de Criterios y sus Valores de Selección.");
			if (this.getSesion(ListaCriterioSeleccion.class.toString()) != null) {
				this.removerSesion(ListaCriterioSeleccion.class.toString());
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return "ADM004";
	}
	
	public List<ListaCriterioSeleccion> getListaCriterio() {
		return listaCriterio;
	}

	public void setListaCriterio(List<ListaCriterioSeleccion> listaCriterio) {
		this.listaCriterio = listaCriterio;
	}

	public SelectItem[] getCriterios() {
		return criterios;
	}

	public void setCriterios(SelectItem[] criterios) {
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

	public List<ValorSeleccion> getListaValores() {
		return listaValores;
	}

	public void setListaValores(List<ValorSeleccion> listaValores) {
		this.listaValores = listaValores;
	}

	public List<ValorSeleccion> getListaValorEliminadas() {
		return listaValorEliminadas;
	}

	public void setListaValorEliminadas(
			List<ValorSeleccion> listaValorEliminadas) {
		this.listaValorEliminadas = listaValorEliminadas;
	}

	public ValorSeleccion getValor() {
		return valor;
	}

	public void setValor(ValorSeleccion valor) {
		this.valor = valor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public ListaCriterioSeleccion getCriterio() {
		return criterio;
	}

	public void setCriterio(ListaCriterioSeleccion criterio) {
		this.criterio = criterio;
	}

	public void setDisableAceptarValor(boolean disableAceptaValor) {
		this.disableAceptarValor = disableAceptaValor;
	}

	public boolean isDisableAceptarValor() {
		return disableAceptarValor;
	}

	public void setRecargo(boolean recargo) {
		this.recargo = recargo;
	}

	public boolean isRecargo() {
		return recargo;
	}

}
