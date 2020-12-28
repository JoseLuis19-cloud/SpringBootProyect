package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class BeanIdUsuario implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private Long idUsuario;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
