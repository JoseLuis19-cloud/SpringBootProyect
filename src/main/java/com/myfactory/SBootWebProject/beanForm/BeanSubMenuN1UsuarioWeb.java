package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class BeanSubMenuN1UsuarioWeb implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private Integer idSubmenuNivel1;;
	private Integer numOrdenMenu;
	private String 	textoSubMenuN1;
	private String 	hrefAplicacionN1;
	private Boolean  yaSeleccionado;;

	public BeanSubMenuN1UsuarioWeb(Integer idSubmenuNivel1, Integer numOrdenMenu, String textoSubMenuN1,
			String hrefAplicacionN1, Boolean yaSeleccionado) {
		super();
		this.idSubmenuNivel1 = idSubmenuNivel1;
		this.numOrdenMenu = numOrdenMenu;
		this.textoSubMenuN1 = textoSubMenuN1;
		this.hrefAplicacionN1 = hrefAplicacionN1;
		this.yaSeleccionado    = yaSeleccionado;
	}
	
	
	public Integer getIdSubmenuNivel1() {
		return idSubmenuNivel1;
	}
	public void setIdSubmenuNivel1(Integer idSubmenuNivel1) {
		this.idSubmenuNivel1 = idSubmenuNivel1;
	}
	public Integer getNumOrdenMenu() {
		return numOrdenMenu;
	}
	public void setNumOrdenMenu(Integer numOrdenMenu) {
		this.numOrdenMenu = numOrdenMenu;
	}
	public String getTextoSubMenuN1() {
		return textoSubMenuN1;
	}
	public void setTextoSubMenuN1(String textoSubMenuN1) {
		this.textoSubMenuN1 = textoSubMenuN1;
	}
	public String getHrefAplicacionN1() {
		return hrefAplicacionN1;
	}
	public void setHrefAplicacionN1(String hrefAplicacionN1) {
		this.hrefAplicacionN1 = hrefAplicacionN1;
	}
	
	public Boolean getYaSeleccionado() {
		return yaSeleccionado;
	}


	public void setYaSeleccionado(Boolean yaSeleccionado) {
		this.yaSeleccionado = yaSeleccionado;
	}

	
	
}
