package com.myfactory.SBootWebProject.servicesJPA.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.Factura;
import com.myfactory.SBootWebProject.model.FacturaSituacion;
import com.myfactory.SBootWebProject.model.FormaPago;
import com.myfactory.SBootWebProject.model.Provincia;
import com.myfactory.SBootWebProject.model.TpoCliente;
import com.myfactory.SBootWebProject.repository.ClienteJPAPagRepository;
import com.myfactory.SBootWebProject.repository.ClienteJPARepository;
import com.myfactory.SBootWebProject.repository.FacturaJPAPagRepository;
import com.myfactory.SBootWebProject.repository.FacturaJPARepository;
import com.myfactory.SBootWebProject.repository.comunes.FacturaSituacionJPADao;
import com.myfactory.SBootWebProject.repository.FormaPagoJPARepository;
import com.myfactory.SBootWebProject.repository.TipoClienteJPARepository;
import com.myfactory.SBootWebProject.repository.comunes.ProvinciaJPADao;
import com.myfactory.SBootWebProject.servicesJPA.ServJPA;

@Service
public class ServJPAImpl implements ServJPA{

	@Autowired
	ClienteJPARepository reposSDataCliente;
	
	@Autowired
	ClienteJPAPagRepository reposSDataPagCliente;
	
	@Autowired
	ClienteJPARepository reposSDBaseCliente;
	
	@Autowired
	FacturaJPARepository reposSDataFactura;
	
	@Autowired
	FacturaJPAPagRepository reposSDataPagFactura;
	
	@Autowired
	FormaPagoJPARepository reposSDataFormaPago;
	
	@Autowired
	TipoClienteJPARepository reposSDataTipoCliente;
	
	@Autowired
	ProvinciaJPADao provinciaJPADao;
	
	@Autowired
	FacturaSituacionJPADao facturaSituacionJPADao;

	@Override
	public Iterable<Cliente> buscarTodosClientes() {

		return reposSDataCliente.findAll();
	}
	
	@Override
	public Optional<Cliente> buscarIdCliente(Integer idCliente) {

		return reposSDataCliente.findById(idCliente);
	}
	
	@Override
	public Cliente altaCliente(Cliente cliente) {

		return reposSDataCliente.save(cliente);
	}
	
	@Override
	public Iterable<Factura> buscarTodasFacturas() {

		return reposSDataFactura.findAll();
	}

	@Override
	public Optional<Factura> buscarIdFactura(Integer idFactura) {

		return reposSDataFactura.findById(idFactura);
	}

	@Override
	public Factura altaFactura(Factura factura) {
		try {
			factura = reposSDataFactura.save(factura);
		} catch (Exception ex) {
			factura = null;
		}
		return factura;
	}
	
	@Override
	public Optional<Factura> buscarFacturaId(Integer idFactura) {

		return reposSDataFactura.findById(idFactura);
	}

	@Override
	public Page<Cliente> paginacionClientes(Integer numPag, Integer numRegPag, String buscarApellido) {
		Page<Cliente> restulPag ;
		
		//if (buscarApellido.equals("")  )
		//	{		
		//	 restulPag = reposSDataPagCliente.findAll(PageRequest.of(numPag, numRegPag, Sort.by("idCliente")));
		//	}
	//	else
	//		{
			 restulPag = reposSDataPagCliente.findMayorApellido(PageRequest.of(numPag, numRegPag, Sort.by("apellidos")), buscarApellido);
	//		}

		if (restulPag.hasContent()) {
			System.out.println("tiene contenido la paginacion");
		}
		return restulPag;
	}

	@Override
	public Page<Factura> paginacionFacturas(Integer numPag, Integer numRegPag) {

		Page<Factura> restulPag = reposSDataPagFactura.findAll(PageRequest.of(numPag, numRegPag, Sort.by("idFactura")));

		if (restulPag.hasContent()) {
			System.out.println("tiene contenido la paginacion");
		}

		return restulPag;
	}

	@Override
	public boolean bajaIdCliente(Integer idCliente) {
		try {
			reposSDataCliente.deleteById(idCliente);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Iterable<FormaPago> getFormasPago() {

		return reposSDataFormaPago.findAll();
	}

	@Override
	public Iterable<TpoCliente> getTipoCliente() {

		return reposSDataTipoCliente.findAll();
	}
	
	@Override
	public Iterable<Provincia> getProvincia(){
		return provinciaJPADao.findAll();
	}
	
	@Override
	public Iterable<FacturaSituacion> getSituacionesFactura() {
		return facturaSituacionJPADao.findAll();
	}
	

	@Override
	public Iterable<Cliente> findByApellidos(String apellidos) {
		Iterable<Cliente> listClienteMismoApell = null;
		try {

		//	listClienteMismoApell = reposSDBaseCliente.findByApellidos(apellidos);
		} catch (Exception ex) {
			System.out.println("111");
		}
		return listClienteMismoApell;
	}
	
	@Override
	public  Cliente grabarImagen( Cliente cliente){
		Cliente cliente1 = null;
		try {
			 cliente1 = reposSDBaseCliente.save(cliente);
		} catch (Exception ex) {
			System.out.println("111");
		}
		return cliente1;
	} 

	
}
