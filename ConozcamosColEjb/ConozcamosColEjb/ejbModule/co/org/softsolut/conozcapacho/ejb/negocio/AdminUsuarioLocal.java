package co.org.softsolut.conozcapacho.ejb.negocio;

import javax.ejb.Local;

import co.org.softsolut.conozcapacho.ejb.negocio.dto.UsuarioDTO;
import co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion.ExcepcionDAO;

@Local
public interface AdminUsuarioLocal {

	void registrarUsuario(UsuarioDTO usuarioDTO) throws ExcepcionDAO;

}
