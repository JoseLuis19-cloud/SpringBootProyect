package com.myfactory.SBootWebProject.servicesJPA;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Menu;
import com.myfactory.SBootWebProject.model.SubMenuNivel1;

@Service
public interface ServJPAMenu {

	public Iterable<Menu> obtenerMenusAplicacion() ;
	
	public Iterable<Menu> obtenerSubmenuN1Aplicacion(Integer idMenu) ;
	
	public String updateElementoMenu(Menu menu) ;
	
	public Menu insertarElementoMenu(Menu menu);
	
	public Iterable<Menu> obtenerMenusAplicacionSin0();
	
	public Iterable<SubMenuNivel1> obtenerSubMenuAplicacionSin0(Integer idMenu);
	
	public SubMenuNivel1 insertarElementoSubMenu(SubMenuNivel1 subMenuNivel1);
	
	public Optional<Menu> findIdMenu(Integer idMenu);
	
	public void eliminarElementoMenu(Integer idMenu);
}
