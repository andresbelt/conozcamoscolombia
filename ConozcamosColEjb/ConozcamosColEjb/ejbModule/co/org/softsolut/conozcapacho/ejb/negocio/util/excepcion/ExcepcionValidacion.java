package co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion;

/**
 * 
 * @author Mauricio Marin
 *
 */
public class ExcepcionValidacion extends Exception {
	public ExcepcionValidacion() {
		super();
	}
	
	public ExcepcionValidacion(String mensaje) {
		super(mensaje);
	}
}
