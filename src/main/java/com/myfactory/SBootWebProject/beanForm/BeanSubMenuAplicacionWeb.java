package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class BeanSubMenuAplicacionWeb implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Integer idMenu;
	private Integer idSubMenuN1;
	private Integer numOrdenSubMenu;
	private String textoMenuSubMenu;
	private String hrefAplicacionSubMenu;
	private boolean indActivoSubMenu;
	
	public BeanSubMenuAplicacionWeb() {
		 
	}
	
	public Integer getNumOrdenSubMenu() {
		return numOrdenSubMenu;
	}

	public void setNumOrdenSubMenu(Integer numOrdenSubMenu) {
		this.numOrdenSubMenu = numOrdenSubMenu;
	}

	public String getTextoMenuSubMenu() {
		return textoMenuSubMenu;
	}

	public void setTextoMenuSubMenu(String textoMenuSubMenu) {
		this.textoMenuSubMenu = textoMenuSubMenu;
	}

	public String getHrefAplicacionSubMenu() {
		return hrefAplicacionSubMenu;
	}

	public void setHrefAplicacionSubMenu(String hrefAplicacionSubMenu) {
		this.hrefAplicacionSubMenu = hrefAplicacionSubMenu;
	}

	public boolean isIndActivoSubMenu() {
		return indActivoSubMenu;
	}

	public void setIndActivoSubMenu(boolean indActivoSubMenu) {
		this.indActivoSubMenu = indActivoSubMenu;
	}

	public Integer getIdSubMenuN1() {
		return idSubMenuN1;
	}

	public void setIdSubMenuN1(Integer idSubMenuN1) {
		this.idSubMenuN1 = idSubMenuN1;
	}	
	
	public Integer getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}
	
	
}
