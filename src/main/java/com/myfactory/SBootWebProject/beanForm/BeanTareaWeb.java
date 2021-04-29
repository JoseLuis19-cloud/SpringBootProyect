package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.myfactory.SBootWebProject.model.TpoFrecuRepeticion;
import com.myfactory.SBootWebProject.model.User;

@Component
@RequestScope
public class BeanTareaWeb  implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private Integer idAviso;
	private User usuario;
    private Calendar fecCreacionAviso;
	private Calendar fecLimiteAviso;
    private Boolean indLeido;
    private String desTarea;
    private String dirEnlaceProceso;
	private TpoFrecuRepeticion tpoFrecuRepeticion;
	
	public Integer getIdAviso() {
		return idAviso;
	}
	public void setIdAviso(Integer idAviso) {
		this.idAviso = idAviso;
	}
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	public Calendar getFecCreacionAviso() {
		return fecCreacionAviso;
	}
	public void setFecCreacionAviso(Calendar fecCreacionAviso) {
		this.fecCreacionAviso = fecCreacionAviso;
	}
	public Calendar getFecLimiteAviso() {
		return fecLimiteAviso;
	}
	public void setFecLimiteAviso(Calendar fecLimiteAviso) {
		this.fecLimiteAviso = fecLimiteAviso;
	}
	public Boolean getIndLeido() {
		return indLeido;
	}
	public void setIndLeido(Boolean indLeido) {
		this.indLeido = indLeido;
	}
	public String getDesTarea() {
		return desTarea;
	}
	public void setDesTarea(String desTarea) {
		this.desTarea = desTarea;
	}
	public String getDirEnlaceProceso() {
		return dirEnlaceProceso;
	}
	public void setDirEnlaceProceso(String dirEnlaceProceso) {
		this.dirEnlaceProceso = dirEnlaceProceso;
	}
	public TpoFrecuRepeticion getTpoFrecuRepeticion() {
		return tpoFrecuRepeticion;
	}
	public void setTpoFrecuRepeticion(TpoFrecuRepeticion tpoFrecuRepeticion) {
		this.tpoFrecuRepeticion = tpoFrecuRepeticion;
	}
	
	
}
