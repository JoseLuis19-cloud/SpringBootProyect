package com.myfactory.SBootWebProject.model;

import java.io.Serializable;
import java.sql.Date;
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


@Entity(name = "Factura")
@Table(name = "FACTURA")
public class Factura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID_FACTURA", nullable = false, unique = true)
	private Integer idFactura;

	// @Column(name="ID_CLIENTE_FK", nullable=false, unique=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENTE_FK")
	private Cliente cliente;
	
	@Column(name = "NUM_FACTURA", nullable = true, unique = false)
	private String numFactura;

	@Column(name = "IMP_FACTURA", nullable = false, unique = false)
	private Float impFactura;

	@Column(name = "CONCEPTO", nullable = false, unique = false)
	private String concepto;

	@Column(name = "POR_IVA", nullable = false, unique = false)
	private Integer porIva;

	@Column(name = "FEC_FACTURA", nullable = true, unique = false)
    @Temporal(TemporalType.DATE)
	private Calendar fecFactura;
	
	@Column(name = "COD_SITUACION", nullable = false, unique = false)
	private Integer codSituacion;
	
	@Column(name = "POR_DESCUENTO", nullable = true, unique = false)
	private Float porDescuento;
	
	@Column(name = "COD_DIVISA", nullable = true, unique = false)
	private Integer codDivisa;
	
	@Column(name = "COD_USARIO", nullable = true, unique = false)
	private Long codUsuario;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FOR_PAGO_FK")
	private FormaPago  formaPago;

	public Calendar getFecFactura() {
		return fecFactura;
	}

	public void setFecFactura(Calendar fecFactura) {
		this.fecFactura = fecFactura;
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public Float getImpFactura() {
		return impFactura;
	}

	public void setImpFactura(Float impFactura) {
		this.impFactura = impFactura;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Integer getPorIva() {
		return porIva;
	}

	public void setPorIva(Integer porIva) {
		this.porIva = porIva;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}
	
	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}
	
	public Integer getCodSituacion() {
		return codSituacion;
	}

	public void setCodSituacion(Integer codSituacion) {
		this.codSituacion = codSituacion;
	}

	public Float getPorDescuento() {
		return porDescuento;
	}

	public void setPorDescuento(Float porDescuento) {
		this.porDescuento = porDescuento;
	}

	public Integer getCodDivisa() {
		return codDivisa;
	}

	public void setCodDivisa(Integer codDivisa) {
		this.codDivisa = codDivisa;
	}

}
