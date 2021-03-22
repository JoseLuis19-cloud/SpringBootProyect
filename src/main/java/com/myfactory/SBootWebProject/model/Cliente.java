package com.myfactory.SBootWebProject.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CLIENTE", unique = true)
	private Integer idCliente;

	@Column(name = "NOMBRE", nullable = false, unique = false)
	private String nombre;

	@Column(name = "APELLIDOS", nullable = true, unique = false)
	private String apellidos;

	@Column(name = "FEC_NACIMIENTO", nullable = true, unique = false)
	private Date fecNacimiento;

	@Column(name = "DNI", nullable = true, unique = false)
	private String DNI;

	@Column(name = "DIRECCION", nullable = true, unique = false)
	private String direccion;

	@Column(name = "PAIS", nullable = true, unique = false)
	private String pais;
	
	@Column(name = "DIR_EMAIL", nullable = true, unique = false)
	private String dirEmail;
	
	@Column(name = "TELEFONO", nullable = true, unique = false)
	private String telefono;
	
	@Column(name = "FEC_ALTA_CLIENTE", nullable = true, unique = false)
    @Temporal(TemporalType.DATE)
	private Calendar fecAltaCliente;

	// @Column(name="ID_TPO_CLIENTE_FK", nullable=false, unique=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TPO_CLIENTE_FK")
	private TpoCliente tpoCliente;
	
	@Lob
	//@Column(length=100000)
	@Column(name = "IMG_FOTO_PERSONA", nullable = true)
	private java.sql.Blob imagenFotoCliente;

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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

	public Date getFecNacimiento() {
		return fecNacimiento;
	}

	public void setFecNacimiento(Date fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public TpoCliente getTpoCliente() {
		return tpoCliente;
	}

	public void setTpoCliente(TpoCliente tpoCliente) {
		this.tpoCliente = tpoCliente;
	}
	
	public String getDirEmail() {
		return dirEmail;
	}

	public void setDirEmail(String dirEmail) {
		this.dirEmail = dirEmail;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Calendar getFecAltaCliente() {
		return fecAltaCliente;
	}

	public void setFecAltaCliente(Calendar fecAltaCliente) {
		this.fecAltaCliente = fecAltaCliente;
	}
	
	public java.sql.Blob getImagenFotoCliente() {
		return imagenFotoCliente;
	}

	public void setImagenFotoCliente(java.sql.Blob imagenFotoCliente) {
		this.imagenFotoCliente = imagenFotoCliente;
	}

}
