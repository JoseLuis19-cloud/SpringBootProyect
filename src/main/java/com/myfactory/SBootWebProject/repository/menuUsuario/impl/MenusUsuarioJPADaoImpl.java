package com.myfactory.SBootWebProject.repository.menuUsuario.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.myfactory.SBootWebProject.model.MenusUsuario;
import com.myfactory.SBootWebProject.repository.menuUsuario.CustomMenusUsuarioJPADao;
import com.myfactory.SBootWebProject.repository.menuUsuario.MenusUsuarioJPADao;

public class MenusUsuarioJPADaoImpl implements CustomMenusUsuarioJPADao 
{
	@PersistenceContext
    private EntityManager em;
	
 	@Override
	/* @Query("SELECT mu from MenusUsuario mu "
 		 	+ " JOIN mu.menu me"
			+ " JOIN mu.user us"
			+ " JOIN mu.subMenu1 sm1"
			+ " WHERE us.id = :idUsuario"
		//	+ "  AND me.idMenu > 0"
			+ "  AND sm1.idSubmenuNivel1 = 0"
			+ " ORDER BY me.numOrdenMenu") */
 	public List <MenusUsuario> obtenerMenuUsuario(Long idUsuario)
 	{
		TypedQuery<MenusUsuario> query = em.createQuery(
				  "SELECT mu from MenusUsuario mu "
				  + " JOIN mu.menu me " 
				  + " JOIN mu.user us"
				  + " JOIN mu.subMenu1 sm1"
				  + " WHERE us.id = :idUsuario"
				  + "  AND sm1.idSubmenuNivel1 = 0" 
				  + " ORDER BY me.numOrdenMenu", MenusUsuario.class);
		return query.setParameter("idUsuario", idUsuario).getResultList();
 	}

	  @Override
	/* @Query("SELECT mu from MenusUsuario mu "
 			+ "  JOIN mu.subMenu1 sm1"
 			+ "  JOIN mu.menu me"
			+ "  JOIN mu.user us"
			+ " WHERE us.id = :idUsuario"
			+ "   AND me.idMenu = :idMenu"
			+ "   AND sm1.idSubmenuNivel1 > 0"
			+ " ORDER BY me.numOrdenMenu") */
 	public List<MenusUsuario> obtenerSubMenuUsuario(@Param("idUsuario") Long idUsuario, @Param("idMenu") Integer idMenu)
 	{ 
 		TypedQuery<MenusUsuario> query = em.createQuery(
 				   "SELECT mu from MenusUsuario mu"
				+  " JOIN mu.subMenu1 sm"
				+  " JOIN mu.menu me"
				+  " JOIN mu.user us"
				+  " WHERE us.id = :idUsuario"
				+  " AND me.idMenu = :idMenu" 
			 	+  " AND sm.idSubmenuNivel1 > 0"
				+  " ORDER BY me.numOrdenMenu", MenusUsuario.class);
		return query.setParameter("idUsuario", idUsuario).setParameter("idMenu", idMenu).getResultList();	
 	}
	
 	@Override
/*	@Query("SELECT mu from MenusUsuario mu "
 		 	+ "  JOIN mu.menu me"
			+ "  JOIN mu.user us"
			+ "  JOIN mu.subMenu1 sm1"
			+ " WHERE us.id = :idUsuario"
		 	+ "   AND me.idMenu > 0"
			+ "   AND sm1.idSubmenuNivel1 = 0"
			+ " ORDER BY me.numOrdenMenu")*/
 	public List <MenusUsuario> obtenerMenuUsuSin0(@Param("idUsuario") Long idUsuario){
 		TypedQuery<MenusUsuario> query = em.createQuery( 
 				"SELECT mu from MenusUsuario mu "
	 		 	+ "  JOIN mu.menu me"
				+ "  JOIN mu.user us"
				+ "  JOIN mu.subMenu1 sm1"
				+ " WHERE us.id = :idUsuario"
			 	+ "   AND me.idMenu > 0"
				+ "   AND sm1.idSubmenuNivel1 = 0"
				+ " ORDER BY me.numOrdenMenu" , MenusUsuario.class);
 		return query.setParameter("idUsuario", idUsuario).getResultList();	
	}
	
