package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
public class BeanMenuPrinUsuarioWeb implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private Integer idMenuWeb;
	private Integer numOrdenMenuWeb;
	private String 	textoMenuWeb;
	private String 	hrefAplicacionWeb;
	private List<BeanSubMenuN1UsuarioWeb> listBeanSubMenuN1UsuarioWeb;
	
	public BeanMenuPrinUsuarioWeb() {};

	public BeanMenuPrinUsuarioWeb(Integer idMenuWeb, Integer numOrdenMenuWeb, String textoMenuWeb,
			String hrefAplicacionWeb, List<BeanSubMenuN1UsuarioWeb> beanSubMenuN1UsuarioWeb) {
		super();
		this.idMenuWeb = idMenuWeb;
		this.numOrdenMenuWeb = numOrdenMenuWeb;
		this.textoMenuWeb = textoMenuWeb;
		this.hrefAplicacionWeb = hrefAplicacionWeb;
		this.listBeanSubMenuN1UsuarioWeb = beanSubMenuN1UsuarioWeb;
	}


	public Integer getIdMenuWeb() {
		return idMenuWeb;
	}


	public void setIdMenuWeb(Integer idMenuWeb) {
		this.idMenuWeb = idMenuWeb;
	}


	public Integer getNumOrdenMenuWeb() {
		return numOrdenMenuWeb;
	}


	public void setNumOrdenMenuWeb(Integer numOrdenMenuWeb) {
		this.numOrdenMenuWeb = numOrdenMenuWeb;
	}


	public String getTextoMenuWeb() {
		return textoMenuWeb;
	}


	public void setTextoMenuWeb(String textoMenuWeb) {
		this.textoMenuWeb = textoMenuWeb;
	}


	public String getHrefAplicacionWeb() {
		return hrefAplicacionWeb;
	}


	public void setHrefAplicacionWeb(String hrefAplicacionWeb) {
		this.hrefAplicacionWeb = hrefAplicacionWeb;
	}

	public List<BeanSubMenuN1UsuarioWeb> getListBeanSubMenuN1UsuarioWeb() {
		return listBeanSubMenuN1UsuarioWeb;
	}

	public void setListBeanSubMenuN1UsuarioWeb(List<BeanSubMenuN1UsuarioWeb> listBeanSubMenuN1UsuarioWeb) {
		this.listBeanSubMenuN1UsuarioWeb = listBeanSubMenuN1UsuarioWeb;
	}
	
	
}
