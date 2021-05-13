package com.myfactory.SBootWebProject.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myfactory.SBootWebProject.beanForm.BeanErrorValidacion;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioWeb;
import com.myfactory.SBootWebProject.constantes.ConstantesErroresAplicacion;
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
		Iterable <User> listUsuarios =  servJPAUsuario.listaUsuariosOrdenByName();
	
		modelo.addAttribute("numPag", String.valueOf(numPagInt));
		// modelo.addAttribute("numRegPag", pagUsuario.getContent().size());
		modelo.addAttribute("pagUsuarios", listUsuarios);
		modelo.addAttribute("standardDate", new Date());
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "gestionWeb/usuarios/PaginacionUsuarios";
	}
	
	@RequestMapping("/listausuarioshist")
	public String listaUsuariosHist(Model modelo) {
		
	 modelo.addAttribute("pagUsuarios", servJPAUsuario.listadoUsuariosHistorico());
	 modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

	 return "gestionWeb/usuarios/PaginacionUsuariosHistorico";
	}

	@RequestMapping(value = "/formeditarusuario", method = RequestMethod.GET)
	public String formEditarUsuario(Model modelo, @ModelAttribute(value="idUsuario") String idUsuario) {
	 BeanUsuarioWeb beanUsuarioWeb = new BeanUsuarioWeb();
	
	 beanUsuarioWeb = cargarBeansDatos.cargarBeanUsuario(servJPAUsuario.findIdUsuario(new Long(idUsuario)).get() );
	 beanUsuarioWeb.setRolUsuarioWeb(servJPAUsuario.obtenerRoles());
	
	 modelo.addAttribute("usuarioWeb", beanUsuarioWeb); 
	modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
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
		
		 BeanErrorValidacion datosError = null;
		// Set <Role> setRoles = new HashSet<>();  
		 Map<String, Object> resultValUsuario;
		 User usuario ;
		 
		 resultValUsuario = validarUsuario(beanUsuarioWeb, codRole, false);

		 datosError = (BeanErrorValidacion) resultValUsuario.get("errorValidacion");
		
		 if (datosError.getCodError().intValue() != 0 ) 
		    {
			 modelo.addAttribute("errorValidacion" , true);
			 modelo.addAttribute("mensajeError", datosError.getCodError().toString() + ", " + datosError.getDesError() );
			 
			 modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
			 modelo.addAttribute("usuarioWeb", beanUsuarioWeb);
			 return "GestionWeb/usuarios/FormInsertarUsuario";    
		    }
		  else
		    {
			 usuario = (User) resultValUsuario.get("empleadoValidacion");	 
		  // Dar de alta Usuario
			 usuario = servJPAUsuario.insertarUsuario(usuario);	
				
			 return "redirect:/gestionWeb/usuarios/" + "pagusuarios";
		    }
		 

	/*   if ( usuarioNuevo.getPassword( ) != null )
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
		   
		   return "redirect:/gestionWeb/usuarios/" + "pagusuarios";
	 	  }
	   else
	   	 {
		   modelo.addAttribute("usuarioWeb", beanUsuarioWeb);
		   return "GestionWeb/usuarios/FormInsertarUsuario"; 
	   	 } */
	}
	
	@RequestMapping(value = "/formbajausuario")
	public String formBajaUsuario(Model modelo, 
									@RequestParam(value = "idUsuario", required = true) Long idUsuario) { 
	
	 BeanUsuarioWeb beanUsuarioWeb = new BeanUsuarioWeb();
	 beanUsuarioWeb = cargarBeansDatos.cargarBeanUsuario(servJPAUsuario.findIdUsuario(new Long(idUsuario)).get() );
	 
	 beanUsuarioWeb.setFecBajaUsuarioWeb(Calendar.getInstance());
	 
	 modelo.addAttribute("usuarioWeb", beanUsuarioWeb);
	 modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	 return "GestionWeb/usuarios/FormBajaUsuario";
 	}
	
	@RequestMapping(value = "/bajausuario", method = RequestMethod.POST)
	public String bajaUsuario(Model modelo, 
			@Valid @ModelAttribute("formUsuarioWeb") BeanUsuarioWeb beanUsuarioWeb) { 
	
	User usuario = servJPAUsuario.findIdUsuario(beanUsuarioWeb.getIdUsuarioWeb() ).get() ;
	usuario.setEnabled(false);
	usuario.setFecBajaUsuario(beanUsuarioWeb.getFecBajaUsuarioWeb() );
	
	servJPAUsuario.modificarUsuario(usuario); 

	return "redirect:/gestionWeb/usuarios/" + "pagusuarios";
	}
	
	@RequestMapping(value = "/activarusuario", method = RequestMethod.POST)
	public String reactivarUsuario(Model modelo,
			@Valid @ModelAttribute("usuarioWeb") BeanUsuarioWeb beanUsuarioWeb) { 
	
	User usuario = servJPAUsuario.findIdUsuario(beanUsuarioWeb.getIdUsuarioWeb()).get() ;
 // Activar Usuario
	usuario.setEnabled(true);
	usuario.setFecAltaUsuario( beanUsuarioWeb.getFecAltaUsuarioWeb() );
	
	servJPAUsuario.modificarUsuario(usuario); 

	return "redirect:/gestionWeb/usuarios/" + "pagusuarios";
	}
	
	@RequestMapping(value = "/modificarusuario", method = RequestMethod.POST)
	public String modificarUsuario(Model modelo,
			@Valid @ModelAttribute("formUsuarioWeb") BeanUsuarioWeb beanUsuarioWeb, 
			BindingResult resultValidacion,
			@RequestParam(value = "rolAplicacion", required = true) String codRole) {
		
		    boolean esModificacion = true;
		    BeanErrorValidacion datosError = null;
			// Set <Role> setRoles = new HashSet<>();  
			Map<String, Object> resultValUsuario;
			User usuario;
			 
			 resultValUsuario = validarUsuario(beanUsuarioWeb, codRole, esModificacion);

			 datosError = (BeanErrorValidacion) resultValUsuario.get("errorValidacion");
			
			 if (datosError.getCodError().intValue() != 0 ) 
			    {
				 modelo.addAttribute("errorValidacion" , true);
				 modelo.addAttribute("mensajeError", datosError.getCodError().toString() + ", " + datosError.getDesError() );
				 
				 modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
				 modelo.addAttribute("usuarioWeb", beanUsuarioWeb);
				 return "GestionWeb/usuarios/FormEditarUsuario";    
			    }
			  else
			    {
				 usuario = (User) resultValUsuario.get("empleadoValidacion");	 
			  // Modficar un Usuario
				 servJPAUsuario.modificarUsuario(usuario);
					
				 return "redirect:/gestionWeb/usuarios/" + "pagusuarios";
			    }
 
	}
	
	@RequestMapping(value = "/formcambiarpass")
	public String formCambiarPassword(Model modelo, 
									@RequestParam(value = "idUsuario", required = true) Long idUsuario) { 
	
	 BeanUsuarioWeb beanUsuarioWeb = new BeanUsuarioWeb();
	 beanUsuarioWeb = cargarBeansDatos.cargarBeanUsuario(servJPAUsuario.findIdUsuario(new Long(idUsuario)).get() );
	
	 modelo.addAttribute("usuarioWeb", beanUsuarioWeb);
	 modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	 return "GestionWeb/usuarios/FormCambiarPassword";
 	}
	
	@RequestMapping(value = "/cambiarpass", method = RequestMethod.POST)
	public String formCambiarPassword(Model modelo, 
			@Valid @ModelAttribute("formUsuarioWeb") BeanUsuarioWeb beanUsuarioWeb) { 
	
		User usuario = servJPAUsuario.findIdUsuario(beanUsuarioWeb.getIdUsuarioWeb() ).get() ;

		// Encriptar la nueva password tecleada por el usuario
		GeneradorEncriptacion generadorEncriptacion = new GeneradorEncriptacion();
		String passWordEncriptada = generadorEncriptacion.generarPasswordEncrip(beanUsuarioWeb.getPasswordWeb().trim());
	
		usuario.setPassword(passWordEncriptada);
	
		servJPAUsuario.modificarUsuario(usuario); 

		return "redirect:/gestionWeb/usuarios/" + "pagusuarios";
	}
 
	
	@RequestMapping(value = "/formactivar")
	public String formActivarUsuario(Model modelo, @RequestParam(value = "idUsuario", required = true) Long idUsuario) { 
	 BeanUsuarioWeb beanUsuarioWeb = new BeanUsuarioWeb();
	 
	 beanUsuarioWeb = cargarBeansDatos.cargarBeanUsuario(servJPAUsuario.findIdUsuario(new Long(idUsuario)).get() );
	 
	 beanUsuarioWeb.setFecAltaUsuarioWeb(Calendar.getInstance() );
	 
	 modelo.addAttribute("usuarioWeb", beanUsuarioWeb);
	 modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	 return "GestionWeb/usuarios/FormActivarUsuario";
 	}
	
	@RequestMapping(value = "/activarusuario")
	public String activarUsuario(Model modelo, @RequestParam(value = "idUsuario", required = true) Long idUsuario) { 
	 BeanUsuarioWeb beanUsuarioWeb = new BeanUsuarioWeb();

	 User usuario = servJPAUsuario.findIdUsuario(beanUsuarioWeb.getIdUsuarioWeb() ).get() ;
	 usuario.setEnabled(true);
	 servJPAUsuario.modificarUsuario(usuario); ;
	 
	 beanUsuarioWeb.setFecAltaUsuarioWeb(Calendar.getInstance() );
	 
	 modelo.addAttribute("usuarioWeb", beanUsuarioWeb);
	 modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	 return "GestionWeb/usuarios/FormActivarUsuario";
 	}
	
	
	public Map<String, Object> validarUsuario(BeanUsuarioWeb beanUsuarioWeb, String codRole, Boolean esModificacion) {
		
	Boolean userError = new Boolean(false);
	User usuarioNuevo = new User();
	Set <Role> setRoles = new HashSet<>(); 
	Map<String, Object> resultadoValidacion = new HashMap<>();
	BeanErrorValidacion datosErrorValidacion = new BeanErrorValidacion(new Integer(0));
	User usuario = null;

	if (! esModificacion ) 
		{
		if ( servJPAUsuario.findByFullName(beanUsuarioWeb.getFullNameWeb().trim() )){
			datosErrorValidacion.setCodError(ConstantesErroresAplicacion.COD_ERROR_FULLNAME_DUPLICADO);
			datosErrorValidacion.setDesError(ConstantesErroresAplicacion.ERROR_FULLNAME_DUPLICADO );
			userError = true;
			}
		
			if (! userError) {
				if (servJPAUsuario.findByName(beanUsuarioWeb.getUsernameWeb().trim()) ) {
					datosErrorValidacion.setCodError(ConstantesErroresAplicacion.COD_ERROR_USUARIO_DUPLICADO);
					datosErrorValidacion.setDesError(ConstantesErroresAplicacion.ERROR_USUARIO_DUPLICADO);
					userError = true;
				}
			}
			
			if (! userError) {
				if (servJPAUsuario.findByEmail(beanUsuarioWeb.getEmailWeb().trim()) ) {
					datosErrorValidacion.setCodError(ConstantesErroresAplicacion.COD_ERROR_EMAIL_DUPLICADO);
					datosErrorValidacion.setDesError(ConstantesErroresAplicacion.ERROR_EMAIL_DUPLICADO);
					userError = true;
					}
				}
		}
	   else
		{	
		usuario = servJPAUsuario.findIdUsuario(beanUsuarioWeb.getIdUsuarioWeb()).get();
		// Si cambia algun campo importante los buscamos si  ya existe y no esta duplicado.
		
			if ( ! beanUsuarioWeb.getFullNameWeb().trim().equals( usuario.getFullName() ) ) {
				if ( servJPAUsuario.findByFullName(beanUsuarioWeb.getFullNameWeb().trim() )){
					datosErrorValidacion.setCodError(ConstantesErroresAplicacion.COD_ERROR_FULLNAME_DUPLICADO);
					datosErrorValidacion.setDesError(ConstantesErroresAplicacion.ERROR_FULLNAME_DUPLICADO );
					userError = true;
					}
				}

			if (! userError) {
				if (! usuario.getUsername().equals(beanUsuarioWeb.getUsernameWeb().trim()) ) {
					if ( servJPAUsuario.findByName( beanUsuarioWeb.getUsernameWeb().trim()) )
						{
						datosErrorValidacion.setCodError(ConstantesErroresAplicacion.COD_ERROR_USUARIO_DUPLICADO);
						datosErrorValidacion.setDesError(ConstantesErroresAplicacion.ERROR_USUARIO_DUPLICADO);
						userError = true;
					}
				}
			}
		
		if (! userError) {
			if ( ! usuario.getEmail().equals(beanUsuarioWeb.getEmailWeb().trim() ) ) {
				if (servJPAUsuario.findByEmail(beanUsuarioWeb.getEmailWeb().trim()) ) {
					datosErrorValidacion.setCodError(ConstantesErroresAplicacion.COD_ERROR_EMAIL_DUPLICADO);
					datosErrorValidacion.setDesError(ConstantesErroresAplicacion.ERROR_EMAIL_DUPLICADO);
					userError = true;
				}
			}
		}
		
		}
	
	if (! userError) 
		{
		usuarioNuevo.setEmail(beanUsuarioWeb.getEmailWeb());
		usuarioNuevo.setIndEmpleado(beanUsuarioWeb.isIndEmpleadoWeb());
		usuarioNuevo.setFullName(beanUsuarioWeb.getFullNameWeb());
		usuarioNuevo.setUsername(beanUsuarioWeb.getUsernameWeb());
		usuarioNuevo.setFecAltaUsuario(beanUsuarioWeb.getFecAltaUsuarioWeb());
		
		Role role = new Role();
		role.setId(new Integer(codRole.trim()));
		setRoles.add(role);
		
		usuarioNuevo.setRoles(setRoles);
		 
		if (! esModificacion)
	 		{
	 	 // Encriptar password tecleado por el usuario
			GeneradorEncriptacion generadorEncriptacion = new GeneradorEncriptacion();
			String passordEncriptada = generadorEncriptacion.generarPasswordEncrip(beanUsuarioWeb.getPasswordWeb().trim());
			usuarioNuevo.setEnabled(true);
			usuarioNuevo.setPassword(passordEncriptada); 	
	 		}
		else
			{
			usuarioNuevo.setEnabled(true);
			usuarioNuevo.setId(beanUsuarioWeb.getIdUsuarioWeb());	
			usuarioNuevo.setPassword(usuario.getPassword());
			}
		}
		resultadoValidacion.put("empleadoValidacion", usuarioNuevo);
		resultadoValidacion.put("errorValidacion" , datosErrorValidacion);

		return resultadoValidacion;
	}
}