	@Override
/*	@Query("SELECT mu from MenusUsuario mu "
 			+ "  JOIN mu.subMenu1 sm1"
 		 	+ "  JOIN mu.menu me"
			+ "  JOIN mu.user us"
			+ " WHERE us.id = :idUsuario"
		 	+ "   AND me.idMenu = :idMenu"
			+ "   AND sm1.idSubmenuNivel1 > 0"
			+ " ORDER BY me.numOrdenMenu") */
 	public List <MenusUsuario> obtenerSubMenuUsuSin0(@Param("idUsuario") Long idUsuario, @Param("idMenu") Integer idMenu){
		TypedQuery<MenusUsuario> query = em.createQuery( 
				"SELECT mu from MenusUsuario mu "
	 			+ "  JOIN mu.subMenu1 sm1"
	 		 	+ "  JOIN mu.menu me"
				+ "  JOIN mu.user us"
				+ " WHERE us.id = :idUsuario"
			 	+ "   AND me.idMenu = :idMenu"
				+ "   AND sm1.idSubmenuNivel1 > 0"
				+ " ORDER BY me.numOrdenMenu", MenusUsuario.class);
		return query.setParameter("idUsuario", idUsuario).setParameter("idMenu", idMenu ) .getResultList();	
	}
	
	@Override
/*	@Query("SELECT COUNT(mu) from MenusUsuario mu "
			+ "  JOIN mu.menu me"
		 	+ "  WHERE me.idMenu = :idMenu") */
 	public Long numElementosMenuUsuario(@Param("idMenu") Integer idMenu) {
		
		TypedQuery<Long> query = em.createQuery(
				  "SELECT COUNT(mu) from MenusUsuario mu "
				+ "  JOIN mu.menu me"
			 	+ "  WHERE me.idMenu = :idMenu", Long.class);
		
		return query.setParameter("idMenu", idMenu ).getSingleResult();	
	}
	
 	@Override
/*	@Query("SELECT COUNT(mu) from MenusUsuario mu "
			+ "  JOIN mu.subMenu1 sm1"
		 	+ "  WHERE sm1.idSubmenuNivel1 = :idSubMenu")*/
 	public Long numElementosSubMenuUsuario(@Param("idSubMenu") Integer idSubMenu) {
		
		TypedQuery<Long> query = em.createQuery(
				  "SELECT COUNT(mu) from MenusUsuario mu" 
				 + " JOIN mu.subMenu1 sm1" 
				 + " WHERE sm1.idSubmenuNivel1 = :idSubMenu", Long.class);
		return query.setParameter("idSubMenu", idSubMenu).getSingleResult();	
	}
	
	//Delete los registro de menu usuario que tengan el idMenu y tengan submenu por eso preguntamos si es idSubmenuNivel1 > 0
//	@Query("DELETE from MenusUsuario mu"
//			+ "  JOIN mu.menu me"
//			+ "  JOIN mu.subMenu1 sm1"
//		 	+ "  WHERE me.idMenu = :idMenu"
//		 	+ "    AND sm1.idSubmenuNivel1 > 0")
 //	public void eliminarElementosSubmenu(@Param("idMenu") Integer idMenu);
	
	@Override
	@Transactional
	public void eliminarElementosSubmenuSQL(Integer idMenu, Integer idUsuario)
	 {
	 String updateQuery = "DELETE FROM MENUS_USUARIO WHERE MENUS_USUARIO.COD_NIVEL_1_FK = :idMenu AND MENUS_USUARIO.ID_USUARIO_FK = :idUsuario";
	 
     em.createNativeQuery(updateQuery)
             .setParameter("idMenu", idMenu)
             .setParameter("idUsuario", idUsuario)
             .executeUpdate();
 
    // transaction.commit();
	/* Session session = entityManager.unwrap(Session.class);
	      Transaction txn = session.beginTransaction();
	      Query updateQuery = session.createQuery("DELETE FROM MENUS_USUARIO WHERE MENUS_USUARIO.COD_NIVEL_1_FK = ?1 AND MENUS_USUARIO.ID_USUARIO_FK = ?2");
	          updateQuery.setParameter(1, idMenu);
	      updateQuery.setParameter(2, idUsuario);
	      updateQuery.executeUpdate();
	      txn.commit();*/
	 }

}
