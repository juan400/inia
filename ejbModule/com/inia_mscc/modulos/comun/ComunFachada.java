package com.inia_mscc.modulos.comun;

import java.io.IOException;

import com.inia_mscc.excepciones.ErrorEnviandoMailException;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.proveedores.MailSenderProvider;
import com.inia_mscc.modulos.comun.servicios.MailSenderServices;

public class ComunFachada {
	private MailSenderServices srvMailSender;

	public ComunFachada(Enumerados.ServicioComun servicio) throws IOException {
		switch (servicio) {
		case MailSender:
			srvMailSender = new MailSenderProvider();
			break;
		}
	}

	public void enviarMailTextoPlano(String email, String subject, String body) throws ErrorEnviandoMailException, Exception {
		srvMailSender.enviarMailTextoPlano(email, subject, body);
	}

}
