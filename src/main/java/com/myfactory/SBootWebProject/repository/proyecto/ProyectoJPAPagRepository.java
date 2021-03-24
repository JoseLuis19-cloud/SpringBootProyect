package com.myfactory.SBootWebProject.repository.proyecto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.myfactory.SBootWebProject.model.Proyecto;

public interface ProyectoJPAPagRepository extends PagingAndSortingRepository<Proyecto, Integer> {

	@Query("select pro from Proyecto pro where pro.nomProyecto >= :buscarProyecto")
	Page<Proyecto>  findMayorProyecto( Pageable pageable, @Param("buscarProyecto") String nomProyecto) ;

	
}
