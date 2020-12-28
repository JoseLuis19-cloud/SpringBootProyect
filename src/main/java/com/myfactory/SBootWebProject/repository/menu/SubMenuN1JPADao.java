package com.myfactory.SBootWebProject.repository.menu;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.myfactory.SBootWebProject.model.Menu;

public interface SubMenuN1JPADao extends CrudRepository<Menu, Integer>
	{ 

	@Query("SELECT m FROM Menu m WHERE m.idMenu = :idMenu")
	Iterable <Menu> obtenerSubMenu1Aplicacion(@Param("idMenu") Integer idMenu);  
 

//	@Query("SELECT m FROM Menu m WHERE m.idMenu = :idMenu"
//			+ "  JOIN m.subMenuNivel1 ms1"
//			+ " WHERE ms1.idSubmenuNivel1 > 0 ")
//	Iterable <Menu> obtenerSubMenu1AplicacionSin0(@Param("idMenu") Integer idMenu);  
 	}