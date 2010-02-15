package com.bean.gem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioGEM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoPropiedadCultivo;
import com.inia_mscc.modulos.gem.entidades.Cultivo;
import com.inia_mscc.modulos.gem.entidades.Propiedad;

public class PropiedadesBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Cultivo> listaCultivos;
	private SelectItem[] cultivos;
	private Cultivo cultivo;
	private String cultivoSeleccionado;
	private boolean disableSeleccionCultivo = false;
	private boolean disableAceptarPropiedad = true;
	private List<Propiedad> listaPropiedades;
	private SelectItem[] tipoPropiedades;
	private String tipoSeleccionado;
	private Propiedad propiedad;
	private Date fecha;
	private String codigo;
	private String nombre;
	private String unidadedida;

	public boolean isInit() {
		try {
			tipoPropiedades = new SelectItem[TipoPropiedadCultivo.values().length];
			for (int i = 0; i < TipoPropiedadCultivo.values().length; i++) {
				TipoPropiedadCultivo tipo = TipoPropiedadCultivo.values()[i];
				SelectItem si = new SelectItem(tipo.name());
				tipoPropiedades[i] = si;
			}
			tipoSeleccionado = TipoPropiedadCultivo.Ninguno.name();
			this.setCultivo(null);
			this.setCultivo((Cultivo) this.getSesion(Cultivo.class.toString()));
			if (this.getCultivo() != null) {
				cultivos = new SelectItem[1];
				cultivos[0] = new SelectItem(this.getCultivo().get_nombre());
				this.setCultivoSeleccionado(this.getCultivo().get_nombre());
				if (this.getCultivo().get_listaPropiedades() != null
						&& !this.getCultivo().get_listaPropiedades().isEmpty()) {
					this.setListaPropiedades(this.getCultivo()
							.get_listaPropiedades());
				} else {
					this.setListaPropiedades(new ArrayList<Propiedad>());
				}
				cultivoSeleccionado = this.getCultivo().get_nombre();
				this.setDisableSeleccionCultivo(true);
			} else {
				List<Cultivo> listaCultivos = this.getGEMFachada(
						ServicioGEM.Cultivo).ObtenerCultivos(null);
				cultivos = new SelectItem[listaCultivos.size() + 1];
				cultivos[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				int i = 1;
				for (Cultivo c : listaCultivos) {
					SelectItem si = new SelectItem(c.get_nombre());
					cultivos[i] = si;
					i++;
				}
				cultivoSeleccionado = this.getTextBundleKey("combo_seleccione");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return false;
	}

	public PropiedadesBean() {
		try {
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void TakeSelectionCultivo() {
		try {
			cultivo = new Cultivo();
			if (!this.getCultivoSeleccionado().isEmpty()) {
				this.getCultivo().set_nombre(this.getCultivoSeleccionado());
				this.setCultivo(this.getGEMFachada(ServicioGEM.Cultivo)
						.ObtenerCultivo(this.getCultivo()));
				if (this.getCultivo().get_listaPropiedades() != null) {
					this.setListaPropiedades(this.getCultivo()
							.get_listaPropiedades());
				} else {
					this.setListaPropiedades(new ArrayList<Propiedad>());
				}
			} else {
				this.setListaPropiedades(new ArrayList<Propiedad>());
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public String ModificarPropiedad() {
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String propiedadElegida = (String) paramMap.get("propiedadElegida");
		Iterator<Propiedad> it = this.getListaPropiedades().iterator();
		while (it.hasNext()) {
			Propiedad propiedadSeleccionada = (Propiedad) it.next();
			if (propiedadSeleccionada.get_codigo().equalsIgnoreCase(
					propiedadElegida)) {
				disableAceptarPropiedad = false;
				this.setPropiedad(propiedadSeleccionada);
				this.setCodigo(this.getPropiedad().get_codigo());
				this.setNombre(this.getPropiedad().get_nombre());
				this.setTipoSeleccionado(this.getPropiedad().get_tipo().name());
				this.setUnidadedida(this.getPropiedad().get_unidadMedida());
				break;
			}
		}

		return "GEM003";
	}

	public String EliminarPropiedad() {
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String propiedadElegida = (String) paramMap.get("propiedadElegida");
		Iterator<Propiedad> it = this.getListaPropiedades().iterator();
		Propiedad prop = new Propiedad();
		while (it.hasNext()) {
			Propiedad propiedadSeleccionada = (Propiedad) it.next();
			if (propiedadSeleccionada.get_codigo().equalsIgnoreCase(
					propiedadElegida)) {
				disableAceptarPropiedad = true;
				prop = propiedadSeleccionada;
				break;
			}
		}
		this.getListaPropiedades().remove(prop);
		this.setExito("Se elimino la propiedad de la lista acutla");
		return "GEM003";
	}

	public String AceptarPropiedad() {
		if (this.getPropiedad() != null) {
			this.getPropiedad().set_codigo(this.getCodigo());
			this.getPropiedad().set_nombre(this.getNombre());
			this.getPropiedad().set_unidadMedida(this.getUnidadedida());
			this.getPropiedad().set_tipo(
					TipoPropiedadCultivo.valueOf(this.getTipoSeleccionado()));
			this.setCodigo("");
			this.setNombre("");
			this.setUnidadedida("");
			this.setTipoSeleccionado(Enumerados.TipoPropiedadCultivo.Ninguno
					.name());
			this.setExito("Se realizarion los comabios "
					+ "correctamente la propiedada del cultivo.");
			disableAceptarPropiedad = true;
		} else {
			this.setPropiedad(new Propiedad());
			this.getPropiedad().set_codigo(this.getCodigo());
			this.getPropiedad().set_nombre(this.getNombre());
			this.getPropiedad().set_unidadMedida(this.getUnidadedida());
			this.getPropiedad().set_tipo(
					TipoPropiedadCultivo.valueOf(this.getTipoSeleccionado()));
			this.setCodigo("");
			this.setNombre("");
			this.setUnidadedida("");
			this.getListaPropiedades().add(this.getPropiedad());
			this.setExito("Se agrego correctamente la propiedad al cultivo");
		}
//		Iterator<Propiedad> it = this.getListaPropiedades().iterator();
//		Propiedad propiedadSeleccionada = null;
//		while (it.hasNext()) {
//			propiedadSeleccionada = (Propiedad) it.next();
//			if (propiedadSeleccionada.get_codigo().equalsIgnoreCase(
//					this.getPropiedad().get_codigo())) {
//				disableAceptarPropiedad = false;
//				propiedadSeleccionada.set_codigo(this.getCodigo());
//				propiedadSeleccionada.set_nombre(this.getNombre());
//				propiedadSeleccionada.set_unidadMedida(this.getUnidadedida());
//				this.setPropiedad(propiedadSeleccionada);
//				this.setCodigo("");
//				this.setNombre("");
//				this.setUnidadedida("");
//				this
//						.setTipoSeleccionado(Enumerados.TipoPropiedadCultivo.Ninguno
//								.name());
//				this.setExito("Se realizarion los comabios "
//						+ "correctamente la propiedada para el cultivo.");
//			}
//		}
//		if (propiedadSeleccionada != null) {
//			this.getListaPropiedades().add(propiedadSeleccionada);
//			this.setExito("Se agrego correctamente la propiedad al cultivo");
//		} else {
//
//		}
		return "";
	}

	public List<Cultivo> getListaCultivos() {
		return listaCultivos;
	}

	public void setListaCultivos(List<Cultivo> listaCultivos) {
		this.listaCultivos = listaCultivos;
	}

	public SelectItem[] getCultivos() {
		return cultivos;
	}

	public void setCultivos(SelectItem[] cultivos) {
		this.cultivos = cultivos;
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	public String getCultivoSeleccionado() {
		return cultivoSeleccionado;
	}

	public void setCultivoSeleccionado(String cultivoSeleccionado) {
		this.cultivoSeleccionado = cultivoSeleccionado;
	}

	public boolean isDisableSeleccionCultivo() {
		return disableSeleccionCultivo;
	}

	public void setDisableSeleccionCultivo(boolean disableSeleccionCultivo) {
		this.disableSeleccionCultivo = disableSeleccionCultivo;
	}

	public List<Propiedad> getListaPropiedades() {
		return listaPropiedades;
	}

	public void setListaPropiedades(List<Propiedad> listaPropiedades) {
		this.listaPropiedades = listaPropiedades;
	}

	public SelectItem[] getTipoPropiedades() {
		return tipoPropiedades;
	}

	public void setTipoPropiedades(SelectItem[] tipoPropiedades) {
		this.tipoPropiedades = tipoPropiedades;
	}

	public Propiedad getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidadedida() {
		return unidadedida;
	}

	public void setUnidadedida(String unidadedida) {
		this.unidadedida = unidadedida;
	}

	public String getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	public void setTipoSeleccionado(String tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
	}

	public boolean isDisableAceptarPropiedad() {
		return disableAceptarPropiedad;
	}

	public void setDisableAceptarPropiedad(boolean disableAceptarPropiedad) {
		this.disableAceptarPropiedad = disableAceptarPropiedad;
	}
}