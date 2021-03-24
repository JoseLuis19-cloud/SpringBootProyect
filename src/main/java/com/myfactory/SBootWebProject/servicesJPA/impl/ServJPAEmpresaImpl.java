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
	
	
 
	public Empresa altaEmpresa(Empresa empresa){
		return empresaJPARepository.save(empresa);
	}
	
	public Empresa modifEmpresa(Empresa empresa){
		return empresaJPARepository.save(empresa);
	}

	@Override
	public Page<Empresa> pagEmpresas(Integer numPag, Integer numRegPag, String buscarApellido) {
		Page<Empresa> restulPag;
		
		//  if (buscarApellido.equals("")  )
		// 	{		
		restulPag = empresaJPAPagRepository.findAll(PageRequest.of(numPag, numRegPag, Sort.by("idEmpresa")));
		// 	}
	//	else
	//		{
		//	 restulPag = empresaJPAPagRepository.findMayorEmpresa(PageRequest.of(numPag, numRegPag, Sort.by("apellidos")), buscarApellido);
	//		}

		if (restulPag.hasContent()) {
			System.out.println("Tiene contenido la paginacion");
		}
		return restulPag;
	}
}
