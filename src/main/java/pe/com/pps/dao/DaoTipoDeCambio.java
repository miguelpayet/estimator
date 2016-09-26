package pe.com.pps.dao;

import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

public class DaoTipoDeCambio {

	private static final String CAMBIO = "USDPEN";
	private static final String CONSULTA = "select%20*%20from%20yahoo.finance.xchange%20where%20pair%20%3D%20%22{}%22";
	private static final String RUTA = "https://query.yahooapis.com/v1/public/yql?q={}&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

	private String crearDireccion() {
		String consulta = CONSULTA.replace("{}", CAMBIO);
		return RUTA.replace("{}", consulta);
	}

	private Double extraerTipoDeCambio(String unXml) {
		Double tipoDeCambio = 0d;
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		InputSource source = new InputSource(new StringReader(unXml));
		try {
			String status = xpath.evaluate("/query/results/rate[@id='USDPEN']/Rate", source);
		} catch (XPathExpressionException e) {
			tipoDeCambio = 0d;
		}
		return tipoDeCambio;
	}

	public String leerResultado(String unaDireccion) {
		String resultado;
		try {
			URL oracle = new URL(unaDireccion);
			BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
			String inputLine;
			StringBuffer sb = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				sb.append(inputLine);
			in.close();
			resultado = sb.toString();
		} catch (IOException e) {
			resultado = "";
		}
		return resultado;
	}

	public Double leerTipoDeCambio() {
		String resultado = leerResultado(crearDireccion());
		Double tipoDeCambio = extraerTipoDeCambio(resultado);
		return tipoDeCambio;
	}

}
