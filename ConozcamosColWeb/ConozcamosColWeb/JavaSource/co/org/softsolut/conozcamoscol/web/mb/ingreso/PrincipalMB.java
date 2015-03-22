package co.org.softsolut.conozcamoscol.web.mb.ingreso;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.org.softsolut.conozcapacho.ejb.negocio.dto.UsuarioDTO;

@ManagedBean
@SessionScoped
public class PrincipalMB {
	private FacesContext fc;

	private UsuarioDTO usuarioDTO;

	public PrincipalMB() {
		fc = FacesContext.getCurrentInstance();
		
		HttpSession sesion = ((HttpServletRequest) fc.getExternalContext().getRequest()).getSession(false);
		
		usuarioDTO = (UsuarioDTO)sesion.getAttribute("usuarioConozcamosCol");
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
}
