package com.myfactory.SBootWebProject.dto;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

 
public class ProyectoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer idProyecto;
	private String nomProyecto;
	private Calendar fecIniProyecto;
	private Calendar fecFinProyecto;
	private Long impProyecto;
	
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
	public Long getImpProyecto() {
		return impProyecto;
	}
	public void setImpProyecto(Long impProyecto) {
		this.impProyecto = impProyecto;
	}
	public String getUbicacionProyecto() {
		return ubicacionProyecto;
	}
	public void setUbicacionProyecto(String ubicacionProyecto) {
		this.ubicacionProyecto = ubicacionProyecto;
	}
	private String ubicacionProyecto;
	
	
}
