package com.bean.gem;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.gem.entidades.Archivo;

public class SubirEscenarioBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Archivo archivoSubido = new Archivo();

	public SubirEscenarioBean() {
	}

	public boolean isInit(){
		return false;
	}
	
	public void listener(UploadEvent event) throws IOException {
		UploadItem item = event.getUploadItem();
		System.out.println("FileUploadBean.listener()");
		Archivo file = new Archivo();
		file.set_length(item.getData().length);
		file.set_nombre(item.getFileName());
		file.set_data(item.getData());
		archivoSubido = file;
		archivoSubido = file;
	}

	public void paint(OutputStream outputStream, Object obj) throws IOException {
		outputStream.write((byte[]) obj);
		System.out.println("FileUploadBean.paint()");
		super.setExito("subio bien");
	}

	public Archivo getArchivoSubido() {
		return archivoSubido;
	}

	public void setArchivoSubido(Archivo archivoSubido) {
		this.archivoSubido = archivoSubido;
	}

}