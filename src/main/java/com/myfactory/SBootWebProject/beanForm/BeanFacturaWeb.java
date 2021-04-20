package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class BeanFacturaWeb  implements Serializable , Cloneable{
	
	private static final long serialVersionUID = 1L;

	private Integer idFacturaWeb;
	    
	private String numFactura;
	    
		@NotNull(message = "Debe introducir un importe ")
	//	@Min(1)
	//	@Pattern(regexp="(^$|[0-9]{10})", message = "El formato de importe no es correcto" ) 
		@Size(min = 1, max = 10, message = "El importe de la factura debe tener 10 numeros como máximo")
		private String impFacturaWeb;
		
		@NotNull(message = "Debe introducir un concepto de la factura")
	    private String conceptoWeb;
		
		@Size(min = 1, max = 2, message = "El porcetaje del IVA debe tener 2 numeros como máximo")
	//  @Pattern(regexp="(^$|[0-9]{10})", message = "El porcentaje de iva no es correcto" ) 
	    private String porIvaWeb;
	    
	    @DateTimeFormat(pattern = "dd/MM/yyyy")
	    private Calendar fecFacturaWeb;
	    
	    private Integer idFormPagoWeb;
	    
		private Integer codSituacionWeb;
		
		private Float porDescuentoWeb;
		
		private Integer codDivisaWeb;
	       
		public BeanFacturaWeb() {
		}
	    
		public BeanFacturaWeb(Integer idFacturaWeb, String impFacturaWeb, String conceptoWeb, String porIvaWeb,
				Calendar fecFacturaWeb, Integer idFormPagoWeb) {
			super();
			this.idFacturaWeb = idFacturaWeb;
			this.impFacturaWeb = impFacturaWeb;
			this.conceptoWeb = conceptoWeb;
			this.porIvaWeb = porIvaWeb;
			this.fecFacturaWeb = fecFacturaWeb;
			this.idFormPagoWeb =  idFormPagoWeb;
			
			this.codSituacionWeb = new Integer(1);
			
			this.porDescuentoWeb = new Float(0);
			
			this.codDivisaWeb = new Integer(0);;
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
		public String getImpFacturaWeb() {
			return impFacturaWeb;
		}
		public void setImpFacturaWeb(String impFacturaWeb) {
			this.impFacturaWeb = impFacturaWeb;
		}
		public String getConceptoWeb() {
			return conceptoWeb;
		}
		public void setConceptoWeb(String conceptoWeb) {
			this.conceptoWeb = conceptoWeb;
		}
		public String getPorIvaWeb() {
			return porIvaWeb;
		}
		public void setPorIvaWeb(String porIvaWeb) {
			this.porIvaWeb = porIvaWeb;
		}
		public Calendar getFecFacturaWeb() {
			return fecFacturaWeb;
		}
		public void setFecFacturaWeb(Calendar fecFacturaWeb) {
			this.fecFacturaWeb = fecFacturaWeb;
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
}
