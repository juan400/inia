package com.bean.eje;

import java.awt.Graphics2D;
import java.io.Serializable;

public class PaintData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Graphics2D g;
	int color;
	float scale;
	private String varUno;
	private String varDos;
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public Graphics2D getG() {
		return g;
	}

	public void setG(Graphics2D g) {
		this.g = g;
	}

	public void setVarUno(String varUno) {
		this.varUno = varUno;
	}

	public String getVarUno() {
		return varUno;
	}

	public void setVarDos(String varDos) {
		this.varDos = varDos;
	}

	public String getVarDos() {
		return varDos;
	}

}