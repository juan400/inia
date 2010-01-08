package com.inia_mscc.excepciones;

public class ErrorEnviandoMailException extends RuntimeException {

	 public static final String EXCEPTION_NAME = "ErrorAlEnviarMailException";

	    private static final long serialVersionUID = 1L;

		public ErrorEnviandoMailException() {
			super();
		}

		public ErrorEnviandoMailException(String message, Throwable cause) {
			super(message, cause);
		}

		public ErrorEnviandoMailException(String message) {
			super(message);
		}

		public ErrorEnviandoMailException(Throwable cause) {
			super(cause);
		}

}
