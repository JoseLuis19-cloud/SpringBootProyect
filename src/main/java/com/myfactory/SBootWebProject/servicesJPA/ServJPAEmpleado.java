package com.myfactory.SBootWebProject.servicesJPA;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Empleado;
import com.myfactory.SBootWebProject.model.Pais;
import com.myfactory.SBootWebProject.model.PuestoTrabajo;

@Service
public interface ServJPAEmpleado {
	
	public Empleado altaEmpleado(Empleado empleado);
	
	public Empleado modifEmpleado(Empleado empleado);
	
	public void bajaEmpleado(Empleado empleado);

	public Iterable<Empleado> obtenerListEmpleados() ;
	
	public Optional<Empleado> buscarIdEmpleado(Long idEmpleado);
	
	public Empleado grabarImagen(Empleado empleado);
	
	public Page<Empleado> pagEmpleados(Integer numPag, Integer numRegPag, String buscarApellido);
	
	public Iterable<Pais> obtenerPaises() ;
	
	public Iterable<PuestoTrabajo> obtenerPuestoTrabajo() ;
	
	public Iterable<Empleado> listEmpleadosProyecto() ;
}
