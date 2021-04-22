package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class BeanFacturacionProyectoWeb  implements Serializable , Cloneable{
	
	private static final long serialVersionUID = 1L;

	private Integer idFacturacionMes;
	private Integer idProyecto;
	private Integer idEmpleado;
	private Long impFacturadoMes;
	private Integer porcentajeIVA;
	private Calendar fecFacturacion;

	public BeanFacturacionProyectoWeb() {
	}
	
	public Integer getIdFacturacionMes() {
		return idFacturacionMes;
	}

	public void setIdFacturacionMes(Integer idFacturacionMes) {
		this.idFacturacionMes = idFacturacionMes;
	}

	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Long getImpFacturadoMes() {
		return impFacturadoMes;
	}

	public void setImpFacturadoMes(Long impFacturadoMes) {
		this.impFacturadoMes = impFacturadoMes;
	}

	public Integer getPorcentajeIVA() {
		return porcentajeIVA;
	}

	public void setPorcentajeIVA(Integer porcentajeIVA) {
		this.porcentajeIVA = porcentajeIVA;
	}

	public Calendar getFecFacturacion() {
		return fecFacturacion;
	}

	public void setFecFacturacion(Calendar fecFacturacion) {
		this.fecFacturacion = fecFacturacion;
	}
}
