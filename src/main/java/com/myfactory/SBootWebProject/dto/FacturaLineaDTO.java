package com.myfactory.SBootWebProject.dto;

import java.io.Serializable;

public class FacturaLineaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Float CANTIDAD;
	private String CONCEPTO;
	private Float POR_IVA;
	private Float IMP_LIN_FACTURA;
	private Float POR_DESCUENTO;
	
	public Float getCANTIDAD() {
		return CANTIDAD;
	}
	public void setCANTIDAD(Float cANTIDAD) {
		CANTIDAD = cANTIDAD;
	}
	public String getCONCEPTO() {
		return CONCEPTO;
	}
	public void setCONCEPTO(String cONCEPTO) {
		CONCEPTO = cONCEPTO;
	}
	public Float getPOR_IVA() {
		return POR_IVA;
	}
	public void setPOR_IVA(Float pOR_IVA) {
		POR_IVA = pOR_IVA;
	}
	public Float getIMP_LIN_FACTURA() {
		return IMP_LIN_FACTURA;
	}
	public void setIMP_LIN_FACTURA(Float iMP_LIN_FACTURA) {
		IMP_LIN_FACTURA = iMP_LIN_FACTURA;
	}
	public Float getPOR_DESCUENTO() {
		return POR_DESCUENTO;
	}
	public void setPOR_DESCUENTO(Float pOR_DESCUENTO) {
		POR_DESCUENTO = pOR_DESCUENTO;
	}

	
}
