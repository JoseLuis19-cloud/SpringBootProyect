package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
 

@Component
@RequestScope
public class BeanFacturaLineas  implements Serializable , Cloneable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idLinFactura;
	
	@Size(min = 1, max = 99, message = "La cantidad debe estar entre  1 y  99")
	@NotNull(message = "Debe introducir un cantidad de la factura")
	private Long cantidad;

	@NotNull(message = "Debe introducir un concepto ")
	private String concepto;
	
	private Long porIva;
	
	@NotNull(message = "Debe introducir un importe de factura")
	private Float impFactura;

	@Size(min = 1, max = 99, message = "El porcentaje de descuento debe estar entre  1 y  99")
	private Long porDescuento;
	
	public Integer getIdLinFactura() {
		return idLinFactura;
	}

	public void setIdLinFactura(Integer idLinFactura) {
		this.idLinFactura = idLinFactura;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Long getPorIva() {
		return porIva;
	}

	public void setPorIva(Long porIva) {
		this.porIva = porIva;
	}

	public Float getImpFactura() {
		return impFactura;
	}

	public void setImpFactura(Float impFactura) {
		this.impFactura = impFactura;
	}

	public Long getPorDescuento() {
		return porDescuento;
	}

	public void setPorDescuento(Long porDescuento) {
		this.porDescuento = porDescuento;
	}
	

}
