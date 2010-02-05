package com.bean.gem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;
import java.io.File;
import java.io.IOException;

import org.eclipse.jdt.internal.compiler.parser.diagnose.RangeUtil;
import org.jboss.util.Strings.Range;

//Xerces classes.
//import org.apache.xerces.dom.DocumentImpl;
//import org.apache.xml.serialize.*;

public class wgen {

	// ##Estas son las arrays que se definen en el xml y caracterizan el
	// clima de ua region **********************
	// #order: rows: max_C, min_C, solar_rad, cols: mean: a, b, c, d, stdev:
	// a, b, c, d
	public Map<String, double[][]> param_dict;

	// #[P000,P010,P100,P110] from out precip data, i.e.:
	// [drydrydry,drywetdry,wetdrydry,wetwetdry]
	public double[] prob_ = { 0.62259353432618958, 0.44121447028423771,
			0.67097608274078857, 0.48002219755826858 };

	public wgen() {
	}

	private double C_F(double tc) {
		double tf = 9 * tc / 5 + 32;
		return tf;
	}

	private double precip(double mean) { // #our data has an overall mean of
		// 6.217768
		double k = Math.random();
		double ppt = -(mean * Math.log(1 - k));
		return ppt;
	}

	private double[][] sin_fit(String state, int day) {
		// #models non precip variables based on best fit sin curves. Need to
		// add
		// stochasticity and constant matrix effects- but this gives mean
		double[][] c;
		if (state == "dry") {
			double[][] dry = {
					{ -14.974737211233927, 0.015636886230500097,
							1.574329296449279, 11.532013519495585,
							1.4200818794930099, 0.019835241996656455,
							0.36178643519394749, 4.6960945435392265 },
					{ -12.607026868237501, 0.01644817504524268,
							1.3389681268941718, 2.0983606157408747,
							1.446519606509828, 0.015670217929789051,
							1.3967338855632128, 4.5503280665089969 },
					{ 229.58347668767991, 0.016033219592976149,
							-1.1484582365319118, 329.15476806896186,
							41.50980793045629, 0.014472308834135071,
							-0.67016098373625821, 84.510507285933485 } };
			c = dry;
		} else {
			double[][] wet = {
					{ -12.925455392811854, 0.017311475775113421,
							1.2019800640953939, 12.373124838415173,
							1.1406368970931773, 0.021733921890600392,
							-0.00466885771765324, 4.5028413295737195 },
					{ -12.056801821532833, 0.017290180610238164,
							1.1635140486223283, 4.3342738004304442,
							1.5765905236974651, 0.015795774310735387,
							1.5040647292366149, 4.0380182600585091 },
					{ 140.9127971622481, 0.017868978637799508,
							-1.5584047076567853, 215.80610127472397,
							50.624280723563047, 0.015326278863157008,
							-0.97734210161354629, 84.543269465709486 } };
			c = wet;
		}

		double[][] p = new double[3][2]; // #order: max_C,
		// min_C, solar_rad:
		// [mean, Stdev];

		double[] lineaUno = new double[2];
		lineaUno[0] = (c[0][0] * Math.sin(c[0][1] * day + c[0][2]) + c[0][3]);
		lineaUno[1] = (c[0][4] * Math.sin(c[0][5] * day + c[0][6]) + c[0][7]);
		p[0] = lineaUno;
		double[] lineaDos = new double[2];
		lineaDos[0] = (c[1][0] * Math.sin(c[1][1] * day + c[1][2]) + c[1][3]);
		lineaDos[1] = (c[1][4] * Math.sin(c[1][5] * day + c[1][6]) + c[1][7]);
		p[1] = lineaDos;
		double[] lineaTres = new double[2];
		lineaTres[0] = (c[2][0] * Math.sin(c[2][1] * day + c[2][2]) + c[2][3]);
		lineaTres[1] = (c[2][4] * Math.sin(c[2][5] * day + c[2][6]) + c[2][7]);
		p[2] = lineaTres;
		return p;
	}

