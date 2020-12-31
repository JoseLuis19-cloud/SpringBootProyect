package com.myfactory.SBootWebProject.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myfactory.SBootWebProject.beanForm.BeanCamposGesMenuUsu;
import com.myfactory.SBootWebProject.beanForm.BeanMenuAplicacionWeb;
import com.myfactory.SBootWebProject.beanForm.BeanMenuUsuarioSession;
import com.myfactory.SBootWebProject.beanForm.BeanMenuUsuarioWeb;
import com.myfactory.SBootWebProject.beanForm.BeanSubMenuAplicacionWeb;
import com.myfactory.SBootWebProject.beanForm.BeanSubMenuN1UsuarioWeb;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.model.Menu;
import com.myfactory.SBootWebProject.model.MenusUsuario;
import com.myfactory.SBootWebProject.model.SubMenuNivel1;
import com.myfactory.SBootWebProject.model.User;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAMenu;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAMenusUsuario;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAUsuario;

@Controller
@RequestMapping("/gestionmenususuario")
public class ControllerWebGestionMenusUsuario {
	
	@Autowired
	ServJPAMenusUsuario servJPAMenusUsuario;
	
	@Autowired
	ServJPAUsuario servJPAUsuario;

	@Autowired
	ServJPAMenu servJPAMenu;
	
	@Autowired
	public BeanUsuarioSession beanUsuarioSession;
	
