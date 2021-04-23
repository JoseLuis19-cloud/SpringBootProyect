package com.myfactory.SBootWebProject.model;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy="proyecto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
 	private Set<ProyectoFacturacionMes> proyectoFacturacionMes = new HashSet<>();

	
	@Column(name = "NOM_PROYECTO")
	private String nomProyecto;
 
	@Column(name = "FEC_INI_PROYECTO")
	@Temporal(TemporalType.DATE)
	private Calendar fecIniProyecto;
	
	@Column(name = "FEC_FIN_PROYECTO")
	@Temporal(TemporalType.DATE)
	private Calendar fecFinProyecto;
	
	@Column(name = "IMP_PROYECTO")
	private Float impProyecto;

	@Column(name = "UBICACION_PROYECTO")
	private String ubicacionProyecto;
	
	@Column(name = "IND_FIN_PROYECTO")
	private boolean indFinProyecto;
	
	@Column(name = "NOM_GERENTE_PROYECTO")
	private String nomGerenteProyecto;
	
	@Column(name = "INFO_COMPLEMENTARIA")
	private String infoComplementaria;
	
	
//	@ManyToMany(fetch = FetchType.EAGER)
	//	@JoinTable(
	//			name = "REL_PROYECTO_EMPRESAS",
	//			joinColumns = @JoinColumn(name = "ID_PROYECTO"),
	//			inverseJoinColumns = @JoinColumn(name = "ID_EMPRESA")
	//		)
	
/*	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "REL_PROYECTO_EMPLEADO",
			joinColumns = @JoinColumn(name = "ID_PROYECTO"),
			inverseJoinColumns = @JoinColumn(name = "ID_EMPLEADO")
			) */
	
	// private Set<Empresa> empresas = new HashSet<>(); 
	
	// private Set<Empleado> empleados = new HashSet<>(); 
	
//	public Set<Empresa> getEmpresas() {
//		return empresas;
//	}

//	public void setEmpresas(Set<Empresa> empresas) {
//		this.empresas = empresas;
//	}
	
//	public Set<Empleado> getEmpleados() {
//		return empleados;
//	}

//	public void setEmpleados(Set<Empleado> empleados) {
//		this.empleados = empleados;
//	}

	public String getNomGerenteProyecto() {
		return nomGerenteProyecto;
	}

	public void setNomGerenteProyecto(String nomGerenteProyecto) {
		this.nomGerenteProyecto = nomGerenteProyecto;
	}

	public String getInfoComplementaria() {
		return infoComplementaria;
	}

	public void setInfoComplementaria(String infoComplementaria) {
		this.infoComplementaria = infoComplementaria;
	}

	public boolean isIndFinProyecto() {
		return indFinProyecto;
	}

	public void setIndFinProyecto(boolean indFinProyecto) {
		this.indFinProyecto = indFinProyecto;
	}

	public String getUbicacionProyecto() {
		return ubicacionProyecto;
	}

	public void setUbicacionProyecto(String ubicacionProyecto) {
		this.ubicacionProyecto = ubicacionProyecto;
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
	
	public Float getImpProyecto() {
		return impProyecto;
	}

	public void setImpProyecto(Float impProyecto) {
		this.impProyecto = impProyecto;
	}
	
	public Set<ProyectoFacturacionMes> getProyectoFacturacionMes() {
		return proyectoFacturacionMes;
	}

	public void setProyectoFacturacionMes(Set<ProyectoFacturacionMes> proyectoFacturacionMes) {
		this.proyectoFacturacionMes = proyectoFacturacionMes;
	}

	 }
