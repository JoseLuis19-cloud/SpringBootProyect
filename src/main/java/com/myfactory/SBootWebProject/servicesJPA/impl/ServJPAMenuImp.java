package com.myfactory.SBootWebProject.servicesJPA.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Menu;
import com.myfactory.SBootWebProject.model.SubMenuNivel1;
import com.myfactory.SBootWebProject.repository.menu.MenuJPADao;
import com.myfactory.SBootWebProject.repository.menu.SubMenuN1JPADao;
import com.myfactory.SBootWebProject.repository.menu.SubMenuN1NuevoJPADao;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAMenu;

@Service
public class ServJPAMenuImp implements ServJPAMenu {

	@Autowired
	MenuJPADao menuJPARepository;
	
	@Autowired
	SubMenuN1JPADao subMenuN1JPARepository;
	
	@Autowired
	SubMenuN1NuevoJPADao subMenuN1NuevoJPARepository;
	
	@Override
	public Iterable<Menu> obtenerMenusAplicacion() {
		return menuJPARepository.obtenerMenuAplicacion();
	}
	
	@Override
	public Iterable<Menu> obtenerSubmenuN1Aplicacion(Integer idMenu) {
		return subMenuN1JPARepository.obtenerSubMenu1Aplicacion(idMenu);
	}
	
	@Override
	public Iterable<SubMenuNivel1> obtenerSubMenuAplicacionSin0(Integer idMenu) {
		return subMenuN1NuevoJPARepository.obtenerSubMenu1AplicacionSin0(idMenu);
	}
	
	@Override
	public Iterable<Menu> obtenerMenusAplicacionSin0() {
		return menuJPARepository.obtenerMenuAplicacionSin0();
	}
	
	@Override
	public String updateElementoMenu(Menu menu)
	{	
	menuJPARepository.save(menu);
	return "";	
	};
	
	@Override
	public Menu insertarElementoMenu(Menu menu)
	{	
	return menuJPARepository.save(menu);
	};
	
	@Override
	public SubMenuNivel1 insertarElementoSubMenu(SubMenuNivel1 subMenuNivel1)
	{	
	return subMenuN1NuevoJPARepository.save(subMenuNivel1);
	};
	
	@Override
	public Optional<Menu> findIdMenu(Integer idMenu) {
		return menuJPARepository.findById(idMenu);
	}
}
