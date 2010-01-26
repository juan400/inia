package com.bean.pruebas;

import java.util.ArrayList;

//import javax.faces.component.UISelectItem;
import javax.faces.model.SelectItem;

import com.inia_mscc.modulos.adm.entidades.Pais;

public class PaisesBean {
	private ArrayList<Pais> paises = new ArrayList<Pais>();
	private ArrayList<String> paisesNombre = new ArrayList<String>();
	private SelectItem[] paisesNombreOptions;
	private ArrayList<SelectItem> paisesOptions = new ArrayList<SelectItem>();
	private String pais = "";


}