package com.myfactory.SBootWebProject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FACTURA_SITUACION")
public class FacturaSituacion implements Serializable {
		
	private static final long serialVersionUID = 1L;
		
	    @Id 
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    @Column(name="COD_SIT_FACTURA",  nullable=false, unique=true)
	    private Integer codSitFactura;
	    
	    @Column(name="NOM_SIT_FACTURA",  nullable=false, unique=true)
	    private String nomSitFactura;

		public Integer getCodSitFactura() {
			return codSitFactura;
		}

		public void setCodSitFactura(Integer codSitFactura) {
			this.codSitFactura = codSitFactura;
		}

		public String getNomSitFactura() {
			return nomSitFactura;
		}

		public void setNomSitFactura(String nomSitFactura) {
			this.nomSitFactura = nomSitFactura;
		}

}
