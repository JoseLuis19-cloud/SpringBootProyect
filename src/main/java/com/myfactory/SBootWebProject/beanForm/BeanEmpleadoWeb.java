package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.Calendar;

import javax.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.myfactory.SBootWebProject.model.Pais;
import com.myfactory.SBootWebProject.model.PuestoTrabajo;
import com.myfactory.SBootWebProject.model.Role;


@Component
@SessionScope
public class BeanEmpleadoWeb  implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private Long idEmpleadoWeb;	
	private String nombreWeb;
	private String apellidosWeb;
	private String nifWeb;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar fecAltaEmplelado2Web;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar fecBajaEmpleladoWeb;
	private String telefMovilWeb;
	@Email (message = "El formato de la dirección email no es correcto")
	private String emailWeb;
	private boolean indBajaUsuarioWeb;
	private boolean indbajaEmpleadoWeb;
	private String numSeguridaSocialWeb;
	private String numCuentaCorrienteWeb;
	private String codPostalWeb;
	private String direccionWeb;
	private String telefono2;
	private Integer codPaisWeb;
	private Iterable<Pais> paisWeb;
	private Integer codPuestoTrabajoWeb;
	private Iterable<PuestoTrabajo> puestoTrabajoWeb;
	private String impBrutoAnualWeb;
	
	public BeanEmpleadoWeb() {
	}

	public Long getIdEmpleadoWeb() {
		return idEmpleadoWeb;
	}


	public void setIdEmpleadoWeb(Long idEmpleadoWeb) {
		this.idEmpleadoWeb = idEmpleadoWeb;
	}



	public String getNombreWeb() {
		return nombreWeb;
	}



	public void setNombreWeb(String nombreWeb) {
		this.nombreWeb = nombreWeb;
	}



	public String getApellidosWeb() {
		return apellidosWeb;
	}



	public void setApellidosWeb(String apellidosWeb) {
		this.apellidosWeb = apellidosWeb;
	}



	public String getNifWeb() {
		return nifWeb;
	}

	public void setNifWeb(String nifWeb) {
		this.nifWeb = nifWeb;
	}

	public Calendar getFecBajaEmpleladoWeb() {
		return fecBajaEmpleladoWeb;
	}

	public void setFecBajaEmpleladoWeb(Calendar fecBajaEmpleladoWeb) {
		this.fecBajaEmpleladoWeb = fecBajaEmpleladoWeb;
	}

	public String getTelefMovilWeb() {
		return telefMovilWeb;
	}

	public void setTelefMovilWeb(String telefMovilWeb) {
		this.telefMovilWeb = telefMovilWeb;
	}

	public String getEmailWeb() {
		return emailWeb;
	}

	public void setEmailWeb(String emailWeb) {
		this.emailWeb = emailWeb;
	}

	public boolean isIndBajaUsuarioWeb() {
		return indBajaUsuarioWeb;
	}

	public void setIndBajaUsuarioWeb(boolean indBajaUsuarioWeb) {
		this.indBajaUsuarioWeb = indBajaUsuarioWeb;
	}



	public boolean isIndbajaEmpleadoWeb() {
		return indbajaEmpleadoWeb;
	}

	public void setIndbajaEmpleadoWeb(boolean indbajaEmpleadoWeb) {
		this.indbajaEmpleadoWeb = indbajaEmpleadoWeb;
	}

	public String getNumSeguridaSocialWeb() {
		return numSeguridaSocialWeb;
	}



	public void setNumSeguridaSocialWeb(String numSeguridaSocialWeb) {
		this.numSeguridaSocialWeb = numSeguridaSocialWeb;
	}



	public String getNumCuentaCorrienteWeb() {
		return numCuentaCorrienteWeb;
	}



	public void setNumCuentaCorrienteWeb(String numCuentaCorrienteWeb) {
		this.numCuentaCorrienteWeb = numCuentaCorrienteWeb;
	}
	

	public String getCodPostalWeb() {
		return codPostalWeb;
	}

	public void setCodPostalWeb(String codPostalWeb) {
		this.codPostalWeb = codPostalWeb;
	}

	public String getDireccionWeb() {
		return direccionWeb;
	}

	public void setDireccionWeb(String direccionWeb) {
		this.direccionWeb = direccionWeb;
	}
	
	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	
	public Integer getCodPaisWeb() {
		return codPaisWeb;
	}

	public void setCodPaisWeb(Integer codPaisWeb) {
		this.codPaisWeb = codPaisWeb;
	}

	public Iterable<Pais> getPaisWeb() {
		return paisWeb;
	}

	public void setPaisWeb(Iterable<Pais> paisWeb) {
		this.paisWeb = paisWeb;
	}
	
	
	public Integer getCodPuestoTrabajo() {
		return codPuestoTrabajoWeb;
	}

	public void setCodPuestoTrabajoWeb(Integer codPuestoTrabajo) {
		this.codPuestoTrabajoWeb = codPuestoTrabajo;
	}

	public Iterable<PuestoTrabajo> getPuestoTrabajoWeb() {
		return puestoTrabajoWeb;
	}
	
	public void setPuestoTrabajoWeb(Iterable<PuestoTrabajo> puestoTrabajoWeb) {
		this.puestoTrabajoWeb = puestoTrabajoWeb;
	}

	public String getImpBrutoAnualWeb() {
		return impBrutoAnualWeb;
	}

	public void setImpBrutoAnualWeb(String impBrutoAnualWeb) {
		this.impBrutoAnualWeb = impBrutoAnualWeb;
	}

	public Integer getCodPuestoTrabajoWeb() {
		return codPuestoTrabajoWeb;
	}
	
	public Calendar getFecAltaEmplelado2Web() {
		return fecAltaEmplelado2Web;
	}

	public void setFecAltaEmplelado2Web(Calendar fecAltaEmplelado2Web) {
		this.fecAltaEmplelado2Web = fecAltaEmplelado2Web;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
	BeanEmpleadoWeb nuevoBeanClienteWeb = new BeanEmpleadoWeb ();
	return nuevoBeanClienteWeb;
	}   
		
}
