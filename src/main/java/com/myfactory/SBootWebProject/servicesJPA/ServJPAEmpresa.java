package com.myfactory.SBootWebProject.servicesJPA;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Empresa;

@Service
public interface ServJPAEmpresa {
	
	public Empresa buscarIdEmpresa(Integer idEmpresa);
	
	public Empresa altaEmpresa(Empresa empresa);
	
	public Empresa modifEmpresa(Empresa empresa);
	
	public Page<Empresa> pagEmpresas(Integer numPag, Integer numRegPag, String buscarEmpresas);
	
	 
}
