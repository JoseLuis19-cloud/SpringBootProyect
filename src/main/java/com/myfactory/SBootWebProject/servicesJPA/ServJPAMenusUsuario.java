package com.myfactory.SBootWebProject.servicesJPA;

import org.springframework.stereotype.Service;

import com.myfactory.SBootWebProject.model.MenusUsuario;

@Service
public interface ServJPAMenusUsuario {

	public Iterable<MenusUsuario> obtenerMenuUsuario(Long idUsuario);
	
	public Iterable<MenusUsuario> obtenerSubMenuUsuario(Long idUsuario, Integer idMenu);
	
	public Iterable<MenusUsuario> obtenerMenuUsuSin0(Long idUsuario);
	
	public Iterable<MenusUsuario> obtenerSubMenuUsuSin0(Long idUsuario, Integer idMenu);
	
	public MenusUsuario insertarMenuUsuario(MenusUsuario menuUsuario);
	
	public void suprimirMenuUsuario(MenusUsuario menuUsuario);
	
	public void deleteMenuUsuario(MenusUsuario menuUsuario);
	
}