	@GetMapping("/obtenermenusuario")
	public String obtenerMenuUsuario(Model modelo, 
			@RequestParam(value = "idUsuario", required = true) Long idUsuario,
			@RequestParam(value = "nomUsuario", required = true) String nomUsuario
			) 
	{
		BeanMenuUsuarioWeb menuUsuApliWeb = null;
		boolean encontradoEnMenuAplicacion;
	
	    List <BeanMenuUsuarioWeb> listMenuUsuWeb = new ArrayList<BeanMenuUsuarioWeb>() ;
	    
		List <BeanMenuAplicacionWeb>  listMenuAplicacionSelecWeb = new ArrayList<BeanMenuAplicacionWeb>();
	    
	    
		Iterable <MenusUsuario> menuUsuario = servJPAMenusUsuario.obtenerMenuUsuSin0(idUsuario);
		Iterable <Menu> menuAplicacion = servJPAMenu.obtenerMenusAplicacionSin0();
		
	 	Iterator<Menu> menuApli = menuAplicacion.iterator();
	 	
		Menu menuApliIter = null;

		while(menuApli.hasNext())
		{
			menuApliIter = menuApli.next();
			encontradoEnMenuAplicacion = false;
			
			for (MenusUsuario elemenMenusUsuario : menuUsuario) {

				if (elemenMenusUsuario.getMenu().getIdMenu().equals(menuApliIter.getIdMenu()) )
		    		{
		    		encontradoEnMenuAplicacion = true;
		    		break;
		    		}
			   }
   
			if (encontradoEnMenuAplicacion)
				{
				menuUsuApliWeb = new BeanMenuUsuarioWeb(menuApliIter.getIdMenu(), menuApliIter.getTextoMenu(), new Boolean(encontradoEnMenuAplicacion));
				listMenuUsuWeb.add(menuUsuApliWeb);
				}
			else
				{
				BeanMenuAplicacionWeb  menuAplicacionWeb = new BeanMenuAplicacionWeb();
				
				menuAplicacionWeb.setIdMenu(menuApliIter.getIdMenu());
				menuAplicacionWeb.setTextoMenu(menuApliIter.getTextoMenu());
			
				listMenuAplicacionSelecWeb.add(menuAplicacionWeb);	 
				}
		}
		
     // Mostrar solo los Menus de aplicación que no tenga el usuario asignados
	    
		modelo.addAttribute("idUsuarioApli", idUsuario);
		modelo.addAttribute("nomUsuario", nomUsuario);
		
		modelo.addAttribute("listMenuUsuApliWeb", listMenuUsuWeb);
		modelo.addAttribute("listMenuAplicacion", listMenuAplicacionSelecWeb);
		
		BeanCamposGesMenuUsu beanCamposGesMenuUsu = new BeanCamposGesMenuUsu();
		modelo.addAttribute("beanCamposGesMenuUsu", beanCamposGesMenuUsu);
		 
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionMenus/GestionMenusUsuario";
	}
	
 
	 @RequestMapping(value = "/obtenersubmenuusu", method = RequestMethod.GET)
	 public String obtenerSubMenuUsu(Model modelo, @RequestParam("idMenu")  String idMenu, @RequestParam("idUsuario")  String idUsuario) {
		
		List <BeanSubMenuN1UsuarioWeb> listSubMenuUsuWeb = new ArrayList<BeanSubMenuN1UsuarioWeb>() ;
		List <BeanSubMenuAplicacionWeb> listSubMenuApliWeb = new ArrayList<BeanSubMenuAplicacionWeb>() ;

		BeanSubMenuN1UsuarioWeb subMenuUsuApliWeb = null;
		boolean encontradoSubmenuApli;
	    
		Iterable<MenusUsuario> subMenuUsuario = servJPAMenusUsuario.obtenerSubMenuUsuSin0(new Long(idUsuario), new Integer(idMenu) );
		
		Iterable<SubMenuNivel1> subMenuAplicacion = servJPAMenu.obtenerSubMenuAplicacionSin0(new Integer(idMenu)) ;
	 	Iterator<SubMenuNivel1> subMenuApli = subMenuAplicacion.iterator();
	 	 
	 	SubMenuNivel1 subMenuApliIter = null;

		while(subMenuApli.hasNext())
		{
			subMenuApliIter = subMenuApli.next();
			encontradoSubmenuApli = false;
			
			for (MenusUsuario elemenSubMenusUsuario : subMenuUsuario)
			 {
				 if (elemenSubMenusUsuario.getMenu().getIdMenu().equals(subMenuApliIter.getIdSubmenuNivel1()) )
		    		{
					encontradoSubmenuApli = true;
		    		break;
		    		}
			  }
			
			if (encontradoSubmenuApli)
				{
				subMenuUsuApliWeb = new BeanSubMenuN1UsuarioWeb(subMenuApliIter.getIdSubmenuNivel1(), subMenuApliIter.getNumOrdenMenu() , subMenuApliIter.getTextoSubMenuN1(), subMenuApliIter.getHrefAplicacionN1() , new Boolean(encontradoSubmenuApli));
				listSubMenuUsuWeb.add(subMenuUsuApliWeb);
				}
			else
				{
				BeanSubMenuAplicacionWeb  subMenuAplicacionWeb = new BeanSubMenuAplicacionWeb();
	
				subMenuAplicacionWeb.setIdSubMenuN1(subMenuApliIter.getIdSubmenuNivel1() );
				subMenuAplicacionWeb.setTextoMenuSubMenu(subMenuApliIter.getTextoSubMenuN1() );
		
				listSubMenuApliWeb.add(subMenuAplicacionWeb);	 
				}
		}
		
		modelo.addAttribute("listSubMenuUsuApliWeb", listSubMenuUsuWeb);
		modelo.addAttribute("subMenuApliSelec", listSubMenuApliWeb);
		
		modelo.addAttribute("nomUsuario", servJPAUsuario.findIdUsuario(new Long(idUsuario.trim())).get().getUsername()); 
		modelo.addAttribute("nomMenuPrincipal", servJPAMenu.findIdMenu(new Integer(idMenu)).get().getTextoMenu());
		 
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	
		return "GestionMenus/GestionSubMenuUsuarioApli";
	}
	 
	
// 	@RequestMapping(value = "/obtenersubmenuajax/{idmenu,idusuario}", method = RequestMethod.GET)
	 @RequestMapping(value = "/obtenersubmenuajax", method = RequestMethod.GET)
	 public String obtenerSubMenuAjax(Model modelo, @RequestParam("idMenu")  String idMenu, @RequestParam("idUsuario")  String idUsuario) {
		
		List <BeanSubMenuN1UsuarioWeb> listSubMenuUsuWeb = new ArrayList<BeanSubMenuN1UsuarioWeb>() ;
		
		BeanSubMenuN1UsuarioWeb subMenuUsuApliWeb = null;
		boolean encontradoSubmenu;
	    
		Iterable<MenusUsuario> subMenuUsuario = servJPAMenusUsuario.obtenerSubMenuUsuSin0(new Long(idUsuario), new Integer(idMenu) );
		Iterator<MenusUsuario> subMenuUsu = subMenuUsuario.iterator();
		
		Iterable<SubMenuNivel1> subMenuAplicacion = servJPAMenu.obtenerSubMenuAplicacionSin0(new Integer(idMenu)) ;
	 	Iterator<SubMenuNivel1> subMenuApli = subMenuAplicacion.iterator();
	 	 
	 	SubMenuNivel1 subMenuApliIter = null;

		while(subMenuApli.hasNext())
		{
			subMenuApliIter = subMenuApli.next();
			encontradoSubmenu = false;
			
			MenusUsuario subMenuUsuIter = null;
			while(subMenuUsu.hasNext())
			 {
				subMenuUsuIter = subMenuUsu.next();
				
				if (subMenuApliIter.getMenu().getIdMenu().equals(subMenuUsuIter.getMenu().getIdMenu() ) )
		    		{
					encontradoSubmenu = true;
		    		break;
		    		}
			   }

			subMenuUsuApliWeb = new BeanSubMenuN1UsuarioWeb(subMenuApliIter.getIdSubmenuNivel1(), subMenuApliIter.getNumOrdenMenu() , subMenuApliIter.getTextoSubMenuN1(), subMenuApliIter.getHrefAplicacionN1() , new Boolean(encontradoSubmenu));
		    listSubMenuUsuWeb.add(subMenuUsuApliWeb);
		   }
	    
		modelo.addAttribute("listSubMenuUsuApliWeb", listSubMenuUsuWeb);
		modelo.addAttribute("subMenuApliSelec", subMenuAplicacion);
		
		return "GestionMenus/fragments/ListaSubMenuUsuarioApli :: ListSubMenuApli";
	}
	 
	 
	@RequestMapping(value = "/obtenermenuaplicacionajax", method = RequestMethod.GET)
	public String obtenerMenuAplicacion(Model modelo) {
		Iterable<Menu> menuAplicacion = servJPAMenu.obtenerMenusAplicacionSin0();
		modelo.addAttribute("menuAplicacionWeb", menuAplicacion);

	return "GestionMenus/fragments/ListaSubMenuUsuarioApli :: ListSubMenuApli";
	}
	 
