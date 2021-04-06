package com.myfactory.SBootWebProject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfactory.SBootWebProject.beanForm.BeanCamposBusqueda;
import com.myfactory.SBootWebProject.beanForm.BeanProyectoWeb;
import com.myfactory.SBootWebProject.beanForm.BeanPrueba1;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.beanForm.Person;
import com.myfactory.SBootWebProject.common.CrearBotoneraPag;
import com.myfactory.SBootWebProject.constantes.ConstantesAplicacion;
import com.myfactory.SBootWebProject.model.Empleado;
import com.myfactory.SBootWebProject.model.Empresa;
import com.myfactory.SBootWebProject.model.Proyecto;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAEmpleado;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAEmpresa;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAMenu;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAMenusUsuario;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAProyecto;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAUsuario;

@Controller
@RequestMapping("/gestionWeb/proyectos")
public class ControllerWebProyectos {
	
	@Autowired
	ServJPAMenusUsuario servJPAMenusUsuario;
	
	@Autowired
	ServJPAUsuario servJPAUsuario;

	@Autowired
	ServJPAMenu servJPAMenu;
	
	@Autowired
	public BeanUsuarioSession beanUsuarioSession;
	
	@Autowired
	ServJPAEmpresa  servJPAEmpresa;
	@Autowired
	ServJPAProyecto servJPAProyecto;
	@Autowired
	ServJPAEmpleado servJPAEmpleado;
	@Autowired
	BeanProyectoWeb beanEmpleadoWeb;
	@Autowired
	CargarBeansDatos cargarBeansDatos;
	
	public  List<BeanPrueba1> lPrueba1 = new ArrayList<BeanPrueba1>();

	@GetMapping("/formaltaproyecto")
 	public String formularioAltaEmpresa(Model modelo)  {
		
	 BeanProyectoWeb datosProyectoWeb = new BeanProyectoWeb ();
			
	 modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	 
	 List<Empresa> listEmpresasDisponibles = new ArrayList<Empresa>();
	 obtenerEmpresasDisponibles(listEmpresasDisponibles);
	 
	 List<Empleado> listEmpleadosDisponibles = new ArrayList<Empleado>();
	 obtenerEmpleadosDisponibles(listEmpleadosDisponibles);
	 
	 BeanPrueba1 prueba1 = new BeanPrueba1();
	 prueba1.setApellidosWeb("1");
	 prueba1.setNombreWeb("2");
	 
	 lPrueba1.add(prueba1);

	 modelo.addAttribute("lprueba", lPrueba1);
	 modelo.addAttribute("listaEmpresasProyecto", obtenerEmpresasDisponibles(listEmpresasDisponibles));
	 modelo.addAttribute("listaEmpleadosProyecto", obtenerEmpleadosDisponibles(listEmpleadosDisponibles));
	 
	 modelo.addAttribute("datosProyectoWeb", datosProyectoWeb);
	 
	 return "GestionWeb/proyectos/FormAltaProyecto";
	}

	@RequestMapping(value = "/altaproyecto", method = RequestMethod.POST)
	public String altaProyecto(@Valid @ModelAttribute("formProyectoWeb") BeanProyectoWeb formProyectoWeb, 
				BindingResult resultValidacion,
				RedirectAttributes redirectAttrs,
				Model modelo, @RequestParam(value = "provinciaEmpresa", required = false) String codProvincia) {
		
		Proyecto nuevoProyecto = new Proyecto();
		
		// nuevoProyecto.setNomProyecto(formProyectoWeb.getNomProyecto() );
		nuevoProyecto.setImpProyecto( formProyectoWeb.getImpProyectoWeb());
		
		servJPAProyecto.altaProyecto(nuevoProyecto);
		
		return "redirect:/gestionWeb/proyecto/" + "pagproyectos";
	}
	
