package co.org.softsolut.conozcapacho.ejb.negocio;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import co.org.softsolut.conozcapacho.ejb.negocio.dto.UsuarioDTO;
import co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion.ExcepcionDAO;
import co.org.softsolut.conozcapacho.ejb.persistencia.dao.UsuarioDAO;
import co.org.softsolut.conozcapacho.ejb.persistencia.entidades.Usuario;

/**
 * Session Bean implementation class AdminUsuario
 */
@Stateless
@LocalBean
public class AdminUsuario implements AdminUsuarioLocal {

	@EJB
	UsuarioDAO usuarioDAO;

	@Override
	public void registrarUsuario(UsuarioDTO usuarioDTO) throws ExcepcionDAO {
		Usuario usuario = new Usuario();
		usuario.setUsuNombre(usuarioDTO.getUsuNombre());
		usuario.setUsuApellido(usuarioDTO.getUsuApellido());
		usuario.setUsuEmail(usuarioDTO.getUsuEmail());
		usuario.setUsuUsuario(usuarioDTO.getUsuUsuario());
		usuario.setUsuContrasenia(usuarioDTO.getUsuContrasenia());
		
			
		
		usuarioDAO.insertarUsuario(usuario);
	}

}
