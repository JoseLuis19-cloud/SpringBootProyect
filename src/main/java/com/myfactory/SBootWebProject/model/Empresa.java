package com.myfactory.SBootWebProject.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EMPRESA")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_EMPRESA", unique = true)
	private Integer idEmpresa;

	@Column(name = "NOM_EMPRESA", nullable = false, unique = true)
	private String nomEmpresa;
	
	@Column(name = "CIF", nullable = true, unique = true)
	private String cif;

	@Column(name = "DIRECCION", nullable = true, unique = false)
	private String direcion;

	@Column(name = "COD_PROVINCIA", nullable = true, unique = false)
	private Integer codProvincia;

	@Column(name = "COD_POSTAL", nullable = true, unique = false)
	private String codPostal;

	@Column(name = "TELEFONO", nullable = true, unique = false)
	private String telefono;

	@Column(name = "NOM_CONTACTO_1", nullable = true, unique = false)
	private String nomContacto1;
	
	@Column(name = "TELEF_CONTACTO_1", nullable = true, unique = false)
	private String telefContacto1;
	
	@Column(name = "EMAIL_CONTACTO_1", nullable = true, unique = false)
	private String emailContacto1;
	
	@Column(name = "NOM_CONTACTO_2", nullable = true, unique = false)
	private String nomContacto2;
	
	@Column(name = "TELEF_CONTACTO_2", nullable = true, unique = false)
	private String telefContacto2;
	
	@Column(name = "EMAIL_CONTACTO_2", nullable = true, unique = false)
	private String emailContacto2;
	
	@Column(name = "FEC_ALTA_EMPRESA", nullable = true, unique = false)
    @Temporal(TemporalType.DATE)
	private Calendar fecAltaEmpresa;

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomEmpresa() {
		return nomEmpresa;
	}

	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}

	public String getDirecion() {
		return direcion;
	}

	public void setDirecion(String direcion) {
		this.direcion = direcion;
	}

	public Integer getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(Integer codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public String getEmailContacto1() {
		return emailContacto1;
	}

	public void setEmailContacto1(String emailContacto1) {
		this.emailContacto1 = emailContacto1;
	}

	public String getEmailContacto2() {
		return emailContacto2;
	}

	public void setEmailContacto2(String emailContacto2) {
		this.emailContacto2 = emailContacto2;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNomContacto1() {
		return nomContacto1;
	}

	public void setNomContacto1(String nomContacto1) {
		this.nomContacto1 = nomContacto1;
	}

	public String getTelefContacto1() {
		return telefContacto1;
	}

	public void setTelefContacto1(String telefContacto1) {
		this.telefContacto1 = telefContacto1;
	}

	public String getNomContacto2() {
		return nomContacto2;
	}

	public void setNomContacto2(String nomContacto2) {
		this.nomContacto2 = nomContacto2;
	}

	public String getTelefContacto2() {
		return telefContacto2;
	}

	public void setTelefContacto2(String telefContacto2) {
		this.telefContacto2 = telefContacto2;
	}

	public Calendar getFecAltaEmpresa() {
		return fecAltaEmpresa;
	}

	public void setFecAltaEmpresa(Calendar fecAltaEmpresa) {
		this.fecAltaEmpresa = fecAltaEmpresa;
	}
	
	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}
}
