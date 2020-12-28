package com.myfactory.SBootWebProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.MenusUsuario;

public interface ClienteJPAPagRepository extends PagingAndSortingRepository<Cliente, Integer> {

 	// public Iterable <MenusUsuario> obtenerSubMenuUsuario(@Param("idUsuario") Long idUsuario, @Param("idMenu") Integer idMenu);
 	
 	// Paginacion de Clientes Ordenadas por apellido de cliente filtrado por apellido.
	@Query("select cli from Cliente cli where cli.apellidos >= :buscarApellidos")
	Page<Cliente>  findMayorApellido( Pageable pageable, @Param("buscarApellidos") String apellidos) ;

}
