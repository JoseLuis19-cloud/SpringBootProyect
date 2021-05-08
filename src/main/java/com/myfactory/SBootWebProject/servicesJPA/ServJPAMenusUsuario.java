package com.myfactory.SBootWebProject.servicesJPA;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myfactory.SBootWebProject.model.MenusUsuario;

@Service
public interface ServJPAMenusUsuario {

	public List<MenusUsuario> obtenerMenuUsuario(Long idUsuario);
	
	public List<MenusUsuario> obtenerSubMenuUsuario(Long idUsuario, Integer idMenu);
	
	public List<MenusUsuario> obtenerMenuUsuSin0(Long idUsuario);
	
	public Iterable<MenusUsuario> obtenerSubMenuUsuSin0(Long idUsuario, Integer idMenu);
	
	public MenusUsuario insertarMenuUsuario(MenusUsuario menuUsuario);
	
	public void suprimirMenuUsuario(MenusUsuario menuUsuario);
	
	public void deleteMenuUsuario(MenusUsuario menuUsuario);
	
	public MenusUsuario insertarSubMenuUsuario(MenusUsuario menuUsuario);
	
	public void suprimirSubMenuUsuario(MenusUsuario menuUsuario);
	
	public Boolean existenElementosMenuUsuario (Integer idMenu) ;
	
	public Boolean existeElementoSubMenuUsuario (Integer idSubMenu) ;
	
//	public void eliminarElementosSubMenuUsuario (Integer idSubMenu) ;
	
	public void suprimirMenuUsuarioSQL (Integer idSubMenu, Integer idUsuario) ;	
}
