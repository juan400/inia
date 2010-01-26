package com.bean.seg;

import java.util.Date;

import javax.ejb.SessionBean;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class MailerBean implements SessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void ejbCreate() {
	}

	public void ejbPostCreate() {
	}

	public void sendMail(String address) throws java.rmi.RemoteException {
		Session session = null;
		try {
			session = (Session) PortableRemoteObject.narrow(
					new InitialContext().lookup("java:Mail"), Session.class);
		} catch (javax.naming.NamingException e) {
			e.printStackTrace();
		}

		try {
			MimeMessage m = new MimeMessage(session);
			m.setFrom();
			Address[] to = new InternetAddress[] { new InternetAddress(address) };
			m.setRecipients(Message.RecipientType.TO, to);
			m.setSubject("JavaMail Test");
			m.setSentDate(new Date());
			m.setContent("Test from inside EJB Using JBoss", "text/plain");
			Transport.send(m);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
	}

	public void ejbActivate() {
	}

	public void ejbPassivate() {
	}

	public void ejbRemove() {
	}

	public void setSessionContext(javax.ejb.SessionContext ec) {
	}
}