	private Object[] zmodel(int day, String state, Object[] objects,
			double ppt2, Calendar refDay) {
		// [[maxcm,maxcs],[mincm,mincs],[sm,ss]]=sin_fit(state,day);
		// //#generates the seasonally and
		double[][] p = sin_fit(state, day);
		double maxcm = p[0][0];
		double maxcs = p[0][1];
		double mincm = p[1][0];
		double mincs = p[1][1];
		double sm = p[2][0];
		double ss = p[2][1];
		// #moisture dependent mean and stdev for each variable on the given
		// day.
		// e=numpy.random.normal(0,1,3); //#generates 3 normally distributed
		// random
		double[] e = new double[3];
		Random ran = new Random();
		e[0] = ran.nextGaussian();
		e[1] = ran.nextGaussian();
		e[2] = ran.nextGaussian();

		double numbers;
		double mean = -1;
		double stdev = 1;

		double max_c;
		double min_c;
		double sol_rad;
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
		refDay.add(Calendar.DAY_OF_MONTH, day);
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

	public ArrayList ppt_occ(double[] prob) {
		int[] adj = { 0, 0, 0, 365 };
		double[] p_adj1 = new double[prob.length + 1];
		System.arraycopy(prob, 0, p_adj1, 0, prob.length);
		double[] p_adj2 = new double[prob.length + 1];
		System.arraycopy(prob, 0, p_adj2, 0, prob.length);
		double[] p;
		if (adj[2] == 0) {
			p = p_adj1;
		} else {
			p = p_adj2;
		}
		Random rrrr = new Random();
		double[] rand = new double[2];
		for (int j = 0; j < 2; j++) {
			rand[j] = rrrr.nextDouble();
		}
		// double[] ppt=new double[364];
		ArrayList ppt = new ArrayList();
		// sum(p)
		double suma = 0d;
		for (int i = 0; i < p.length; i++) {
			suma += p[i];
		}
		if (rand[0] < (suma) / 4) {
			ppt.add(0);
		} else {
			ppt.add(1);
		}

		if (ppt.get(0) == Integer.valueOf(1)) {
			if (rand[1] < (p[1] + p[3]) / 2) {
				ppt.add(0);
			} else {
				ppt.add(1);
			}
		} else {
			if (rand[1] < (p[0] + p[2]) / 2) {
				ppt.add(0);
			} else {
				ppt.add(1);
			}
		}
		rand = new double[365];
		for (int j = 0; j < 365; j++) {
			rand[j] = rrrr.nextDouble();
		}
		Range rango = new Range(2, 365);

		for (int day = 2; day < 365; day++) {
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

	public ArrayList<Object[]> Sim_wea() {
		// (int[] yearbounds, double[] prob, double meanppt, double intensity,
		// int[] adj) {
		ArrayList<Object[]> table = new ArrayList<Object[]>();
		try {
			File directorio = new File("c:\\temp\\ArchivoClimaGenerado");
			if (!directorio.isDirectory()) {
				System.out.println(" NO es un directorio");
				directorio.mkdir();
			}
			File f = new File("Wather_sim_pickle.txt");
			if (f.exists()){
				f.deleteOnExit();
			}
			int[] yearbounds = { 2007, 2009 };
			double[] prob = prob_;
			double meanppt = 6.217768;
			double intensity = 1.0d;
			int[] adj = new int[] { 0, 0, 0, 365 };

			// table=[[DateTime.Date(int(0),1,1),0,0,0,0,0,0,0]]
			Calendar dia = Calendar.getInstance();
			dia.set(0, 1, 1);

			Object[] fila = { dia, 0, 0, 0, 0, 0, 0, 0 };
			table.add(fila);
			System.out.println(dia.toString());
			int i = 0;
			meanppt = meanppt * intensity;
			Range rango = new Range();
			rango.begin = yearbounds[0];
			rango.end = yearbounds[1];
			for (int year = rango.begin; year <= rango.end; year++) {
				ArrayList ppt = ppt_occ(prob);
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
					Object[] fila1 = (Object[]) zmodel(1, state, table.get(i),
							ppt2, RefDay);
					table.add(fila1);
					StringBuilder strBuild = new StringBuilder();
					strBuild.append(fila1[0].toString());
					strBuild.append(" ");
					strBuild.append(fila1[1].toString());
					strBuild.append(" ");
					strBuild.append(fila1[2].toString());
					strBuild.append(" ");
					strBuild.append(fila1[3].toString());
					strBuild.append(" ");
					strBuild.append(fila1[4].toString());
					strBuild.append(" ");
					strBuild.append(fila1[5].toString());
					strBuild.append(" ");
					strBuild.append(fila1[6].toString());
					strBuild.append(" ");
					strBuild.append(fila1[7].toString());
					strBuild.append(" ");
					strBuild.append(fila1[8].toString());
					strBuild.append(" ");
					strBuild.append(fila1[9].toString());
					i += 1;
					if (i == 1) {
						StringBuilder strBuild1 = new StringBuilder();
						strBuild1.append("DIA");
						strBuild1.append(" ");
						strBuild1.append("MES");
						strBuild1.append(" ");
						strBuild1.append("ANIO");
						strBuild1.append(" ");
						strBuild1.append("C_F(max_c)");
						strBuild1.append(" ");
						strBuild1.append("max_c");
						strBuild1.append(" ");
						strBuild1.append("C_F(min_c)");
						strBuild1.append(" ");
						strBuild1.append("min_c");
						strBuild1.append(" ");
						strBuild1.append("((min_c+max_c)/2)");
						strBuild1.append(" ");
						strBuild1.append("ppt2");
						strBuild1.append(" ");
						strBuild1.append("sol_rad");
						ArchivosTexto.saveString(f, strBuild1.toString());
					} else {
						ArchivosTexto.appendString(f, strBuild.toString());
					}
				}
			}
			System.out.println(f.getAbsolutePath() + "" + f.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// #import pickle
		return table;
	}
}