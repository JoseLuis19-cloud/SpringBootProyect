package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class BeanCamposBusqueda implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private String apellidosBusqueda;
	private String nombreBusqueda;
	private String numFacturaBusqueda;
	private String nomEmpresa;
	private String nomProyecto;

	public String getApellidosBusqueda() {
		return apellidosBusqueda;
	}

	public void setApellidosBusqueda(String apellidosBusqueda) {
		this.apellidosBusqueda = apellidosBusqueda;
	}

	public String getNombreBusqueda() {
		return nombreBusqueda;
	}

	public void setNombreBusqueda(String nombreBusqueda) {
		this.nombreBusqueda = nombreBusqueda;
	}

	public String getNumFacturaBusqueda() {
		return numFacturaBusqueda;
	}
	
	public String getNomProyecto() {
		return nomProyecto;
	}

	public void setNomProyecto(String nomProyecto) {
		this.nomProyecto = nomProyecto;
	}
	
	public String getNomEmpresa() {
		return nomEmpresa;
	}

	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}

	public void setNumFacturaBusqueda(String numFacturaBusqueda) {
		this.numFacturaBusqueda = numFacturaBusqueda;
	}


	
}
