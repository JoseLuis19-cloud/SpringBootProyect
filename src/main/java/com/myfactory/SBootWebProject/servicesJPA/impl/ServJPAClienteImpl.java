package com.myfactory.SBootWebProject.servicesJPA.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.Factura;
import com.myfactory.SBootWebProject.model.FormaPago;
import com.myfactory.SBootWebProject.model.TpoCliente;
import com.myfactory.SBootWebProject.repository.ClienteJPAPagRepository;
import com.myfactory.SBootWebProject.repository.ClienteJPARepository;
import com.myfactory.SBootWebProject.repository.FacturaJPAPagRepository;
import com.myfactory.SBootWebProject.repository.FacturaJPARepository;
import com.myfactory.SBootWebProject.repository.FormaPagoJPARepository;
import com.myfactory.SBootWebProject.repository.TipoClienteJPARepository;
import com.myfactory.SBootWebProject.servicesJPA.ServJPA;
import com.myfactory.SBootWebProject.servicesJPA.ServJPACliente;

@Service
public class ServJPAClienteImpl implements ServJPACliente {

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
	public boolean bajaIdCliente(Integer idCliente) {
		try {
			reposSDataCliente.deleteById(idCliente);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	@Override
	public Iterable<TpoCliente> getTipoCliente() {

		return reposSDataTipoCliente.findAll();
	}
	
	@Override
	public Cliente modificarCliente(Cliente cliente) {

		return reposSDataCliente.save(cliente);
	}

	
}
