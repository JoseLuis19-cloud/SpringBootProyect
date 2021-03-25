package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class BeanProyectoWeb  implements Serializable , Cloneable{
	
	private static final long serialVersionUID = 1L;

	private Integer idProyectoWeb;
	
	private String nomProyectoWeb;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String fecIniProyectoWeb;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String fecFinProyectoWeb;

	private Long impProyectoWeb;
	 
	public BeanProyectoWeb() {
	}
	    
	public BeanProyectoWeb(Integer idProyectoWeb, String nomProyecto, String fecIniProyectoWeb, String fecFinProyectoWeb, Long impProyectoWeb) {
			super();
			
	}

	public String getNomProyectoWeb() {
		return nomProyectoWeb;
	}

	public void setNomProyectoWeb(String nomProyectoWeb) {
		this.nomProyectoWeb = nomProyectoWeb;
	}

	public Long getImpProyectoWeb() {
		return impProyectoWeb;
	}

	public void setImpProyectoWeb(Long impProyectoWeb) {
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


}
