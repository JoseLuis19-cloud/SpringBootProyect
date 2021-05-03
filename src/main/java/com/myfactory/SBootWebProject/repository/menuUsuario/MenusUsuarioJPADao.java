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
			+ "  JOIN mu.menu me"
		 	+ "  WHERE me.idMenu = :idMenu")
 	public Number numElementosMenuUsuario(@Param("idMenu") Integer idMenu);
	
	
	@Query("SELECT COUNT(mu) from MenusUsuario mu "
			+ "  JOIN mu.subMenu1 sm1"
		 	+ "  WHERE sm1.idSubmenuNivel1 = :idSubMenu")
 	public Number numElementosSubMenuUsuario(@Param("idSubMenu") Integer idSubMenu);
	
	//Delete los registro de menu usuario que tengan el idMenu y tengan submenu por eso preguntamos si es idSubmenuNivel1 > 0
//	@Query("DELETE from MenusUsuario mu"
//			+ "  JOIN mu.menu me"
//			+ "  JOIN mu.subMenu1 sm1"
//		 	+ "  WHERE me.idMenu = :idMenu"
//		 	+ "    AND sm1.idSubmenuNivel1 > 0")
 //	public void eliminarElementosSubmenu(@Param("idMenu") Integer idMenu);
	
	
	// DELETE posts
	// FROM posts
	// INNER JOIN projects ON projects.project_id = posts.project_id
	// WHERE projects.client_id = :client_id
	
	// @Query(value = "DELETE FROM MENUS_USUARIO "
	//			+ " + INNER JOIN MENU ON MENU.ID_MENU = MENU_USUARIO.COD_NIVEL_1_FK"
	//	 		+ " + WHERE MENUS_USUARIO.COD_NIVEL_1_FK = ?1", nativeQuery = true)
	
	 @Query(value = "DELETE FROM MENUS_USUARIO "
	 		+ " + WHERE MENUS_USUARIO.COD_NIVEL_1_FK = ?1"
	 		+ " + AND MENUS_USUARIO.ID_USUARIO_FK = ?2", nativeQuery = true)
	 public void eliminarElementosSubmenu(String emailAddress, Integer idUsuario);
}
