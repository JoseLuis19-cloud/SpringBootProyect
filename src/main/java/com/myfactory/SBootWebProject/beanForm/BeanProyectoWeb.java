package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
@RequestScope
public class BeanProyectoWeb  implements Serializable , Cloneable{
	
	private static final long serialVersionUID = 1L;

	private Integer idProyectoWeb;
	
	private String nomProyectoWeb;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String fecIniProyectoWeb;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String fecFinProyectoWeb;

	private Float impProyectoWeb;
	
	private String  ubicacionProyectoWeb;
	
	private String  nomGerenteProyectoWeb;

	private String  infoComplementariaWeb;

	public BeanProyectoWeb() {
	}
	    
	public BeanProyectoWeb(Integer idProyectoWeb, String nomProyecto, String fecIniProyectoWeb, String fecFinProyectoWeb, Long impProyectoWeb, String  ubicacionProyectoWeb) {
			super();
			
	}

	public String getUbicacionProyectoWeb() {
		return ubicacionProyectoWeb;
	}

	public void setUbicacionProyectoWeb(String ubicacionProyectoWeb) {
		this.ubicacionProyectoWeb = ubicacionProyectoWeb;
	}

	public String getNomProyectoWeb() {
		return nomProyectoWeb;
	}

	public void setNomProyectoWeb(String nomProyectoWeb) {
		this.nomProyectoWeb = nomProyectoWeb;
	}

	public Float getImpProyectoWeb() {
		return impProyectoWeb;
	}

	public void setImpProyectoWeb(Float impProyectoWeb) {
		this.impProyectoWeb = impProyectoWeb;
	}

	public Integer getIdProyectoWeb() {
		return idProyectoWeb;
	}

	public void setIdProyectoWeb(Integer idProyectoWeb) {
		this.idProyectoWeb = idProyectoWeb;
	}

	public String getFecIniProyectoWeb() {
		return fecIniProyectoWeb;
	}

	public void setFecIniProyectoWeb(String fecIniProyectoWeb) {
		this.fecIniProyectoWeb = fecIniProyectoWeb;
	}

	public String getFecFinProyectoWeb() {
		return fecFinProyectoWeb;
	}

	public void setFecFinProyectoWeb(String fecFinProyectoWeb) {
		this.fecFinProyectoWeb = fecFinProyectoWeb;
	}
	
	public String getInfoComplementariaWeb() {
		return infoComplementariaWeb;
	}

	public void setInfoComplementariaWeb(String infoComplementariaWeb) {
		this.infoComplementariaWeb = infoComplementariaWeb;
	}

	public String getNomGerenteProyectoWeb() {
		return nomGerenteProyectoWeb;
	}

	public void setNomGerenteProyectoWeb(String nomGerenteProyecto) {
		this.nomGerenteProyectoWeb= nomGerenteProyecto;
	}


}
