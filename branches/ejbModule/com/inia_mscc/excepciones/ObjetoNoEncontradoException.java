package com.inia_mscc.excepciones;

public class ObjetoNoEncontradoException extends RuntimeException {

	
	 public static final String EXCEPTION_NAME = "ObjetoNoEncotradoException";

	    private static final long serialVersionUID = 1L;

		public ObjetoNoEncontradoException() {
			super();
		}

		public ObjetoNoEncontradoException(String message, Throwable cause) {
			super(message, cause);
		}

		public ObjetoNoEncontradoException(String message) {
			super(message);
		}

		public ObjetoNoEncontradoException(Throwable cause) {
			super(cause);
		}

}
