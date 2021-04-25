package com.myfactory.SBootWebProject.servicesJPA;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.Factura;
import com.myfactory.SBootWebProject.model.FacturaSituacion;
import com.myfactory.SBootWebProject.model.FormaPago;
import com.myfactory.SBootWebProject.model.Provincia;
import com.myfactory.SBootWebProject.model.TpoCliente;
 
@Service
public interface ServJPA {

	public Iterable<Cliente> buscarTodosClientes() ;

	public Optional<Cliente> buscarIdCliente(Integer idCliente);

	public Cliente altaCliente(Cliente cliente) ;

	public Iterable<Factura> buscarTodasFacturas() ;

	public Optional<Factura> buscarIdFactura(Integer idFactura) ;

	public Factura altaFactura(Factura factura) ;

	public Optional<Factura> buscarFacturaId(Integer idFactura);

	public Page<Cliente> paginacionClientes(Integer numPag, Integer numRegPag, String buscaApellidos) ;

	public Page<Factura> paginacionFacturas(Integer numPag, Integer numRegPag);

	public boolean bajaIdCliente(Integer idCliente);

	public Iterable<FormaPago> getFormasPago();

	public Iterable<TpoCliente> getTipoCliente();
	 
	public Iterable<Provincia> getProvincia();
	
	public Iterable<FacturaSituacion> getSituacionesFactura() ;
	
	public Iterable<Cliente> findByApellidos(String apellidos);
	
	public  Cliente grabarImagen( Cliente cliente); 

}
