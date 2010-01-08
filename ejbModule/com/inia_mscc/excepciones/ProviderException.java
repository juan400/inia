package com.inia_mscc.excepciones;

public class ProviderException extends RuntimeException {

	 public static final String EXCEPTION_NAME = "HibernateException";

	    private static final long serialVersionUID = 1L;

		public ProviderException() {
			super();
		}

		public ProviderException(String message, Throwable cause) {
			super(message, cause);
		}

		public ProviderException(String message) {
			super(message);
		}

		public ProviderException(Throwable cause) {
			super(cause);
		}
}
