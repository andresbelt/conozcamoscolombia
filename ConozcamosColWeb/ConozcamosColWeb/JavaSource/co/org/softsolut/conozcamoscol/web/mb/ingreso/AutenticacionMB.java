/**
 * @author Andres
 *
 */
package co.org.softsolut.conozcamoscol.web.mb.ingreso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;



import co.org.softsolut.conozcapacho.ejb.negocio.sistema.AdminSistema;
import co.org.softsolut.conozcapacho.ejb.negocio.dto.UsuarioDTO;
import co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion.ExcepcionDAO;
import co.org.softsolut.conozcapacho.ejb.negocio.util.excepcion.ExcepcionValidacion;

@ManagedBean
@RequestScoped

public class AutenticacionMB{
	
private String usuario;
private String contrasenia;
private FacesContext fc;
@EJB
AdminSistema adminSistema;

	
	public AutenticacionMB(){
	
	}
	
	public void autenticarUsuario(ActionEvent actionEvent){
		
		fc = FacesContext.getCurrentInstance();
		 RequestContext requestContext = RequestContext.getCurrentInstance();
		// Autenticar al usuario en el sistema
		try {
			UsuarioDTO usuarioDTO = adminSistema.autenticarUsuario(usuario, contrasenia);

			//Abrir una nueva sesión mandando como parámetro true
			HttpSession sesion = ((HttpServletRequest) fc.getExternalContext().getRequest()).getSession(true);
			
			//Guardar el usuario autenticadoo en la sesion
			sesion.setAttribute("usuarioConozcamosCol", usuarioDTO);
			
			fc.getExternalContext().redirect("/ConozcamosColWeb/jsp/menu.xhtml" 
					);
		} catch (ExcepcionDAO e) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"El usuario no existe", e.getMessage()));
		} catch (ExcepcionValidacion e) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"", e.getMessage()));
		} catch (IOException e) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error autenticando al usuario", e.getMessage()));
		}
		   
        requestContext.update("growl");

	}
	
	public void Cerrarsesion(ActionEvent actionEvent) throws IOException{
		
		fc = FacesContext.getCurrentInstance();

		HttpSession sesion = ((HttpServletRequest) fc.getExternalContext().getRequest()).getSession(false);
			sesion.invalidate();
			fc.getExternalContext().redirect("/ConozcamosColWeb/jsp/inicio.xhtml" );
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


