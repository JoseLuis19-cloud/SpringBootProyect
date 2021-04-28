package com.myfactory.SBootWebProject.dto;

import java.io.Serializable;

public class FacturaLineaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long CANTIDAD;
	private String CONCEPTO;
	private Long POR_IVA;
	private Float IMP_LIN_FACTURA;
	private Long POR_DESCUENTO;
	
	
	public Long getCANTIDAD() {
		return CANTIDAD;
	}
	public void setCANTIDAD(Long cANTIDAD) {
		CANTIDAD = cANTIDAD;
	}
	public String getCONCEPTO() {
		return CONCEPTO;
	}
	public void setCONCEPTO(String cONCEPTO) {
		CONCEPTO = cONCEPTO;
	}
	public Long getPOR_IVA() {
		return POR_IVA;
	}
	public void setPOR_IVA(Long pOR_IVA) {
		POR_IVA = pOR_IVA;
	}
	public Float getIMP_LIN_FACTURA() {
		return IMP_LIN_FACTURA;
	}
	public void setIMP_LIN_FACTURA(Float iMP_LIN_FACTURA) {
		IMP_LIN_FACTURA = iMP_LIN_FACTURA;
	}
	public Long getPOR_DESCUENTO() {
		return POR_DESCUENTO;
	}
	public void setPOR_DESCUENTO(Long pOR_DESCUENTO) {
		POR_DESCUENTO = pOR_DESCUENTO;
	}

	
}
