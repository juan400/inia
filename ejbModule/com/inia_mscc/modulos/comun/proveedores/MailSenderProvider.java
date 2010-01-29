package com.inia_mscc.modulos.comun.proveedores;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.comun.servicios.MailSenderServices;

public class MailSenderProvider implements MailSenderServices {
	
	
private MailSenderServices ejbMail; 
	
	public MailSenderProvider() {
		try {
			Context ctx = new InitialContext();
			ejbMail = (MailSenderServices) ctx.lookup("EJBMailSender");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public void enviarMailTextoPlano(String email, String subject, String body) {
		ejbMail.enviarMailTextoPlano(email, subject, body);
	}

	
	
}

//#El archivo de configuracion del mail para jboss esta dentro de la carpeta server/default/deploy/mail-service.xml
//
//<?xml version="1.0" encoding="UTF-8"?>
//<!-- $Id: mail-service.xml 62350 2007-04-15 16:50:12Z dimitris@jboss.org $ -->
//<server>
//
//  <!-- ==================================================================== -->
//  <!-- Mail Connection Factory                                              -->
//  <!-- ==================================================================== -->
//
//  <mbean code="org.jboss.mail.MailService"
//         name="jboss:service=Mail">
//    <attribute name="JNDIName">java:/MailSenderService</attribute>
//    <attribute name="User">usuario_origen@dominio.com</attribute>
//    <attribute name="Password">Password</attribute>
//    <attribute name="Configuration">
//      <!-- A test configuration -->
//      <configuration>
//        <!-- Change to your mail server prototocol -->
//        <property name="mail.store.protocol" value="pop3"/>
//        <property name="mail.transport.protocol" value="smtp"/>
//
//        <!-- Change to the user who will receive mail  -->
//        <property name="mail.user" value="usuario_origen@dominio.com"/>
//
//        <!-- Change to the mail server  -->
//        <property name="mail.pop3.host" value="127.0.0.1"/>
//
//        <!-- Change to the SMTP gateway server -->
//        <property name="mail.smtp.host" value="127.0.0.1"/>
//        
//        <!-- The mail server port -->
//        <property name="mail.smtp.port" value="25"/>
//        
//        <!-- Change to the address mail will be from  -->
//        <property name="mail.from" value="usuario_origen@dominio.com"/>
//
//        <!-- Enable debugging output from the javamail classes -->
//        <property name="mail.debug" value="true"/>
//      </configuration>
//    </attribute>
//    <depends>jboss:service=Naming</depends>
//  </mbean>
//  
//</server>
