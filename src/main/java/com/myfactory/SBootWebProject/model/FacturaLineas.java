package com.myfactory.SBootWebProject.model;

import java.io.Serializable;
import java.sql.Date;
 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity(name = "FacturaLineas")
@Table(name = "FACTURA_LINEAS")
public class FacturaLineas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LIN_FACTURA", nullable = false, unique = true)
	private Integer idLinFactura;
	
	@ManyToOne(fetch = FetchType.LAZY)
 	@JoinColumn(name = "ID_FACTURA_FK", nullable = false, updatable = false)
 	private Factura factura;
	 
	@Column(name = "CANTIDAD", nullable = false, unique = false)
	private Long cantidad;

	@Column(name = "CONCEPTO", nullable = false, unique = false)
	private String concepto;
	
	@Column(name = "POR_IVA", nullable = false, unique = false)
	private Long porIva;
	
	@Column(name = "IMP_LIN_FACTURA", nullable = false, unique = false)
	private Float impFactura;

	@Column(name = "POR_DESCUENTO", nullable = false, unique = false)
	private Long porDescuento;
	
	@Column(name = "FEC_ACTUALIZACION", nullable = false, unique = false)
	private Date fecactualizacion;;
	
	
	

}
