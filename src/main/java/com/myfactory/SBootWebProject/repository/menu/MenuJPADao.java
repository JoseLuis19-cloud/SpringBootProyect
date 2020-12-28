package com.myfactory.SBootWebProject.repository.menu;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.myfactory.SBootWebProject.model.Menu;


public interface MenuJPADao extends CrudRepository<Menu, Integer> {
	
	// @Query("select m from Menu m where m.activo = true")
	@Query("select m from Menu m")
	Iterable <Menu> obtenerMenuAplicacion();	 
	
	@Query("select m from Menu m WHERE m.idMenu > 0 ORDER BY m.numOrdenMenu")
	Iterable <Menu> obtenerMenuAplicacionSin0();

}
