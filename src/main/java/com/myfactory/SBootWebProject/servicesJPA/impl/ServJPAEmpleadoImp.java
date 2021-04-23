package com.myfactory.SBootWebProject.servicesJPA.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myfactory.SBootWebProject.model.Empleado;
import com.myfactory.SBootWebProject.model.Pais;
import com.myfactory.SBootWebProject.repository.PuestoTrabajoJPADao;
import com.myfactory.SBootWebProject.repository.comunes.PaisJPADao;
import com.myfactory.SBootWebProject.repository.empleado.EmpleadoJPABaseRepository;
import com.myfactory.SBootWebProject.repository.empleado.EmpleadoJPAPagRepository;
import com.myfactory.SBootWebProject.model.PuestoTrabajo;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAEmpleado;

@Service
public class ServJPAEmpleadoImp implements ServJPAEmpleado {

	@Autowired
	EmpleadoJPABaseRepository empleadoJPARepository;
	
	@Autowired
	EmpleadoJPAPagRepository empleadoJPAPagRepository;
	
	@Autowired
	PaisJPADao paisJPADao;
	
	@Autowired
	PuestoTrabajoJPADao puestoTrabajoJPADao;
	
	
	@Override
	public Empleado altaEmpleado(Empleado empleado){
		return empleadoJPARepository.save(empleado);
	}
	
	@Override
	public void modifEmpleado(Empleado empleado){
	 empleadoJPARepository.save(empleado);
	}
	
	@Override
	public void bajaEmpleado(Empleado empleado)
	{
	   empleadoJPARepository.save(empleado);
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
	
	@Override
	public Page<Empleado> pagEmpleados(Integer numPag, Integer numRegPag, String buscarApellido) {
		Page<Empleado> restulPag;
		
		 if (buscarApellido.equals("")  )
		 	{		
		 	 restulPag = empleadoJPAPagRepository.findAll(PageRequest.of(numPag, numRegPag, Sort.by("apellidos")));
		 	}
	 	else
	 		{
			 restulPag = empleadoJPAPagRepository.findMayorApellido(PageRequest.of(numPag, numRegPag, Sort.by("apellidos")), buscarApellido);
	 		}

		if (restulPag.hasContent()) {
			System.out.println("tiene contenido la paginacion");
		}
		return restulPag;
	}
	
	@Override
	public Iterable<Pais> obtenerPaises(){
		return paisJPADao.findAll();
	}
	
	@Override
	public Iterable<PuestoTrabajo> obtenerPuestoTrabajo(){
		return puestoTrabajoJPADao.findAll();
	}
	
	@Override
	public Iterable<Empleado> listEmpleadosProyecto() {
		return empleadoJPARepository.findAll();
	}	
}