	@RequestMapping("/pagproyectos")
	public String paginacionProyectos(Model modelo, @RequestParam(value = "numPag", required = false) String numPag,
												      @RequestParam(value = "tpoAccion", required = false) String tpoAccion,
	 											      @RequestParam(value = "numPos", required = false) String numPos,
	 											      @RequestParam(value = "numBloquePag", required = false) Integer numBloquePag,
	 											      @RequestParam(value = "proyectosBus", required = false) String proyectoBus,
	 											      @ModelAttribute("objBusqueda") BeanCamposBusqueda busquedaCampo )
	{
	 // Con esta variable sabemos la pagina exacta, dentro de todas paginacias posibles,  de donde llega a la paginacion.
		int numPagInt = 0;
		int numPosInt = 0;
		HashMap<String, Integer>  paramBotonera = null;
		CrearBotoneraPag botoneraPag = null;
		
		if (numBloquePag == null)
			{
			numBloquePag = 0;
			}

		// La primera vez que entra
		if (numPag == null && tpoAccion == null)
			{
			numPagInt = 0;
			}
		  else
			//No es la primera vez que entra
		  	{
			// Ha pinchado o avance o retroceso serguro
			if (tpoAccion != null)
				{
					if (tpoAccion.equals("avan")) {
						numPagInt = Integer.parseInt(numPag) + 1;
						} 
					else 
						{
						numPagInt = Integer.parseInt(numPag) - 1;
						}	
				
				}
			else
				{
				// Ha pinchado el numero de pagina
				numPagInt = Integer.parseInt(numPag);
				}
		}

		if (busquedaCampo.getNomProyecto() == null)
			{
			busquedaCampo = new BeanCamposBusqueda();
			
			if (proyectoBus == null)
				{
				busquedaCampo.setNomProyecto("");
				}
			  else
				{
				  busquedaCampo.setNomProyecto(proyectoBus);
				}
			}
			else
			{
				busquedaCampo.setNomProyecto(busquedaCampo.getNomProyecto());
			}
		
		modelo.addAttribute("objBusqueda", busquedaCampo);

		Page<Proyecto> pagProyectos = servJPAProyecto.pagProyectos(new Integer(numPagInt), ConstantesAplicacion.REG_POR_PAGINA, busquedaCampo.getNomProyecto().trim());
		modelo.addAttribute("pagGenerica", pagProyectos);
		modelo.addAttribute("numPag", String.valueOf(numPagInt));
		modelo.addAttribute("numRegPag", pagProyectos.getContent().size());
	
		try
		 {
		   botoneraPag = new CrearBotoneraPag();
		   paramBotonera = CrearBotoneraPag.calculaNumPagBotoneraNue(numPagInt, tpoAccion,  numPos, pagProyectos.getTotalElements(), new Double(numBloquePag.intValue()) );
		 }
		catch (Exception exp)
		 {
			exp.printStackTrace();
		 }
		
		String URLPag = "/gestionWeb/proyectos/pagproyectos?numPag=" ;
		
		CrearBotoneraPag.montarEnlacesBotonera(paramBotonera, modelo, numPagInt, URLPag, busquedaCampo.getNomProyecto().trim());

	 // Si ha pinchado avance o retroceso de pagina.
		if (tpoAccion != null)
			{
			// Detectamoos cambio de bloque ponerlo
			if (paramBotonera.get("numBloquePag").intValue() != numBloquePag.intValue()  )
				{
				if (tpoAccion.equals("avan")) {
					numPosInt = 1;
					}
				else
					{
					numPosInt = 5;	 
					}
				}
				else
				{
			//  Si es el mismo bloque la paginacion
					if (paramBotonera.get("numBloquePag").intValue() == 0)
					{
					numPosInt = numPagInt + 1;	
					}
					else
					{
					numPosInt = numPagInt - 5 ;
					}
				
				}
			}
		   else
			{
			   // Si es primera vez que entra
			 if (numPos == null)
				{
				 numPosInt = 1;
				}
			   else
				{	
				   // Si ha pinchado boton pagina
 			 numPosInt = Integer.parseInt(numPos);
				}
			}

		
		switch (numPosInt) {
		case 1:
			modelo.addAttribute("numPagAct1", "S" );
			break;
		case 2:
			modelo.addAttribute("numPagAct2", "S" );
			break;
		case 3:
			modelo.addAttribute("numPagAct3", "S" );
			break;
		case 4:
			modelo.addAttribute("numPagAct4", "S" );
			break;
		case 5:
			modelo.addAttribute("numPagAct5", "S" );
			break;
		}
		

		if ( pagProyectos.isLast()  )
			{
			modelo.addAttribute("indUltPag", "S");
			}
		else
			{
			modelo.addAttribute("indUltPag", "N");
			}
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

		return "GestionWeb/proyectos/PagProyectos";
	}
	
	private List<Empresa> obtenerEmpresasDisponibles(List <Empresa> listaEmpresaYaSelec)
	{
	  List<Empresa> listEmpresasDisponibles = new ArrayList<Empresa>();
	  
	  Iterator<Empresa> listEmpreIter = servJPAEmpresa.listEmpresasProyecto().iterator();
	  Empresa empresaI = null;
			 
	  while(listEmpreIter.hasNext())
	  {
		empresaI = listEmpreIter.next();
		Boolean encontradoEnEmpresasSelec = false;
				
		for (Empresa eleEmpresa : listaEmpresaYaSelec) {

			if (eleEmpresa.getIdEmpresa().equals(empresaI.getIdEmpresa() ) )
			   	{
				encontradoEnEmpresasSelec = true;
			    break;
			    }
			 }
	   
			if (! encontradoEnEmpresasSelec)
				{
				listEmpresasDisponibles.add(empresaI );
				}
	 }
	 
	return listEmpresasDisponibles;
	}	
	
