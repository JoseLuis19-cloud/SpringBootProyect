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
@Table(name = "AVISO")
public class Aviso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_AVISO", unique = true)
	private Integer idAviso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO_FK")
	private User usuario;

	@Column(name = "FEC_CREACION_AVISO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar fecCreacionAviso;
		
	@Column(name = "FEC_LIMITE_AVISO", nullable = false)
    @Temporal(TemporalType.DATE)
	private Calendar fecLimiteAviso;

	@Column(name = "IND_LEIDO", nullable = false)
    private Boolean indLeido;
	
	@Column(name = "DES_TAREA", nullable = false)
    private String desTarea;
	
	@Column(name = "DIR_ENLACE_PROCESO", nullable = false)
    private String dirEnlaceProceso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COD_FREC_REPETICION_FK")
	private TpoFrecuRepeticion tpoFrecuRepeticion;

	public Integer getIdAviso() {
		return idAviso;
	}

	public void setIdAviso(Integer idAviso) {
		this.idAviso = idAviso;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Calendar getFecCreacionAviso() {
		return fecCreacionAviso;
	}

	public void setFecCreacionAviso(Calendar fecCreacionAviso) {
		this.fecCreacionAviso = fecCreacionAviso;
	}

	public Calendar getFecLimiteAviso() {
		return fecLimiteAviso;
	}

	public void setFecLimiteAviso(Calendar fecLimiteAviso) {
		this.fecLimiteAviso = fecLimiteAviso;
	}

	public Boolean getIndLeido() {
		return indLeido;
	}

	public void setIndLeido(Boolean indLeido) {
		this.indLeido = indLeido;
	}

	public String getDesTarea() {
		return desTarea;
	}

	public void setDesTarea(String desTarea) {
		this.desTarea = desTarea;
	}

	public String getDirEnlaceProceso() {
		return dirEnlaceProceso;
	}

	public void setDirEnlaceProceso(String dirEnlaceProceso) {
		this.dirEnlaceProceso = dirEnlaceProceso;
	}

	public TpoFrecuRepeticion getTpoFrecuRepeticion() {
		return tpoFrecuRepeticion;
	}

	public void setTpoFrecuRepeticion(TpoFrecuRepeticion tpoFrecuRepeticion) {
		this.tpoFrecuRepeticion = tpoFrecuRepeticion;
	}
	

}
