package com.myfactory.SBootWebProject.servicesJPA.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.FacturaSituacion;
import com.myfactory.SBootWebProject.model.FormaPago;
import com.myfactory.SBootWebProject.model.Provincia;
import com.myfactory.SBootWebProject.model.TpoCliente;
import com.myfactory.SBootWebProject.model.TpoFrecuRepeticion;
import com.myfactory.SBootWebProject.repository.ClienteJPAPagRepository;
import com.myfactory.SBootWebProject.repository.ClienteJPARepository;
import com.myfactory.SBootWebProject.repository.FacturaJPAPagRepository;
import com.myfactory.SBootWebProject.repository.FacturaJPARepository;
import com.myfactory.SBootWebProject.repository.comunes.FacturaSituacionJPADao;
import com.myfactory.SBootWebProject.repository.FormaPagoJPARepository;
import com.myfactory.SBootWebProject.repository.TipoClienteJPADao;
import com.myfactory.SBootWebProject.repository.comunes.ProvinciaJPADao;
import com.myfactory.SBootWebProject.repository.comunes.TpoFreRepeticionJPADao;
import com.myfactory.SBootWebProject.servicesJPA.ServComunesAplicacionJPA;

@Service
public class ServComunesAplicacionJPAImpl implements ServComunesAplicacionJPA{

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
	TipoClienteJPADao reposSDataTipoCliente;
	
	@Autowired
	ProvinciaJPADao provinciaJPADao;
	
	@Autowired
	FacturaSituacionJPADao facturaSituacionJPADao;
	
	@Autowired
	TpoFreRepeticionJPADao tpoFreRepeticionJPADao;


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
	public Iterable<TpoFrecuRepeticion> getTpoFrecRepeticion() {
		return tpoFreRepeticionJPADao.findAll( );
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
