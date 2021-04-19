package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class BeanErrorValidacion  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer codError;
	private String  desError;
	

	public BeanErrorValidacion() {
	}
	
	public BeanErrorValidacion(Integer codError) {
		this.codError = codError;
	}

	public Integer getCodError() {
		return codError;
	}


	public void setCodError(Integer codError) {
		this.codError = codError;
	}


	public String getDesError() {
		return desError;
	}


	public void setDesError(String desError) {
		this.desError = desError;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		

}
