package pe.com.pps.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	private static DecimalFormat df;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy' 'HH:mm:ss");

	static {
		//Locale bLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
		DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(); //bLocale);
		df = new DecimalFormat("###,###,###.00", unusualSymbols);
	}

	public static String format(double unDoble) {
		return df.format(unDoble);
	}

	public static String format(Date unaFecha) {
		return sdf.format(unaFecha);
	}

	public static double round(double unDoble, int unaPrecision) {
		return Math.round(unDoble * Math.pow(10, unaPrecision)) / Math.pow(10, unaPrecision);
	}

}
