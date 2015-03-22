package co.org.softsolut.conozcapacho.ejb.persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion.ExcepcionDAO;
import co.org.softsolut.conozcapacho.ejb.persistencia.entidades.Usuario;

/**
 * Session Bean implementation class UsuarioDAO
 */
@Stateless
@LocalBean
public class UsuarioDAO {
	@PersistenceContext(unitName = "ConozcamosColPU")
	EntityManager manager;
	/**
	 * 
	 * @param usuario
	 * @throws ExcepcionDAO
	 */
	
	public Usuario consultarUsuarioXLogin(String usuario) throws ExcepcionDAO {
		Usuario usuarioCons = null;

		try {
			String sql = "SELECT u FROM Usuario u "
					+ "WHERE u.usuUsuario = :usuUsuario";

			Query consulta = manager.createQuery(sql);
			consulta.setParameter("usuUsuario", usuario);

			usuarioCons = (Usuario) consulta.getSingleResult();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ExcepcionDAO(e.getMessage());
		}

		return usuarioCons;
	}

	/**
	 * 
	 * @param usuario
	 * @throws ExcepcionDAO 
	 */
	public void insertarUsuario(Usuario usuario) throws ExcepcionDAO {
		try {
		
			manager.persist(usuario);
			manager.flush();
		} catch (PersistenceException pe) {
if(pe != null)
			throw new ExcepcionDAO();
		}
	}
	
	/**
	 * 
	 * @param usuario
	 * @throws ExcepcionDAO
	 */
	public void actualizarUsuario(Usuario usuario) throws ExcepcionDAO {
		try {
			manager.merge(usuario);
			manager.flush();
		} catch (PersistenceException pe) {
			throw new ExcepcionDAO(pe.getMessage());
		}
	}

}