	 @RequestMapping(value = "/anadirmenuusuario", method = RequestMethod.POST)
	 public String anadirMenuUsuario(Model modelo, 
			 						@ModelAttribute("beanCamposGesMenuUsu") BeanCamposGesMenuUsu beanCamposGesMenuUsu) {
		 
		List <BeanMenuAplicacionWeb>  listMenuAplicacionSelecWeb = new ArrayList<BeanMenuAplicacionWeb>();
		 
		if (beanCamposGesMenuUsu.getIdAccion().equals("todas") )
			{	 
			 listMenuAplicacionSelecWeb  = obtenerOpcApliDispoUsuario( beanCamposGesMenuUsu.getIdUsuario() );
			 
			 for (BeanMenuAplicacionWeb elemenMenusUsuApli : listMenuAplicacionSelecWeb) {
				 
				 User user = new User();
				 user.setId(beanCamposGesMenuUsu.getIdUsuario());
				 
				 Menu menu = new Menu();
				 menu.setIdMenu(elemenMenusUsuApli.getIdMenu());
				 
				 SubMenuNivel1 subMenuNivel1 = new SubMenuNivel1();
				 subMenuNivel1.setIdSubmenuNivel1(new Integer(0));
				 
				 MenusUsuario menusUsuario = new MenusUsuario();
				 
				 menusUsuario.setUser(user);
				 menusUsuario.setMenu(menu); 
				 menusUsuario.setSubMenu1(subMenuNivel1); 
				 
				 MenusUsuario menuAplicacion = servJPAMenusUsuario.insertarMenuUsuario(menusUsuario);
			 }

			 }
		 else
		 	{
			 User user = new User();
			 user.setId(beanCamposGesMenuUsu.getIdUsuario());
			 
			 Menu menu = new Menu();
			 menu.setIdMenu(beanCamposGesMenuUsu.getIdUsuario().intValue());
			 
			 SubMenuNivel1 subMenuNivel1 = new SubMenuNivel1();
			 subMenuNivel1.setIdSubmenuNivel1(new Integer(0));
			 
			 MenusUsuario menusUsuario = new MenusUsuario();
			 
			 menusUsuario.setUser(user);
			 menusUsuario.setMenu(menu); 
			 menusUsuario.setSubMenu1(subMenuNivel1); 
			 
			 MenusUsuario menuAplicacion = servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
		 	}

		// Mostrar solo los Menus de aplicación que no tenga el usuario asignados	    
		modelo.addAttribute("idUsuarioApli", beanCamposGesMenuUsu.getIdUsuario());
		// modelo.addAttribute("nomUsuario", nomUsuario);
		
		modelo.addAttribute("listMenuUsuApliWeb", obtenerMenusUsuarioActualizado(beanCamposGesMenuUsu.getIdUsuario()));
		modelo.addAttribute("listMenuAplicacion", listMenuAplicacionSelecWeb);
			
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

		return "GestionMenus/fragments/ListaSubMenuUsuarioApli :: ListSubMenuApli";
	}
	 
	 
	 @RequestMapping(value = "/eliminarmenuusuario", method = RequestMethod.POST)
	 public String eliminarMenuUsuario(Model modelo) {
		 
		 User user = new User();
		 user.setId(new Long(1));
		 
		 Menu menu = new Menu();
		 menu.setIdMenu(new Integer(1));
		 
		 SubMenuNivel1 subMenuNivel1 = new SubMenuNivel1();
		 subMenuNivel1.setIdSubmenuNivel1(new Integer(0));
		 
		 MenusUsuario menusUsuario = new MenusUsuario();
		 
		 menusUsuario.setUser(user);
		 menusUsuario.setMenu(menu); 
		 menusUsuario.setSubMenu1(subMenuNivel1); 
		 
		 MenusUsuario menuAplicacion = servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
		 
		 modelo.addAttribute("menuAplicacionWeb", menuAplicacion);

		return "GestionMenus/fragments/ListaSubMenuUsuarioApli :: ListSubMenuApli";
	}
	 
