package com.myfactory.SBootWebProject.repository.empresa;

import org.springframework.data.repository.CrudRepository;

import com.myfactory.SBootWebProject.model.Empresa;

	public interface EmpresaJPABaseRepository extends CrudRepository<Empresa, Integer> {
	 
	}