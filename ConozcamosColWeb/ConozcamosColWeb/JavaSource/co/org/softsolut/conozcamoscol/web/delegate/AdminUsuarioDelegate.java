/**
 * 
 */
/**
 * @author Andres
 *
 */
package co.org.softsolut.conozcamoscol.web.delegate;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.org.softsolut.conozcapacho.ejb.negocio.AdminUsuarioLocal;
import co.org.softsolut.conozcapacho.ejb.negocio.dto.UsuarioDTO;
import co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion.ExcepcionAplicacion;
import co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion.ExcepcionDAO;



public class AdminUsuarioDelegate {
	private AdminUsuarioLocal adminUsuarioLocal;

	/**
	 * 
	 * @throws ExcepcionAplicacion
	 */
	public AdminUsuarioDelegate() throws ExcepcionAplicacion {
		try {
			Context context = new InitialContext();
			adminUsuarioLocal = (AdminUsuarioLocal) context
					.lookup("java:global/ConozcamosColEar/ConozcamosColEjb/AdminUsuario!co.org.softsolut.conozcapacho.ejb.negocio.AdminUsuarioLocal");
		} catch (NamingException e) {
			throw new ExcepcionAplicacion(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param usuarioDTO
	 * @throws ExcepcionDAO
	 */
	public void registrarUsuario(UsuarioDTO usuarioDTO) throws ExcepcionDAO {
	
				
		adminUsuarioLocal.registrarUsuario(usuarioDTO);
	
	
	}

}


