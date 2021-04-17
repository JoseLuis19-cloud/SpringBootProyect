package com.myfactory.SBootWebProject.repository.menu;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.myfactory.SBootWebProject.model.SubMenuNivel1;

public interface SubMenuN1NuevoJPADao extends CrudRepository<SubMenuNivel1, Integer>
	{ 

	@Query("SELECT sm1 FROM SubMenuNivel1 sm1"
			+ " JOIN sm1.menu me "
			+ " WHERE me.idMenu = :idMenu"
			+ " ORDER BY sm1.numOrdenMenu")
	Iterable <SubMenuNivel1> obtenerSubMenu1Aplicacion(@Param("idMenu") Integer idMenu);  
	
	
	@Query("SELECT sm1 FROM SubMenuNivel1 sm1"
			+ " JOIN  sm1.menu me "
			+ " WHERE me.idMenu = :idMenu"
			+ " AND   sm1.idSubmenuNivel1 > 0"
			+ " ORDER BY sm1.numOrdenMenu")
	Iterable <SubMenuNivel1> obtenerSubMenu1AplicacionSin0(@Param("idMenu") Integer idMenu);  
	}

