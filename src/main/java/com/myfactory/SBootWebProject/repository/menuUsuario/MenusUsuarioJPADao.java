package com.myfactory.SBootWebProject.repository.menuUsuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.myfactory.SBootWebProject.model.MenusUsuario;


public interface MenusUsuarioJPADao extends CrudRepository<MenusUsuario, Integer>
{
	@Query("SELECT mu from MenusUsuario mu "
 		 	+ " JOIN mu.menu me"
			+ " JOIN mu.user us"
			+ " JOIN mu.subMenu1 sm1"
			+ " WHERE us.id = :idUsuario"
		//	+ "  AND me.idMenu > 0"
			+ "  AND sm1.idSubmenuNivel1 = 0"
			+ " ORDER BY me.numOrdenMenu")
 	public Iterable <MenusUsuario> obtenerMenuUsuario(@Param("idUsuario") Long idUsuario);

	@Query("SELECT mu from MenusUsuario mu "
 			+ "  JOIN mu.subMenu1 sm1"
 			+ "  JOIN mu.menu me"
			+ "  JOIN mu.user us"
			+ " WHERE us.id = :idUsuario"
			+ "   AND me.idMenu = :idMenu"
			+ "   AND sm1.idSubmenuNivel1 > 0"
			+ " ORDER BY me.numOrdenMenu")
 	public Iterable <MenusUsuario> obtenerSubMenuUsuario(@Param("idUsuario") Long idUsuario, @Param("idMenu") Integer idMenu);
	
	@Query("SELECT mu from MenusUsuario mu "
 		 	+ "  JOIN mu.menu me"
			+ "  JOIN mu.user us"
			+ "  JOIN mu.subMenu1 sm1"
			+ " WHERE us.id = :idUsuario"
		 	+ "   AND me.idMenu > 0"
			+ "   AND sm1.idSubmenuNivel1 = 0"
			+ " ORDER BY me.numOrdenMenu")
 	public Iterable <MenusUsuario> obtenerMenuUsuSin0(@Param("idUsuario") Long idUsuario);
	
	@Query("SELECT mu from MenusUsuario mu "
 			+ "  JOIN mu.subMenu1 sm1"
 		 	+ "  JOIN mu.menu me"
			+ "  JOIN mu.user us"
			+ " WHERE us.id = :idUsuario"
		 	+ "   AND me.idMenu = :idMenu"
			+ "   AND sm1.idSubmenuNivel1 > 0"
			+ " ORDER BY me.numOrdenMenu")
	
 	public Iterable <MenusUsuario> obtenerSubMenuUsuSin0(@Param("idUsuario") Long idUsuario, @Param("idMenu") Integer idMenu);
	
	@Query("SELECT COUNT(mu) from MenusUsuario mu "
		 	+ "   WHERE mu.idMenu = :idMenu")
	
 	public Number numElementosMenuUsuario(@Param("idMenu") Integer idMenu);

}
