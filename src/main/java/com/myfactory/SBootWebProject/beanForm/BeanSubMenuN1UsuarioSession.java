package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class BeanSubMenuN1UsuarioSession implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private Integer idSubmenuNivel1Session;
	private Integer numOrdenMenuSession;
	private String 	textoSubMenuN1Session;
	private String 	hrefAplicacionN1Session;

	public BeanSubMenuN1UsuarioSession(Integer idSubmenuNivel1Session, Integer numOrdenMenuSession, String textoSubMenuN1Session,
			String hrefAplicacionN1Session) {
		super();
		this.idSubmenuNivel1Session  = idSubmenuNivel1Session;
		this.numOrdenMenuSession 	 = numOrdenMenuSession;
		this.textoSubMenuN1Session 	 = textoSubMenuN1Session;
		this.hrefAplicacionN1Session = hrefAplicacionN1Session;
	}

	public Integer getIdSubmenuNivel1Session() {
		return idSubmenuNivel1Session;
	}

	public void setIdSubmenuNivel1Session(Integer idSubmenuNivel1Session) {
		this.idSubmenuNivel1Session = idSubmenuNivel1Session;
	}

	public Integer getNumOrdenMenuSession() {
		return numOrdenMenuSession;
	}

	public void setNumOrdenMenuSession(Integer numOrdenMenuSession) {
		this.numOrdenMenuSession = numOrdenMenuSession;
	}

	public String getTextoSubMenuN1Session() {
		return textoSubMenuN1Session;
	}

	public void setTextoSubMenuN1Session(String textoSubMenuN1Session) {
		this.textoSubMenuN1Session = textoSubMenuN1Session;
	}

	public String getHrefAplicacionN1Session() {
		return hrefAplicacionN1Session;
	}

	public void setHrefAplicacionN1Session(String hrefAplicacionN1Session) {
		this.hrefAplicacionN1Session = hrefAplicacionN1Session;
	}

}
