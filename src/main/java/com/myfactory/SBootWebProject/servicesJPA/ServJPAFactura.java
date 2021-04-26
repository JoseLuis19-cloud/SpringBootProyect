package com.myfactory.SBootWebProject.servicesJPA;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Factura;
import com.myfactory.SBootWebProject.model.FormaPago;

@Service
public interface ServJPAFactura {

	public Iterable<Factura> buscarTodasFacturas() ;

	public Optional<Factura> buscarIdFactura(Integer idFactura) ;

	public Factura altaFactura(Factura factura) ;
	
	public Factura modifFactura(Factura factura) ;

	public Optional<Factura> buscarFacturaId(Integer idFactura);

	public Page<Factura> paginacionFacturas(Integer numPag, Integer numRegPag);
	
	public Iterable<FormaPago> getFormasPago();
	
	public String  asignarNumFactura(Integer idSecuencial);
	
	public void  incrementarNumFactura(String anyoCurso, Integer numAsignado );

}
