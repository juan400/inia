package com.bean.gem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;

import org.jboss.util.Strings.Range;

import wox.serial.Easy;

import com.bean.gem.leerXML.Course;
import com.bean.gem.leerXML.Student;
import com.inia_mscc.modulos.gem.entidades.Archivo;

//Xerces classes.
//import org.apache.xerces.dom.DocumentImpl;
//import org.apache.xml.serialize.*;

public class wgen {

	// ##Estas son las arrays que se definen en el xml y caracterizan el
	// clima de ua region **********************
	// #order: rows: max_C, min_C, solar_rad, cols: mean: a, b, c, d, stdev:
	// a, b, c, d
	private double[][] dry;
	private double[][] wet;
	// #[P000,P010,P100,P110] from out precip data, i.e.:
	// [drydrydry,drywetdry,wetdrydry,wetwetdry]
	public double[] prob_;

	public wgen() {
	}

	private double C_F(double tc) {
		double tf = ((9 * (float) tc) / 5) + 32;
		return tf;
	}

	private double precip(double mean) { // #our data has an overall mean of
		// 6.217768
		double k = Math.random();
		double ppt = -(mean * Math.log(1 - k));
		return ppt;
	}

	/**
	 * #models non precip variables based on best fit sin curves. #Need to add
	 * stochasticity and constant matrix effects- but this gives mean
	 * 
	 * @param state
	 * @param day
	 * @return
	 */
	private double[][] sin_fit(String state, int day) {
		double[][] c;
		if (state == "dry") {
			c = getDry();
		} else {
			c = getWet();
		}
		double[][] line = {
				{
						((c[0][0] * Math.sin((c[0][1] * day) + c[0][2])) + c[0][3]),
						((c[0][4] * Math.sin((c[0][5] * day) + c[0][6])) + c[0][7]) },
				{
						((c[1][0] * Math.sin((c[1][1] * day) + c[1][2])) + c[1][3]),
						((c[1][4] * Math.sin((c[1][5] * day) + c[1][6])) + c[1][7]) },
				{
						((c[2][0] * Math.sin((c[2][1] * day) + c[2][2])) + c[2][3]),
						((c[2][4] * Math.sin((c[2][5] * day) + c[2][6])) + c[2][7]) } };
		return line;
	}

	/**
	 * #generates the seasonally and moisture dependent mean and stdev for each
	 * variable on the given day.
	 * 
	 * @param day
	 * @param state
	 * @param ppt2
	 * @param refDay
	 * @return
	 */
	private Object[] zmodel(int day, String state, double ppt2, Calendar refDay) {
		double[][] p = sin_fit(state, day);
		double maxcm = p[0][0];
		double maxcs = p[0][1];
		double mincm = p[1][0];
		double mincs = p[1][1];
		double sm = p[2][0];
		double ss = p[2][1];

		double[] e = new double[3];
		e[0] = Math.random();
		e[1] = Math.random();
		e[2] = Math.random();
		double max_c = 0d;
		double min_c = 0d;
		double sol_rad = 0d;
		if (day > 0) {
			max_c = maxcm + maxcs * e[0];// #maxc
			min_c = mincm + mincs * e[1];// #minc
			sol_rad = sm + ss * e[2];// #solrad
		} else {
			max_c = maxcm;
			min_c = mincm;
			sol_rad = sm;
		}
		if (Math.abs(max_c) > 1000000) {
			max_c = 1000000;
		}
		if (Math.abs(min_c) > 1000000) {
			min_c = 1000000;
		}
		if (Math.abs(sol_rad) > 1000000) {
			sol_rad = 1000000;
		}
		Object[] row = new Object[10];
		refDay.add(Calendar.DAY_OF_MONTH, 1);
		row[0] = refDay.get(Calendar.DAY_OF_MONTH);
		row[1] = refDay.get(Calendar.MONTH);
		row[2] = refDay.get(Calendar.YEAR);
		row[3] = C_F(max_c);
		row[4] = max_c;
		row[5] = C_F(min_c);
		row[6] = min_c;
		row[7] = ((min_c + max_c) / 2);
		row[8] = ppt2;
		row[9] = sol_rad;
		return row;
	}

