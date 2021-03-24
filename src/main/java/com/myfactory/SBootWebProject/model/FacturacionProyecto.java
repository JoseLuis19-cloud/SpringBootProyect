package com.myfactory.SBootWebProject.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "FACTURACION_PROYECTO")
public class FacturacionProyecto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_FACTURACION_MES", unique = true)
	private Integer idFacturacionMes;

	@Column(name = "ID_PROYECTO", nullable = false, unique = false)
	private Integer idProyecto;
	
	@Column(name = "ID_EMPLEADO", nullable = false, unique = false)
	private Integer idEmpleado;

	@Column(name = "IMP_FACTURADO_MES", nullable = false, unique = false)
	private Long impFacturadoMes;

	@Column(name = "PORCENTAJE_IVA", nullable = false, unique = false)
	private Integer porcentajeIVA;

	@Column(name = "FEC_FACTURACION", nullable = false, unique = false)
	@Temporal(TemporalType.DATE)
	private Calendar fecFacturacion;

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
