package com.myfactory.SBootWebProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.myfactory.SBootWebProject.model.Empleado;

public interface EmpleadoJPARepository extends CrudRepository<Empleado, Long> {
	
		//  @Query("select cli from Cliente cli where cli.apellidos = :apellidos")
		 // Iterable <Empleado>  findByApellidos(@Param("apellidos") String apellidos)  ;
		  
	  
}
