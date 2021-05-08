package com.myfactory.SBootWebProject.repository.menuUsuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.myfactory.SBootWebProject.model.MenusUsuario;

public interface CustomMenusUsuarioJPADao
{
 
/*	@Query("SELECT mu from MenusUsuario mu "
 		 	+ " JOIN mu.menu me"
			+ " JOIN mu.user us"
			+ " JOIN mu.subMenu1 sm1"
			+ " WHERE us.id = :idUsuario"
		//	+ "  AND me.idMenu > 0"
			+ "  AND sm1.idSubmenuNivel1 = 0"
			+ " ORDER BY me.numOrdenMenu") */
 	public List <MenusUsuario> obtenerMenuUsuario(@Param("idUsuario") Long idUsuario);

 	/*	@Query("SELECT mu from MenusUsuario mu "
 			+ "  JOIN mu.subMenu1 sm1"
 			+ "  JOIN mu.menu me"
			+ "  JOIN mu.user us"
			+ " WHERE us.id = :idUsuario"
			+ "   AND me.idMenu = :idMenu"
			+ "   AND sm1.idSubmenuNivel1 > 0"
			+ " ORDER BY me.numOrdenMenu")*/
 	public List <MenusUsuario> obtenerSubMenuUsuario(@Param("idUsuario") Long idUsuario, @Param("idMenu") Integer idMenu);
	
	/*@Query("SELECT mu from MenusUsuario mu "
 		 	+ "  JOIN mu.menu me"
			+ "  JOIN mu.user us"
			+ "  JOIN mu.subMenu1 sm1"
			+ " WHERE us.id = :idUsuario"
		 	+ "   AND me.idMenu > 0"
			+ "   AND sm1.idSubmenuNivel1 = 0"
			+ " ORDER BY me.numOrdenMenu")*/
 	public List <MenusUsuario> obtenerMenuUsuSin0(@Param("idUsuario") Long idUsuario);
	
/*	@Query("SELECT mu from MenusUsuario mu "
 			+ "  JOIN mu.subMenu1 sm1"
 		 	+ "  JOIN mu.menu me"
			+ "  JOIN mu.user us"
			+ " WHERE us.id = :idUsuario"
		 	+ "   AND me.idMenu = :idMenu"
			+ "   AND sm1.idSubmenuNivel1 > 0"
			+ " ORDER BY me.numOrdenMenu")*/
	
 	public List <MenusUsuario> obtenerSubMenuUsuSin0(@Param("idUsuario") Long idUsuario, @Param("idMenu") Integer idMenu);
	
 	/*	@Query("SELECT COUNT(mu) from MenusUsuario mu "
			+ "  JOIN mu.menu me"
		 	+ "  WHERE me.idMenu = :idMenu")*/
 	public Number numElementosMenuUsuario(@Param("idMenu") Integer idMenu);
	
	
 	/* @Query("SELECT COUNT(mu) from MenusUsuario mu "
			+ "  JOIN mu.subMenu1 sm1"
		 	+ "  WHERE sm1.idSubmenuNivel1 = :idSubMenu")*/
 	public Number numElementosSubMenuUsuario(@Param("idSubMenu") Integer idSubMenu);
	
// @Modifying
//	@Query(value = "DELETE FROM MENUS_USUARIO "
	// 		+ " WHERE MENUS_USUARIO.COD_NIVEL_1_FK = ?1"
	// 		+ " AND MENUS_USUARIO.ID_USUARIO_FK = ?2", nativeQuery = true)
	 public void eliminarElementosSubmenuSQL(Integer idMenu, Integer idUsuario);
	
}
