package com.inia_mscc.excepciones;

public class IniaPersistenciaException  extends RuntimeException  {
	
	  public static final String EXCEPTION_NAME = "HibernateException";

	    private static final long serialVersionUID = 1L;

		public IniaPersistenciaException() {
			super();
		}

		public IniaPersistenciaException(String message, Throwable cause) {
			super(message, cause);
		}

		public IniaPersistenciaException(String message) {
			super(message);
		}

		public IniaPersistenciaException(Throwable cause) {
			super(cause);
		}

}
