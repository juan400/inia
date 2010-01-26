package com.inia_mscc.modulos.comun.entidades;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {

	/**
	 * main de prueba
	 * 
	 * @param args
	 *            Se ignoran.
	 */
	public void enviar() {
		try {
			// Propiedades de la conexión
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", "juan400@gmail.com");
			props.setProperty("mail.smtp.auth", "true");

			// Preparamos la sesion
			Session session = Session.getDefaultInstance(props);

			// Construimos el mensaje
//			MimeMessage message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("yo@yo.com"));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
//					"chuidiang@gmail.com"));
//			message.setSubject("Hola");
//			message.setText("Mensajito con Java Mail" + "de los buenos."
//					+ "poque si");
			
			//TODO Cuerpo del mail, parte de texto
			BodyPart texto = new MimeBodyPart();

			// Texto del mensaje
			texto.setText("Texto del mensaje");
			
			//TODO Cuerpo del mail, parte del adjunto con la imagen. Suponemos que la tenemos en un fichero.
			BodyPart adjunto = new MimeBodyPart();

			// Cargamos la imagen
			adjunto.setDataHandler(new DataHandler(new FileDataSource(
					"../Recursos/Imagenes/logoINIA.gif")));

			// Opcional. De esta forma transmitimos al receptor el nombre
			// original del
			// fichero de imagen.
			adjunto.setFileName("logoINIA.gif");

			//TODO Cuerpo del mail, juntamos ambas en una sola parte que luego debemos añadir al mensaje.
			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(texto);
			multiParte.addBodyPart(adjunto);
			
			//TODO Construimos el mensaje, le ponemos este MimeMultiPart como contenido y rellenamos el resto de campos from, to y subject.
			MimeMessage message = new MimeMessage(session);

			// Se rellena el From
			message.setFrom(new InternetAddress("juan400@gmail.com"));

			// Se rellenan los destinatarios
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("juan400@gmail.com"));

			// Se rellena el subject
			message.setSubject("Hola");

			// Se mete el texto y la foto adjunta.
			message.setContent(multiParte);
			//TODO Una vez construido el mensaje -el simple o el compuesto con adjunto- lo enviamos. Para ello necesitamos una instancia de la clase Transport. Se hace de la siguiente manera
			Transport t = session.getTransport("smtp");

			// Aqui usuario y password de gmail
			t.connect("juan400@gmail.com","andres4003341");
			t.sendMessage(message,message.getAllRecipients());
			t.close();
			
			
			//Lo enviamos.
//			Transport t = session.getTransport("smtp");
//			t.connect("chuidiang@gmail.com", "la clave");
//			t.sendMessage(message, message.getAllRecipients());

			// Cierre.
			t.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}