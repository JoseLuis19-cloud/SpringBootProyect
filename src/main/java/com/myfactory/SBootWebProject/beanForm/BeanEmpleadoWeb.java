package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.Calendar;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
public class BeanEmpleadoWeb  implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private Long idEmpleadoWeb;	
	private String nombreWeb;
	private String apellidosWeb;
	private String nifWeb;
    private Calendar fecAltaEmpleladoWeb;
    private Calendar fecBajaEmpleladoWeb;
	private String telefMovilWeb;
	private String emailWeb;
	private boolean indBajaUsuarioWeb;
	private boolean indbajaEmpleadoWeb;
	private String numSeguridaSocialWeb;
	private String numCuentaCorrienteWeb;	


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



	public Calendar getFecAltaEmpleladoWeb() {
		return fecAltaEmpleladoWeb;
	}



	public void setFecAltaEmpleladoWeb(Calendar fecAltaEmpleladoWeb) {
		this.fecAltaEmpleladoWeb = fecAltaEmpleladoWeb;
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



		@Override
		protected Object clone() throws CloneNotSupportedException {
			BeanEmpleadoWeb nuevoBeanClienteWeb = new BeanEmpleadoWeb ();
		   return nuevoBeanClienteWeb;
		 }   
		
}
