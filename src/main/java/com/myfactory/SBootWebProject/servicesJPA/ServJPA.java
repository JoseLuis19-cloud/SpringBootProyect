package com.myfactory.SBootWebProject.servicesJPA;

import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.FacturaSituacion;
import com.myfactory.SBootWebProject.model.FormaPago;
import com.myfactory.SBootWebProject.model.Provincia;
import com.myfactory.SBootWebProject.model.TpoCliente;
import com.myfactory.SBootWebProject.model.TpoFrecuRepeticion;
 
@Service
public interface ServJPA {

	public Iterable<FormaPago> getFormasPago();

	public Iterable<TpoCliente> getTipoCliente();
	 
	public Iterable<Provincia> getProvincia();
	
	public Iterable<FacturaSituacion> getSituacionesFactura() ;
	 
	public Iterable<TpoFrecuRepeticion> getTpoFrecRepeticion() ;

	public  Cliente grabarImagen(Cliente cliente); 

}
