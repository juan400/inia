package com.inia_mscc.modulos.comun.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.modulos.comun.servicios.MailSenderServices;

@Stateless(name = "EJBMailSender", mappedName = "EJBMailSender")
@Remote(MailSenderServices.class)
public class EJBMailSender implements MailSenderServices {

	private Session mail;

	public void enviarMailTextoPlano(String email, String subject, String body) {
		// MailSenderService fue renombrado en el archivo de configuracion
		// C:\jboss-5.1.0.GA\server\default\deploy\mail-service.xml
		InitialContext ctx;
		MimeMessage message = new MimeMessage(mail);
		try {
			ctx = new InitialContext();
			mail = (Session) ctx.lookup("java:/MailSenderService");
			message.setSubject(subject);
			message.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(email, false));
			message.setText(body);
			Transport.send(message);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}