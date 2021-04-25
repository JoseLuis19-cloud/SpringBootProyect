package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class BeanFacturaWeb  implements Serializable , Cloneable{
	
	private static final long serialVersionUID = 1L;

	private Integer idFacturaWeb;
	    
	private String numFactura;

	private Float impFacturaWeb;
	    
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar fecAltaFacturaWeb;
	    
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar fecFacturaEmisionWeb;
	    
	private Integer idFormPagoWeb;
	    
	private Integer codSituacionWeb;
		
	private Float porDescuentoWeb;
		
	private Integer codDivisaWeb;
	
	private String codFactura;
	
	private String notaFactura;
		
	public String getNotaFactura() {
		return notaFactura;
	}

	public void setNotaFactura(String notaFactura) {
		this.notaFactura = notaFactura;
	}

	private List<BeanFacturaLineas> beanFacturaLineas;


	public Float getImpFacturaWeb() {
		return impFacturaWeb;
	}

	public void setImpFacturaWeb(Float impFacturaWeb) {
		this.impFacturaWeb = impFacturaWeb;
	}

	public Calendar getFecFacturaEmisionWeb() {
		return fecFacturaEmisionWeb;
	}

	public void setFecFacturaEmisionWeb(Calendar fecFacturaEmisionWeb) {
		this.fecFacturaEmisionWeb = fecFacturaEmisionWeb;
	}

		public BeanFacturaWeb() {
		}
	    
		public BeanFacturaWeb(Integer idFacturaWeb, Float impFacturaWeb,
				Calendar fecFacturaWeb, Integer idFormPagoWeb) {
			super();
			this.idFacturaWeb = idFacturaWeb;
			this.impFacturaWeb = impFacturaWeb;
			this.fecAltaFacturaWeb = fecFacturaWeb;
			this.idFormPagoWeb =  idFormPagoWeb;
			this.codSituacionWeb = new Integer(1);
			this.porDescuentoWeb = new Float(0);
			this.codDivisaWeb = new Integer(978);;
		}

		public Integer getIdFormPagoWeb() {
			return idFormPagoWeb;
		}

		public void setIdFormPagoWeb(Integer idFormPagoWeb) {
			this.idFormPagoWeb = idFormPagoWeb;
		}

		public Integer getIdFacturaWeb() {
			return idFacturaWeb;
		}
		public void setIdFacturaWeb(Integer idFacturaWeb) {
			this.idFacturaWeb = idFacturaWeb;
		}
		
		public Calendar getFecAltaFacturaWeb() {
			return fecAltaFacturaWeb;
		}
		public void setFecAltaFacturaWeb(Calendar fecAltaFacturaWeb) {
			this.fecAltaFacturaWeb = fecAltaFacturaWeb;
		}
		
		public String getNumFactura() {
			return numFactura;
		}

		public void setNumFactura(String numFactura) {
			this.numFactura = numFactura;
		}
		
		public Integer getCodSituacionWeb() {
			return codSituacionWeb;
		}

		public void setCodSituacionWeb(Integer codSituacion) {
			this.codSituacionWeb = codSituacion;
		}

		public Float getPorDescuentoWeb() {
			return porDescuentoWeb;
		}

		public void setPorDescuentoWeb(Float porDescuento) {
			this.porDescuentoWeb = porDescuento;
		}

		public Integer getCodDivisaWeb() {
			return codDivisaWeb;
		}

		public void setCodDivisaWeb(Integer codDivisa) {
			this.codDivisaWeb = codDivisa;
		}
		
		public List<BeanFacturaLineas> getBeanFacturaLineas() {
			return beanFacturaLineas;
		}

		public void setBeanFacturaLineas(List<BeanFacturaLineas> beanFacturaLineas) {
			this.beanFacturaLineas = beanFacturaLineas;
		}
		
		public String getCodFactura() {
			return codFactura;
		}

		public void setCodFactura(String codFactura) {
			this.codFactura = codFactura;
		}

}
