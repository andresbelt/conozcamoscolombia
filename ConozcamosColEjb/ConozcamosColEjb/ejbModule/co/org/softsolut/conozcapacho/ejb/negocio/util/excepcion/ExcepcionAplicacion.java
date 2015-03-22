package co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion;

/**
 * 
 * @author Mauricio Marin
 *
 */
public class ExcepcionAplicacion extends Exception {
	public ExcepcionAplicacion() {
		super();
	}

	public ExcepcionAplicacion(String mensaje) {
		super(mensaje);
	}
}
