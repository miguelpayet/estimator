package pe.com.pps.model;

public class ExcepcionCronograma extends Throwable {

	public ExcepcionCronograma(String message) {
		super(message);
	}

	public ExcepcionCronograma(String message, Throwable throwable) {
		super(message, throwable);
	}

}
