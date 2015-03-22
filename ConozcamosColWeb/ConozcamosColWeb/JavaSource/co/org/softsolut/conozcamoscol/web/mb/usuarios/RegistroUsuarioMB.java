/**
 * 
 */
/**
 * @author Andres
 *
 */
package co.org.softsolut.conozcamoscol.web.mb.usuarios;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import co.org.softsolut.conozcamoscol.web.delegate.AdminUsuarioDelegate;
import co.org.softsolut.conozcapacho.ejb.negocio.dto.UsuarioDTO;
import co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion.ExcepcionAplicacion;
import co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion.ExcepcionDAO;
import co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion.ExcepcionValidacion;



@ManagedBean
@SessionScoped
public class RegistroUsuarioMB {
	private String nombres;
	private String apellidos;
	private String email;
	private String usuario;
	private String contrasenia;
	
	private AdminUsuarioDelegate adminUsuarioDelegate;
	private FacesContext fc;
	public RegistroUsuarioMB(){
		
	}
	
	
	public void registrarUsu() throws ExcepcionValidacion{
		fc = FacesContext.getCurrentInstance();
	
		try {
			 
	
		// Se encapsula la información en un DTO para ser enviada al negocio
			
			
			UsuarioDTO usuarioDTO = new UsuarioDTO();
							
						
			usuarioDTO.setUsuNombre(nombres);
			usuarioDTO.setUsuApellido(apellidos);
			usuarioDTO.setUsuEmail(email);
			usuarioDTO.setUsuUsuario(usuario);
			usuarioDTO.setUsuContrasenia(contrasenia);
		

			// Se invoca la lógica de negocio para crear un usuario en el
			// sistema
			adminUsuarioDelegate = new AdminUsuarioDelegate();	
			
			adminUsuarioDelegate.registrarUsuario(usuarioDTO);


			//Se redirecciona a la página inicio para que se autentique
			
			
			fc.getExternalContext().redirect("/ConozcamosColWeb/jsp/inicio.xhtml");
							
			limpiarCampos();
				
						}catch (ExcepcionAplicacion e) {
				
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"app", e.getMessage()));
		} catch (ExcepcionDAO e) {
		
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
"", e.getMessage()));
		} catch (IOException e) {
		
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"IO", e.getMessage()));
		}
		
		fc.renderResponse();
	
		
		return;
	}
	
	public void limpiarCampos() {
	
		usuario="";
		apellidos="";
		contrasenia="";
		nombres="";
		email="";
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}





}