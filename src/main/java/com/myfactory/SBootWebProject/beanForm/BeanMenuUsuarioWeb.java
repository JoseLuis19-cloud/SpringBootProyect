package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class BeanMenuUsuarioWeb implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Integer idMenuUsuWeb;
	private Integer idMenuWeb;
	private String elementoMenu;
	private Boolean yaSeleccionado;
	
	public BeanMenuUsuarioWeb(Integer idMenuUsuWeb, Integer idMenuWeb, String elementoMenu, Boolean yaSeleccionado) {
		super();
		this.idMenuUsuWeb = idMenuUsuWeb;
		this.idMenuWeb = idMenuWeb;
		this.elementoMenu = elementoMenu;
		this.yaSeleccionado = yaSeleccionado;
	}
	
	
	public Integer getIdMenuWeb() {
		return idMenuWeb;
	}
	public void setIdMenuWeb(Integer idMenuWeb) {
		this.idMenuWeb = idMenuWeb;
	}
	public String getElementoMenu() {
		return elementoMenu;
	}
	public void setElementoMenu(String elementoMenu) {
		this.elementoMenu = elementoMenu;
	}
	public Boolean getYaSeleccionado() {
		return yaSeleccionado;
	}
	public void setYaSeleccionado(Boolean yaSeleccionado) {
		this.yaSeleccionado = yaSeleccionado;
	}
	
	public Integer getIdMenuUsuWeb() {
		return idMenuUsuWeb;
	}
	public void setIdMenuUsuWeb(Integer idMenuUsuWeb) {
		this.idMenuUsuWeb = idMenuUsuWeb;
	}

	
}
