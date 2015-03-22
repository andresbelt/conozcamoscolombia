package co.org.softsolut.conozcapacho.ejb.negocio.dto;

import java.io.Serializable;

import co.org.softsolut.conozcapacho.ejb.persistencia.entidades.Usuario;

/**
 * DTO para transferir la información de un usuario del sistema
 * 
 * @author
 *
 */
public class UsuarioDTO implements Serializable {
	private int usuCodigo;
	private String usuNombre;
	private String usuApellido;
	private String usuEmail;
	private String usuUsuario;
	private String usuContrasenia;
	


	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UsuarioDTO(Usuario usuario) {
		this.usuCodigo = usuario.getUsuCodigo();
		this.usuNombre = usuario.getUsuNombre();
		this.usuApellido = usuario.getUsuApellido();
		this.usuEmail = usuario.getUsuEmail();
		this.usuUsuario = usuario.getUsuUsuario();
		this.usuContrasenia = usuario.getUsuContrasenia();

		
		//Llaves foraneas
	
	}

	public int getUsuCodigo() {
		return usuCodigo;
	}

	public void setUsuCodigo(int usuCodigo) {
		this.usuCodigo = usuCodigo;
	}

	public String getUsuNombre() {
		return usuNombre;
	}

	public void setUsuNombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	public String getUsuApellido() {
		return usuApellido;
	}

	public void setUsuApellido(String usuApellido) {
		this.usuApellido = usuApellido;
	}

	public String getUsuEmail() {
		return usuEmail;
	}

	public void setUsuEmail(String usuEmail) {
		this.usuEmail = usuEmail;
	}

	public String getUsuUsuario() {
		return usuUsuario;
	}

	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}

	public String getUsuContrasenia() {
		return usuContrasenia;
	}

	public void setUsuContrasenia(String usuContrasenia) {
		this.usuContrasenia = usuContrasenia;
	}
	
}
