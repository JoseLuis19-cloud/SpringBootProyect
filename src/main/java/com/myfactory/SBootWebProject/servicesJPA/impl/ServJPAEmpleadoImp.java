package com.myfactory.SBootWebProject.servicesJPA.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfactory.SBootWebProject.model.Empleado;
import com.myfactory.SBootWebProject.repository.EmpleadoJPARepository;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAEmpleado;

@Service
public class ServJPAEmpleadoImp implements ServJPAEmpleado {

	@Autowired
	EmpleadoJPARepository empleadoJPARepository;
	
	@Override
	public Iterable<Empleado> obtenerListEmpleados() {
		return empleadoJPARepository.findAll() ;
	}

}
