package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.Calendar;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
@RequestScope
public class BeanFicheroSO  implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private String nomFichero;
	private String fechaCreacion;
	
	public String getNomFichero() {
		return nomFichero;
	}
	public void setNomFichero(String nomFichero) {
		this.nomFichero = nomFichero;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
