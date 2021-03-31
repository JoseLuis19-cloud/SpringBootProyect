package com.myfactory.SBootWebProject.controller;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.myfactory.SBootWebProject.beanForm.BeanMenuAplicacionWeb;
import com.myfactory.SBootWebProject.beanForm.BeanSubMenuAplicacionWeb;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
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

		BeanMenuAplicacionWeb elementoEdicionMenuApli  = new BeanMenuAplicacionWeb();
		modelo.addAttribute("elemenEditMenuApli", elementoEdicionMenuApli);

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
		
		BeanSubMenuAplicacionWeb elementoEdicionSubMenuApli  = new BeanSubMenuAplicacionWeb();
		elementoEdicionSubMenuApli.setIdMenu(new Integer(idMenu.trim()));
		modelo.addAttribute("elemenEditSubMenuApli", elementoEdicionSubMenuApli);
	    
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
		
		elemenMenu = serviciosJPAMenu.insertarElementoMenu(elemenMenu);
		
		Iterable <Menu> menuPrincipalAplicacion = serviciosJPAMenu.obtenerMenusAplicacionSin0();
		
		modelo.addAttribute("menuPrincipal", menuPrincipalAplicacion);
		
		modelo.addAttribute("elemenMenuNuevoWeb", beanMenuAplicacionWeb);
		
		if (elemenMenu == null) {
			modelo.addAttribute("errorAltaElemento", true);
		}
		 
	 	
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
	
	@RequestMapping(value =  "/updateelementosubmenu", method = RequestMethod.POST)
	public String modificarElementoSubMenu(Model modelo, @ModelAttribute("beanSubMenuAplicacionWeb") BeanSubMenuAplicacionWeb beanSubMenuAplicacionWeb) 
	{
		SubMenuNivel1 elemenSubMenu = new SubMenuNivel1();		
	
		Menu elemenMenu = new Menu();
		elemenMenu.setIdMenu(beanSubMenuAplicacionWeb.getIdSubMenuN1());
		elemenSubMenu.setMenu(elemenMenu) ;
		elemenSubMenu.setIdSubmenuNivel1(beanSubMenuAplicacionWeb.getIdSubMenuN1());
		elemenSubMenu.setNumOrdenMenu(beanSubMenuAplicacionWeb.getNumOrdenSubMenu() ) ;
		elemenSubMenu.setTextoSubMenuN1(beanSubMenuAplicacionWeb.getTextoMenuSubMenu()  );
		elemenSubMenu.setHrefAplicacionN1(beanSubMenuAplicacionWeb.getHrefAplicacionSubMenu());
		elemenSubMenu.setActivo(beanSubMenuAplicacionWeb.isIndActivoSubMenu() );
		
	//	SubMenuNivel1 elemenSubMenuNuevo = new SubMenuNivel1();		
	/* elemenSubMenuNuevo  = */ serviciosJPAMenu.insertarElementoSubMenu(elemenSubMenu);
		
	 // Carga el menu general
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionMenus/GestionMenus";
	}
			
	@RequestMapping(value = "/updateelementomenu", method = RequestMethod.POST)
	public String updateElementoMenu(Model modelo, @ModelAttribute("beanMenuAplicacionWeb") BeanMenuAplicacionWeb beanMenuAplicacionWeb)
		{ 
		Menu elementoMenu = new Menu();
		
		elementoMenu.setIdMenu(beanMenuAplicacionWeb.getIdMenu() ) ;
		elementoMenu.setNumOrdenMenu(beanMenuAplicacionWeb.getNumOrdenMenu() ) ;
		elementoMenu.setTextoMenu(beanMenuAplicacionWeb.getTextoMenu() ) ;
		elementoMenu.setHrefAplicacion(beanMenuAplicacionWeb.getHrefAplicacion() );
 
		serviciosJPAMenu.updateElementoMenu(elementoMenu);
		
		
	Iterable <Menu> menuPrincipalAplicacion = serviciosJPAMenu.obtenerMenusAplicacionSin0();
		
		modelo.addAttribute("menuPrincipal", menuPrincipalAplicacion);
		
	 // Instanciar elemento menu nuevo por si da de alta uno
	 	BeanMenuAplicacionWeb elemenMenuNuevo = new BeanMenuAplicacionWeb();
	 	
	  long count = StreamSupport.stream(menuPrincipalAplicacion.spliterator(), false).count();
	  
	  Long numRegMenuPrin = new Long(count);
	  
	 	elemenMenuNuevo.setNumOrdenMenu(numRegMenuPrin.intValue() + 1 );
	 	elemenMenuNuevo.setIndActivo(false);
	 
		modelo.addAttribute("elemenMenuNuevoWeb", elemenMenuNuevo);

		BeanMenuAplicacionWeb elementoEdicionMenuApli  = new BeanMenuAplicacionWeb();
		modelo.addAttribute("elemenEditMenuApli", elementoEdicionMenuApli);

	 // Carga el menu general
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionMenus/GestionMenus";
		}
	

	
	@RequestMapping(value = "/ventanamodal", method = RequestMethod.GET)
	public String popupventana(Model modelo) {
		
 
		return "GestionMenus/VentanaModal";
	}
	
}
