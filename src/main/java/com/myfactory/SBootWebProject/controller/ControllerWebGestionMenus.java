package com.myfactory.SBootWebProject.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myfactory.SBootWebProject.beanForm.BeanErrorValidacion;
import com.myfactory.SBootWebProject.beanForm.BeanFormMenu;
import com.myfactory.SBootWebProject.beanForm.BeanFormSubMenu;
import com.myfactory.SBootWebProject.beanForm.BeanMenuAplicacionWeb;
import com.myfactory.SBootWebProject.beanForm.BeanSubMenuAplicacionWeb;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.model.Menu;

import com.myfactory.SBootWebProject.model.SubMenuNivel1;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAMenu;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAMenusUsuario;

@Controller
@RequestMapping("/gestionmenus")
public class ControllerWebGestionMenus {
	
	@Autowired
	ServJPAMenu serviciosJPAMenu;
	
	@Autowired
	ServJPAMenusUsuario  servJPAMenusUsuarioImp;
	
	@Autowired
	BeanUsuarioSession beanUsuarioSession;
 
	@GetMapping("/obtenermenuprincipal")
	public String obtenerMenuPrincipal(Model modelo) {
  // Carga opciones del menu principal
	  Iterable <Menu> menuPrincipalAplicacion = serviciosJPAMenu.obtenerMenusAplicacionSin0Todos(); 
	  
	  BeanFormMenu beanFormMenu = new BeanFormMenu();
	  
	  List<Menu> listMenu = StreamSupport
			  .stream(menuPrincipalAplicacion.spliterator(), false)
			  .collect(Collectors.toList());

	  ArrayList<Menu> arrListMenu = new ArrayList<Menu>(listMenu);
	  beanFormMenu.setBeanMenuAplicacionWeb(arrListMenu); 
	  
	  modelo.addAttribute("menuPrincipal", beanFormMenu);

   // Instanciar elemento menu nuevo por si da de alta uno
	  BeanMenuAplicacionWeb elemenMenuNuevo = new BeanMenuAplicacionWeb();
	 	
	  long count = StreamSupport.stream(menuPrincipalAplicacion.spliterator(), false).count();
	  
	
	  Long numRegMenuPrin = new Long(count);
	  elemenMenuNuevo.setNumOrdenMenu(numRegMenuPrin.intValue() + 1 );
	  modelo.addAttribute("elemenMenuNuevoWeb", elemenMenuNuevo); 

	  BeanMenuAplicacionWeb elementoEdicionMenuApli  = new BeanMenuAplicacionWeb();
	  elementoEdicionMenuApli.setIndActivo(false);
	  modelo.addAttribute("elemenEditMenuApli", elementoEdicionMenuApli);

  // Carga el menu general
	  modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	  return "GestionMenus/GestionMenus";
	}
	
