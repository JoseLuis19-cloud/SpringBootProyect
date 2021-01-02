package com.myfactory.SBootWebProject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Paises")
public class Pais implements Serializable {
		
	private static final long serialVersionUID = 1L;
		
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    @Column(name="id",  nullable=false, unique=false)
	    private Integer idPais;
	    
	    @Column(name="iso",  nullable=false, unique=false)
	    private String codISO;
	    
	    @Column(name="nombre",  nullable=false, unique=false)
	    private String nombrePais;

		public Integer getIdPais() {
			return idPais;
		}

		public void setIdPais(Integer idPais) {
			this.idPais = idPais;
		}

		public String getCodISO() {
			return codISO;
		}

		public void setCodISO(String codISO) {
			this.codISO = codISO;
		}

		public String getNombrePais() {
			return nombrePais;
		}

		public void setNombrePais(String nombrePais) {
			this.nombrePais = nombrePais;
		}
	    
	    

}
