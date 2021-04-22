package com.myfactory.SBootWebProject.dto;

import java.io.Serializable;
import java.sql.Date;
 

public class ProyectoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer ID_PROYECTO;
	private String NOM_PROYECTO;
	private Date FEC_INI_PROYECTO;
	private Date FEC_FIN_PROYECTO;
	private Float IMP_PROYECTO;
	private String UBICACION_PROYECTO;
	
	
	public Integer getID_PROYECTO() {
		return ID_PROYECTO;
	}
	public void setID_PROYECTO(Integer iD_PROYECTO) {
		ID_PROYECTO = iD_PROYECTO;
	}
	public String getNOM_PROYECTO() {
		return NOM_PROYECTO;
	}
	public void setNOM_PROYECTO(String nOM_PROYECTO) {
		NOM_PROYECTO = nOM_PROYECTO;
	}
	public Date getFEC_INI_PROYECTO() {
		return FEC_INI_PROYECTO;
	}
	public void setFEC_INI_PROYECTO(Date fEC_INI_PROYECTO) {
		FEC_INI_PROYECTO = fEC_INI_PROYECTO;
	}
	public Date getFEC_FIN_PROYECTO() {
		return FEC_FIN_PROYECTO;
	}
	public void setFEC_FIN_PROYECTO(Date fEC_FIN_PROYECTO) {
		FEC_FIN_PROYECTO = fEC_FIN_PROYECTO;
	}
	public Float getIMP_PROYECTO() {
		return IMP_PROYECTO;
	}
	public void setIMP_PROYECTO(Float iMP_PROYECTO) {
		IMP_PROYECTO = iMP_PROYECTO;
	}
	public String getUBICACION_PROYECTO() {
		return UBICACION_PROYECTO;
	}
	public void setUBICACION_PROYECTO(String uBICACION_PROYECTO) {
		UBICACION_PROYECTO = uBICACION_PROYECTO;
	}
	
	
}