	@RequestMapping(value = "/obtenersubmenu", method = RequestMethod.GET)
	public String obtenerSubMenu(Model modelo, @ModelAttribute(value="field") String idMenu, @ModelAttribute(value="nomElemMenu") String nomEleMenu)
	{
	Boolean noTieneSubmenus;
		  
	Iterable <SubMenuNivel1> subMenuNivel1 = serviciosJPAMenu.obtenerSubMenuAplicacionSin0(new Integer(idMenu));
	// Iterator<SubMenuNivel1> menu = subMenuNivel1.iterator();
		
		BeanFormSubMenu beanFormSubMenu = new BeanFormSubMenu();
			  
		List<SubMenuNivel1> listSubMenu = StreamSupport
				  .stream(subMenuNivel1.spliterator(), false)
				  .collect(Collectors.toList());

		ArrayList<SubMenuNivel1> arrListMenu = new ArrayList<SubMenuNivel1>(listSubMenu);
		beanFormSubMenu.setBeanSubMenuAplicacionWeb(arrListMenu); 
		
		modelo.addAttribute("subMenu", beanFormSubMenu);
		 
	  //  modelo.addAttribute("subMenuNivel1", subMenuNivel1);
		
		System.out.println(subMenuNivel1.iterator().next().getMenu().getIdMenu());
	    
	    modelo.addAttribute("elemenMenuPrincipal", nomEleMenu);

	 // Instanciar elemento submenu nuevo por si da de alta uno
	    BeanSubMenuAplicacionWeb elemenSubMenuNuevo = new BeanSubMenuAplicacionWeb();
	    
	    long count = StreamSupport.stream(subMenuNivel1.spliterator(), false).count();
		  
		  Long numRegSubMenu = new Long(count);
		  
		  if (numRegSubMenu.longValue()  ==0L) {
			  noTieneSubmenus = true;
		  }
		  else
		  {
			noTieneSubmenus = false;
		  }
		  
		modelo.addAttribute("noTieneSubmenus", noTieneSubmenus);
		modelo.addAttribute("errorAltaElemento", false);  
		  
		
		elemenSubMenuNuevo.setIdMenu(new Integer(idMenu));
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
	
	@RequestMapping(value = "/insertelementomenu", method = RequestMethod.POST)
	public String insertElementoMenu(Model modelo, @ModelAttribute("beanMenuAplicacionWeb") BeanMenuAplicacionWeb beanMenuAplicacionWeb)
	{
		Menu elemenMenu = new Menu();		
	
		elemenMenu.setNumOrdenMenu(beanMenuAplicacionWeb.getNumOrdenMenu()) ;
		elemenMenu.setTextoMenu(beanMenuAplicacionWeb.getTextoMenu() );
		elemenMenu.setHrefAplicacion(beanMenuAplicacionWeb.getHrefAplicacion());
	 	elemenMenu.setActivo(beanMenuAplicacionWeb.getIndActivo());
		
		elemenMenu = serviciosJPAMenu.insertarElementoMenu(elemenMenu);
		
		
		Iterable <Menu> menuPrincipalAplicacion = serviciosJPAMenu.obtenerMenusAplicacionSin0();
		
		BeanFormMenu beanFormMenu = new BeanFormMenu();
		  
		List<Menu> listMenu = StreamSupport
				  .stream(menuPrincipalAplicacion.spliterator(), false)
				  .collect(Collectors.toList());

		ArrayList<Menu> arrListMenu = new ArrayList<Menu>(listMenu);
		beanFormMenu.setBeanMenuAplicacionWeb(arrListMenu); 
		  
		modelo.addAttribute("menuPrincipal", beanFormMenu);
		
		modelo.addAttribute("elemenMenuNuevoWeb", beanMenuAplicacionWeb);
		
		if (elemenMenu == null) {
			modelo.addAttribute("errorAltaElemento", true);
		}

	 // Carga el menu general
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	//	retu rn "GestionMenus/GestionMenus";
		return "redirect:/gestionmenus/obtenermenuprincipal";
	}
	
	@RequestMapping(value = "/insertelementosubmenu", method = RequestMethod.POST)
	public String insertElementoSubMenu(@ModelAttribute  BeanSubMenuAplicacionWeb elemenSubMenuNuevoWeb, Model modelo ) 
	{
		SubMenuNivel1 elemenSubMenu = new SubMenuNivel1();	
	
		Menu elemenMenu = new Menu();
	 	elemenMenu.setIdMenu(elemenSubMenuNuevoWeb.getIdMenu());
		elemenSubMenu.setMenu(elemenMenu) ;
		elemenSubMenu.setNumOrdenMenu(elemenSubMenuNuevoWeb.getNumOrdenSubMenu()  ) ;
		elemenSubMenu.setTextoSubMenuN1(elemenSubMenuNuevoWeb.getTextoMenuSubMenu()  );
		elemenSubMenu.setHrefAplicacionN1(elemenSubMenuNuevoWeb.getHrefAplicacionSubMenu());
		elemenSubMenu.setActivo(elemenSubMenuNuevoWeb.isIndActivoSubMenu() );
		
	//	SubMenuNivel1 elemenSubMenuNuevo = new SubMenuNivel1();		
		serviciosJPAMenu.insertarElementoSubMenu(elemenSubMenu);
		
	 // Carga el menu general
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "redirect:/gestionmenus/obtenersubmenu";
	}
	
	@RequestMapping(value =  "/updateelementosubmenu", method = RequestMethod.POST)
	public String modificarElementoSubMenu(Model modelo, @ModelAttribute("beanSubMenuAplicacionWeb") BeanSubMenuAplicacionWeb beanSubMenuAplicacionWeb) 
	{
		SubMenuNivel1 elemenSubMenu = new SubMenuNivel1();	
		Boolean noTieneSubmenus;
	
		Menu elemenMenu = new Menu();
		elemenMenu.setIdMenu(beanSubMenuAplicacionWeb.getIdSubMenuN1());
		elemenSubMenu.setMenu(elemenMenu) ;
		elemenSubMenu.setIdSubmenuNivel1(beanSubMenuAplicacionWeb.getIdSubMenuN1());
		elemenSubMenu.setNumOrdenMenu(beanSubMenuAplicacionWeb.getNumOrdenSubMenu() ) ;
		elemenSubMenu.setTextoSubMenuN1(beanSubMenuAplicacionWeb.getTextoMenuSubMenu()  );
		elemenSubMenu.setHrefAplicacionN1(beanSubMenuAplicacionWeb.getHrefAplicacionSubMenu());
		elemenSubMenu.setActivo(beanSubMenuAplicacionWeb.isIndActivoSubMenu() );
	
		serviciosJPAMenu.insertarElementoSubMenu(elemenSubMenu);
		
		Iterable <SubMenuNivel1> subMenuNivel1 = serviciosJPAMenu.obtenerSubMenuAplicacionSin0(new Integer(beanSubMenuAplicacionWeb.getIdMenu()));
		BeanFormSubMenu beanFormSubMenu = new BeanFormSubMenu();
		  
		List<SubMenuNivel1> listSubMenu = StreamSupport
				  .stream(subMenuNivel1.spliterator(), false)
				  .collect(Collectors.toList());

		ArrayList<SubMenuNivel1> arrListMenu = new ArrayList<SubMenuNivel1>(listSubMenu);
		beanFormSubMenu.setBeanSubMenuAplicacionWeb(arrListMenu); 
		
		modelo.addAttribute("subMenu", beanFormSubMenu);
		
	 // Instanciar elemento submenu nuevo por si da de alta uno
	    BeanSubMenuAplicacionWeb elemenSubMenuNuevo = new BeanSubMenuAplicacionWeb();
	    
	    long count = StreamSupport.stream(subMenuNivel1.spliterator(), false).count();
		  
		  Long numRegSubMenu = new Long(count);
		  
		  if (numRegSubMenu.longValue()  ==0L) {
			  noTieneSubmenus = true;
		  }
		  else
		  {
			noTieneSubmenus = false;
		  }
		  
		modelo.addAttribute("noTieneSubmenus", noTieneSubmenus);
		modelo.addAttribute("errorAltaElemento", false);  
		  
		elemenSubMenuNuevo.setIdMenu(new Integer(beanSubMenuAplicacionWeb.getIdMenu()));
		elemenSubMenuNuevo.setNumOrdenSubMenu(numRegSubMenu.intValue() + 1 );
		elemenSubMenuNuevo.setIndActivoSubMenu(false);
		
		modelo.addAttribute("elemenSubMenuNuevoWeb", elemenSubMenuNuevo);
		
		BeanSubMenuAplicacionWeb elementoEdicionSubMenuApli  = new BeanSubMenuAplicacionWeb();
		elementoEdicionSubMenuApli.setIdMenu(beanSubMenuAplicacionWeb.getIdMenu());
		modelo.addAttribute("elemenEditSubMenuApli", elementoEdicionSubMenuApli);
		 
		// Carga el menu general
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionMenus/GestionSubmenus";
	}
			
	@RequestMapping(value = "/updateelementomenu", method = RequestMethod.POST)
	public String updateElementoMenu(Model modelo, @ModelAttribute("elemenEditMenuApli") BeanMenuAplicacionWeb beanMenuAplicacionWeb)
	  { 
		
		Menu elementoMenu = new Menu();
		
		elementoMenu.setIdMenu(beanMenuAplicacionWeb.getIdMenu() ) ;
		elementoMenu.setNumOrdenMenu(beanMenuAplicacionWeb.getNumOrdenMenu() ) ;
		elementoMenu.setTextoMenu(beanMenuAplicacionWeb.getTextoMenu() ) ;
		elementoMenu.setHrefAplicacion(beanMenuAplicacionWeb.getHrefAplicacion() );
	 	elementoMenu.setActivo(beanMenuAplicacionWeb.getIndActivo());
 
		serviciosJPAMenu.updateElementoMenu(elementoMenu);
		
		Iterable <Menu> menuPrincipalAplicacion = serviciosJPAMenu.obtenerMenusAplicacionSin0Todos();
	 
		BeanFormMenu beanFormMenu = new BeanFormMenu();
		  
		 List<Menu> listMenu = StreamSupport
				  .stream(menuPrincipalAplicacion.spliterator(), false)
				  .collect(Collectors.toList());

		 ArrayList<Menu> arrListMenu = new ArrayList<Menu>(listMenu);
		 beanFormMenu.setBeanMenuAplicacionWeb(arrListMenu); 
		  
		 modelo.addAttribute("menuPrincipal", beanFormMenu);

	 // Instanciar elemento menu nuevo por si da de alta uno
	 	BeanMenuAplicacionWeb elemenMenuNuevo = new BeanMenuAplicacionWeb();
	 	long count = StreamSupport.stream(menuPrincipalAplicacion.spliterator(), false).count();
	  	Long numRegMenuPrin = new Long(count);
	 	elemenMenuNuevo.setNumOrdenMenu(numRegMenuPrin.intValue() + 1 );
	 	elemenMenuNuevo.setIndActivo(false);
	 
		modelo.addAttribute("elemenMenuNuevoWeb", elemenMenuNuevo);
		BeanMenuAplicacionWeb elementoEdicionMenuApli = new BeanMenuAplicacionWeb(new Integer(0), new Integer(0), "", "", false);
		modelo.addAttribute("elemenEditMenuApli", elementoEdicionMenuApli);

	 // Carga el menu general
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionMenus/GestionMenus";
	  }
	
	@RequestMapping(value = "/ventanamodal", method = RequestMethod.GET)
	public String popupventana(Model modelo) {
		// Comprobar que  no esta asignado a ningún menu de usuario.
 
		return "GestionMenus/VentanaModal";
	}
	
	@RequestMapping(value = "/eliminarmenu", method = RequestMethod.POST)
	public String bajaMenu(Model modelo, @RequestParam(value = "idMenu", required = true) String idMenu)  {
		BeanErrorValidacion datosErrorValidacion = new BeanErrorValidacion(new Integer(0));
		
	 // Comprobamos que el elemento de menú no está dado de alta en ningún menu usuario.
		if (! servJPAMenusUsuarioImp.existenElementosMenuUsuario(new Integer(idMenu) ) ) 
		   {
		   serviciosJPAMenu.eliminarElementoMenu(new Integer(idMenu));
		   }
		else
		  { 
			datosErrorValidacion.setCodError(new Integer(11) );
			datosErrorValidacion.setDesError( "El elemento de menu esta asignado a algun menu de un usuario de la aplicación" );
			modelo.addAttribute("errorValidacion" , true);
		    modelo.addAttribute("mensajeError", datosErrorValidacion.getCodError().toString() + ", " + datosErrorValidacion.getDesError() );
		  }
		
	// Carga el menu general
	   modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	   		
	  return "redirect:/gestionmenus/obtenermenuprincipal";
	}
	
	@RequestMapping(value = "/actualizarNumOrden", method = RequestMethod.POST)
	public String actualizarNumOrden(@ModelAttribute  BeanFormMenu beanFormMenu, Model modelo )  {
		
	 int numOrdenSec = 1;
	
	 for (Menu elemenMEnu : beanFormMenu.getBeanMenuAplicacionWeb())
	   {
		if ( elemenMEnu.getNumOrdenMenu().intValue() != numOrdenSec )
		   {
			Menu menu = serviciosJPAMenu.findIdMenu(new Integer (elemenMEnu.getIdMenu())).get() ;
		
		 	menu.setNumOrdenMenu(elemenMEnu.getNumOrdenMenu() );
		 	serviciosJPAMenu.modifNumOrden(menu);
			}
		numOrdenSec ++;
	   }
	   
	// Carga el menu general
	   modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	   		
	  return "redirect:/gestionmenus/obtenermenuprincipal";
	}
	
	@RequestMapping(value = "/actualizarNumOrdenSubmenu", method = RequestMethod.POST)
	public String actualizarNumOrdenSubMenu(@ModelAttribute  BeanFormSubMenu beanFormSubMenu, Model modelo )  {
		
	 int numOrdenSec = 1;
	
	 for (SubMenuNivel1 elemenSubMenu : beanFormSubMenu.getBeanSubMenuAplicacionWeb()  )
	   {
		 if ( elemenSubMenu.getNumOrdenMenu().intValue() != numOrdenSec )
			{
			 SubMenuNivel1 subMenu1 = serviciosJPAMenu.findIdSubMenu(new Integer (elemenSubMenu.getIdSubmenuNivel1() )).get() ;
		
			 subMenu1.setNumOrdenMenu(elemenSubMenu.getNumOrdenMenu() );
			 serviciosJPAMenu.modifNumOrdenSubMenu(subMenu1 );
			}
		 numOrdenSec ++;
	   }
	   
	// Carga el menu general
	   modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	   		
	  return "redirect:/gestionmenus/obtenersubmenu";
	}
}
