package com.myfactory.SBootWebProject.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfactory.SBootWebProject.beanForm.BeanCamposBusqueda;

import com.myfactory.SBootWebProject.beanForm.BeanEmpresaWeb;

import com.myfactory.SBootWebProject.beanForm.BeanProyectoWeb;

import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;

import com.myfactory.SBootWebProject.common.CrearBotoneraPag;
import com.myfactory.SBootWebProject.constantes.ConstantesAplicacion;

import com.myfactory.SBootWebProject.model.Empresa;
import com.myfactory.SBootWebProject.servicesJPA.ServJPA;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAEmpleado;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAEmpresa;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAMenu;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAMenusUsuario;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAProyecto;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAUsuario;

@Controller
@RequestMapping("/gestionWeb/empresas/")
public class ControllerWebEmpresas {
	
	@Autowired
	ServJPAMenusUsuario servJPAMenusUsuario;
	@Autowired
	ServJPAUsuario servJPAUsuario;
	@Autowired
	ServJPAMenu servJPAMenu;
	@Autowired
	public BeanUsuarioSession beanUsuarioSession;
	
	@Autowired
	ServJPA servJPA;
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
	
	@GetMapping("/formaltaempresa")
 	public String formularioAltaEmpresa(Model modelo)  {
		
	 BeanEmpresaWeb datosEmpresaWeb = new BeanEmpresaWeb ();
		
	 modelo.addAttribute("provinciasWeb", servJPA.getProvincia() );	
	 modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	 modelo.addAttribute("datosEmpresaWeb", datosEmpresaWeb);
	 
	 return "GestionWeb/empresas/FormAltaEmpresa";
	}

	@RequestMapping(value = "/altaempresa", method = RequestMethod.POST)
	public String altaEmpresa(@Valid @ModelAttribute("formEmpresaWeb") BeanEmpresaWeb formEmpresaWeb, 
				BindingResult resultValidacion,
				RedirectAttributes redirectAttrs,
				Model modelo, @RequestParam(value = "provinciaEmpresa", required = true) String codProvincia) {
		
		Empresa nuevaEmpresa = new Empresa();
		
		nuevaEmpresa.setNomEmpresa(formEmpresaWeb.getNomEmpresaWeb());
		nuevaEmpresa.setCodPostal (formEmpresaWeb.getCodPostalWeb());
		nuevaEmpresa.setCodProvincia(new Integer(codProvincia));
		nuevaEmpresa.setDirecion(formEmpresaWeb.getDirecionWeb());
		
		nuevaEmpresa.setEmailContacto1(formEmpresaWeb.getEmailContacto1Web());
		nuevaEmpresa.setEmailContacto2(formEmpresaWeb.getEmailContacto2Web());
		
		nuevaEmpresa.setTelefContacto1(formEmpresaWeb.getTelefContacto1Web());
		nuevaEmpresa.setTelefContacto2(formEmpresaWeb.getTelefContacto2Web());
		
		nuevaEmpresa.setNomContacto1(formEmpresaWeb.getNomContacto1Web() );
		nuevaEmpresa.setNomContacto2(formEmpresaWeb.getNomContacto2Web() );
		
		nuevaEmpresa.setFecAltaEmpresa(  formEmpresaWeb.getFecAltaEmpresaWeb() );
		nuevaEmpresa.setCif(formEmpresaWeb.getCIFWeb());
		
		servJPAEmpresa.altaEmpresa(nuevaEmpresa);
		
		return "redirect:/gestionWeb/empresas/" + "pagempresas";
	 
	// return "GestionWeb/empresa/FormEditarEmpresa";
	}
	
	@GetMapping("/formeditarempresa")
 	public String formularioEditarEmpresa(Model modelo, @RequestParam(value = "idEmpresa", required = false ) Integer idEmpresa)  {
 
	 Empresa empresa = servJPAEmpresa.buscarIdEmpresa(idEmpresa);
	 modelo.addAttribute("empresaWeb", cargarBeansDatos.cargarBeanEmpresa(empresa));
	 
	return "GestionWeb/empresas/FormEditarEmpresa";
	}
	
	@RequestMapping("/pagempresas")
	public String paginacionEmpresas(Model modelo, @RequestParam(value = "numPag", required = false) String numPag,
												      @RequestParam(value = "tpoAccion", required = false) String tpoAccion,
	 											      @RequestParam(value = "numPos", required = false) String numPos,
	 											      @RequestParam(value = "numBloquePag", required = false) Integer numBloquePag,
	 											      @RequestParam(value = "empresasBus", required = false) String empresaBus,
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

		if (busquedaCampo.getNomEmpresa()  == null)
			{
			busquedaCampo = new BeanCamposBusqueda();
			
			if (empresaBus == null)
				{
				 busquedaCampo.setNomEmpresa("");
				}
			  else
				{
				  busquedaCampo.setNomEmpresa(empresaBus);
				}
			}
			else
			{
			 busquedaCampo.setNomEmpresa(busquedaCampo.getNomEmpresa());
			}
			
		
		modelo.addAttribute("objBusqueda", busquedaCampo);

		Page<Empresa> pagEmpresas = servJPAEmpresa.pagEmpresas(new Integer(numPagInt), ConstantesAplicacion.REG_POR_PAGINA, busquedaCampo.getNomEmpresa());
		modelo.addAttribute("pagGenerica", pagEmpresas);
		modelo.addAttribute("numPag", String.valueOf(numPagInt));
		modelo.addAttribute("numRegPag", pagEmpresas.getContent().size());
	
		try
		 {
		   botoneraPag = new CrearBotoneraPag();
		   paramBotonera = CrearBotoneraPag.calculaNumPagBotoneraNue(numPagInt, tpoAccion,  numPos, pagEmpresas.getTotalElements(), new Double(numBloquePag.intValue()) );
		 }
		catch (Exception exp)
		 {
			exp.printStackTrace();
		 }
		
		String URLPag = "/gestionWeb/empresas/pagempresas?numPag=" ;
		
		CrearBotoneraPag.montarEnlacesBotonera(paramBotonera, modelo, numPagInt, URLPag, busquedaCampo.getNomEmpresa().trim());

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
		
		if (pagEmpresas.isLast()  )
			{
			modelo.addAttribute("indUltPag", "S");
			}
		else
			{
			modelo.addAttribute("indUltPag", "N");
			}
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

		return "GestionWeb/empresas/PagEmpresas";
	}
}
