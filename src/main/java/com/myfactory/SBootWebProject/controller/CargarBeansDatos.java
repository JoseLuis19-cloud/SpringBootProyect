package com.myfactory.SBootWebProject.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import com.myfactory.SBootWebProject.beanForm.BeanClienteWeb;
import com.myfactory.SBootWebProject.beanForm.BeanFacturaWeb;
import com.myfactory.SBootWebProject.beanForm.BeanProyectoWeb;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioWeb;
import com.myfactory.SBootWebProject.beanForm.BeanEmpleadoWeb;
import com.myfactory.SBootWebProject.beanForm.BeanEmpresaWeb;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.Empleado;
import com.myfactory.SBootWebProject.model.Empresa;
import com.myfactory.SBootWebProject.model.Factura;
import com.myfactory.SBootWebProject.model.FormaPago;
import com.myfactory.SBootWebProject.model.Proyecto;
import com.myfactory.SBootWebProject.model.User;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAEmpleado;

@Component
@ApplicationScope
public class CargarBeansDatos  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	ServJPAEmpleado servJPAEmpleado;
	
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
		 facturaWeb.setFecFacturaWeb(new SimpleDateFormat("dd/MM/yyyy").format(factura.getFecFactura()));
		return facturaWeb;
	  }
	  
	  
	  public BeanClienteWeb cargarBeanCliente (Cliente cliente)
	  {
		 BeanClienteWeb clienteWeb = new BeanClienteWeb();
		    
		 clienteWeb.setIdClienteWeb(cliente.getIdCliente());
		 clienteWeb.setApellidosWeb(cliente.getApellidos());
		 clienteWeb.setNombreWeb(cliente.getNombre());
		 clienteWeb.setDireccionWeb( cliente.getDireccion() );

		 clienteWeb.setDirEmailWeb(cliente.getDirEmail() );
		 clienteWeb.setFecNacimientoWeb( new SimpleDateFormat("dd/MM/yyyy").format(cliente.getFecNacimiento())   );
		 clienteWeb.setFecNacimiento2Web( cliente.getFecNacimiento().toString() );
		 clienteWeb.setDNIWeb( cliente.getDNI() );
		 clienteWeb.setTelefonoWeb( cliente.getTelefono()  );
		 clienteWeb.setPaisWeb( cliente.getPais()  );
		 clienteWeb.setIdTpoCliente(   cliente.getTpoCliente().getIdTpoCliente() );
		//  facturaWeb.setFormasPago(factura.getFormaPago());
		  
		 // Factura nueva=(Factura)f.clone();
		 return clienteWeb;
	  }
	  
	  public BeanUsuarioWeb cargarBeanUsuario (User usuario)
	  {
		 BeanUsuarioWeb beanUsuarioWeb = new BeanUsuarioWeb();
		 
		 beanUsuarioWeb.setIdUsuarioWeb(usuario.getId());
		 beanUsuarioWeb.setEmailWeb( usuario.getEmail());
	//	 beanUsuarioWeb.setEnabled(usuario.get.isEnabledWeb());
		// beanUsuarioWeb.setIndEmpleado(usuario.getEmpleado().G.isIndEmpleadoWeb());
		 beanUsuarioWeb.setFullNameWeb(usuario.getFullName() );
		 beanUsuarioWeb.setUsernameWeb(usuario.getUsername() );
		 beanUsuarioWeb.setFecAltaUsuarioWeb(usuario.getFecAltaUsuario());
		 	 
		 beanUsuarioWeb.setIdRole( 1 ) ;		
 
		 return beanUsuarioWeb;
	  }

	  public BeanEmpleadoWeb cargarBeanEmpleado (Empleado empleado)
	  {
		  BeanEmpleadoWeb beanEmpleadoWeb = new BeanEmpleadoWeb();
		  
		  beanEmpleadoWeb.setIdEmpleadoWeb(empleado.getIdEmpleado());	
		  beanEmpleadoWeb.setNombreWeb(empleado.getNombre());
		  beanEmpleadoWeb.setApellidosWeb(empleado.getApellidos());
		  beanEmpleadoWeb.setDireccionWeb(empleado.getDireccion());
		  beanEmpleadoWeb.setCodPostalWeb(empleado.getCodPostal() );
		  beanEmpleadoWeb.setNifWeb(empleado.getNif());
		  beanEmpleadoWeb.setFecAltaEmplelado2Web(empleado.getFecAltaEmplelado());
		  beanEmpleadoWeb.setFecBajaEmpleladoWeb(empleado.getFecBajaEmplelado() );
		 
		  beanEmpleadoWeb.setTelefMovilWeb(empleado.getTelefMovil() );
		  beanEmpleadoWeb.setTelefono2(empleado.getTelefono2() );
		  beanEmpleadoWeb.setEmailWeb(empleado.getEmail());
		  
		  beanEmpleadoWeb.setNumSeguridaSocialWeb(empleado.getNumSeguridaSocial()); 
		  beanEmpleadoWeb.setNumCuentaCorrienteWeb(empleado.getNumCuentaCorriente() ); 
		  beanEmpleadoWeb.setPaisWeb(servJPAEmpleado.obtenerPaises()); 
		  beanEmpleadoWeb.setCodPaisWeb(empleado.getPais().getIdPais());
		  
		  beanEmpleadoWeb.setPuestoTrabajoWeb(servJPAEmpleado.obtenerPuestoTrabajo() ); 
		  beanEmpleadoWeb.setCodPuestoTrabajoWeb(empleado.getPuestoTrabajo().getIdPuestoTrabajo());	  
		  
		  if (empleado.getImpBrutoAnual() != null)
		  	{
			  beanEmpleadoWeb.setImpBrutoAnualWeb(empleado.getImpBrutoAnual().toString());
		  	}
		  else
		  {
			  beanEmpleadoWeb.setImpBrutoAnualWeb("0");
		  }
		  
		  return beanEmpleadoWeb;
	  }
	  
	  public BeanProyectoWeb cargarBeanProyecto(Proyecto proyecto) 
	  {
		  BeanProyectoWeb proyectoWeb = new BeanProyectoWeb();
		  
		  proyectoWeb.setIdProyectoWeb(proyecto.getIdProyecto() );
		  proyectoWeb.setNomProyectoWeb(proyecto.getNomProyecto()  );
		  proyectoWeb.setFecIniProyectoWeb(  new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFecIniProyecto() ));
		  proyectoWeb.setFecFinProyectoWeb(  new SimpleDateFormat("dd/MM/yyyy").format(proyecto.getFecFinProyecto() ));
		  return proyectoWeb;
	  }
	  
	  public BeanEmpresaWeb cargarBeanEmpresa (Empresa empresa ) 
	  {
		  BeanEmpresaWeb datosEmpresaWeb = new BeanEmpresaWeb();
		  
		  datosEmpresaWeb.setIdEmpresaWeb(empresa.getIdEmpresa()   );
		  datosEmpresaWeb.setNomEmpresaWeb(empresa.getNomEmpresa()  );
		  datosEmpresaWeb.setCodPostalWeb(empresa.getCodPostal());
		  datosEmpresaWeb.setDirecionWeb(empresa.getDirecion());
		  datosEmpresaWeb.setTelefonoWeb(empresa.getTelefono());
		  
		  datosEmpresaWeb.setNomContacto1Web(empresa.getNomContacto1());
		  datosEmpresaWeb.setTelefContacto1Web(empresa.getTelefContacto1() );
		  datosEmpresaWeb.setEmailContacto1Web(empresa.getEmailContacto1());
		  
		  datosEmpresaWeb.setNomContacto2Web( empresa.getNomContacto2());
		  datosEmpresaWeb.setTelefContacto2Web(empresa.getTelefContacto2() );
		  datosEmpresaWeb.setEmailContacto2Web(empresa.getEmailContacto2());
		  
	 //  datosEmpresaWeb.setFecAltaEmpresaWeb( new SimpleDateFormat("dd/MM/yyyy").format(empresa.getFecAltaEmpresa()  ));
		  
		  return datosEmpresaWeb;
	  }
}
