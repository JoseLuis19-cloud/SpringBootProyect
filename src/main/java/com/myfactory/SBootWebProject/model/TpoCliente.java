package com.myfactory.SBootWebProject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TPO_CLIENTE")
public class TpoCliente implements Serializable {
		
	private static final long serialVersionUID = 1L;
		
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    @Column(name="ID_TPO_CLIENTE",  nullable=false, unique=false)
	    private Integer idTpoCliente;
	    
	    @Column(name="DES_TPO_CLIENTE",  nullable=false, unique=false)
	    private String desTpoCliente;
	    

		public Integer getIdTpoCliente() {
			return idTpoCliente;
		}

		public void setIdTpoCliente(Integer idTpoCliente) {
			this.idTpoCliente = idTpoCliente;
		}

		public String getDesTpoCliente() {
			return desTpoCliente;
		}

		public void setDesTpoCliente(String desTpoCliente) {
			this.desTpoCliente = desTpoCliente;
		}
	    
	 

}
