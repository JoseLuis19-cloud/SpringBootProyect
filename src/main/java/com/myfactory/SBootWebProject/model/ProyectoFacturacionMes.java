package com.myfactory.SBootWebProject.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PROYECTO_FACTURACION_MES")
public class ProyectoFacturacionMes implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_PROYECTO_FAC_MES")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProyectoFacMes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROYECTO_FK", nullable = false, updatable = false)
	private Proyecto proyecto;
	
	@Column(name = "NUM_MES")
	private Integer numMes;
	
	@Column(name = "NUM_ANYO")
	private Integer numAnyo;
	
	@Column(name = "FEC_GENERACION", nullable = true, unique = false)
	@Temporal(TemporalType.DATE)
	private Calendar fecGeneracion;
	
	@Column(name = "COD_ESTADO", nullable = false, unique = false)
	private Integer codEstado;
	

	public Integer getIdProyectoFacMes() {
		return idProyectoFacMes;
	}

	public void setIdProyectoFacMes(Integer idProyectoFacMes) {
		this.idProyectoFacMes = idProyectoFacMes;
	}


	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Integer getNumMes() {
		return numMes;
	}

	public void setNumMes(Integer numMes) {
		this.numMes = numMes;
	}

	public Integer getNumAnyo() {
		return numAnyo;
	}

	public void setNumAnyo(Integer numAnyo) {
		this.numAnyo = numAnyo;
	}

	public Calendar getFecGeneracion() {
		return fecGeneracion;
	}

	public void setFecGeneracion(Calendar fecGeneracion) {
		this.fecGeneracion = fecGeneracion;
	}

	public Integer getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(Integer codEstado) {
		this.codEstado = codEstado;
	}

	 }
