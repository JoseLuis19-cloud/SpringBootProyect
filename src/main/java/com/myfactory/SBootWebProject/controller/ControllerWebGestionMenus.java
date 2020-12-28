package com.myfactory.SBootWebProject.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfactory.SBootWebProject.beanForm.BeanClienteWeb;
import com.myfactory.SBootWebProject.beanForm.BeanMenuAplicacionWeb;
import com.myfactory.SBootWebProject.beanForm.BeanSubMenuAplicacionWeb;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.Menu;
import com.myfactory.SBootWebProject.model.SubMenuNivel1;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAMenu;

@Controller
@RequestMapping("/gestionmenus")
public class ControllerWebGestionMenus {
	
	@Autowired
	ServJPAMenu serviciosJPAMenu;
	
	@Autowired
	BeanUsuarioSession beanUsuarioSession;
 
	@GetMapping("/obtenermenuprincipal")
	public String obtenerMenuPrincipal(Model modelo) {
		// Carga opciones del menu principal
		Iterable <Menu> menuPrincipalAplicacion = serviciosJPAMenu.obtenerMenusAplicacionSin0();
		
		modelo.addAttribute("menuPrincipal", menuPrincipalAplicacion);
		
	 // Instanciar elemento menu nuevo por si da de alta uno
	 	BeanMenuAplicacionWeb elemenMenuNuevo = new BeanMenuAplicacionWeb();
	 	
	  long count = StreamSupport.stream(menuPrincipalAplicacion.spliterator(), false).count();
	  
	  Long numRegMenuPrin = new Long(count);
	  
	 	elemenMenuNuevo.setNumOrdenMenu(numRegMenuPrin.intValue() + 1 );
	 	elemenMenuNuevo.setIndActivo(false);
	 
		modelo.addAttribute("elemenMenuNuevoWeb", elemenMenuNuevo);
		
	 // Carga el menu general
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionMenus/GestionMenus";
	}
	
	@RequestMapping(value = "/obtenersubmenu", method = RequestMethod.GET)
	public String obtenerSubMenu(Model modelo, @ModelAttribute(value="field") String idMenu, @ModelAttribute(value="nomElemMenu") String nomEleMenu)
	{
		Boolean noTieneSubmenus;
		  
		// Set<SubMenuNivel1> subMenuN1 = null;
		Iterable <SubMenuNivel1> subMenuNivel1 = serviciosJPAMenu.obtenerSubMenuAplicacionSin0(new Integer(idMenu));
	
		Iterator<SubMenuNivel1> menu = subMenuNivel1.iterator();
	  //  while(menu.hasNext()){
	  //  	subMenuN1 = menu.next() ;
	  //  }
	    modelo.addAttribute("subMenuNivel1", subMenuNivel1);
	   
	    
	    modelo.addAttribute("elemenMenuPrincipal", nomEleMenu);

	    // Instanciar elemento submenu nuevo por si da de alta uno
	    BeanSubMenuAplicacionWeb elemenSubMenuNuevo = new BeanSubMenuAplicacionWeb();
	    
	    long count = StreamSupport.stream(subMenuNivel1.spliterator(), false).count();
		  
		  Long numRegSubMenu  = new Long(count);
		  
		  if (numRegSubMenu.longValue()  ==0L) {
			  noTieneSubmenus = true;
		  }
		  else
		  {
			  noTieneSubmenus = false;
		  }
		  
		  modelo.addAttribute("noTieneSubmenus", noTieneSubmenus);
		  modelo.addAttribute("errorAltaElemento", false);
		  
		  
		elemenSubMenuNuevo.setNumOrdenSubMenu(numRegSubMenu.intValue() + 1 );
		elemenSubMenuNuevo.setIndActivoSubMenu(false);
		
		modelo.addAttribute("elemenSubMenuNuevoWeb", elemenSubMenuNuevo);
	    
	    // Carga el menu general
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		
		return "GestionMenus/GestionSubmenus";
	}
	
