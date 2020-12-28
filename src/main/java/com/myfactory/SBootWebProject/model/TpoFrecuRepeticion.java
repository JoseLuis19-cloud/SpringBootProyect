package com.myfactory.SBootWebProject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TPO_FRECU_REPETICION")
public class TpoFrecuRepeticion implements Serializable {
		
	private static final long serialVersionUID = 1L;
		
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    @Column(name="ID_FRECU_REPETICION",  nullable=false, unique=false)
	    private Integer idFrecuRepeticion;
	    
	    @Column(name="DES_FRECU_REPETICION",  nullable=false, unique=false)
	    private String desFrecuRepeticion;

		public Integer getIdFrecuRepeticion() {
			return idFrecuRepeticion;
		}

		public void setIdFrecuRepeticion(Integer idFrecuRepeticion) {
			this.idFrecuRepeticion = idFrecuRepeticion;
		}

		public String getDesFrecuRepeticion() {
			return desFrecuRepeticion;
		}

		public void setDesFrecuRepeticion(String desFrecuRepeticion) {
			this.desFrecuRepeticion = desFrecuRepeticion;
		}
	    

		
}
