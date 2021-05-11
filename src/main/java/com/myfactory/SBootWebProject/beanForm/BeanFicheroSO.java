package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

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