	@Deprecated
	@RequestMapping(value = "/obtenersubmenunivelajax/{idmenu}", method = RequestMethod.GET)
	public String showGuestList(Model model, @PathVariable("idmenu") Integer idMenu) {
		
		Set<SubMenuNivel1> subMenuN1 = null;
		Iterable <Menu> subMenuNivel1 = serviciosJPAMenu.obtenerSubmenuN1Aplicacion(idMenu);
	
		Iterator<Menu> menu = subMenuNivel1.iterator();
	    while(menu.hasNext()){
	    	subMenuN1 = menu.next().getSubMenuNivel1();
	    }
	    
		model.addAttribute("subMenuNivel1", subMenuN1);
		return "GestionMenus/fragments/ElementosSubmenuApliciacion :: eleSubmenuApliciacion";
	}
	
	@RequestMapping(value =  "/insertelementomenu", method = RequestMethod.POST)
	public String insertElementoMenu(Model modelo, @ModelAttribute("beanMenuAplicacionWeb") BeanMenuAplicacionWeb beanMenuAplicacionWeb)
	{
		Menu elemenMenu = new Menu();		
	
		elemenMenu.setNumOrdenMenu(beanMenuAplicacionWeb.getNumOrdenMenu()) ;
		elemenMenu.setTextoMenu(beanMenuAplicacionWeb.getTextoMenu() );
		elemenMenu.setHrefAplicacion(beanMenuAplicacionWeb.getHrefAplicacion());
		elemenMenu.setActivo(beanMenuAplicacionWeb.isIndActivo());
		
	//	Menu elemenMenuAlta  = serviciosJPAMenu.insertarElementoMenu(elemenMenu);
		
		Iterable <Menu> menuPrincipalAplicacion = serviciosJPAMenu.obtenerMenusAplicacionSin0();
		
		modelo.addAttribute("menuPrincipal", menuPrincipalAplicacion);
		
		modelo.addAttribute("elemenMenuNuevoWeb", beanMenuAplicacionWeb);
		
		Boolean errorAltaElemento  = true;
		modelo.addAttribute("errorAltaElemento", errorAltaElemento);
	 	
	 // Carga el menu general
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		return "GestionMenus/GestionMenus";
	}
	
	@RequestMapping(value =  "/insertelementosubmenu", method = RequestMethod.POST)
	public String insertElementoSubMenu(Model modelo, @ModelAttribute("beanSubMenuAplicacionWeb") BeanSubMenuAplicacionWeb beanSubMenuAplicacionWeb) 
	{
		SubMenuNivel1 elemenSubMenu = new SubMenuNivel1();		
	
		Menu elemenMenu = new Menu();
		elemenMenu.setIdMenu(beanSubMenuAplicacionWeb.getIdSubMenuN1());
		elemenSubMenu.setMenu(elemenMenu) ;
		elemenSubMenu.setNumOrdenMenu(beanSubMenuAplicacionWeb.getNumOrdenSubMenu() ) ;
		elemenSubMenu.setTextoSubMenuN1(beanSubMenuAplicacionWeb.getTextoMenuSubMenu()  );
		elemenSubMenu.setHrefAplicacionN1(beanSubMenuAplicacionWeb.getHrefAplicacionSubMenu());
		elemenSubMenu.setActivo(beanSubMenuAplicacionWeb.isIndActivoSubMenu() );
		
		SubMenuNivel1 elemenSubMenuNuevo = new SubMenuNivel1();		
		 elemenSubMenuNuevo  = serviciosJPAMenu.insertarElementoSubMenu(elemenSubMenu);
		
	 	
	 // Carga el menu general
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionMenus/GestionMenus";
	}
	
//	@GetMapping("/updateelementomenu")
	// public String updateElementoMenu(Model modelo, @RequestParam(value = "idMenu", required = true) Integer idMenu, Errors errors) 
//	  public String updateElementoMenu(Model modelo, @RequestParam(value = "objMenu", required = true) Menu menu ) 
	// public String updateElementoMenu(@ModelAttribute Menu menu)
//	{ 
//		serviciosJPAMenu.updateElementoMenu(menu);
	//	return "GestionMenus/fragments/ListaSubmenu1  :: LSubmenu1";
//	}
	

	
	@RequestMapping(value = "/ventanamodal", method = RequestMethod.GET)
	public String popupventana(Model modelo) {
		
 
		return "GestionMenus/VentanaModal";
	}
	
}
