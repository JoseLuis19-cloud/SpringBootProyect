package com.myfactory.SBootWebProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.myfactory.SBootWebProject.model.Cliente;


public interface ClienteJPASDataRepository extends JpaRepository<Cliente, Integer>, PagingAndSortingRepository<Cliente, Integer>, CrudRepository<Cliente, Integer>  {

		  @Query("select cli from Cliente cli where cli.apellidos = :apellidos")
		  Iterable <Cliente>  findByApellidos(@Param("apellidos") String apellidos)  ;
		  
		  
		//  @Query("select cli from Cliente cli where cli.apellidos = :apellidos")
		 // Page  <Cliente> findAllByApellidos(@Param("apellidos") String apellidos)  ;
		  
	  
}
