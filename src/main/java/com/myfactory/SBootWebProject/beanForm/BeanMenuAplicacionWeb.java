package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;


@Component
@RequestScope
public class BeanMenuAplicacionWeb implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Integer idMenu;
	private Integer numOrdenMenu;
	@NotNull(message = "Debe introducir el texto del menu!")
	private String textoMenu;
	private String hrefAplicacion;
	private boolean indActivo;
	
	public BeanMenuAplicacionWeb() {
	 
	}

	public Integer getNumOrdenMenu() {
		return numOrdenMenu;
	}

	public void setNumOrdenMenu(Integer numOrdenMenu) {
		this.numOrdenMenu = numOrdenMenu;
	}

	public String getTextoMenu() {
		return textoMenu;
	}

	public void setTextoMenu(String textoMenu) {
		this.textoMenu = textoMenu;
	}

	public String getHrefAplicacion() {
		return hrefAplicacion;
	}

	public void setHrefAplicacion(String hrefAplicacion) {
		this.hrefAplicacion = hrefAplicacion;
	}

	public boolean isIndActivo() {
		return indActivo;
	}

	public void setIndActivo(boolean indActivo) {
		this.indActivo = indActivo;
	}
	public Integer getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	
	
}
