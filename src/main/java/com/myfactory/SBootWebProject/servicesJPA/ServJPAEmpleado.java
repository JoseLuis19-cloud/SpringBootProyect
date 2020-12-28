package com.myfactory.SBootWebProject.servicesJPA;

import org.springframework.stereotype.Service;

import com.myfactory.SBootWebProject.model.Empleado;

@Service
public interface ServJPAEmpleado {

	public Iterable<Empleado> obtenerListEmpleados() ;
	
}
