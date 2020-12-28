package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class BeanUsuarioSession implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Long idUsuario;
	private String aliasUsuario;
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getAliasUsuario() {
		return aliasUsuario;
	}
	public void setAliasUsuario(String aliasUsuario) {
		this.aliasUsuario = aliasUsuario;
	}
	public List<BeanMenuUsuarioSession> getListBeanMenuUsuarioSession() {
		return listBeanMenuUsuarioSession;
	}
	public void setListBeanMenuUsuarioSession(List<BeanMenuUsuarioSession> listBeanMenuUsuarioSession) {
		this.listBeanMenuUsuarioSession = listBeanMenuUsuarioSession;
	}
	private List<BeanMenuUsuarioSession> listBeanMenuUsuarioSession;
	
	public void DataSessionScope() {
		System.out.println("DataSessionScope Constructor Called at "+LocalDateTime.now());
	}

}
