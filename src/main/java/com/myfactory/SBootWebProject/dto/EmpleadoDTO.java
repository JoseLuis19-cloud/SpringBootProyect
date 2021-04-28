package com.myfactory.SBootWebProject.dto;

import java.io.Serializable;

public class EmpleadoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//  private Integer ID_EMPLEADO;
	private String NOMBRE;
	private String APELLIDOS;
	private String NIF;
    private java.sql.Date FEC_ALTA_EMPLEADO;
    private String TEL_MOVIL;
	private String EMAIL;
	private Float IMP_BRUTO_ANUAL;
	private Float IMP_FACTURADO_MES;
	
	
//	public Integer getID_EMPEADO() {
	//		return ID_EMPLEADO;
	//	}
	//	public void setID_EMPLEADO(Integer iD_EMPLEADO) {
	//		ID_EMPLEADO = iD_EMPLEADO;
	//	}
	public String getNOMBRE() {
		return NOMBRE;
	}
	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}
	public String getAPELLIDOS() {
		return APELLIDOS;
	}
	public void setAPELLIDOS(String aPELLIDOS) {
		APELLIDOS = aPELLIDOS;
	}
	public String getNIF() {
		return NIF;
	}
	public void setNIF(String nIF) {
		NIF = nIF;
	}
	public java.sql.Date  getFEC_ALTA_EMPLEADO() {
		return FEC_ALTA_EMPLEADO;
	}
	public void setFEC_ALTA_EMPLEADO(java.sql.Date fEC_ALTA_EMPLEADO) {
		FEC_ALTA_EMPLEADO = fEC_ALTA_EMPLEADO;
	}
	public String getTEL_MOVIL() {
		return TEL_MOVIL;
	}
	public void setTEL_MOVIL(String telefMovil) {
		TEL_MOVIL = telefMovil;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public Float getIMP_BRUTO_ANUAL() {
		return IMP_BRUTO_ANUAL;
	}
	public void setIMP_BRUTO_ANUAL(Float iMP_BRUTO_ANUAL) {
		IMP_BRUTO_ANUAL = iMP_BRUTO_ANUAL;
	}
	public Float getIMP_FACTURADO_MES() {
		return IMP_FACTURADO_MES;
	}
	public void setIMP_FACTURADO_MES(Float iMP_FACTURADO_MES) {
		IMP_FACTURADO_MES = iMP_FACTURADO_MES;
	}

}
