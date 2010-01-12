package com.inia_mscc.config.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class LoggingUtilities {
	
    public static String obtenerStackTrace(Throwable throwable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        Throwable z = throwable;
        z.printStackTrace(printWriter);
        return result.toString();
    }
}