	/**
	 * #adj=[adj1,adj2,adjs,adje] #adjs=start adjusting probabilities by adj1
	 * until adje, then continue adjusting by adj2 #prob=[P000,P010,P100,P110]
	 * #if trmt=='control': adj=[0,0,0,365]
	 * 
	 * @param prob
	 * @return
	 */
	public ArrayList ppt_occ(double[] prob) {
		int[] adj = { 0, 0, 0, 365 };

		double[] p_adj1 = { prob[0] + adj[0], prob[1] + adj[0],
				prob[2] + adj[0], prob[3] + adj[0] };
		double[] p_adj2 = { prob[0] + adj[1], prob[1] + adj[1],
				prob[2] + adj[1], prob[3] + adj[1] };

		double[] p;
		if (adj[2] == 0) {
			p = p_adj1;
		} else {
			p = p_adj2;
		}
		Random rrrr = new Random();
		double[] rand = new double[2];
		for (int j = 0; j < 2; j++) {
			rand[j] = Math.random();
		}

		ArrayList ppt = new ArrayList();

		double suma = 0d;
		for (int i = 0; i < p.length; i++) {
			suma += p[i];
		}
		if (rand[0] < ((suma) / 4)) {
			ppt.add(0);
		} else {
			ppt.add(1);
		}

		if (ppt.get(0) == Integer.valueOf(1)) {
			if (rand[1] < ((p[1] + p[3]) / 2)) {
				ppt.add(0);
			} else {
				ppt.add(1);
			}
		} else {
			if (rand[1] < ((p[0] + p[2]) / 2)) {
				ppt.add(0);
			} else {
				ppt.add(1);
			}
		}
		rand = new double[365];
		for (int j = 0; j < 365; j++) {
			rand[j] = Math.random();
		}
		Range rango = new Range(2, 365);

		for (int day = rango.begin; day < rango.end; day++) {
			if (day == adj[2]) {
				p = p_adj1;
			}
			if (day == adj[3]) {
				p = p_adj2;
			}
			if (ppt.get(day - 2) == Integer.valueOf(1)) {
				if (ppt.get(day - 1) == Integer.valueOf(1)) {
					if (rand[day] < p[3]) {
						ppt.add(0);
					} else {
						ppt.add(1);
					}
				} else {
					if (rand[day] < p[2]) {
						ppt.add(0);
					} else {
						ppt.add(1);
					}
				}
			} else {
				if (ppt.get(day - 1) == Integer.valueOf(1)) {
					if (rand[day] < p[1]) {
						ppt.add(0);
					} else {
						ppt.add(1);
					}
				} else {
					if (rand[day] < p[0]) {
						ppt.add(0);
					} else {
						ppt.add(1);
					}
				}
			}
		}

		return ppt;

	}

