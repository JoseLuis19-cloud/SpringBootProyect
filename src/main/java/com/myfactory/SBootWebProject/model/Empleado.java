package com.myfactory.SBootWebProject.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Empleado")
public class Empleado  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_EMPLEADO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long idEmpleado;
	
	@OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@PrimaryKeyJoinColumn
	private User usuario;

	@Column(name = "NOMBRE", length = 20, nullable = false)
	private String nombre;
	
	@Column(name = "APELLIDOS", length = 30, nullable = true)
	private String apellidos;
	
	@Column(name = "NIF", length = 10, nullable = true)
	private String nif;
	
	@Column(name = "FEC_ALTA_EMPLEADO", nullable = true)
    @Temporal(TemporalType.DATE)
    private Calendar fecAltaEmplelado;
	
	@Column(name = "FEC_BAJA_EMPLEADO", nullable = true)
    @Temporal(TemporalType.DATE)
    private Calendar fecBajaEmplelado;
	
	@Column(name = "TEL_MOVIL", length = 14, nullable = true)
	private String telefMovil;
	
	@Column(name = "EMAIL", length = 30, nullable = true)
	private String email;
	
	@Column(name = "IND_BAJA_USUARIO")
	private boolean indBajaUsuario;
	
	@Column(name = "IND_BAJA_EMPLEADO")
	private boolean indbajaEmpleado;
	
	@Column(name = "NUM_SEG_SOCIAL", length = 12, nullable = true)
	private String numSeguridaSocial;
	
	@Column(name = "NUM_CUENTA_CORRIENTE", length = 24, nullable = true)
	private String numCuentaCorriente;
	
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
		
		  if (usuario == null) {
		        if (this.usuario != null) this.usuario.setEmpleado(null);
		    }
		    else usuario.setEmpleado(this);
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
	public boolean isIndBajaUsuario() {
		return indBajaUsuario;
	}
	public void setIndBajaUsuario(boolean indBajaUsuario) {
		this.indBajaUsuario = indBajaUsuario;
	}
	public boolean isIndbajaEmpleado() {
		return indbajaEmpleado;
	}
	public void setIndbajaEmpleado(boolean indbajaEmpleado) {
		this.indbajaEmpleado = indbajaEmpleado;
	}
	public String getNumSeguridaSocial() {
		return numSeguridaSocial;
	}
	public void setNumSeguridaSocial(String numSeguridaSocial) {
		this.numSeguridaSocial = numSeguridaSocial;
	}
	public String getNumCuentaCorriente() {
		return numCuentaCorriente;
	}
	public void setNumCuentaCorriente(String numCuentaCorriente) {
		this.numCuentaCorriente = numCuentaCorriente;
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
	
}
