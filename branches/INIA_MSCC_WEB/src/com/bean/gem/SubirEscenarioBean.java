package com.bean.gem;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados.EstadoArchivo;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoArchivo;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoExtencionArchivo;
import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.entidades.ArchivosTexto;
import com.inia_mscc.modulos.gem.entidades.Ubicacion;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class SubirEscenarioBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Archivo archivoSubido = new Archivo();
	// private ArrayList<File> files = new ArrayList<File>();
	private int uploadsAvailable = 1;
	private boolean autoUpload = false;
	private boolean useFlash = false;
	private Date fecha = new Date();

	public SubirEscenarioBean() {
	}

	public boolean isInit() {
		return false;
	}

//	public int getSize() {
//		if (getFiles().size() > 0) {
//			return getFiles().size();
//		} else {
//			return 0;
//		}
//	}

	/**
	 * @param event
	 */
	public void listener(UploadEvent event) {
		try {
			UploadItem item = event.getUploadItem();
			setUsuario((Usuario) getSesion(Usuario.class.toString()));
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.set_urlPaht(new URI(
					"C:/Biblioteca/Cajón/Proyecto/INIA/Archivos"
							+ " Versionados/ArchivosSubidos/"));
			archivoSubido = new Archivo(getUsuario().get_login(),
					TipoArchivo.ModeloSimulacion, new Date(),
					EstadoArchivo.Cargado, TipoExtencionArchivo.py, ubicacion);
			File file = new File(archivoSubido.get_nombre());
			file.setExecutable(true);

			if (file.createNewFile()) {
				ArchivosTexto.copiarArchio(item.getFile(), archivoSubido.get_datos());
			}
			archivoSubido.set_datos(file);
			uploadsAvailable--;
			
		} catch (Exception ex) {
			setError(ex.getMessage());
		}
	}

	public String clearUploadData() {
		archivoSubido = null;
//		file.clean();
		setUploadsAvailable(5);
		return null;
	}

	public long getTimeStamp() {
		return System.currentTimeMillis();
	}

//	public ArrayList<File> getFiles() {
//		return files;
//	}
//
//	public void setFiles(ArrayList<File> files) {
//		this.files = files;
//	}

	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	public boolean isAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
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

}