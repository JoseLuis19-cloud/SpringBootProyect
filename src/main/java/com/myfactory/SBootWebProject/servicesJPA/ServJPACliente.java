package com.myfactory.SBootWebProject.servicesJPA;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.TpoCliente;

@Service
public interface ServJPACliente {
	
	public Iterable<Cliente> buscarTodosClientes() ;

	public Optional<Cliente> buscarIdCliente(Integer idCliente);

	public Cliente altaCliente(Cliente cliente) ;
	
	public Cliente modificarCliente(Cliente cliente) ;

	public Page<Cliente> paginacionClientes(Integer numPag, Integer numRegPag, String buscaApellidos) ;
	
	public boolean bajaIdCliente(Integer idCliente);

	public Iterable<TpoCliente> getTipoCliente();
 
}
