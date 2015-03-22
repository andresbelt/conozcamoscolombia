package co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion;

/**
 * 
 * @author 
 * 
 */
public class ExcepcionDAO extends Exception {
	/**
	 * 
	 */
	public ExcepcionDAO() {
		super();
	}
	
	public ExcepcionDAO(String mensaje) {
		super(mensaje);
	}
}
