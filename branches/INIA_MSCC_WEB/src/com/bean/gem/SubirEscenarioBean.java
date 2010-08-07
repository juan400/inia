package com.bean.gem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Region;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioADM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioGEM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoArchivo;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoExtencionArchivo;
import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.entidades.Cultivo;
import com.inia_mscc.modulos.adm.entidades.Ubicacion;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class SubirEscenarioBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date fecha;

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
	private boolean disableUpload = false;
	private List<UploadItem> files;
	private List<Archivo> archivos;
	private Usuario usuarioFiltro;
	private List<Usuario> listaUsuarios;
	private SelectItem[] usuarios;
	private String usuarioElegido;
	private String estado;
	private SelectItem[] estados;

	private boolean recargo = true;

	public SubirEscenarioBean() {
		this.setListaCultivos(this.getGEMFachada(ServicioGEM.Cultivo)
				.ObtenerCultivos(null));
		this.setListaRegiones(this.getAdmFachada(ServicioADM.Region)
				.ObtenerRegiones());
		files = new ArrayList<UploadItem>();
		archivos = new ArrayList<Archivo>();
	}

	public boolean isModificacion() {
		try {
			if (recargo) {
				SelectItem[] usuarios = new SelectItem[1];
				usuarios[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				usuarioElegido = this.getTextBundleKey("combo_seleccione");
				Archivo archi = new Archivo();
				archi.set_usuario((Usuario) this.getSesion(Usuario.class
						.toString()));
				archi.set_tipo(TipoArchivo.Escenario);
				archivos = this.getGEMFachada(ServicioGEM.Archivo)
						.ObtenerArchivos(archi);
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
					regiones[i] = si;
					i++;
				}
				regionElegida = this.getTextBundleKey("combo_seleccione");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return false;
	}

	public String buscarEscenario() {
		Archivo archi = new Archivo();
		archi.set_usuario((Usuario) this.getSesion(Usuario.class.toString()));
		archi.set_fechaHora(this.getFecha());
		//archi.set_cultivo(this.getCultivo());
		archi.set_usuario(this.getUsuarioFiltro());
		archi.set_estado(Estado.valueOf(this.getEstado()));
		archi.set_tipo(TipoArchivo.Escenario);
		archivos = this.getGEMFachada(ServicioGEM.Archivo).ObtenerArchivos(
				archi);
		return "GEM006";
	}

	private String ModificarEscenario() {
		return "GEM006";
	}

	public boolean isInit() {
		try {
			if (recargo) {
				files = new ArrayList<UploadItem>();
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
					regiones[i] = si;
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
				this.setDisableRegion(false);
				this.setDisableUpload(true);
				this.setError("");
				this.setExito("");
				recargo = false;
			} else {
				this.setCultivo(null);
				this.setDisableRegion(true);
				this.setDisableUpload(true);
				this.setError("");
				this.setExito("");
			}
			regiones = new SelectItem[listaRegiones.size() + 1];
			regiones[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int i = 1;
			for (Region c : listaRegiones) {
				SelectItem si = new SelectItem(c.get_codigo(), c.get_nombre(),
						c.get_descripcion());
				regiones[i] = si;
				i++;
			}
			regionElegida = this.getTextBundleKey("combo_seleccione");
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
				recargo = false;
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
		Cultivo cultivoBuscado = null;
		if (this.getListaCultivos() != null
				&& !this.getListaCultivos().isEmpty()) {
			for (Cultivo cultivo : this.getListaCultivos()) {
				if (cultivo.get_nombre().equals(pCodigo)) {
					cultivoBuscado = cultivo;
				}
			}
		}
		return cultivoBuscado;
	}

	private Region BuscarRegion(String pCodigo) {
		Region regionBuscada = null;
		if (this.getListaRegiones() != null
				&& !this.getListaRegiones().isEmpty()) {
			for (Region region : this.getListaRegiones()) {
				if (region.get_codigo().equals(pCodigo)) {
					regionBuscada = region;
				}
			}
		}
		return regionBuscada;
	}

	public void listener(UploadEvent event) {
		try {
			UploadItem item = event.getUploadItem();
			files.add(item);
			recargo = false;
		} catch (Exception ex) {
			setError(ex.getMessage());
		}
	}

	public String RegistrarEscenario() {
		String retorno = "";
		try {
			if (this.getCultivo() != null) {
				if (this.getRegion() != null) {
					if (this.getFiles() != null && !this.getFiles().isEmpty()) {
						this.setUsuario((Usuario) getSesion(Usuario.class
								.toString()));
						Ubicacion ubicacion = new Ubicacion();
						ubicacion.set_tipoArchivo(TipoArchivo.Escenario);
						ubicacion = this.getAdmFachada(ServicioADM.Ubicacion)
								.ObtenerUbicacion(ubicacion);
						archivoSubido = new Archivo(getUsuario().get_login(),
								TipoArchivo.Escenario, new Date(),
								Estado.Activo, TipoExtencionArchivo.py,
								ubicacion);
						archivoSubido.set_datos(this.getFiles().get(0)
								.getFile());
//						archivoSubido.set_cultivo(this.getCultivo());
						archivoSubido.set_usuario(this.getUsuario());
						archivoSubido = this.getGEMFachada(ServicioGEM.Archivo)
								.RegistrarArchivo(archivoSubido);
						if (archivoSubido != null) {
							this.setDisableUpload(true);
							recargo = true;
							this.setExito("Se guardo el archivo para "
									+ "el escenario exitosamente.");
							retorno = "GEM005";
						} else {
							this.setError("No se pudo registrar el archivo");
						}
					} else {
						this
								.setError("No se subieron archivos, seleccione y cargue el archivo para el escenario.");
					}
				} else {
					this.setError("Debe seleccionar una regi�n clim�tica.");
				}
			} else {
				this.setError("Debe seleccionar un cultivo.");
			}

		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return retorno;
	}

	public String clearUploadData() {
		archivoSubido = null;
		files = new ArrayList<UploadItem>();
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

	public void setFiles(List<UploadItem> files) {
		this.files = files;
	}

	public List<UploadItem> getFiles() {
		return files;
	}

	public List<Archivo> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<Archivo> archivos) {
		this.archivos = archivos;
	}

	public Usuario getUsuarioFiltro() {
		return usuarioFiltro;
	}

	public void setUsuarioFiltro(Usuario usuarioFiltro) {
		this.usuarioFiltro = usuarioFiltro;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public SelectItem[] getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(SelectItem[] usuarios) {
		this.usuarios = usuarios;
	}

	public String getUsuarioElegido() {
		return usuarioElegido;
	}

	public void setUsuarioElegido(String usuarioElegido) {
		this.usuarioElegido = usuarioElegido;
	}

	public boolean isRecargo() {
		return recargo;
	}

	public void setRecargo(boolean recargo) {
		this.recargo = recargo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public SelectItem[] getEstados() {
		return estados;
	}

	public void setEstados(SelectItem[] estados) {
		this.estados = estados;
	}

}