	private List<Empleado> obtenerEmpleadosDisponibles(List <Empleado> listaEmpleadosYaSelec)
	{
	  List<Empleado> listEmpleadosDisponibles = new ArrayList<Empleado>();
	  
	  Iterator<Empleado> listEmpleIter = servJPAEmpleado.listEmpleadosProyecto().iterator();
	  Empleado empleadoI = null;
			 
	  while(listEmpleIter.hasNext())
	  {
		empleadoI = listEmpleIter.next();
		Boolean encontradoEnEmpresasSelec = false;
				
		for (Empleado eleEmpleado : listaEmpleadosYaSelec) {

			if (eleEmpleado.getIdEmpleado().equals(empleadoI.getIdEmpleado() ) )
			   	{
				encontradoEnEmpresasSelec = true;
			    break;
			    }
			 }
	   
			if (! encontradoEnEmpresasSelec)
				{
				listEmpleadosDisponibles.add(empleadoI);
				}
	 }
	 
	return listEmpleadosDisponibles;
	}
 
	@RequestMapping(value = "/anadirempresajax" /*, consumes = MediaType.APPLICATION_JSON_VALUE */ )
//	public String anadirEmpresaAjax(@RequestParam(name="idEmpresa") String idEmpresa, 
//									@RequestParam(name="objList")  List<BeanPrueba1> lPru1, Model modelo) {
		
	//	public String anadirEmpresaAjax(/* @RequestParam(name="idEmpresa") String idEmpresa, */
	//			@RequestParam(name="search")  Person per1, Model modelo) {
	
	
	public  @ResponseBody String  getSearchUserProfiles(@RequestBody Person per1, HttpServletRequest request) {

	    System.out.println (per1);
		List<Empresa> listEmpresasDisponibles2 = new ArrayList<Empresa>();
		Empresa empresa = new Empresa();
//		empresa.setIdEmpresa(new Integer (idEmpresa));
		empresa.setNomEmpresa("EEE"); 
		listEmpresasDisponibles2.add(empresa);
		
		List<Empresa> listEmpresasSelec = new ArrayList<Empresa>();
		Empresa empresa2 = new Empresa();
	//	empresa2.setIdEmpresa(new Integer (idEmpresa));
		empresa2.setNomEmpresa("EEE"); 
		listEmpresasDisponibles2.add(empresa2);
		
	//	modelo.addAttribute("listaEmprSelecProyecto", listEmpresasSelec);
//		modelo.addAttribute("listaEmprDispProyecto", listEmpresasDisponibles2);
		
		return "GestionWeb/fragments/SeleccionEmpresasProyecto :: SelecEmprProyecto";
	}
	
	@RequestMapping(value = "/suprimirempresajax" )
	public String suprimirEmpresAjax(@RequestParam("idEmpresa") String idEmpresa, Model modelo) {
	    List<Empresa> listEmpresasDisponibles = new ArrayList<Empresa>();
		Empresa empresa = new Empresa();
		empresa.setIdEmpresa(new Integer (idEmpresa));
		empresa.setNomEmpresa("EEE"); 
		listEmpresasDisponibles.add(empresa);
 
		modelo.addAttribute("listaEmpresasProyecto", listEmpresasDisponibles);
		return "GestionWeb/fragments/SeleccionEmpresasProyecto :: SelecEmprProyecto";
	}
	
	@RequestMapping(value = "/anadirempleadoajax" )
	public String anadirEmpleadoAjax(@RequestParam("idEmpleado") String idEmpleado, Model modelo) {
	    List<Empleado> listEmpleadoDisponibles = new ArrayList<Empleado>();
	    Empleado empleado = new Empleado();
		empleado.setIdEmpleado(new Long (idEmpleado));
		empleado.setNombre("EEE"); 
		listEmpleadoDisponibles.add(empleado);
 
		modelo.addAttribute("listaEmpresasProyecto", listEmpleadoDisponibles);
		return "GestionWeb/fragments/SeleccionEmpleadosProyecto :: SelecEmplProyecto";
	}
	
	@RequestMapping(value = "/suprimirempleadoajax" )
	public String suprimirEmpleadoAjax(@RequestParam("idEmpleado") String idEmpleado, Model modelo) {
	    List<Empleado> listEmpresasDisponibles = new ArrayList<Empleado>();
	    Empleado empleado = new Empleado();
	    empleado.setIdEmpleado(new Long (idEmpleado));
	    empleado.setNombre("EEE"); 
		listEmpresasDisponibles.add(empleado);
 
		modelo.addAttribute("listaEmpresasProyecto", listEmpresasDisponibles);
		return "GestionWeb/fragments/SeleccionEmpresasProyecto :: SelecEmprProyecto";
	}
	
	
	
}
