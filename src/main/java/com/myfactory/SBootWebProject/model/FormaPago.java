package com.myfactory.SBootWebProject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FORMA_PAGO")
public class FormaPago implements Serializable {
		
	private static final long serialVersionUID = 1L;
		
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    @Column(name="ID_FOR_PAGO",  nullable=false, unique=false)
	    private Integer idForPago;
	    
	    @Column(name="DES_FOR_PAGO",  nullable=false, unique=false)
	    private String desForPago;
	    
	    
	    public FormaPago() {
			
		}

		public FormaPago(Integer idForPago, String desForPago) {
			super();
			this.idForPago = idForPago;
			this.desForPago = desForPago;
		}

		public Integer getIdForPago() {
			return idForPago;
		}

		public void setIdForPago(Integer idForPago) {
			this.idForPago = idForPago;
		}

		public String getDesForPago() {
			return desForPago;
		}

		public void setDesForPago(String desForPago) {
			this.desForPago = desForPago;
		}
	    

	

}
