package com.myfactory.SBootWebProject.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PROYECTO")
public class Proyecto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_PROYECTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProyecto;

	@Column(name = "NOM_PROYECTO")
	private String nomProyecto;
 
	@Column(name = "FEC_INI_PROYECTO")
	@Temporal(TemporalType.DATE)
	private Calendar fecIniProyecto;
	
	@Column(name = "FEC_FEC_PROYECTO")
	@Temporal(TemporalType.DATE)
	private Calendar fecFinProyecto;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "PROYECTO_EMPRESAS",
			joinColumns = @JoinColumn(name = "ID_PROYECTO"),
			inverseJoinColumns = @JoinColumn(name = "ID_EMPRESA")
			)
	
	private Set<Empresa> empresas = new HashSet<>(); 
	
	public Set<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getNomProyecto() {
		return nomProyecto;
	}

	public void setNomProyecto(String nomProyecto) {
		this.nomProyecto = nomProyecto;
	}

	public Calendar getFecIniProyecto() {
		return fecIniProyecto;
	}

	public void setFecIniProyecto(Calendar fecIniProyecto) {
		this.fecIniProyecto = fecIniProyecto;
	}

	public Calendar getFecFinProyecto() {
		return fecFinProyecto;
	}

	public void setFecFinProyecto(Calendar fecFinProyecto) {
		this.fecFinProyecto = fecFinProyecto;
	}

	 }
