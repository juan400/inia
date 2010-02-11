package com.inia_mscc.modulos.gem.entidades;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;



public class LectorXML {

	public static void main(String args[]) {
        
		LectorXML miventana = new LectorXML();
	  }
	public LectorXML() {
		try {
//			URL url = new URL("dirección web");
			File archivo = new File("c:/Biblioteca/Cajón/Proyecto/INIA/Archivos Recibidos/climate_parameters_for_site_LE.xml");

//			URLConnection yc = url.openConnection();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
//			Document doc = db.parse(yc.getInputStream());
			Document doc = db.parse(archivo);
			doc.getDocumentElement().normalize();
			System.out.println("Root element "
					+ doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("param");
			System.out.println("Datos de XML");
			for (int i = 0; i < nodeLst.getLength(); i++) {
				Node fstNode = nodeLst.item(i);
				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					Element fstElmnt = (Element) fstNode;

					NodeList fstNmElmntLst = fstElmnt
							.getElementsByTagName("title");
					Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					NodeList fstNm = fstNmElmnt.getChildNodes();

					System.out.println("Titulo : "
							+ ((Node) fstNm.item(0)).getNodeValue());

					NodeList stNmElmntLst = fstElmnt
							.getElementsByTagName("anio");
					Element stNmElmnt = (Element) stNmElmntLst.item(0);
					NodeList stNm = stNmElmnt.getChildNodes();

					System.out.println("Año: "
							+ ((Node) stNm.item(0)).getNodeValue());
				}

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
