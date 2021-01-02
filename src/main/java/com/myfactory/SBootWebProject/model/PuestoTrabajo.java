package com.myfactory.SBootWebProject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PUESTO_TRABAJO")
public class PuestoTrabajo implements Serializable {

	private static final long serialVersionUID = 1L;
		
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    @Column(name="ID_PUESTO_TRABAJO",  nullable=false, unique=true)
	    private Integer idPuestoTrabajo;
	    
	    @Column(name="DES_PUESTO_TRABAJO",  nullable=false, unique=true)
	    private String desPuestoTrabajo;
	    
	    
	    public Integer getIdPuestoTrabajo() {
			return idPuestoTrabajo;
		}

		public void setIdPuestoTrabajo(Integer idPuestoTrabajo) {
			this.idPuestoTrabajo = idPuestoTrabajo;
		}

		public String getDesPuestoTrabajo() {
			return desPuestoTrabajo;
		}

		public void setDesPuestoTrabajo(String desPuestoTrabajo) {
			this.desPuestoTrabajo = desPuestoTrabajo;
		}



}
