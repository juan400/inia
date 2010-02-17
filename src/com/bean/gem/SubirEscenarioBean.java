package com.bean.gem;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.model.SelectItem;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Region;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.EstadoArchivo;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioADM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioGEM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoArchivo;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoExtencionArchivo;
import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.entidades.ArchivosTexto;
import com.inia_mscc.modulos.gem.entidades.Cultivo;
import com.inia_mscc.modulos.gem.entidades.Propiedad;
import com.inia_mscc.modulos.gem.entidades.Ubicacion;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class SubirEscenarioBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date fecha = new Date();

	private List<Cultivo> listaCultivos;
	private SelectItem[] cultivos;
	private String cultivoElegido;
	private Cultivo cultivo;

	private List<Region> listaRegiones;
	private SelectItem[] regiones;
	private String regionElegida;
	private Region region;
	private boolean disableRegion = true;

	private Archivo archivoSubido = new Archivo();
	private boolean useFlash = false;
	private boolean disableUpload = true;

	private boolean recargo = true;

	public SubirEscenarioBean() {

	}

	public boolean isInit() {
		try {
			if (recargo) {
				this.setListaCultivos(this.getGEMFachada(ServicioGEM.Cultivo)
						.ObtenerCultivos(null));
				if (this.getListaCultivos() == null) {
					this.setListaCultivos(new ArrayList<Cultivo>());
				}
				cultivos = new SelectItem[listaCultivos.size() + 1];
				cultivos[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				int i = 1;
				for (Cultivo c : listaCultivos) {
					SelectItem si = new SelectItem(c.get_nombre());
					cultivos[i] = si;
					i++;
				}
				cultivoElegido = this.getTextBundleKey("combo_seleccione");
				this.setListaRegiones(this.getAdmFachada(ServicioADM.Region)
						.ObtenerRegiones());
				if (this.getListaRegiones() == null) {
					this.setListaRegiones(new ArrayList<Region>());
				}
				regiones = new SelectItem[listaRegiones.size() + 1];
				regiones[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				i = 1;
				for (Region c : listaRegiones) {
					SelectItem si = new SelectItem(c.get_codigo(), c
							.get_nombre(), c.get_descripcion());
					cultivos[i] = si;
					i++;
				}
				regionElegida = this.getTextBundleKey("combo_seleccione");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return false;
	}

	public void takeSelectionCultivo() {
		try {
			cultivo = new Cultivo();
			if (!this.getCultivoElegido().isEmpty()
					&& !this.getCultivoElegido().equals(
							this.getTextBundleKey("combo_seleccione"))) {
				this.setCultivo(BuscarCultivo(this.getCultivoElegido()));
				this.setDisableUpload(false);
				this.setError("");
				this.setExito("");
			} else {
				this.setCultivo(null);
				this.setDisableUpload(true);
				this.setError("");
				this.setExito("");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void takeSelectionRegion() {
		try {
			region = new Region();
			if (!this.getRegionElegida().isEmpty()
					&& !this.getRegionElegida().equals(
							this.getTextBundleKey("combo_seleccione"))) {
				this.setRegion(BuscarRegion(this.getRegionElegida()));
				this.setDisableUpload(false);
				this.setError("");
				this.setExito("");
			} else {
				this.setRegion(null);
				this.setDisableUpload(true);
				this.setError("");
				this.setExito("");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}


	private Cultivo BuscarCultivo(String pCodigo) {
		Cultivo cultivoBuscado=null;
		if (this.getListaCultivos() != null
				&& !this.getListaCultivos().isEmpty()) {
			for (Cultivo cultivo : this.getListaCultivos()) {
				if (cultivo.get_nombre().equals(pCodigo)){
					cultivoBuscado=cultivo;
				}
			}
		}
		return cultivoBuscado;
	}
	
	private Region BuscarRegion(String pCodigo) {
		Region regionBuscada=null;
		if (this.getListaRegiones() != null
				&& !this.getListaRegiones().isEmpty()) {
			for (Region region : this.getListaRegiones()) {
				if (region.get_codigo().equals(pCodigo)){
					regionBuscada=region;
				}
			}
		}
		return regionBuscada;
	}

	/**
	 * @param event
	 */
	public void listener(UploadEvent event) {
		try {
			UploadItem item = event.getUploadItem();
			setUsuario((Usuario) getSesion(Usuario.class.toString()));
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.set_urlPaht(new URI("C:/ArchivosSubidos/"));
			archivoSubido = new Archivo(getUsuario().get_login(),
					TipoArchivo.ModeloSimulacion, new Date(),
					EstadoArchivo.Cargado, TipoExtencionArchivo.py, ubicacion);
			File file = new File(archivoSubido.get_nombre());
			file.setExecutable(true);

			if (file.createNewFile()) {
				ArchivosTexto.copiarArchio(item.getFile(), archivoSubido
						.get_datos());
			}
			archivoSubido.set_datos(file);

		} catch (Exception ex) {
			setError(ex.getMessage());
		}
	}

	public String clearUploadData() {
		archivoSubido = null;
		return null;
	}

	public long getTimeStamp() {
		return System.currentTimeMillis();
	}

	public boolean isUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}

	public Archivo getArchivoSubido() {
		return archivoSubido;
	}

	public void setArchivoSubido(Archivo archivoSubido) {
		this.archivoSubido = archivoSubido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public String getCultivoElegido() {
		return cultivoElegido;
	}

	public void setCultivoElegido(String cultivoElegido) {
		this.cultivoElegido = cultivoElegido;
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	public String getRegionElegida() {
		return regionElegida;
	}

	public void setRegionElegida(String regionElegida) {
		this.regionElegida = regionElegida;
	}

	public List<Region> getListaRegiones() {
		return listaRegiones;
	}

	public void setListaRegiones(List<Region> listaRegiones) {
		this.listaRegiones = listaRegiones;
	}

	public SelectItem[] getRegiones() {
		return regiones;
	}

	public void setRegiones(SelectItem[] regiones) {
		this.regiones = regiones;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public boolean isDisableRegion() {
		return disableRegion;
	}

	public void setDisableRegion(boolean disableRegion) {
		this.disableRegion = disableRegion;
	}

	public boolean isDisableUpload() {
		return disableUpload;
	}

	public void setDisableUpload(boolean disableUpload) {
		this.disableUpload = disableUpload;
	}

}