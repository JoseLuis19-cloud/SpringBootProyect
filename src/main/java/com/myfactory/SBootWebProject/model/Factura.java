package com.myfactory.SBootWebProject.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name = "factura")
@Table(name = "FACTURA")
public class Factura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID_FACTURA", nullable = false, unique = true)
	private Integer idFactura;
	
	@Column(name = "NUM_FACTURA", nullable = false, unique = true)
	private String numFactura;

	@OneToMany(mappedBy="factura", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 	private Set<FacturaLinea> facturaLineas = new HashSet<>();

	// @Column(name="ID_CLIENTE_FK", nullable=false, unique=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EMPRESA_FK")
	private Cliente cliente;
	 
	@Column(name = "IMP_FACTURA", nullable = false, unique = false)
	private Float impFactura;

	@Column(name = "POR_IVA", nullable = false, unique = false)
	private Integer porIva;

	@Column(name = "FEC_ALTA_FACTURA", nullable = false, unique = false)
    @Temporal(TemporalType.DATE)
	private Calendar fecFactura;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FOR_PAGO_FK")
	private FormaPago  formaPago;
	
	@Column(name = "POR_DESCUENTO", nullable = false, unique = false)
	private Float porDescuento;
	
	@Column(name = "COD_DIVISA", nullable = false, unique = false)
	private Integer codDivisa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COD_SIT_FACTURA_FK")
	private FacturaSituacion facturaSituacion;
	
	@Column(name = "FEC_EMISION_FACTURA", nullable = true, unique = false)
    @Temporal(TemporalType.DATE)
	private Calendar fecEmisionFactura;
	
	@Column(name = "COD_USUARIO", nullable = false, unique = false)
	private Long codUsuario;

	@Column(name = "NOTA_FACTURA", nullable = true, unique = false)
	private String notaFactura;

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public Set<FacturaLinea> getFacturaLineas() {
		return facturaLineas;
	}

	public void setFacturaLineas(Set<FacturaLinea> facturaLineas) {
		this.facturaLineas = facturaLineas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Float getImpFactura() {
		return impFactura;
	}

	public void setImpFactura(Float impFactura) {
		this.impFactura = impFactura;
	}

	public Integer getPorIva() {
		return porIva;
	}

	public void setPorIva(Integer porIva) {
		this.porIva = porIva;
	}

	public Calendar getFecFactura() {
		return fecFactura;
	}

	public void setFecFactura(Calendar fecFactura) {
		this.fecFactura = fecFactura;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
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

	public FacturaSituacion getFacturaSituacion() {
		return facturaSituacion;
	}

	public void setFacturaSituacion(FacturaSituacion facturaSituacion) {
		this.facturaSituacion = facturaSituacion;
	}

	public Calendar getFecEmisionFactura() {
		return fecEmisionFactura;
	}

	public void setFecEmisionFactura(Calendar fecEmisionFactura) {
		this.fecEmisionFactura = fecEmisionFactura;
	}

	public Long getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Long codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNotaFactura() {
		return notaFactura;
	}

	public void setNotaFactura(String notaFactura) {
		this.notaFactura = notaFactura;
	}
	
}
