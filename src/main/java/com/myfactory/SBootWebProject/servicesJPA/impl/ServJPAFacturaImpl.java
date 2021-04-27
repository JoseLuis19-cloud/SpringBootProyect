package com.myfactory.SBootWebProject.servicesJPA.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myfactory.SBootWebProject.constantes.ConstantesAplicacion;
import com.myfactory.SBootWebProject.model.Factura;
import com.myfactory.SBootWebProject.model.FormaPago;
import com.myfactory.SBootWebProject.model.Secuenciales;
import com.myfactory.SBootWebProject.repository.FacturaJPAPagRepository;
import com.myfactory.SBootWebProject.repository.FacturaJPARepository;
import com.myfactory.SBootWebProject.repository.FormaPagoJPARepository;
import com.myfactory.SBootWebProject.repository.comunes.SecuencialesJPADao;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAFactura;

@Service
public class ServJPAFacturaImpl implements ServJPAFactura{
	
	@Autowired
	FacturaJPARepository reposSDataFactura;
	
	@Autowired
	FacturaJPAPagRepository reposSDataPagFactura;
	
	@Autowired
	FormaPagoJPARepository reposSDataFormaPago;
	
	@Autowired 
	SecuencialesJPADao secuencialesJPADao;

	@Override
	public Iterable<Factura> buscarTodasFacturas() {

		return reposSDataFactura.findAll();
	}

	@Override
	public Optional<Factura> buscarIdFactura(Integer idFactura) {

		return reposSDataFactura.findById(idFactura);
	}

	@Override
	@Transactional
	public Integer altaFactura(Factura factura) {
		try {
			factura = reposSDataFactura.save(factura);
		} catch (Exception ex) {
			factura = null;
		}
		return factura.getIdFactura();
	}
	
	@Override
	@Transactional
	public Factura modifFactura(Factura factura) {
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
	public Page<Factura> paginacionFacturas(Integer numPag, Integer numRegPag) {

		Page<Factura> restulPag = reposSDataPagFactura.findAll(PageRequest.of(numPag, numRegPag, Sort.by("idFactura")));

		if (restulPag.hasContent()) {
			System.out.println("Tiene contenido la paginacion");
		}

		return restulPag;
	}

	@Override
	public Iterable<FormaPago> getFormasPago() {
		return reposSDataFormaPago.findAll();
	}
	
	@Override
	public String asignarNumFactura(Integer idSecuencial) {
		String codFactura;
		Optional<Secuenciales> datosSecFactu = secuencialesJPADao.findById(idSecuencial);
		
		codFactura = datosSecFactu.get().getAnyoCurso() + "/" + datosSecFactu.get().getIdentificadorUnico();
	return codFactura;
	}

	@Override
	public void incrementarNumFactura(String anyoCurso, Integer numAsignado ) {
		Secuenciales secuenciales = new Secuenciales();
		secuenciales.setNomSecuecial("FACTURA");
		secuenciales.setIdSecuencial(ConstantesAplicacion.FACTURAS_SECUENCIAL);
		secuenciales.setAnyoCurso(anyoCurso);
		
		Integer numA = numAsignado.intValue() + 1;
		secuenciales.setIdentificadorUnico(numA.toString());
		secuencialesJPADao.save(secuenciales);
	}
	  
 
	
}
