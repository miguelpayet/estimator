package pe.com.pps.model;

public class Util {

	public static double round(double unDoble, int unaPrecision) {
		double test =  Math.round(unDoble * Math.pow(10, unaPrecision)) /  Math.pow(10, unaPrecision);
		return test;
	}

}
