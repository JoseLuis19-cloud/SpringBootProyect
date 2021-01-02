package com.myfactory.SBootWebProject.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioWeb;
import com.myfactory.SBootWebProject.model.Role;
import com.myfactory.SBootWebProject.model.User;
import com.myfactory.SBootWebProject.security.GeneradorEncriptacion;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAUsuario;

@Controller
@RequestMapping("/gestionWeb/usuarios")
public class ControllerWebUsuarios {

	@Autowired
	ServJPAUsuario servJPAUsuario;
	
	@Autowired
	public BeanUsuarioSession beanUsuarioSession;
	
	private static final Integer RegPorPagina  = new Integer(5);
	
	@RequestMapping("/pagusuarios")
	public String paginacionUsuarios(Model modelo,  @RequestParam(value = "numPag", required = false) String numPag, 
													@RequestParam(value = "tpoAccion", required = false) String tpoAccion) {
		int numPagInt = 0;
		
		if (numPag == null) {
			numPagInt = 0; 
			}
		else
			{
			if (tpoAccion.equals("avan") )
				{
				numPagInt = Integer.parseInt(numPag) + 1; 
				}
				else
				{
				numPagInt = Integer.parseInt(numPag) - 1; 
				}
			}

		Page <User> pagUsuario = servJPAUsuario.paginacionUsuarios(numPagInt, RegPorPagina);
	
		modelo.addAttribute("numPag", String.valueOf(numPagInt));
		modelo.addAttribute("numRegPag", pagUsuario.getContent().size());
		modelo.addAttribute("pagUsuarios", pagUsuario);
		modelo.addAttribute("standardDate", new Date());
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "gestionWeb/usuarios/PaginacionUsuarios";
	}
	
	
	@RequestMapping("/usuariomenu")
	public String usuarioMenus(Model modelo, @RequestParam(value = "idUsuario", required = false) Long idUsuario){
		
		User usuarioMenu = servJPAUsuario.getMenusUsuario( idUsuario);
		
		modelo.addAttribute("aliasUsuario", usuarioMenu.getFullName());
		modelo.addAttribute("nombreUsuario", usuarioMenu.getUsername());
		
		modelo.addAttribute("usuarioMenu", usuarioMenu);
		
	//	modelo.addAttribute("opcionesMenuUsu", usuarioMenu.getMenuUsuario());
		
	 //	Iterator<Menu> menu =  usuarioMenu.getMenuUsuario().iterator();
		
	  //  while(menu.hasNext()){
	   // 	Set<SubMenuNivel1> subMenuNiv1 = menu.next().getSubMenuNivel1();
	    	
	   //  	Iterator<SubMenuNivel1> subMenuNiv1Ite =  subMenuNiv1.iterator();
	    	
	   //  	while(subMenuNiv1Ite.hasNext()){
	    // 		 System.out.println (subMenuNiv1Ite.next().getTextoSubMenuN1());
	   // 	}
	   // }  
		
		return "gestionWeb/UsuarioMenus.html";
	}
	
	@RequestMapping("/forminsertarusuario")
	public String formularioInserCliente(Model modelo ) {
		
		BeanUsuarioWeb beanUsuarioWeb = new BeanUsuarioWeb ();
		
		beanUsuarioWeb.setFecAltaUsuarioWeb(Calendar.getInstance());
		beanUsuarioWeb.setRolUsuarioWeb(servJPAUsuario.obtenerRoles());
		
		modelo.addAttribute("usuarioWeb", beanUsuarioWeb);
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionWeb/usuarios/FormInsertarUsuario";
	}
	
	@RequestMapping(value = "/insertarusuario", method = RequestMethod.POST)
	public String insertarUsuario(Model modelo,
			@Valid @ModelAttribute("formUsuarioWeb") BeanUsuarioWeb beanUsuarioWeb, 
			BindingResult resultValidacion,
			@RequestParam(value = "rolAplicacion", required = true) String codRole) {

		beanUsuarioWeb.setFecAltaUsuarioWeb(Calendar.getInstance());
		beanUsuarioWeb.setRolUsuarioWeb(servJPAUsuario.obtenerRoles());

		 validarUsuario(beanUsuarioWeb, codRole);
		
		
		modelo.addAttribute("usuarioWeb", beanUsuarioWeb);
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionWeb/usuarios/FormInsertarUsuario";
	}
	
	public User validarUsuario(BeanUsuarioWeb beanUsuarioWeb, String codRole) {
		
		 Boolean userEncontrado = new Boolean(false);
		 User usuarioNuevo = new User();
		 Set <Role> setRoles = new HashSet<>(); 
		
		if (servJPAUsuario.findByName(beanUsuarioWeb.getUsernameWeb().trim()) ) {
			System.out.println("Error username del Usuario esta es Duplicado");
			userEncontrado = true;
		}
		
		if (! userEncontrado) {
			if (servJPAUsuario.findByEmail(beanUsuarioWeb.getEmailWeb().trim()) ) {
				System.out.println("Error username del Usuario esta es Duplicado");
				userEncontrado = true;
				}
			}
		
		if (! userEncontrado) {
			if ( servJPAUsuario.findByFullName(beanUsuarioWeb.getEmailWeb().trim() )){
				System.out.println("Error fullname del Usuario esta es Duplicado");
				userEncontrado = true;
				}
			}
		 
		if (! userEncontrado )
			{
			// Encriptar password por el usuario
			// Usuario 
			GeneradorEncriptacion generadorEncriptacion = new GeneradorEncriptacion();
			String passordEncriptada = generadorEncriptacion.generarPasswordEncrip(beanUsuarioWeb.getPasswordWeb().trim());
			
		 	usuarioNuevo.setPassword(passordEncriptada);
		 	
		 	usuarioNuevo.setEmail( beanUsuarioWeb.getEmailWeb());
			usuarioNuevo.setEnabled(beanUsuarioWeb.isEnabledWeb());
			usuarioNuevo.setIndEmpleado(beanUsuarioWeb.isIndEmpleadoWeb());
			usuarioNuevo.setFullName(beanUsuarioWeb.getFullNameWeb());
			usuarioNuevo.setUsername(beanUsuarioWeb.getUsernameWeb() );
			usuarioNuevo.setFecAltaUsuario(beanUsuarioWeb.getFecAltaUsuarioWeb());
			
			Role role = new Role();
			role.setId(new Integer(codRole.trim()));
			setRoles.add(role);
			
			usuarioNuevo.setRoles(setRoles);
			
			servJPAUsuario.insertarUsuario(usuarioNuevo);
			}
		return usuarioNuevo;
		}
}
