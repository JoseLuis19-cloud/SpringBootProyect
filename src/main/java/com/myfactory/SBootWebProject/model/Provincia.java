package com.myfactory.SBootWebProject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROVINCIA")
public class Provincia implements Serializable {
		
	private static final long serialVersionUID = 1L;
		
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    @Column(name="ID_PROVINCIA", nullable=false, unique=true)
	    private Integer idProvincia;
	    
	    @Column(name="PROVINCIA",  nullable=false, unique=true)
	    private String provincia;

		public Integer getIdProvincia() {
			return idProvincia;
		}

		public void setIdProvincia(Integer idProvincia) {
			this.idProvincia = idProvincia;
		}

		public String getProvincia() {
			return provincia;
		}

		public void setProvincia(String provincia) {
			this.provincia = provincia;
		}
	  
}
