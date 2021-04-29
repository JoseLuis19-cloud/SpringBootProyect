package com.myfactory.SBootWebProject.dto;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class FacturaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer ID_FACTURA;
	private String NUM_FACTURA;
	private Float IMP_FACTURA;
	private Integer POR_IVA;
	private java.sql.Date FEC_ALTA_FACTURA;
	// private String  formaPago;
	private Float POR_DESCUENTO;
	private Integer COD_DIVISA;
// @JoinColumn(name = "COD_SIT_FACTURA_FK")
//	private FacturaSituacion facturaSituacion
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private java.sql.Date FEC_EMISION_FACTURA;
	private Long COD_USUARIO;
	private String NOTA_FACTURA;
	
	
	public Integer getID_FACTURA() {
		return ID_FACTURA;
	}
	public void setID_FACTURA(Integer iD_FACTURA) {
		ID_FACTURA = iD_FACTURA;
	}
	public String getNUM_FACTURA() {
		return NUM_FACTURA;
	}
	public void setNUM_FACTURA(String nUM_FACTURA) {
		NUM_FACTURA = nUM_FACTURA;
	}

	public Float getIMP_FACTURA() {
		return IMP_FACTURA;
	}
	public void setIMP_FACTURA(Float iMP_FACTURA) {
		IMP_FACTURA = iMP_FACTURA;
	}
	public Integer getPOR_IVA() {
		return POR_IVA;
	}
	public void setPOR_IVA(Integer pOR_IVA) {
		POR_IVA = pOR_IVA;
	}
	public java.sql.Date getFEC_ALTA_FACTURA() {
		return FEC_ALTA_FACTURA;
	}
	public void setFEC_ALTA_FACTURA(java.sql.Date fEC_ALTA_FACTURA) {
		FEC_ALTA_FACTURA = fEC_ALTA_FACTURA;
	}
	public Float getPOR_DESCUENTO() {
		return POR_DESCUENTO;
	}
	public void setPOR_DESCUENTO(Float pOR_DESCUENTO) {
		POR_DESCUENTO = pOR_DESCUENTO;
	}
	public Integer getCOD_DIVISA() {
		return COD_DIVISA;
	}
	public void setCOD_DIVISA(Integer cOD_DIVISA) {
		COD_DIVISA = cOD_DIVISA;
	}
	public java.sql.Date getFEC_EMISION_FACTURA() {
		return FEC_EMISION_FACTURA;
	}
	public void setFEC_EMISION_FACTURA(java.sql.Date fEC_EMISION_FACTURA) {
		FEC_EMISION_FACTURA = fEC_EMISION_FACTURA;
	}
	public Long getCOD_USUARIO() {
		return COD_USUARIO;
	}
	public void setCOD_USUARIO(Long cOD_USUARIO) {
		COD_USUARIO = cOD_USUARIO;
	}
	public String getNOTA_FACTURA() {
		return NOTA_FACTURA;
	}
	public void setNOTA_FACTURA(String nOTA_FACTURA) {
		NOTA_FACTURA = nOTA_FACTURA;
	}
	
}
