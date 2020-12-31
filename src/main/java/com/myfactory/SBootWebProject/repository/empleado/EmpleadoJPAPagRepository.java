package com.myfactory.SBootWebProject.repository.empleado;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.myfactory.SBootWebProject.model.Empleado;

public interface EmpleadoJPAPagRepository extends PagingAndSortingRepository<Empleado, Long> {

	@Query("select emp from Empleado emp where emp.apellidos >= :buscarApellidos")
	Page<Empleado>  findMayorApellido( Pageable pageable, @Param("buscarApellidos") String apellidos) ;

}
