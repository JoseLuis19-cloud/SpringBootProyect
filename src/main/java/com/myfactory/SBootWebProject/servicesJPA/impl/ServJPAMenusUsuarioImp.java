package com.myfactory.SBootWebProject.servicesJPA.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfactory.SBootWebProject.model.MenusUsuario;
import com.myfactory.SBootWebProject.repository.menuUsuario.MenusUsuarioJPADao;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAMenusUsuario;

@Service
public class ServJPAMenusUsuarioImp implements ServJPAMenusUsuario {

	@Autowired
	MenusUsuarioJPADao menusUsuJPARepository;
	
	@Override
	public List<MenusUsuario> obtenerMenuUsuario(Long idUsuario) {
		return menusUsuJPARepository.obtenerMenuUsuario(idUsuario) ;
	}
	
	@Override
	public List<MenusUsuario> obtenerSubMenuUsuario(Long idUsuario, Integer idMenu) {
		return menusUsuJPARepository.obtenerSubMenuUsuario(idUsuario, idMenu) ;
	}
	
	@Override
	public List<MenusUsuario> obtenerMenuUsuSin0(Long idUsuario) {
		return menusUsuJPARepository.obtenerMenuUsuSin0(idUsuario) ;
	}
	
	@Override
	public Iterable<MenusUsuario> obtenerSubMenuUsuSin0(Long idUsuario, Integer idMenu) {
		return menusUsuJPARepository.obtenerSubMenuUsuSin0(idUsuario, idMenu) ;
	}
	
	@Override
	public MenusUsuario insertarMenuUsuario(MenusUsuario menuUsuario) {
		return menusUsuJPARepository.save(menuUsuario) ;
	}
	
	@Override
	public void suprimirMenuUsuario(MenusUsuario menuUsuario) {
		 menusUsuJPARepository.delete(menuUsuario);
	}
	
	@Override
	public void deleteMenuUsuario(MenusUsuario menuUsuario) {
		  menusUsuJPARepository.delete(menuUsuario);
	}

	@Override
	public MenusUsuario insertarSubMenuUsuario(MenusUsuario menuUsuario) {
		return menusUsuJPARepository.save(menuUsuario) ;
	};
	
	@Override
	public void suprimirSubMenuUsuario(MenusUsuario menuUsuario) {
		 menusUsuJPARepository.delete(menuUsuario);
	};
	
	@Override
	public Boolean existenElementosMenuUsuario (Integer idMenu) {
 
		if (menusUsuJPARepository.numElementosMenuUsuario(idMenu).intValue() > 0 )
		   {
		    return true;
		   }
		 else
		  {
			return  false;
		  }
	};
	
	@Override
	public Boolean existeElementoSubMenuUsuario(Integer idSubMenu) {
 
		if (menusUsuJPARepository.numElementosSubMenuUsuario(idSubMenu).intValue() > 0 )
		   {
		    return true;
		   }
		 else
		  {
			return  false;
		  }
	};

	public void suprimirMenuUsuarioSQL(Integer idMenu, Integer idUsuario) {
		menusUsuJPARepository.eliminarElementosSubmenuSQL(idMenu, idUsuario);
	};

	
}