	 private List <BeanMenuAplicacionWeb> obtenerOpcApliDispoUsuario(Long idUsuario) {
		 
		BeanMenuAplicacionWeb  menuAplicacionWeb;
		List <BeanMenuAplicacionWeb>  listMenuAplicacionSelecWeb = new ArrayList<BeanMenuAplicacionWeb>();		    
		 
		boolean encontradoEnMenuAplicacion;

		Iterable <MenusUsuario> menuUsuario = servJPAMenusUsuario.obtenerMenuUsuSin0(idUsuario);
		Iterable <Menu> menuAplicacion = servJPAMenu.obtenerMenusAplicacionSin0();
			
		Iterator<Menu> menuApli = menuAplicacion.iterator();
		 	
		Menu menuApliIter = null;

		while(menuApli.hasNext())
		{
			menuApliIter = menuApli.next();
			encontradoEnMenuAplicacion = false;
				
			for (MenusUsuario elemenMenusUsuario : menuUsuario) {
				if (elemenMenusUsuario.getMenu().getIdMenu().equals(menuApliIter.getIdMenu()) )
			   		{
			   		encontradoEnMenuAplicacion = true;
			   		break;
			   		}
			   }
	  
			if (! encontradoEnMenuAplicacion) {
				menuAplicacionWeb = new BeanMenuAplicacionWeb();
					
				menuAplicacionWeb.setIdMenu(menuApliIter.getIdMenu());
				menuAplicacionWeb.setTextoMenu(menuApliIter.getTextoMenu());
				
				listMenuAplicacionSelecWeb.add(menuAplicacionWeb);	 
				}
		}
		   
		return listMenuAplicacionSelecWeb;
		 
	 }	 
	 
	 
	 private List <BeanMenuUsuarioWeb> obtenerMenusUsuarioActualizado(Long idUsuario) { 
		boolean encontradoEnMenuAplicacion;
		BeanMenuUsuarioWeb menuUsuApliWeb = null;
		
	    List <BeanMenuUsuarioWeb> listMenuUsuWeb = new ArrayList<BeanMenuUsuarioWeb>() ;
	    
		// List <BeanMenuAplicacionWeb>  listMenuAplicacionSelecWeb = new ArrayList<BeanMenuAplicacionWeb>();
	    
	    
		Iterable <MenusUsuario> menuUsuario = servJPAMenusUsuario.obtenerMenuUsuSin0(idUsuario);
		Iterable <Menu> menuAplicacion = servJPAMenu.obtenerMenusAplicacionSin0();
		
	 	Iterator<Menu> menuApli = menuAplicacion.iterator();
	 	
		Menu menuApliIter = null;

		while(menuApli.hasNext())
		{
			menuApliIter = menuApli.next();
			encontradoEnMenuAplicacion = false;
			
			for (MenusUsuario elemenMenusUsuario : menuUsuario) {

				if (elemenMenusUsuario.getMenu().getIdMenu().equals(menuApliIter.getIdMenu()) )
		    		{
		    		encontradoEnMenuAplicacion = true;
		    		break;
		    		}
			   }
   
			if (encontradoEnMenuAplicacion)
				{
				menuUsuApliWeb = new BeanMenuUsuarioWeb(menuApliIter.getIdMenu(), menuApliIter.getTextoMenu(), new Boolean(encontradoEnMenuAplicacion));
				listMenuUsuWeb.add(menuUsuApliWeb);
				}
		}
		
		return listMenuUsuWeb;
	 }
	 
}
