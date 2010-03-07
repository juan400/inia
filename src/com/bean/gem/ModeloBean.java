package com.bean.gem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Ubicacion;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioADM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioGEM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoArchivo;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoExtencionArchivo;
import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.entidades.Escenario;
import com.inia_mscc.modulos.gem.entidades.Modelo;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class ModeloBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date fecha = new Date();

	private List<Escenario> listaEscenarios;
	private SelectItem[] escenarios;
	private String escenarioElegido;
	private Escenario escenario;

	private Archivo archivoSubido = new Archivo();
	private boolean useFlash = false;
	private boolean disableUpload = false;
	private List<UploadItem> files;
	private String estado;
	private SelectItem[] estados;
	
	private List<Modelo> modelos;
	private Modelo modelo;

	private boolean recargo = true;

	public String RegistrarModelo() {
		this.setError("");
		this.setExito("");
		String retorno = "";
		try {
			if (this.getEscenario() != null) {
				if (this.getFiles() != null && !this.getFiles().isEmpty()) {
					this.setUsuario((Usuario) getSesion(Usuario.class
							.toString()));
					Ubicacion ubicacion = new Ubicacion();
					ubicacion.set_tipoArchivo(TipoArchivo.ModeloSimulacion);
					ubicacion = this.getAdmFachada(ServicioADM.Ubicacion)
							.ObtenerUbicacion(ubicacion);
					archivoSubido = new Archivo(getUsuario().get_login(),
							TipoArchivo.ModeloSimulacion, new Date(),
							Estado.Activo, TipoExtencionArchivo.py, ubicacion);
					archivoSubido.set_datos(this.getFiles().get(0).getFile());
					archivoSubido.set_usuario(this.getUsuario());
					Modelo modelo = new Modelo();
					modelo.set_archivoMSCC(archivoSubido);
					modelo.set_escenario(this.getEscenario());
					modelo.set_fechaHora(this.getFecha());
					modelo.set_estado(Estado.valueOf(this.getEstado()));
					modelo.set_usuarioInvestigador(this.getUsuario());
					modelo = this.getGEMFachada(ServicioGEM.Modelo)
							.RegistrarModelo(modelo);
					if (modelo != null) {
						this.setDisableUpload(true);
						recargo = true;
						this
								.setExito("Se guardo el modelo de simulación exitosamente.");
						this.setEscenario(null);
						this.setArchivoSubido(null);
						this.setModelo(null);
						retorno = "GEM007";
					} else {
						this
								.setError("No se pudo registrar el modelo de simulación");
					}

				} else {
					this
							.setError("No se subieron archivos, seleccione y cargue el archivo para el modelo de simulación.");
				}
			} else {
				this.setError("Debe seleccionar un escenario de ejecución.");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return retorno;
	}

	public String ModificarModelo() {
		this.setError("");
		this.setExito("");
		String retorno = "";
		return "GEM008";
	}

	public String Actualizar() {
		this.setError("");
		this.setExito("");
		String retorno = "";
		return "GEM008";
	}

	public String VerEscenario() {
		this.setError("");
		this.setExito("");
		String retorno = "";
		return "GEM008";
	}

	public String Eliminar() {
		this.setError("");
		this.setExito("");
		String retorno = "";
		return "GEM008";
	}

	public String buscarModelos() {
		this.setError("");
		this.setExito("");
		String retorno = "";
		try {
			Modelo unModelo = new Modelo();
			unModelo.set_usuarioInvestigador(this.getUsuario());
			if (this.getEscenario() != null) {
				unModelo.set_escenario(this.getEscenario());
			}
			if (!this.getEstado().equals(
					this.getTextBundleKey("combo_seleccione"))) {
				unModelo.set_estado(Estado.valueOf(this.getEstado()));
			}
			this.setModelos(this.getGEMFachada(ServicioGEM.Modelo)
					.ObtenerModelos(unModelo));
			retorno = "GEM008";
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return retorno;
	}

	public ModeloBean() {
		try {
			this.setError("");
			this.setExito("");
			Escenario esce = new Escenario();
			this.setUsuario((Usuario) this.getSesion(Usuario.class
					.toString()));
			esce.set_usuarioInvestigador(this.getUsuario());
			this.setUsuario((Usuario) this.getSesion(Usuario.class.toString()));
			this.setListaEscenarios(this.getGEMFachada(ServicioGEM.Escenario)
					.ObtenerEscenarios(esce));
			if (this.getListaEscenarios() == null) {
				this.setListaEscenarios(new ArrayList<Escenario>());
			}
			escenarios = new SelectItem[this.getListaEscenarios().size() + 1];
			escenarios[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int i = 1;
			for (Escenario e : this.getListaEscenarios()) {
				SelectItem si = new SelectItem(e.get_archivoEscenario().get_nombre());
				escenarios[i] = si;
				i++;
			}
			estados = new SelectItem[Estado.values().length];
//			estados[0] = new SelectItem(this
//					.getTextBundleKey("combo_seleccione"));
			SelectItem si = new SelectItem(Estado.Activo.name());
			estados[0] = si;
			si = new SelectItem(Estado.Inactivo.name());
			estados[1] = si;
			estado = Estado.Inactivo.name();
			files = new ArrayList<UploadItem>();
			modelos = new ArrayList<Modelo>();
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public boolean isModificacion() {
		try {
			if (recargo) {
				this.setError("");
				this.setExito("");
				Escenario esce = new Escenario();
				this.setUsuario((Usuario) this.getSesion(Usuario.class
						.toString()));
				esce.set_usuarioInvestigador(this.getUsuario());
				this.setUsuario((Usuario) this.getSesion(Usuario.class.toString()));
				this.setListaEscenarios(this.getGEMFachada(ServicioGEM.Escenario)
						.ObtenerEscenarios(esce));
				if (this.getListaEscenarios() == null) {
					this.setListaEscenarios(new ArrayList<Escenario>());
				}
				escenarios = new SelectItem[this.getListaEscenarios().size() + 1];
				escenarios[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				int i = 1;
				for (Escenario e : this.getListaEscenarios()) {
					SelectItem si = new SelectItem(e.get_archivoEscenario().get_nombre());
					escenarios[i] = si;
					i++;
				}
				escenarioElegido = this.getTextBundleKey("combo_seleccione");
				this.setModelos(this.getGEMFachada(ServicioGEM.Modelo)
						.ObtenerModelos(null));
				if (this.getModelos() == null) {
					this.setModelos(new ArrayList<Modelo>());
				}
				estados = new SelectItem[Estado.values().length];
//				estados[0] = new SelectItem(this
//						.getTextBundleKey("combo_seleccione"));
				SelectItem si = new SelectItem(Estado.Activo.name());
				estados[0] = si;
				si = new SelectItem(Estado.Inactivo.name());
				estados[1] = si;
//				estado = Estado.Inactivo.name();
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return false;
	}

	public boolean isInit() {
		try {
			if (recargo) {
				this.setError("");
				this.setExito("");
				files = new ArrayList<UploadItem>();

				Escenario esce = new Escenario();
				this.setUsuario((Usuario) this.getSesion(Usuario.class
						.toString()));
				esce.set_usuarioInvestigador(this.getUsuario());
				this.setUsuario((Usuario) this.getSesion(Usuario.class.toString()));
				this.setListaEscenarios(this.getGEMFachada(ServicioGEM.Escenario)
						.ObtenerEscenarios(esce));
				if (this.getListaEscenarios() == null) {
					this.setListaEscenarios(new ArrayList<Escenario>());
				}
				escenarios = new SelectItem[this.getListaEscenarios().size() + 1];
				escenarios[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				int i = 1;
				for (Escenario e : this.getListaEscenarios()) {
					SelectItem si = new SelectItem(e.get_archivoEscenario().get_nombre());
					escenarios[i] = si;
					i++;
				}
				escenarioElegido = this.getTextBundleKey("combo_seleccione");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return false;
	}

	public void takeSelectionEscenario() {
		try {
			escenario = new Escenario();
			if (!this.getEscenarioElegido().isEmpty()
					&& !this.getEscenarioElegido().equals(
							this.getTextBundleKey("combo_seleccione"))) {
				this.setEscenario(BuscarEscenario(this.getEscenarioElegido()));
//				this.setDisableEscenario(false);
				this.setDisableUpload(false);
				this.setError("");
				this.setExito("");
				recargo = false;
			} else {
				this.setEscenario(null);
//				this.setDisableRegion(true);
				this.setDisableUpload(true);
				this.setError("");
				this.setExito("");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	private Escenario BuscarEscenario(String pCodigo) {
		Escenario escenarioBuscado = null;
		if (this.getListaEscenarios() != null
				&& !this.getListaEscenarios().isEmpty()) {
			for (Escenario esce : this.getListaEscenarios()) {
				if (esce.get_archivoEscenario().get_nombre().equals(pCodigo)) {
					escenarioBuscado = esce;
				}
			}
		}
		return escenarioBuscado;
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

	public String clearUploadData() {
		archivoSubido = null;
		files = new ArrayList<UploadItem>();
		return null;
	}

	public long getTimeStamp() {
		return System.currentTimeMillis();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public SelectItem[] getEscenarios() {
		return escenarios;
	}

	public void setEscenarios(SelectItem[] escenarios) {
		this.escenarios = escenarios;
	}

	public String getEscenarioElegido() {
		return escenarioElegido;
	}

	public void setEscenarioElegido(String escenarioElegido) {
		this.escenarioElegido = escenarioElegido;
	}

	public Escenario getEscenario() {
		return escenario;
	}

	public void setEscenario(Escenario escenario) {
		this.escenario = escenario;
	}

	public Archivo getArchivoSubido() {
		return archivoSubido;
	}

	public void setArchivoSubido(Archivo archivoSubido) {
		this.archivoSubido = archivoSubido;
	}

	public boolean isUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}

	public boolean isDisableUpload() {
		return disableUpload;
	}

	public void setDisableUpload(boolean disableUpload) {
		this.disableUpload = disableUpload;
	}

	public List<UploadItem> getFiles() {
		return files;
	}

	public void setFiles(List<UploadItem> files) {
		this.files = files;
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

	public boolean isRecargo() {
		return recargo;
	}

	public void setRecargo(boolean recargo) {
		this.recargo = recargo;
	}

	public void setListaEscenarios(List<Escenario> listaEscenarios) {
		this.listaEscenarios = listaEscenarios;
	}

	public List<Escenario> getListaEscenarios() {
		return listaEscenarios;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

}