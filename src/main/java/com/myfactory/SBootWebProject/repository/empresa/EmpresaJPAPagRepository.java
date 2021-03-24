package com.myfactory.SBootWebProject.repository.empresa;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.myfactory.SBootWebProject.model.Empresa;

public interface EmpresaJPAPagRepository extends PagingAndSortingRepository<Empresa, Integer> {

	@Query("select empresa from Empresa empresa where empresa.nomEmpresa >= :buscarEmpresa")
	Page<Empresa>  findMayorEmpresa( Pageable pageable, @Param("buscarEmpresa") String nomEmpresa) ;

	
}