	/**
	 * #Simulates ppt occurances. #yearbounds=years for which to generate
	 * climate #intensity=changes the mean precipitation intensity by factor
	 * #adj=adjusts the probability of precipitation occurence during a specif
	 * period of time (see ppt_occ)
	 * 
	 * @return
	 */
	public ArrayList<String> Sim_wea(int[] yearbounds, double meanppt,
			double intensity, int[] adj) {
		ArrayList<String> Stable = new ArrayList<String>();
		try {
			StringBuilder strBuild1 = new StringBuilder();
			Stable.add(strBuild1.append("DIA").append(" ").append("MES")
					.append(" ").append("ANIO").append(" ")
					.append("C_F(max_c)").append(" ").append("max_c").append(
							" ").append("C_F(min_c)").append(" ").append(
							"min_c").append(" ").append("((min_c+max_c)/2)")
					.append(" ").append("ppt2").append(" ").append("sol_rad")
					.toString());

			Calendar dia = Calendar.getInstance();
			dia.set(0, 1, 1);
			int i = 0;
			meanppt = (meanppt * intensity);
			Range rango = new Range();
			rango.begin = yearbounds[0];
			rango.end = yearbounds[1];
			for (int year = rango.begin; year <= rango.end; year++) {
				ArrayList ppt = ppt_occ(getProb_());
				Calendar RefDay = Calendar.getInstance();
				RefDay.set(year, 1, 1);
				for (int day = 0; day < 365; day++) {
					String state = "";
					double ppt2 = 0d;
					if (ppt.get(day) == Integer.valueOf(1)) {
						state = "wet";
						ppt2 = precip(meanppt);
					} else if (ppt.get(day) == Integer.valueOf(0)) {
						state = "dry";
						ppt2 = 0d;
					}
					Object[] fila1 = (Object[]) zmodel(day, state, ppt2, RefDay);
					StringBuilder strBuild = new StringBuilder();
					Stable.add(strBuild.append(fila1[0].toString()).append(" ")
							.append(fila1[1].toString()).append(" ").append(
									fila1[2].toString()).append(" ").append(
									fila1[3].toString()).append(" ").append(
									fila1[4].toString()).append(" ").append(
									fila1[5].toString()).append(" ").append(
									fila1[6].toString()).append(" ").append(
									fila1[7].toString()).append(" ").append(
									fila1[8].toString()).append(" ").append(
									fila1[9].toString()).toString());
					i += 1;
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return Stable;
	}

	public boolean cargarArchivoParametros(File pfxml) {
		boolean resultado = false;
		try {
			if (pfxml.canRead()) {
				System.out.println("Puede leer!!!");
				ArrayList<String> archivo = ArchivosTexto.leerArchivo(pfxml);
				double[][] dryCargado = new double[3][8];
				double[][] wetCargado = new double[3][8];
				double[] probCargado = new double[4];
				for (int i = 0; i < archivo.size(); i++) {
					if (archivo.get(i).equalsIgnoreCase("<param>")) {
						i++;
						if (archivo.get(i).equalsIgnoreCase("<dry>")) {
							i++;
							if (archivo.get(i).equalsIgnoreCase("<max_c>")) {
								int numeroProbabilidad = 0;
								i++;
								for (int j = i; j < (i + 8); j++) {
									String valorString = archivo
											.get(j)
											.substring(
													archivo.get(j).indexOf(">") + 1,
													archivo.get(j).lastIndexOf(
															"<"));
									dryCargado[0][numeroProbabilidad] = (double) Double
											.parseDouble(valorString);
									numeroProbabilidad++;
								}
								i += numeroProbabilidad;
							}
							i++;
							if (archivo.get(i).equalsIgnoreCase("<min_c>")) {
								int numeroProbabilidad = 0;
								i++;
								for (int j = i; j < (i + 8); j++) {
									String valorString = archivo
											.get(j)
											.substring(
													archivo.get(j).indexOf(">") + 1,
													archivo.get(j).lastIndexOf(
															"<"));
									dryCargado[1][numeroProbabilidad] = (double) Double
											.parseDouble(valorString);
									numeroProbabilidad++;
								}
								i += numeroProbabilidad;
							}
							i++;
							if (archivo.get(i).equalsIgnoreCase("<solar_rad>")) {
								int numeroProbabilidad = 0;
								i++;
								for (int j = i; j < (i + 8); j++) {
									String valorString = archivo
											.get(j)
											.substring(
													archivo.get(j).indexOf(">") + 1,
													archivo.get(j).lastIndexOf(
															"<"));
									dryCargado[2][numeroProbabilidad] = (double) Double
											.parseDouble(valorString);
									numeroProbabilidad++;
								}
								i += numeroProbabilidad;
							}
							i++;
						}
						i++;
						if (archivo.get(i).equalsIgnoreCase("<wet>")) {
							i++;
							if (archivo.get(i).equalsIgnoreCase("<max_c>")) {
								int numeroProbabilidad = 0;
								i++;
								for (int j = i; j < (i + 8); j++) {
									String valorString = archivo
											.get(j)
											.substring(
													archivo.get(j).indexOf(">") + 1,
													archivo.get(j).lastIndexOf(
															"<"));
									wetCargado[0][numeroProbabilidad] = (double) Double
											.parseDouble(valorString);
									numeroProbabilidad++;
								}
								i += numeroProbabilidad;
							}
							i++;
							if (archivo.get(i).equalsIgnoreCase("<min_c>")) {
								int numeroProbabilidad = 0;
								i++;
								for (int j = i; j < (i + 8); j++) {
									String valorString = archivo
											.get(j)
											.substring(
													archivo.get(j).indexOf(">") + 1,
													archivo.get(j).lastIndexOf(
															"<"));
									wetCargado[1][numeroProbabilidad] = (double) Double
											.parseDouble(valorString);
									numeroProbabilidad++;
								}
								i += numeroProbabilidad;
							}
							i++;
							if (archivo.get(i).equalsIgnoreCase("<solar_rad>")) {
								int numeroProbabilidad = 0;
								i++;
								for (int j = i; j < (i + 8); j++) {
									String valorString = archivo
											.get(j)
											.substring(
													archivo.get(j).indexOf(">") + 1,
													archivo.get(j).lastIndexOf(
															"<"));
									wetCargado[2][numeroProbabilidad] = (double) Double
											.parseDouble(valorString);
									numeroProbabilidad++;
								}
								i += numeroProbabilidad;
							}
							i++;
						}
						i++;
						i++;
					}
					if (archivo.get(i).equalsIgnoreCase("<prob>")) {
						int numeroProbabilidad = 0;
						i++;
						for (int j = i; j < (i + 4); j++) {
							String valorString = archivo.get(j).substring(
									archivo.get(j).indexOf(">") + 1,
									archivo.get(j).lastIndexOf("<"));
							probCargado[numeroProbabilidad] = (double) Double
									.parseDouble(valorString);
							numeroProbabilidad++;
						}
						i += numeroProbabilidad;
					}
				}
				setDry(dryCargado);
				setWet(wetCargado);
				setProb_(probCargado);
			}
		} catch (Exception ex) {
			ex.getMessage();

		}
		return true;
	}

	public double[][] getDry() {
		return dry;
	}

	public void setDry(double[][] dry) {
		this.dry = dry;
	}

	public double[][] getWet() {
		return wet;
	}

	public void setWet(double[][] wet) {
		this.wet = wet;
	}

	public double[] getProb_() {
		return prob_;
	}

	public void setProb_(double[] prob) {
		prob_ = prob;
	}

}