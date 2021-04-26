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


@Entity(name = "facturaLinea")
@Table(name = "FACTURA_LINEA")
public class FacturaLinea implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LIN_FACTURA", nullable = false, unique = true)
	private Integer idLinFactura;
	
	@ManyToOne(fetch = FetchType.LAZY)
 	@JoinColumn(name = "ID_FACTURA_FK", nullable = false, updatable = true)
 	private Factura factura;
	 
	@Column(name = "CANTIDAD", nullable = false, unique = false)
	private Long cantidad;

	@Column(name = "CONCEPTO", nullable = false, unique = false)
	private String concepto;
	
	@Column(name = "POR_IVA", nullable = false, unique = false)
	private Long porIva;
	
	@Column(name = "IMP_LIN_FACTURA", nullable = false, unique = false)
	private Float impLinFactura;
	
	@Column(name = "FEC_ACTUALIZACION", nullable = false, unique = false)
	private Date fecactualizacion;
	
	@Column(name = "POR_DESCUENTO", nullable = false, unique = false)
	private Long porDescuento;


	public Integer getIdLinFactura() {
		return idLinFactura;
	}

	public void setIdLinFactura(Integer idLinFactura) {
		this.idLinFactura = idLinFactura;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Long getPorIva() {
		return porIva;
	}

	public void setPorIva(Long porIva) {
		this.porIva = porIva;
	}

	public Float getImpLinFactura() {
		return impLinFactura;
	}

	public void setImpLinFactura(Float impLinFactura) {
		this.impLinFactura = impLinFactura;
	}

	public Long getPorDescuento() {
		return porDescuento;
	}

	public void setPorDescuento(Long porDescuento) {
		this.porDescuento = porDescuento;
	}

	public Date getFecactualizacion() {
		return fecactualizacion;
	}

	public void setFecactualizacion(Date fecactualizacion) {
		this.fecactualizacion = fecactualizacion;
	};
	
	
	

}
