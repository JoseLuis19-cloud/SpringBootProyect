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
		Integer idMenuUsuario = null;
	
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
		    		idMenuUsuario = elemenMenusUsuario.getIdMenu();
		    		break;
		    		}
			   }
   
			if (encontradoEnMenuAplicacion)
				{
				menuUsuApliWeb = new BeanMenuUsuarioWeb(idMenuUsuario, menuApliIter.getIdMenu(), menuApliIter.getTextoMenu(), new Boolean(encontradoEnMenuAplicacion));
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
		beanCamposGesMenuUsu.setIdUsuario(idUsuario);
		beanCamposGesMenuUsu.setNomUsuario(nomUsuario);
		modelo.addAttribute("beanCamposGesMenuUsu", beanCamposGesMenuUsu);
		 
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionMenus/GestionMenusUsuario";
	}
	
 
	 @RequestMapping(value = "/obtenersubmenuusu", method = RequestMethod.GET)
	 public String obtenerSubMenuUsu(Model modelo, @RequestParam("idMenu") String idMenu, 
	 											   @RequestParam("idUsuario") String idUsuario,
	 											   @RequestParam(value = "nomUsuario", required = false)  String nomUsuario)
	 {
		List <BeanSubMenuN1UsuarioWeb> listSubMenuUsuWeb = new ArrayList<BeanSubMenuN1UsuarioWeb>() ;
		List <BeanSubMenuAplicacionWeb> listSubMenuApliWeb = new ArrayList<BeanSubMenuAplicacionWeb>() ;

		BeanSubMenuN1UsuarioWeb subMenuUsuApliWeb = null;
		boolean encontradoSubmenuApli;
		Integer idSubMenuUsuario = null;
	    
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
				 if (elemenSubMenusUsuario.getSubMenu1().getIdSubmenuNivel1().equals(subMenuApliIter.getIdSubmenuNivel1()) )
		    		{
					idSubMenuUsuario = elemenSubMenusUsuario.getIdMenu();
					encontradoSubmenuApli = true;
		    		break;
		    		}
			  }
			
			if (encontradoSubmenuApli)
				{
				subMenuUsuApliWeb = new BeanSubMenuN1UsuarioWeb(idSubMenuUsuario, subMenuApliIter.getIdSubmenuNivel1(), subMenuApliIter.getNumOrdenMenu() , subMenuApliIter.getTextoSubMenuN1(), subMenuApliIter.getHrefAplicacionN1() , new Boolean(encontradoSubmenuApli));
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
		
		BeanCamposGesMenuUsu beanCamposGesMenuUsu = new BeanCamposGesMenuUsu();
		beanCamposGesMenuUsu.setIdUsuario(new Long(idUsuario));
		beanCamposGesMenuUsu.setNomUsuario(nomUsuario);
		beanCamposGesMenuUsu.setIdMenuPrincipal(new Integer (idMenu));
		modelo.addAttribute("beanCamposGesMenuUsu", beanCamposGesMenuUsu);
		
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

			subMenuUsuApliWeb = new BeanSubMenuN1UsuarioWeb(new Integer(1), subMenuApliIter.getIdSubmenuNivel1(), subMenuApliIter.getNumOrdenMenu(), subMenuApliIter.getTextoSubMenuN1(), subMenuApliIter.getHrefAplicacionN1() , new Boolean(encontradoSubmenu));
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
	 
	 @RequestMapping(value = "/actualizarmenuusuario", method = RequestMethod.POST)
	 public String actualizarMenuUsuario(Model modelo, 
			 						@ModelAttribute("beanCamposGesMenuUsu") BeanCamposGesMenuUsu beanCamposGesMenuUsu) {
		 
		List <BeanMenuAplicacionWeb>  listMenuAplicacionSelecWeb = new ArrayList<BeanMenuAplicacionWeb>();
		 
		if (beanCamposGesMenuUsu.getIdAccion().equals("anadirtodos") )
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
			 
			 if (beanCamposGesMenuUsu.getIdAccion().equals("anadiruno") )
			 	{
				 User user = new User();
				 user.setId(beanCamposGesMenuUsu.getIdUsuario());
			 
				 SubMenuNivel1 subMenuNivel1 = new SubMenuNivel1();
				 subMenuNivel1.setIdSubmenuNivel1(new Integer(0));
			 
				 MenusUsuario menusUsuario = new MenusUsuario();
			 
				 menusUsuario.setUser(user);
				 menusUsuario.setSubMenu1(subMenuNivel1); 
			 
				 if (beanCamposGesMenuUsu.getIdMenu1() != null)
			 		{
					 Menu menu1 = new Menu();
					 menu1.setIdMenu( beanCamposGesMenuUsu.getIdMenu1());
					 menusUsuario.setMenu(menu1); 
					 menusUsuario.setNumOrden(1); 
					 servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
			 
				 if (beanCamposGesMenuUsu.getIdMenu2() != null)
				 	{
					 Menu menu2 = new Menu();
					 menu2.setIdMenu(beanCamposGesMenuUsu.getIdMenu2());
					 menusUsuario.setMenu(menu2);
					 menusUsuario.setNumOrden(2); 
					 servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
				 	}
			 
				 if (beanCamposGesMenuUsu.getIdMenu3() != null)
			 		{
					Menu menu3 = new Menu();
					menu3.setIdMenu(beanCamposGesMenuUsu.getIdMenu3());
					menusUsuario.setMenu(menu3);
					menusUsuario.setNumOrden(3); 
					servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
				if (beanCamposGesMenuUsu.getIdMenu4() != null)
			 		{
					Menu menu4 = new Menu();
					menu4.setIdMenu(beanCamposGesMenuUsu.getIdMenu4());
					menusUsuario.setMenu(menu4);
					menusUsuario.setNumOrden(4); 
					servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
				if (beanCamposGesMenuUsu.getIdMenu5() != null)
			 		{
					Menu menu5 = new Menu();
					menu5.setIdMenu(beanCamposGesMenuUsu.getIdMenu5());
					menusUsuario.setMenu(menu5);
					menusUsuario.setNumOrden(5); 
					servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
				if (beanCamposGesMenuUsu.getIdMenu6() != null)
			 		{
					Menu menu6 = new Menu();
					menu6.setIdMenu(beanCamposGesMenuUsu.getIdMenu6());
					menusUsuario.setMenu(menu6);
				 	menusUsuario.setNumOrden(6); 
				 	servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
				if (beanCamposGesMenuUsu.getIdMenu7() != null)
					{
					Menu menu7 = new Menu();
					menu7.setIdMenu(beanCamposGesMenuUsu.getIdMenu7());
					menusUsuario.setIdMenu(null);
					menusUsuario.setMenu(menu7);
					menusUsuario.setNumOrden(7); 
					servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
					}
			 
				if (beanCamposGesMenuUsu.getIdMenu8() != null)
			 		{
					Menu menu8 = new Menu();
					menu8.setIdMenu(beanCamposGesMenuUsu.getIdMenu8());
					menusUsuario.setMenu(menu8);
					menusUsuario.setNumOrden(8); 
					servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
			 
				if (beanCamposGesMenuUsu.getIdMenu9() != null)
			 		{
					Menu menu9 = new Menu();
					menu9.setIdMenu(beanCamposGesMenuUsu.getIdMenu9());
					menusUsuario.setMenu(menu9);
					menusUsuario.setNumOrden(9); 
					servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
			 	}
			 else
			 {
			if (beanCamposGesMenuUsu.getIdAccion().equals("suprimiruno") )
				{	 
				 User user = new User();
				 user.setId(beanCamposGesMenuUsu.getIdUsuario());
				 
				 SubMenuNivel1 subMenuNivel1 = new SubMenuNivel1();
				 subMenuNivel1.setIdSubmenuNivel1(new Integer(0));
				 
				 MenusUsuario menusUsuario = new MenusUsuario();
				 
				 menusUsuario.setUser(user);
				 menusUsuario.setSubMenu1(subMenuNivel1); 
				 
				 if (beanCamposGesMenuUsu.getIdMenu1() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu1());
					 
					 Menu menu1 = new Menu();
					 menu1.setIdMenu(beanCamposGesMenuUsu.getIdMenu1()); 
					 menusUsuario.setMenu(menu1); 
					 menusUsuario.setNumOrden(1); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 
				 if (beanCamposGesMenuUsu.getIdMenu2() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu2());
					 
					 Menu menu2 = new Menu();
					 menu2.setIdMenu(beanCamposGesMenuUsu.getIdMenu2());
					 menusUsuario.setMenu(menu2);
					 menusUsuario.setNumOrden(2); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 
				 if (beanCamposGesMenuUsu.getIdMenu3() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu3());
					 
					 Menu menu3 = new Menu();
					 menu3.setIdMenu(beanCamposGesMenuUsu.getIdMenu3());
					 menusUsuario.setMenu(menu3);
					 menusUsuario.setNumOrden(3); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 if (beanCamposGesMenuUsu.getIdMenu4() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu4());
					 
					 Menu menu4 = new Menu();
					 menu4.setIdMenu(beanCamposGesMenuUsu.getIdMenu4());
					 menusUsuario.setMenu(menu4);
					 menusUsuario.setNumOrden(4); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 if (beanCamposGesMenuUsu.getIdMenu5() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu5());
					 Menu menu5 = new Menu();
					 menu5.setIdMenu(beanCamposGesMenuUsu.getIdMenu5());
					 menusUsuario.setMenu(menu5);
					 menusUsuario.setNumOrden(5); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 if (beanCamposGesMenuUsu.getIdMenu6() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu6());
					 Menu menu6 = new Menu();
					 menu6.setIdMenu(beanCamposGesMenuUsu.getIdMenu6());
					 menusUsuario.setMenu(menu6);
					 menusUsuario.setNumOrden(6); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 if (beanCamposGesMenuUsu.getIdMenu7() != null)
					{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu7());
					 Menu menu7 = new Menu();
					 menu7.setIdMenu(beanCamposGesMenuUsu.getIdMenu7());
					 menusUsuario.setMenu(menu7);
					 menusUsuario.setNumOrden(7); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
					}
				 
				 if (beanCamposGesMenuUsu.getIdMenu8() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu8());
					 Menu menu8 = new Menu();
					 menu8.setIdMenu(beanCamposGesMenuUsu.getIdMenu8());
					 menusUsuario.setMenu(menu8);
					 menusUsuario.setNumOrden(8); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 
				 if (beanCamposGesMenuUsu.getIdMenu9() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu9());
					 Menu menu9 = new Menu();
					 menu9.setIdMenu(beanCamposGesMenuUsu.getIdMenu9());
					 menusUsuario.setMenu(menu9);
					 menusUsuario.setNumOrden(9); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	} 
			}
		}
		 	}

		// Mostrar solo los Menus de aplicación que no tenga el usuario asignados	    
		modelo.addAttribute("idUsuarioApli", beanCamposGesMenuUsu.getIdUsuario());
		modelo.addAttribute("nomUsuario", beanCamposGesMenuUsu.getNomUsuario());
	
		BeanCamposGesMenuUsu beanCamposGesMenuUsu2 = new BeanCamposGesMenuUsu();
		beanCamposGesMenuUsu2.setIdUsuario(beanCamposGesMenuUsu.getIdUsuario());	
		beanCamposGesMenuUsu2.setNomUsuario(beanCamposGesMenuUsu.getNomUsuario());
		modelo.addAttribute("beanCamposGesMenuUsu", beanCamposGesMenuUsu2);
		
		//////////////////////////////
		Boolean encontradoEnMenuAplicacion = null;
		BeanMenuUsuarioWeb menuUsuApliWeb = null;
	    List <BeanMenuUsuarioWeb> listMenuUsuWeb = new ArrayList<BeanMenuUsuarioWeb>() ;
		
		Iterable <MenusUsuario> menuUsuario = servJPAMenusUsuario.obtenerMenuUsuSin0(beanCamposGesMenuUsu.getIdUsuario());
		Iterable <Menu> menuAplicacion = servJPAMenu.obtenerMenusAplicacionSin0();
	 	Iterator <Menu> menuApli = menuAplicacion.iterator();
	 	
		Menu menuApliIter = null;
		Integer idMenuUsuario = null;

		while(menuApli.hasNext())
		{
			menuApliIter = menuApli.next();
			encontradoEnMenuAplicacion = false;
			
			for (MenusUsuario elemenMenusUsuario : menuUsuario) {

				if (elemenMenusUsuario.getMenu().getIdMenu().equals(menuApliIter.getIdMenu()) )
		    		{
		    		encontradoEnMenuAplicacion = true;
		    		idMenuUsuario = elemenMenusUsuario.getIdMenu();
		    		break;
		    		}
			   }
   
			if (encontradoEnMenuAplicacion)
				{
				menuUsuApliWeb = new BeanMenuUsuarioWeb(idMenuUsuario, menuApliIter.getIdMenu(), menuApliIter.getTextoMenu(), new Boolean(encontradoEnMenuAplicacion));
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
		
		/////
		
		modelo.addAttribute("listMenuUsuApliWeb", obtenerMenusUsuarioActualizado(beanCamposGesMenuUsu.getIdUsuario()));
		modelo.addAttribute("listMenuAplicacion", listMenuAplicacionSelecWeb);
		 
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionMenus/GestionMenusUsuario";
	}

	 
	 @RequestMapping(value = "/actualizarsubmenuusuario", method = RequestMethod.POST)
	 public String actualizarSubMenuUsuario(Model modelo, 
			 						@ModelAttribute("beanCamposGesMenuUsu") BeanCamposGesMenuUsu beanCamposGesMenuUsu) {
		 
		List <BeanMenuAplicacionWeb>  listMenuAplicacionSelecWeb = new ArrayList<BeanMenuAplicacionWeb>();
		

		 
		if (beanCamposGesMenuUsu.getIdAccion().equals("anadirtodos") )
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
				// menusUsuario.setSubMenu2(subMenuNivel1); 
				 
				MenusUsuario menuAplicacion = servJPAMenusUsuario.insertarMenuUsuario(menusUsuario);
			 }  
			 }
		 else
		 	{
			 if (beanCamposGesMenuUsu.getIdAccion().equals("anadiruno") )
			 	{
				 User user = new User();
				 user.setId(beanCamposGesMenuUsu.getIdUsuario());
			 
				 MenusUsuario menusUsuario = new MenusUsuario();
				 menusUsuario.setUser(user);
			//	 menusUsuario.setSubMenu1(subMenuNivel1); 
	
				 if (beanCamposGesMenuUsu.getIdMenu1() != null)
			 		{
					 Menu menu1 = new Menu();
					 menu1.setIdMenu(beanCamposGesMenuUsu.getIdMenuPrincipal());
					 menusUsuario.setMenu(menu1);
					 
					 SubMenuNivel1 subMenuNivel11 = new SubMenuNivel1();
					 subMenuNivel11.setIdSubmenuNivel1(beanCamposGesMenuUsu.getIdMenu1());
					 menusUsuario.setSubMenu1(subMenuNivel11);
					 
					 menusUsuario.setNumOrden(1); 
					 servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
			 
				 if (beanCamposGesMenuUsu.getIdMenu2() != null)
				 	{
					 Menu menu2 = new Menu();
					 menu2.setIdMenu(beanCamposGesMenuUsu.getIdMenu2());
					 menusUsuario.setMenu(menu2);
					 
					 SubMenuNivel1 subMenuNivel12 = new SubMenuNivel1();
					 subMenuNivel12.setIdSubmenuNivel1(beanCamposGesMenuUsu.getIdMenu1());
					 menusUsuario.setSubMenu1(subMenuNivel12);
				
					 menusUsuario.setNumOrden(2); 
					 servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
				 	}
			 
				 if (beanCamposGesMenuUsu.getIdMenu3() != null)
			 		{
					Menu menu3 = new Menu();
					menu3.setIdMenu(beanCamposGesMenuUsu.getIdMenu3());
					menusUsuario.setMenu(menu3);
					menusUsuario.setNumOrden(3); 
					servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
				if (beanCamposGesMenuUsu.getIdMenu4() != null)
			 		{
					Menu menu4 = new Menu();
					menu4.setIdMenu(beanCamposGesMenuUsu.getIdMenu4());
					menusUsuario.setMenu(menu4);
					menusUsuario.setNumOrden(4); 
					servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
				if (beanCamposGesMenuUsu.getIdMenu5() != null)
			 		{
					Menu menu5 = new Menu();
					menu5.setIdMenu(beanCamposGesMenuUsu.getIdMenu5());
					menusUsuario.setMenu(menu5);
					menusUsuario.setNumOrden(5); 
					servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
				if (beanCamposGesMenuUsu.getIdMenu6() != null)
			 		{
					Menu menu6 = new Menu();
					menu6.setIdMenu(beanCamposGesMenuUsu.getIdMenu6());
					menusUsuario.setMenu(menu6);
				 	menusUsuario.setNumOrden(6); 
				 	servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
				if (beanCamposGesMenuUsu.getIdMenu7() != null)
					{
					Menu menu7 = new Menu();
					menu7.setIdMenu(beanCamposGesMenuUsu.getIdMenu7());
					menusUsuario.setIdMenu(null);
					menusUsuario.setMenu(menu7);
					menusUsuario.setNumOrden(7); 
					servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
					}
			 
				if (beanCamposGesMenuUsu.getIdMenu8() != null)
			 		{
					Menu menu8 = new Menu();
					menu8.setIdMenu(beanCamposGesMenuUsu.getIdMenu8());
					menusUsuario.setMenu(menu8);
					menusUsuario.setNumOrden(8); 
					servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
			 
				if (beanCamposGesMenuUsu.getIdMenu9() != null)
			 		{
					Menu menu9 = new Menu();
					menu9.setIdMenu(beanCamposGesMenuUsu.getIdMenu9());
					menusUsuario.setMenu(menu9);
					menusUsuario.setNumOrden(9); 
					servJPAMenusUsuario.insertarMenuUsuario(menusUsuario) ;
			 		}
			 	}
			 else
			 {
			if (beanCamposGesMenuUsu.getIdAccion().equals("suprimiruno") )
				{	 
				 User user = new User();
				 user.setId(beanCamposGesMenuUsu.getIdUsuario());
				 
				 SubMenuNivel1 subMenuNivel1 = new SubMenuNivel1();
				 subMenuNivel1.setIdSubmenuNivel1(new Integer(0));
				 
				 MenusUsuario menusUsuario = new MenusUsuario();
				 
				 menusUsuario.setUser(user);
				 menusUsuario.setSubMenu1(subMenuNivel1); 
				 
				 if (beanCamposGesMenuUsu.getIdMenu1() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu1());
					 
					 Menu menu1 = new Menu();
					 menu1.setIdMenu(beanCamposGesMenuUsu.getIdMenuPrincipal()); 
					 menusUsuario.setMenu(menu1); 
					// menusUsuario.setNumOrden(1); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 
				 if (beanCamposGesMenuUsu.getIdMenu2() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu2());
					 
					 Menu menu2 = new Menu();
					 menu2.setIdMenu(beanCamposGesMenuUsu.getIdMenu2());
					 menusUsuario.setMenu(menu2);
					 menusUsuario.setNumOrden(2); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 
				 if (beanCamposGesMenuUsu.getIdMenu3() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu3());
					 
					 Menu menu3 = new Menu();
					 menu3.setIdMenu(beanCamposGesMenuUsu.getIdMenu3());
					 menusUsuario.setMenu(menu3);
					 menusUsuario.setNumOrden(3); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 if (beanCamposGesMenuUsu.getIdMenu4() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu4());
					 
					 Menu menu4 = new Menu();
					 menu4.setIdMenu(beanCamposGesMenuUsu.getIdMenu4());
					 menusUsuario.setMenu(menu4);
					 menusUsuario.setNumOrden(4); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 if (beanCamposGesMenuUsu.getIdMenu5() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu5());
					 Menu menu5 = new Menu();
					 menu5.setIdMenu(beanCamposGesMenuUsu.getIdMenu5());
					 menusUsuario.setMenu(menu5);
					 menusUsuario.setNumOrden(5); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 if (beanCamposGesMenuUsu.getIdMenu6() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu6());
					 Menu menu6 = new Menu();
					 menu6.setIdMenu(beanCamposGesMenuUsu.getIdMenu6());
					 menusUsuario.setMenu(menu6);
					 menusUsuario.setNumOrden(6); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 if (beanCamposGesMenuUsu.getIdMenu7() != null)
					{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu7());
					 Menu menu7 = new Menu();
					 menu7.setIdMenu(beanCamposGesMenuUsu.getIdMenu7());
					 menusUsuario.setMenu(menu7);
					 menusUsuario.setNumOrden(7); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
					}
				 
				 if (beanCamposGesMenuUsu.getIdMenu8() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu8());
					 Menu menu8 = new Menu();
					 menu8.setIdMenu(beanCamposGesMenuUsu.getIdMenu8());
					 menusUsuario.setMenu(menu8);
					 menusUsuario.setNumOrden(8); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	}
				 
				 if (beanCamposGesMenuUsu.getIdMenu9() != null)
				 	{
					 menusUsuario.setIdMenu(beanCamposGesMenuUsu.getIdMenuUsu9());
					 Menu menu9 = new Menu();
					 menu9.setIdMenu(beanCamposGesMenuUsu.getIdMenu9());
					 menusUsuario.setMenu(menu9);
					 menusUsuario.setNumOrden(9); 
				     servJPAMenusUsuario.suprimirMenuUsuario(menusUsuario) ;
				 	} 
			}
		}
		 	}

		// Mostrar solo los Menus de aplicación que no tenga el usuario asignados	    
		modelo.addAttribute("idUsuarioApli", beanCamposGesMenuUsu.getIdUsuario());
		modelo.addAttribute("nomUsuario", beanCamposGesMenuUsu.getNomUsuario());
	
		BeanCamposGesMenuUsu beanCamposGesMenuUsu2 = new BeanCamposGesMenuUsu();
		beanCamposGesMenuUsu2.setIdUsuario(beanCamposGesMenuUsu.getIdUsuario());	
		beanCamposGesMenuUsu2.setNomUsuario(beanCamposGesMenuUsu.getNomUsuario());
		modelo.addAttribute("beanCamposGesMenuUsu", beanCamposGesMenuUsu2);
		
		//////////////////////////////
		Boolean encontradoEnMenuAplicacion = null;
		BeanSubMenuN1UsuarioWeb subMenuUsuApliWeb = null;

	    List <BeanSubMenuN1UsuarioWeb> listSubMenuUsuWeb = new ArrayList<BeanSubMenuN1UsuarioWeb>() ;
		List <BeanSubMenuAplicacionWeb> listSubMenuApliWeb = new ArrayList<BeanSubMenuAplicacionWeb>() ;
		
		Iterable <MenusUsuario> menuUsuario = servJPAMenusUsuario.obtenerSubMenuUsuSin0(beanCamposGesMenuUsu.getIdUsuario(), beanCamposGesMenuUsu.getIdMenuPrincipal() );
		Iterable <SubMenuNivel1> subMenuAplicacion = servJPAMenu.obtenerSubMenuAplicacionSin0(beanCamposGesMenuUsu.getIdMenuPrincipal()) ;
	 	Iterator <SubMenuNivel1> subMenuApli = subMenuAplicacion.iterator();
	 	
	 	SubMenuNivel1 subMenuApliIter = null;
		Integer idSubMenuUsuario = null;

		while(subMenuApli.hasNext())
		{
			subMenuApliIter = subMenuApli.next();
			encontradoEnMenuAplicacion = false;
			
			for (MenusUsuario elemenMenusUsuario : menuUsuario) {

				if (elemenMenusUsuario.getSubMenu1().getIdSubmenuNivel1().equals(subMenuApliIter.getIdSubmenuNivel1() ) )
		    		{
		    		encontradoEnMenuAplicacion = true;
		    		idSubMenuUsuario = elemenMenusUsuario.getIdMenu();
		    		break;
		    		}
			   }
   
			if (encontradoEnMenuAplicacion)
				{
				subMenuUsuApliWeb = new BeanSubMenuN1UsuarioWeb(idSubMenuUsuario, subMenuApliIter.getIdSubmenuNivel1(), subMenuApliIter.getNumOrdenMenu(), subMenuApliIter.getTextoSubMenuN1(), subMenuApliIter.getHrefAplicacionN1()  , new Boolean(encontradoEnMenuAplicacion));
				listSubMenuUsuWeb.add(subMenuUsuApliWeb);
				}
			else
				{
				BeanSubMenuAplicacionWeb menuSubAplicacionWeb = new BeanSubMenuAplicacionWeb();
				menuSubAplicacionWeb.setIdMenu(subMenuApliIter.getIdSubmenuNivel1());
				menuSubAplicacionWeb.setTextoMenuSubMenu(subMenuApliIter.getTextoSubMenuN1() );
			
				listSubMenuApliWeb.add(menuSubAplicacionWeb);	 
				}
		}
		
		/////
		
		modelo.addAttribute("listSubMenuUsuApliWeb", listSubMenuUsuWeb);
		modelo.addAttribute("subMenuApliSelec", listSubMenuApliWeb);
		
		
		 
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
	//	BeanCamposGesMenuUsu beanCamposGesMenuUsu = new BeanCamposGesMenuUsu();
		 
		modelo.addAttribute("nomUsuario", servJPAUsuario.findIdUsuario(new Long(beanCamposGesMenuUsu.getIdUsuario())).get().getUsername()); 
		modelo.addAttribute("nomMenuPrincipal", servJPAMenu.findIdMenu(new Integer(beanCamposGesMenuUsu.getIdMenuPrincipal())).get().getTextoMenu());
		modelo.addAttribute("beanCamposGesMenuUsu", beanCamposGesMenuUsu);	 
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

		return "GestionMenus/GestionSubMenuUsuarioApli"; 
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
	   
		Iterable <MenusUsuario> menuUsuario = servJPAMenusUsuario.obtenerMenuUsuSin0(idUsuario);
		Iterable <Menu> menuAplicacion = servJPAMenu.obtenerMenusAplicacionSin0();
		
	 	Iterator<Menu> menuApli = menuAplicacion.iterator();
	 	
		Menu menuApliIter = null;
		Integer idMenuUsu = null;

		while(menuApli.hasNext())
		{
			menuApliIter = menuApli.next();
			encontradoEnMenuAplicacion = false;
			
			for (MenusUsuario elemenMenusUsuario : menuUsuario) {
				if (elemenMenusUsuario.getMenu().getIdMenu().equals(menuApliIter.getIdMenu()) )
		    		{
					idMenuUsu = elemenMenusUsuario.getIdMenu();
		    		encontradoEnMenuAplicacion = true;
		    		break;
		    		}
			   }

			if (encontradoEnMenuAplicacion)
				{
				menuUsuApliWeb = new BeanMenuUsuarioWeb(idMenuUsu, menuApliIter.getIdMenu(), menuApliIter.getTextoMenu(), new Boolean(encontradoEnMenuAplicacion));
				listMenuUsuWeb.add(menuUsuApliWeb);
				}
		}
		
		return listMenuUsuWeb;
	 }
	 
}
