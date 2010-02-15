package com.bean.gem;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.gem.entidades.ArchivosTexto;
import com.inia_mscc.modulos.seg.entidades.Usuario;

/**
 * @author Ilya Shaikovsky
 * 
 */
public class FileUploadBean extends MaestroBean {

	private ArrayList<File> files = new ArrayList<File>();
	private int uploadsAvailable = 5;
	private boolean autoUpload = false;
	private boolean useFlash = false;
	private Date fecha = new Date();

	public int getSize() {
		if (getFiles().size() > 0) {
			return getFiles().size();
		} else {
			return 0;
		}
	}

	public FileUploadBean() {
	}

	public void listener(UploadEvent event) {
		try {
			UploadItem item = event.getUploadItem();
			setUsuario((Usuario) getSesion(Usuario.class.toString()));
			Calendar RefDay = new GregorianCalendar();
			String fechaEscrita = RefDay.get(Calendar.YEAR)+"-"+(RefDay.get(Calendar.MONTH)+1)+""+RefDay.get(Calendar.DAY_OF_MONTH)+" "+RefDay.get(Calendar.HOUR_OF_DAY)+""+(RefDay.get(Calendar.MINUTE)+1)+""+RefDay.get(Calendar.SECOND);
			RefDay.set(fecha.getYear(), fecha.getMonth(), fecha.getDay(), fecha.getHours(), fecha.getMinutes(), fecha.getSeconds());
			String nombreArchivo = "C:/Biblioteca/Cajón/Proyecto/INIA/Archivos Versionados/ArchivosSubidos/mscc_"
					+ getUsuario().get_login() + "_" + fechaEscrita + ".py";
			File file = new File(nombreArchivo);
			file.setExecutable(true);
			if (file.createNewFile()) {
				ArchivosTexto.copiarArchio(item.getFile(), file);
			}
			files.add(file);
			uploadsAvailable--;
		} catch (Exception ex) {
			setError(ex.getMessage());
		}
	}

	public String clearUploadData() {
		files.clear();
		setUploadsAvailable(5);
		return null;
	}

	public long getTimeStamp() {
		return System.currentTimeMillis();
	}

	public ArrayList<File> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<File> files) {
		this.files = files;
	}

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

}