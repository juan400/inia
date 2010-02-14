package com.bean.gem;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Ciudad;
import com.inia_mscc.modulos.adm.entidades.Departamento;
import com.inia_mscc.modulos.adm.entidades.Transaccion;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioADM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioGEM;
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
	private boolean deshabilitarSeleccionCultivo;
	private List<Propiedad> listaPropiedades;
	private SelectItem[] propiedades;
	private Propiedad propiedad;
	private String propiedadSeleccionada;
	private Date fecha;
	private String codigo;
	private String nombre;
	private String unidadedida;
	private String tipoPropiedad;

	public boolean isInit() {
		return false;
	}

	public PropiedadesBean() {
		this.setCultivo((Cultivo) this.getSesion(Cultivo.class.toString()));
		if (this.getCultivo() != null) {
			this.setCultivoSeleccionado(this.getCultivo().get_nombre());
			this.setListaPropiedades(this.getCultivo().get_listaPropiedades());
			this.setDeshabilitarSeleccionCultivo(true);
		} else {

		}
	}

	public void TakeSelectionCultivo() {
		try {
			cultivo = new Cultivo();
			this.getCultivo().set_nombre(this.getCultivoSeleccionado());
			this.setCultivo(this.getGEMFachada(ServicioGEM.Cultivo)
					.ObtenerCultivo(this.getCultivo()));
			this.setListaPropiedades(this.getCultivo().get_listaPropiedades());
			propiedades = new SelectItem[this.getListaPropiedades().size() + 1];
			propiedades[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int l = 1;
			for (Propiedad p : this.getListaPropiedades()) {
				SelectItem si = new SelectItem(p.get_codigo(), p.get_nombre());
				propiedades[l] = si;
				l++;
			}
			propiedadSeleccionada = propiedades[0].getValue().toString();

		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void TakeSelectionPropiedad() {
		try {
			propiedad = new Propiedad();
			for (Propiedad prop : this.getListaPropiedades()) {
				if (prop.get_codigo().equalsIgnoreCase(
						this.getPropiedadSeleccionada())) {
					this.setPropiedad(prop);
					this.setCodigo(this.getPropiedad().get_codigo());
					this.setNombre(this.getPropiedad().get_nombre());
					this.setUnidadedida(this.getPropiedad().get_unidadMedida());
					this
							.setTipoPropiedad(this.getPropiedad().get_tipo()
									.name());
				}
			}

		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public String VerPropiedad() {

		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String transaccionElegida = (String) paramMap.get("propiedadElegida");
		Iterator<Propiedad> it = this.getListaPropiedades().iterator();
		while (it.hasNext()) {
			Propiedad propiedadSeleccionada = (Propiedad) it.next();
			if (propiedadSeleccionada.get_id() == (long) Long
					.parseLong(transaccionElegida)) {
				this.setPropiedad(propiedadSeleccionada);

				this.setCodigo(this.getPropiedad().get_codigo());
				this.setNombre(this.getPropiedad().get_nombre());
				this.setTipoPropiedad(this.getPropiedad().get_tipo().name());
				this.setUnidadedida(this.getPropiedad().get_unidadMedida());
			}
		}

		return "";
	}

	public String Eliminar() {
		return "";
	}

	public List<Cultivo> getListaCultivos() {
		return listaCultivos;
	}

	public void setListaCultivos(List<Cultivo> listaCultivos) {
		this.listaCultivos = listaCultivos;
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	public List<Propiedad> getListaPropiedades() {
		return listaPropiedades;
	}

	public void setListaPropiedades(List<Propiedad> listaPropiedades) {
		this.listaPropiedades = listaPropiedades;
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

	public String getTipoPropiedad() {
		return tipoPropiedad;
	}

	public void setTipoPropiedad(String tipoPropiedad) {
		this.tipoPropiedad = tipoPropiedad;
	}

	public String getCultivoSeleccionado() {
		return cultivoSeleccionado;
	}

	public void setCultivoSeleccionado(String cultivoSeleccionado) {
		this.cultivoSeleccionado = cultivoSeleccionado;
	}

	public boolean isDeshabilitarSeleccionCultivo() {
		return deshabilitarSeleccionCultivo;
	}

	public void setDeshabilitarSeleccionCultivo(
			boolean deshabilitarSeleccionCultivo) {
		this.deshabilitarSeleccionCultivo = deshabilitarSeleccionCultivo;
	}

	public SelectItem[] getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(SelectItem[] propiedades) {
		this.propiedades = propiedades;
	}

	public SelectItem[] getCultivos() {
		return cultivos;
	}

	public void setCultivos(SelectItem[] cultivos) {
		this.cultivos = cultivos;
	}

	public Propiedad getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}

	public String getPropiedadSeleccionada() {
		return propiedadSeleccionada;
	}

	public void setPropiedadSeleccionada(String propiedadSeleccionada) {
		this.propiedadSeleccionada = propiedadSeleccionada;
	}

}