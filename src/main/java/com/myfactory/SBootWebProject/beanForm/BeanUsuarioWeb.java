package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.myfactory.SBootWebProject.model.Role;


@Component
@RequestScope
public class BeanUsuarioWeb  implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idUsuarioWeb;
	private String fullNameWeb;
	private String usernameWeb;
	private String passwordWeb;
	private String emailWeb;
	private boolean enabledWeb;
	private boolean indEmpleadoWeb;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar fecAltaUsuarioWeb;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar fecBajaUsuarioWeb;	
	private Iterable<Role> rolUsuarioWeb;
	private Integer idRole;

	public BeanUsuarioWeb() {
	}

	public Long getIdUsuarioWeb() {
	return idUsuarioWeb;
	}



	public void setIdUsuarioWeb(Long idUsuarioWeb) {
		this.idUsuarioWeb = idUsuarioWeb;
	}



	public String getFullNameWeb() {
		return fullNameWeb;
	}



	public void setFullNameWeb(String fullNameWeb) {
		this.fullNameWeb = fullNameWeb;
	}



	public String getUsernameWeb() {
		return usernameWeb;
	}



	public void setUsernameWeb(String usernameWeb) {
		this.usernameWeb = usernameWeb;
	}



	public String getPasswordWeb() {
		return passwordWeb;
	}



	public void setPasswordWeb(String passwordWeb) {
		this.passwordWeb = passwordWeb;
	}



	public String getEmailWeb() {
		return emailWeb;
	}



	public void setEmailWeb(String emailWeb) {
		this.emailWeb = emailWeb;
	}



	public boolean isEnabledWeb() {
		return enabledWeb;
	}



	public void setEnabledWeb(boolean enabledWeb) {
		this.enabledWeb = enabledWeb;
	}



	public boolean isIndEmpleadoWeb() {
		return indEmpleadoWeb;
	}



	public void setIndEmpleadoWeb(boolean indEmpleadoWeb) {
		this.indEmpleadoWeb = indEmpleadoWeb;
	}



	public Calendar getFecAltaUsuarioWeb() {
		return fecAltaUsuarioWeb;
	}



	public void setFecAltaUsuarioWeb(Calendar fecAltaUsuarioWeb) {
		this.fecAltaUsuarioWeb = fecAltaUsuarioWeb;
	}



	public Calendar getFecBajaUsuarioWeb() {
		return fecBajaUsuarioWeb;
	}



	public void setFecBajaUsuarioWeb(Calendar fecBajaUsuarioWeb) {
		this.fecBajaUsuarioWeb = fecBajaUsuarioWeb;
	}

	public Iterable<Role> getRolUsuarioWeb() {
	return rolUsuarioWeb;
	}

	public void setRolUsuarioWeb(Iterable<Role> rolUsuarioWeb) {
		this.rolUsuarioWeb = rolUsuarioWeb;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		BeanUsuarioWeb nuevoBeanClienteWeb = new BeanUsuarioWeb ();
		return nuevoBeanClienteWeb;
	}  
	
	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
	
		
}
