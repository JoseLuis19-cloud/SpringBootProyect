package com.myfactory.SBootWebProject.controller;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import com.myfactory.SBootWebProject.beanForm.BeanClienteWeb;
import com.myfactory.SBootWebProject.beanForm.BeanFacturaWeb;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.Factura;
import com.myfactory.SBootWebProject.model.FormaPago;

@Component
@ApplicationScope
public class CargarBeansDatos  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	   Iterable <FormaPago>  formasPago;

		public CargarBeansDatos() {
		}
	    
		
	  public BeanFacturaWeb cargarBeanFactura (Factura factura)
	  {
		  BeanFacturaWeb facturaWeb = new BeanFacturaWeb();
		  facturaWeb.setIdFacturaWeb(factura.getIdFactura());
		  facturaWeb.setNumFactura(factura.getNumFactura());
		  facturaWeb.setImpFacturaWeb(factura.getImpFactura().toString());
		  facturaWeb.setConceptoWeb(factura.getConcepto());
		  facturaWeb.setPorIvaWeb(factura.getPorIva().toString());
		  facturaWeb.setFecFacturaWeb(factura.getFecFactura().toString());
		//  facturaWeb.setFormasPago(factura.getFormaPago());
		  return facturaWeb;
	  }
	  
	  
	  public BeanClienteWeb cargarBeanCliente (Cliente cliente)
	  {
		  BeanClienteWeb clienteWeb = new BeanClienteWeb();
		  clienteWeb.setApellidosWeb(cliente.getApellidos());
		  clienteWeb.setNombreWeb(cliente.getNombre());
		  clienteWeb.setDirEmailWeb(cliente.getDirEmail() );
		  clienteWeb.setFecNacimientoWeb( cliente.getFecNacimiento().toString() );
		  clienteWeb.setDNIWeb( cliente.getDNI() );
		  clienteWeb.setTelefonoWeb( cliente.getTelefono()  );
		  clienteWeb.setPaisWeb( cliente.getPais()  );
		//  facturaWeb.setFormasPago(factura.getFormaPago());
		  
		  
		 // Factura nueva=(Factura)f.clone();
		  return clienteWeb;
	  }

		
}
