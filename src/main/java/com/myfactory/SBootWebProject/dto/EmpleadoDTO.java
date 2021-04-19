package com.myfactory.SBootWebProject.dto;

import java.io.Serializable;
import java.util.Calendar;
import com.myfactory.SBootWebProject.model.User;

public class EmpleadoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idEmpleado;
	private User usuario;
	private String nombre;
	private String apellidos;
	private String nif;
    private Calendar fecAltaEmplelado;
    private Calendar fecBajaEmplelado;
	private String telefMovil;
	private String email;
	
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public Calendar getFecAltaEmplelado() {
		return fecAltaEmplelado;
	}
	public void setFecAltaEmplelado(Calendar fecAltaEmplelado) {
		this.fecAltaEmplelado = fecAltaEmplelado;
	}
	public Calendar getFecBajaEmplelado() {
		return fecBajaEmplelado;
	}
	public void setFecBajaEmplelado(Calendar fecBajaEmplelado) {
		this.fecBajaEmplelado = fecBajaEmplelado;
	}
	public String getTelefMovil() {
		return telefMovil;
	}
	public void setTelefMovil(String telefMovil) {
		this.telefMovil = telefMovil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
