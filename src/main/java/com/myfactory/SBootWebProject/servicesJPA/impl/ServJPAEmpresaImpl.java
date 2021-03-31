package com.myfactory.SBootWebProject.servicesJPA.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myfactory.SBootWebProject.model.Empresa;
import com.myfactory.SBootWebProject.repository.empresa.EmpresaJPABaseRepository;
import com.myfactory.SBootWebProject.repository.empresa.EmpresaJPAPagRepository;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAEmpresa;

@Service
public class ServJPAEmpresaImpl implements ServJPAEmpresa  {

	@Autowired
	EmpresaJPABaseRepository empresaJPARepository;
	
	@Autowired
	EmpresaJPAPagRepository empresaJPAPagRepository;
	
	public Empresa buscarIdEmpresa(Integer idEmpresa) { 
		return  ((Optional <Empresa>) empresaJPARepository.findById(idEmpresa)).get() ;
	}
	public Empresa altaEmpresa(Empresa empresa){
		return empresaJPARepository.save(empresa);
	}
	
	public Empresa modifEmpresa(Empresa empresa){
		return empresaJPARepository.save(empresa);
	}

	@Override
	public Page<Empresa> pagEmpresas(Integer numPag, Integer numRegPag, String buscarEmpresa) {
		
	Page<Empresa> restulPag;
		
	if (buscarEmpresa.equals("") )
		{		
		restulPag = empresaJPAPagRepository.findAll(PageRequest.of(numPag, numRegPag, Sort.by("nomEmpresa")));
		}
	 else
	 	{
	 	restulPag = empresaJPAPagRepository.findMayorEmpresa(PageRequest.of(numPag, numRegPag, Sort.by("nomEmpresa")), buscarEmpresa);
	 	}

	if (restulPag.hasContent()) {
		System.out.println("Tiene contenido la paginacion");
	}
	return restulPag;
	}
	
	public Iterable <Empresa> listEmpresasProyecto()
	{
	 return empresaJPAPagRepository.findAll();
	}
}
