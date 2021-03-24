package com.myfactory.SBootWebProject.repository.empresa;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.myfactory.SBootWebProject.model.Empresa;
import com.myfactory.SBootWebProject.model.User;

	public interface EmpresaJPABaseRepository extends CrudRepository<Empresa, Integer> {
	 
	}