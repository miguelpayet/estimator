package pe.com.pps.model;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Util {

	private static DecimalFormat df;

	static {
		DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
		df = new DecimalFormat("###,###,###.00", unusualSymbols);
	}

	public static String format(double unDoble) {
		return df.format(unDoble);
	}

	public static String format(Date unaFecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy' 'HH:mm:ss");
		return sdf.format(unaFecha);
	}

	public static Properties loadProperties(String unFilename) throws IOException {
		Properties props = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream(unFilename);
		props.load(stream);
		return props;
	}

	public static double round(double unDoble, int unaPrecision) {
		return Math.round(unDoble * Math.pow(10, unaPrecision)) / Math.pow(10, unaPrecision);
	}

	private Util() {
		// constructor privado para impedir instanciación
	}

}
