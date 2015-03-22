package co.org.softsolut.conozcapacho.ejb.negocio.sistema;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.org.softsolut.conozcapacho.ejb.negocio.dto.UsuarioDTO;
import co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion.ExcepcionDAO;
import co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion.ExcepcionValidacion;
import co.org.softsolut.conozcapacho.ejb.persistencia.dao.UsuarioDAO;
import co.org.softsolut.conozcapacho.ejb.persistencia.entidades.Usuario;

/**
 * Session Bean implementation class AdminSistema
 */
@Stateless
@LocalBean
public class AdminSistema {
@EJB
UsuarioDAO usuarioDAO;
	/**
	 *
	 * 
	 * @param usuario
	 * @param contrasenia
	 * @throws ExcepcionDAO
	 * @throws ExcepcionValidacion 
	 */
	public UsuarioDTO autenticarUsuario(String usuario, String contrasenia) 
		throws ExcepcionDAO, ExcepcionValidacion {
			UsuarioDTO usuarioDTO = null;

			try {
				// Consultar si el usuario existe en la BD
				Usuario usuarioCons = usuarioDAO.consultarUsuarioXLogin(usuario);

				if (usuarioCons != null) {
					//Verificar la correctitud de la contraseña
					if(!contrasenia.equals(usuarioCons.getUsuContrasenia()))
						throw new ExcepcionValidacion("La contraseña ingresada no " +
								"corresponde a la del usuario");
					
					usuarioDTO = new UsuarioDTO(usuarioCons);
				}
			} catch (ExcepcionDAO e) {
				if (e.getMessage().contains("No entity found for query"))
					throw new ExcepcionValidacion(
							"El usuario ingresado no existe en el sistema");
				else
					throw e;
			}
	
		return usuarioDTO;}
		
	
}
