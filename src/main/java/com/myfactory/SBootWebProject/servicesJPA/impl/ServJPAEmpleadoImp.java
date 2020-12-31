package com.myfactory.SBootWebProject.servicesJPA.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.Empleado;
import com.myfactory.SBootWebProject.repository.EmpleadoJPARepository;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAEmpleado;

@Service
public class ServJPAEmpleadoImp implements ServJPAEmpleado {

	@Autowired
	EmpleadoJPARepository empleadoJPARepository;
	
	public Empleado altaEmpleado(Empleado empleado){
		return empleadoJPARepository.save(empleado);
	}
	
	public Empleado modifEmpleado(Empleado empleado){
		return empleadoJPARepository.save(empleado);
	}
	
	@Override
	public Iterable<Empleado> obtenerListEmpleados() {
		return empleadoJPARepository.findAll() ;
	}
	@Override
	public Optional<Empleado> buscarIdEmpleado(Long idEmpleado) {
		return empleadoJPARepository.findById(idEmpleado);
	}

	@Override
	public Empleado grabarImagen(Empleado empleado){
		Empleado empleado1 = null;
		try {
			empleado1 = empleadoJPARepository.save(empleado);
		} catch (Exception ex) {
			System.out.println("Error al grabar imagen empleado");
		}
		return empleado1;
	} 
}
