package com.myfactory.SBootWebProject.dto;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;

import com.myfactory.SBootWebProject.model.User;

public class EmpleadoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long ID_EMPEADO;
	private String NOMBRE;
	private String APELLIDOS;
	private String NIF;
    private Calendar FEC_ALTA_EMPLEADO;
    private String TEL_MOVIL;
	private String EMAIL;
	private Float IMP_BRUTO_ANUAL;
	private Float IMP_FACTURADO_MES;
	
	
	public Long getID_EMPEADO() {
		return ID_EMPEADO;
	}
	public void setID_EMPEADO(Long iD_EMPEADO) {
		ID_EMPEADO = iD_EMPEADO;
	}
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
	public Calendar getFEC_ALTA_EMPLEADO() {
		return FEC_ALTA_EMPLEADO;
	}
	public void setFEC_ALTA_EMPLEADO(Calendar fEC_ALTA_EMPLEADO) {
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
