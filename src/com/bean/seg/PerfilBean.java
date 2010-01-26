package com.bean.seg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.inia_mscc.modulos.seg.entidades.Perfil;

public class PerfilBean implements Serializable{

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean init = false;
	private List<Perfil> perfiles;

	
	public boolean isInit() {
		perfiles = new ArrayList<Perfil>();
		//perfiles = segFachada.otenerPerfiles();
		for (int i = 0; i < 4; i++) {
		 	Perfil per = new Perfil();
		 	per.set_id(i);
		 	per.set_nombre("pepe"+i);
		 	per.set_descripcion("anduvo esta mierda"+i);
		 	perfiles.add(per);
		}
		return init;
	}


	public void setInit(boolean init) {
		this.init = init;
	}


	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	
	@PostConstruct
	public List<Perfil> getPerfiles() {
		return perfiles;
	}
}