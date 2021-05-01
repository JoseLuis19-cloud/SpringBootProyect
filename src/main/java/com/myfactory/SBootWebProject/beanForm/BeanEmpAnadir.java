package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class BeanEmpAnadir  implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private Integer idEmpresa;
	private String nomEmpresa;
	
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getNomEmpresa() {
		return nomEmpresa;
	}
	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}
	
 
}
