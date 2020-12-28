package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
public class BeanMenuUsuarioSession implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private Integer idMenuSesion;
	private Integer numOrdenMenuSesion;
	private String textoMenuSesion;
	private String hrefAplicacionSesion;
	private List<BeanSubMenuN1UsuarioSession> listBeanSubMenuN1UsuSession;
	
	public BeanMenuUsuarioSession() {
		
	};

	public BeanMenuUsuarioSession(Integer idMenuSesion, Integer numOrdenMenuSesion, String textoMenuSesion,
			String hrefAplicacionSesion, List<BeanSubMenuN1UsuarioSession> listBeanSubMenuN1UsuarioSessionParam) {
		super();
		this.idMenuSesion = idMenuSesion;
		this.numOrdenMenuSesion = numOrdenMenuSesion;
		this.textoMenuSesion = textoMenuSesion;
		this.hrefAplicacionSesion= hrefAplicacionSesion;
		this.listBeanSubMenuN1UsuSession = listBeanSubMenuN1UsuarioSessionParam;
	}

	public Integer getIdMenuSesion() {
		return idMenuSesion;
	}

	public void setIdMenuSesion(Integer idMenuSesion) {
		this.idMenuSesion = idMenuSesion;
	}

	public Integer getNumOrdenMenuSesion() {
		return numOrdenMenuSesion;
	}

	public void setNumOrdenMenuSesion(Integer numOrdenMenuSesion) {
		this.numOrdenMenuSesion = numOrdenMenuSesion;
	}

	public String getTextoMenuSesion() {
		return textoMenuSesion;
	}

	public void setTextoMenuSesion(String textoMenuSesion) {
		this.textoMenuSesion = textoMenuSesion;
	}

	public String getHrefAplicacionSesion() {
		return hrefAplicacionSesion;
	}

	public void setHrefAplicacionSesion(String hrefAplicacionSesion) {
		this.hrefAplicacionSesion = hrefAplicacionSesion;
	}

	public List<BeanSubMenuN1UsuarioSession> getListBeanSubMenuN1UsuSession() {
		return listBeanSubMenuN1UsuSession;
	}

	public void setListBeanSubMenuN1UsuSession(List<BeanSubMenuN1UsuarioSession> listBeanSubMenuN1UsuSession) {
		this.listBeanSubMenuN1UsuSession = listBeanSubMenuN1UsuSession;
	}


}
