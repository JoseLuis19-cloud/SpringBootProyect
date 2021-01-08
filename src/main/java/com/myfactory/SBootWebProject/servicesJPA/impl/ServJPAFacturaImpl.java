package com.myfactory.SBootWebProject.servicesJPA.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.myfactory.SBootWebProject.model.Factura;
import com.myfactory.SBootWebProject.model.FormaPago;
import com.myfactory.SBootWebProject.repository.FacturaJPAPagRepository;
import com.myfactory.SBootWebProject.repository.FacturaJPARepository;
import com.myfactory.SBootWebProject.repository.FormaPagoJPARepository;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAFactura;

@Service
public class ServJPAFacturaImpl implements ServJPAFactura{
	
	@Autowired
	FacturaJPARepository reposSDataFactura;
	
	@Autowired
	FacturaJPAPagRepository reposSDataPagFactura;
	
	@Autowired
	FormaPagoJPARepository reposSDataFormaPago;

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
	public Page<Factura> paginacionFacturas(Integer numPag, Integer numRegPag) {

		Page<Factura> restulPag = reposSDataPagFactura.findAll(PageRequest.of(numPag, numRegPag, Sort.by("idFactura")));

		if (restulPag.hasContent()) {
			System.out.println("tiene contenido la paginacion");
		}

		return restulPag;
	}

	@Override
	public Iterable<FormaPago> getFormasPago() {
		return reposSDataFormaPago.findAll();
	}
	
}
