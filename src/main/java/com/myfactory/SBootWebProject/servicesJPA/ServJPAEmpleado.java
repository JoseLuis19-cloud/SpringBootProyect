package com.myfactory.SBootWebProject.servicesJPA;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Empleado;

@Service
public interface ServJPAEmpleado {
	
	public Empleado altaEmpleado(Empleado empleado);
	
	public Empleado modifEmpleado(Empleado empleado);

	public Iterable<Empleado> obtenerListEmpleados() ;
	
	public Optional<Empleado> buscarIdEmpleado(Long idEmpleado);
	
	public Empleado grabarImagen(Empleado empleado);
	
}
