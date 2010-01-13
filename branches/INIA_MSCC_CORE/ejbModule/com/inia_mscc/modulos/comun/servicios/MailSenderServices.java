package com.inia_mscc.modulos.comun.servicios;

import com.inia_mscc.excepciones.ErrorEnviandoMailException;



public interface MailSenderServices {
	
	  public void enviarMailTextoPlano(String email, String subject, String body)throws ErrorEnviandoMailException;

}
