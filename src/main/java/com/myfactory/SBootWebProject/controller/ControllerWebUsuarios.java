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
	CargarBeansDatos cargarBeansDatos;
	
	@Autowired
	public BeanUsuarioSession beanUsuarioSession;
	
	private static final Integer RegPorPagina  = new Integer(5);
	private  String mensajeErrorActualizacion  = "";
	
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

		// Page <User> pagUsuario = servJPAUsuario.paginacionUsuarios(numPagInt, RegPorPagina);
				Iterable <User> listUsuarios =  servJPAUsuario.listadoUsuarios() ;
	
		modelo.addAttribute("numPag", String.valueOf(numPagInt));
		// modelo.addAttribute("numRegPag", pagUsuario.getContent().size());
		modelo.addAttribute("pagUsuarios", listUsuarios);
		modelo.addAttribute("standardDate", new Date());
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "gestionWeb/usuarios/PaginacionUsuarios";
	}
	
	@RequestMapping("/usuariomenu")
	public String usuarioMenus(Model modelo, @RequestParam(value = "idUsuario", required = false) Long idUsuario){
		
		User usuarioMenu = servJPAUsuario.getMenusUsuario(idUsuario);
		modelo.addAttribute("aliasUsuario", usuarioMenu.getFullName());
		modelo.addAttribute("nombreUsuario", usuarioMenu.getUsername());
		
		modelo.addAttribute("usuarioMenu", usuarioMenu);
		
	//	modelo.addAttribute("opcionesMenuUsu", usuarioMenu.getMenuUsuario());
	//	Iterator<Menu> menu =  usuarioMenu.getMenuUsuario().iterator();
	//  while(menu.hasNext()){
    // 	Set<SubMenuNivel1> subMenuNiv1 = menu.next().getSubMenuNivel1();    	
	//   Iterator<SubMenuNivel1> subMenuNiv1Ite =  subMenuNiv1.iterator();
	//  	while(subMenuNiv1Ite.hasNext()){
	// 		 System.out.println (subMenuNiv1Ite.next().getTextoSubMenuN1());
	// 	}
	// }  
		
	  return "gestionWeb/UsuarioMenus.html";
	}
	 
	@RequestMapping(value = "/formeditarusuario", method = RequestMethod.GET)
	public String formEditarUsuario(Model modelo, @ModelAttribute(value="idUsuario") String idUsuario) {
	 BeanUsuarioWeb beanUsuarioWeb = new BeanUsuarioWeb();
	
	 beanUsuarioWeb = cargarBeansDatos.cargarBeanUsuario(servJPAUsuario.findIdUsuario(new Long(idUsuario)).get() );
	 beanUsuarioWeb.setRolUsuarioWeb(servJPAUsuario.obtenerRoles());
	
	 modelo.addAttribute("usuarioWeb", beanUsuarioWeb); 
		
	 return "GestionWeb/usuarios/FormEditarUsuario";
	}
	
	@RequestMapping(value = "/forminsertarusuario", method = RequestMethod.GET)
	public String insertarUsuario(Model modelo) {

	 BeanUsuarioWeb	beanUsuarioWeb = new BeanUsuarioWeb();
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
		
	   Set <Role> setRoles = new HashSet<>(); 
		 
	   User usuarioNuevo = validarUsuario(beanUsuarioWeb, codRole, false);

	   if ( usuarioNuevo.getPassword( ) != null )
	 	  {
		   usuarioNuevo.setId(beanUsuarioWeb.getIdUsuarioWeb());
		   usuarioNuevo.setEmail(beanUsuarioWeb.getEmailWeb());
		   usuarioNuevo.setEnabled(beanUsuarioWeb.isEnabledWeb());
		   usuarioNuevo.setIndEmpleado(beanUsuarioWeb.isIndEmpleadoWeb());
		   usuarioNuevo.setFullName(beanUsuarioWeb.getFullNameWeb());
		   usuarioNuevo.setUsername(beanUsuarioWeb.getUsernameWeb());
		   usuarioNuevo.setFecAltaUsuario(beanUsuarioWeb.getFecAltaUsuarioWeb());
		
		   Role role = new Role();
		   role.setId(new Integer(codRole.trim()));
		   setRoles.add(role);
		   usuarioNuevo.setRoles(setRoles);

		   servJPAUsuario.insertarUsuario(usuarioNuevo);
	 	  }
		
		modelo.addAttribute("usuarioWeb", beanUsuarioWeb);
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "redirect:/gestionWeb/usuarios/" + "pagusuarios";
		
	// return "GestionWeb/usuarios/FormEditarUsuario";
	}
	
	@RequestMapping(value = "/modificarusuario", method = RequestMethod.POST)
	public String modificarUsuario(Model modelo,
			@Valid @ModelAttribute("formUsuarioWeb") BeanUsuarioWeb beanUsuarioWeb, 
			BindingResult resultValidacion,
			@RequestParam(value = "rolAplicacion", required = true) String codRole) {

	  User usuario ;	
	  usuario = validarUsuario(beanUsuarioWeb, codRole, true);
	 	
	   if ( usuario.getPassword( ) != null )
	 	  {
		   servJPAUsuario.modificarUsuario(usuario);
	 	  }
	   // Pintar el error en pantalla mensajeErrorActualizacion
	
		return "redirect:/gestionWeb/usuarios/" + "pagusuarios";	
	//	return "GestionWeb/usuarios/FormEditarUsuario";
	}
	
	public User validarUsuario(BeanUsuarioWeb beanUsuarioWeb, String codRole, Boolean esModificacion) {
		
		 Boolean userError = new Boolean(false);
		 User usuarioNuevo = new User();
		 Set <Role> setRoles = new HashSet<>(); 
		 
		 mensajeErrorActualizacion = "";
		 
		if (! esModificacion ) 
			{
			if (servJPAUsuario.findByName(beanUsuarioWeb.getUsernameWeb().trim()) ) {
				System.out.println("Error username del Usuario esta es duplicado");
				mensajeErrorActualizacion= "Error username del Usuario esta es duplicado";
				userError = true;
			}
		
			if (! userError) {
				if (servJPAUsuario.findByEmail(beanUsuarioWeb.getEmailWeb().trim()) ) {
					System.out.println("Error el email esta duplicado");
					mensajeErrorActualizacion= "Error el email esta duplicado";
					userError = true;
				}
				}
		
			if (! userError) {
				if ( servJPAUsuario.findByFullName(beanUsuarioWeb.getFullNameWeb().trim() )){
					System.out.println("Error fullname del Usuario esta duplicado");
					mensajeErrorActualizacion= "Error fullname del Usuario esta duplicado";
					userError = true;
					}
				}
		}
	else
	{	
		
		usuarioNuevo.setId(beanUsuarioWeb.getIdUsuarioWeb() ) ;	
		
		User usuario = servJPAUsuario.findIdUsuario(beanUsuarioWeb.getIdUsuarioWeb()).get();
		
		if ( ! beanUsuarioWeb.getUsernameWeb().trim().equals( usuario.getUsername() )  ) {
			
			if (servJPAUsuario.findByName(beanUsuarioWeb.getUsernameWeb().trim()) ) {
				System.out.println("Error username del Usuario esta es duplicado");
				mensajeErrorActualizacion= "Error username del Usuario esta es duplicado";
				userError = true;
			}
 
		}
	
		if (! userError) {
			if ( ! beanUsuarioWeb.getEmailWeb().trim().equals( usuario.getEmail() ) ) {
				if (servJPAUsuario.findByEmail(beanUsuarioWeb.getEmailWeb().trim()) ) {
					System.out.println("Error el email esta duplicado");
					mensajeErrorActualizacion= "Error el email esta duplicado";
					userError = true;
				}
			}
		}
	
		if (! userError) {
			if ( ! beanUsuarioWeb.getFullNameWeb().trim().equals( usuario.getFullName() ) ) {
				if ( servJPAUsuario.findByFullName(beanUsuarioWeb.getFullNameWeb().trim() )){
					System.out.println("Error fullname del Usuario esta duplicado");
					mensajeErrorActualizacion= "Error fullname del Usuario esta duplicado";
					userError = true;
					}
				}
		}
	} // Fin if principal
		 
	if (! userError )
	 	{
	 // Encriptar password tecleado por el usuario
		GeneradorEncriptacion generadorEncriptacion = new GeneradorEncriptacion();
		String passordEncriptada = generadorEncriptacion.generarPasswordEncrip(beanUsuarioWeb.getPasswordWeb().trim());
			
		usuarioNuevo.setPassword(passordEncriptada); 	
		usuarioNuevo.setEmail(beanUsuarioWeb.getEmailWeb());
		usuarioNuevo.setEnabled(beanUsuarioWeb.isEnabledWeb());
		usuarioNuevo.setIndEmpleado(beanUsuarioWeb.isIndEmpleadoWeb());
		usuarioNuevo.setFullName(beanUsuarioWeb.getFullNameWeb());
		usuarioNuevo.setUsername(beanUsuarioWeb.getUsernameWeb());
		usuarioNuevo.setFecAltaUsuario(beanUsuarioWeb.getFecAltaUsuarioWeb());
			
		Role role = new Role();
		role.setId(new Integer(codRole.trim()));
		setRoles.add(role);
		
		usuarioNuevo.setRoles(setRoles);
		}
		return usuarioNuevo;
	}
}
