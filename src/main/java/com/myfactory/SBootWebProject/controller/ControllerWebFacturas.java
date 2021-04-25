package com.myfactory.SBootWebProject.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfactory.SBootWebProject.beanForm.BeanClienteWeb;
import com.myfactory.SBootWebProject.beanForm.BeanErrorValidacion;
import com.myfactory.SBootWebProject.beanForm.BeanFacturaLineas;
import com.myfactory.SBootWebProject.beanForm.BeanFacturaWeb;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.common.CrearBotoneraPag;
import com.myfactory.SBootWebProject.constantes.ConstantesAplicacion;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.Empleado;
import com.myfactory.SBootWebProject.model.Factura;
import com.myfactory.SBootWebProject.model.FacturaLineas;
import com.myfactory.SBootWebProject.model.FormaPago;
import com.myfactory.SBootWebProject.model.Menu;
import com.myfactory.SBootWebProject.model.Pais;
import com.myfactory.SBootWebProject.model.PuestoTrabajo;
import com.myfactory.SBootWebProject.model.SubMenuNivel1;
import com.myfactory.SBootWebProject.servicesJPA.ServJPA;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAFactura;

@Controller
@RequestMapping("/gestionWeb/facturas")
public class ControllerWebFacturas {

	@Autowired
	ServJPA servicioJPA;
	@Autowired
	ServJPAFactura servJPAFactura;
	@Autowired
	BeanClienteWeb facturaWeb;
	@Autowired
	CargarBeansDatos cargarBeansDatos;
	@Autowired
	BeanUsuarioSession beanUsuarioSession;
	
	private static final int numFac = 22;
	
	@GetMapping("/formeditarfactura")
	public String formularioEditarFactura(Model modelo, @RequestParam(value = "idFactura", required = false) String idFactura)  {

		Optional<Factura> factura = servicioJPA.buscarIdFactura(new Integer(Integer.parseInt(idFactura)));
		
		modelo.addAttribute("facturaWeb", cargarBeansDatos.cargarBeanFactura(factura.get()) );
		modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "gestionWeb/facturas/FormEditarFactura";
	}

	@GetMapping("/formaltafactura")
	public String formAltaFactura(Model modelo)  {
	
	 	BeanFacturaWeb facturaWeb = new BeanFacturaWeb ();
	 	
	 	BeanFacturaLineas beanFacturaLineas = new BeanFacturaLineas();
		List<BeanFacturaLineas> lineasFactura = new ArrayList<BeanFacturaLineas>();
		lineasFactura.add(beanFacturaLineas);
		facturaWeb.setBeanFacturaLineas(lineasFactura);
	 	
		facturaWeb.setFecFacturaWeb(Calendar.getInstance());
		
		modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
		
		modelo.addAttribute("datosFacturaWeb", facturaWeb);
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "gestionWeb/facturas/FormAltaFactura";
	}

	@RequestMapping(value = "/insertarfactura", method = RequestMethod.POST)
	public String altaFactura(@ModelAttribute("datosFacturaWeb") @Valid BeanFacturaWeb datosFacturaWeb,
		BindingResult resultValidacion, 
		Model modelo, @RequestParam(value = "formaPago", required = true) String formaPago) {
		
		Factura factura = new Factura();
		BeanErrorValidacion datosError = null;
		BeanErrorValidacion datosErrorLinea  = null;
		 
		Set<FacturaLineas> lineasFactu = new HashSet<FacturaLineas>();

	 	if (! resultValidacion.hasErrors()) {
	
	 		modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
		
	 		Map<String, Object> resultValFactura;
	 		resultValFactura = validarDatosFactura(datosFacturaWeb, formaPago);
		
	 		datosError = (BeanErrorValidacion) resultValFactura.get("errorValidacion");
			
	 		if (datosError.getCodError().intValue() != 0 ) 
	 			{
	 			modelo.addAttribute("errorValidacion" , true);
	 			modelo.addAttribute("mensajeError", datosError.getCodError().toString() + ", " + datosError.getDesError() );
	 			}
	 		else
	 			{
		
			  for (BeanFacturaLineas elemenLinFactura : datosFacturaWeb.getBeanFacturaLineas())
	   				{
				  	FacturaLineas lineasFactura = new FacturaLineas();
				  	Map<String, Object> resultValLineaFactura;
				  	resultValLineaFactura = validarLineasFactura(elemenLinFactura);
			 
				  	datosErrorLinea = (BeanErrorValidacion) resultValFactura.get("errorValidacion");
					
					 if (datosErrorLinea.getCodError().intValue() != 0 ) 
					 	{
						 modelo.addAttribute("errorValidacion" , true);
						 modelo.addAttribute("mensajeError", datosErrorLinea.getCodError().toString() + ", " + datosErrorLinea.getDesError() );
						 break;
					 	}
					 else
					 	{
						FacturaLineas factuLinea = (FacturaLineas) resultValLineaFactura.get("facturaLineaValidacion" );
						lineasFactu.add(factuLinea);  
					 	}
					 
	   			}
		    }
		
		// factura.setSituacionFactura(situacionFactura);
		factura.setFacturaLineas(lineasFactu);
      // Dar de alta en cascada
		
		factura = servJPAFactura.altaFactura(factura);
		
	 	}
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	//	redirectAttrs.addFlashAttribute("mensaje", "Agregado correctamente").addFlashAttribute("clase", "success");

		return "gestionWeb/facturas/FormInsertarFactura";
	}
	
	@RequestMapping(value = "/modiffactura", method = RequestMethod.POST)
	public String modifFactura(@ModelAttribute("datosFacturaWeb") @Valid BeanFacturaWeb datosFacturaWeb,
			BindingResult resultValidacion, 
			 Model modelo, @RequestParam(value = "formaPago", required = true) String formaPago) {

		Factura factura = new Factura();
		BeanErrorValidacion datosError = null;
		BeanErrorValidacion datosErrorLinea  = null;
	
		Set<FacturaLineas> lineasFactu = new HashSet<FacturaLineas>();

	 	if (! resultValidacion.hasErrors()) {
	
	 		modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
		
	 		Map<String, Object> resultValFactura;
	 		resultValFactura = validarDatosFactura(datosFacturaWeb, formaPago);
		
	 		datosError = (BeanErrorValidacion) resultValFactura.get("errorValidacion");
			
	 		if (datosError.getCodError().intValue() != 0 ) 
	 			{
	 			modelo.addAttribute("errorValidacion" , true);
	 			modelo.addAttribute("mensajeError", datosError.getCodError().toString() + ", " + datosError.getDesError() );
	 			}
	 		else
	 			{
		
			  for (BeanFacturaLineas elemenLinFactura : datosFacturaWeb.getBeanFacturaLineas())
	   				{
				  	FacturaLineas lineasFactura = new FacturaLineas();
				  	Map<String, Object> resultValLineaFactura;
				  	resultValLineaFactura = validarLineasFactura(elemenLinFactura);
			 
				  	datosErrorLinea = (BeanErrorValidacion) resultValFactura.get("errorValidacion");
					
					 if (datosErrorLinea.getCodError().intValue() != 0 ) 
					 	{
						 modelo.addAttribute("errorValidacion" , true);
						 modelo.addAttribute("mensajeError", datosErrorLinea.getCodError().toString() + ", " + datosErrorLinea.getDesError() );
						 break;
					 	}
					 else
					 	{
						FacturaLineas factuLinea = (FacturaLineas) resultValLineaFactura.get("facturaLineaValidacion" );
						lineasFactu.add(factuLinea);  
					 	}
	   			}
		    }
		
		// factura.setSituacionFactura(situacionFactura);
		factura.setFacturaLineas(lineasFactu);
		
      // Dar de alta en cascada
		
		factura = servJPAFactura.modifFactura(factura);
	 	}
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	//	redirectAttrs.addFlashAttribute("mensaje", "Agregado correctamente").addFlashAttribute("clase", "success");

		return "gestionWeb/facturas/FormEditarFactura";
	}
		
	@RequestMapping("/pagfacturasnue")
	public String paginacionFacturasNue(Model modelo, @RequestParam(value = "numPag", required = false) String numPag,
													      @RequestParam(value = "tpoAccion", required = false) String tpoAccion,
		 											      @RequestParam(value = "numPos", required = false) String numPos,
		 											      @RequestParam(value = "numBloquePag", required = false) Integer numBloquePag )
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

	 
			
			Page<Factura> pagFactura= servicioJPA.paginacionFacturas(new Integer(numPagInt), ConstantesAplicacion.REG_POR_PAGINA);
			modelo.addAttribute("pagGenerica", pagFactura);
			modelo.addAttribute("numPag", String.valueOf(numPagInt));
			modelo.addAttribute("numRegPag", pagFactura.getContent().size());
			
		// 	modelo.addAttribute("numTotalReg", pagCliente.getTotalElements());
		
			try
			 {
			   botoneraPag = new CrearBotoneraPag();
			   paramBotonera = CrearBotoneraPag.calculaNumPagBotoneraNue(numPagInt, tpoAccion,  numPos, pagFactura.getTotalElements(), new Double(numBloquePag.intValue()) );
			 }
			catch (Exception exp)
			 {
				exp.printStackTrace();
			 }
			
			modelo.addAttribute("numPagVisibles", paramBotonera.get("numPagVisibles") );
			
			modelo.addAttribute("numPagWeb1", paramBotonera.get("numPagWeb1") );
			modelo.addAttribute("numPagWeb2", paramBotonera.get("numPagWeb2") );
			modelo.addAttribute("numPagWeb3", paramBotonera.get("numPagWeb3") );
			modelo.addAttribute("numPagWeb4", paramBotonera.get("numPagWeb4") );
			modelo.addAttribute("numPagWeb5", paramBotonera.get("numPagWeb5") );

			modelo.addAttribute("linkBotonAnt",  "/gestionWeb/facturas/pagfacturasnue?numPag=" + numPagInt + "&tpoAccion=ant" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
			modelo.addAttribute("linkBoton1",    "/gestionWeb/facturas/pagfacturasnue?numPag=" + paramBotonera.get("numPaginaReal1") + "&numPos=1" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
			modelo.addAttribute("linkBoton2",    "/gestionWeb/facturas/pagfacturasnue?numPag=" + paramBotonera.get("numPaginaReal2") + "&numPos=2" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
			modelo.addAttribute("linkBoton3", 	 "/gestionWeb/facturas/pagfacturasnue?numPag=" + paramBotonera.get("numPaginaReal3") + "&numPos=3" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
			modelo.addAttribute("linkBoton4", 	 "/gestionWeb/facturas/pagfacturasnue?numPag=" + paramBotonera.get("numPaginaReal4") + "&numPos=4" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
			modelo.addAttribute("linkBoton5", 	 "/gestionWeb/facturas/pagfacturasnue?numPag=" + paramBotonera.get("numPaginaReal5") + "&numPos=5" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
			modelo.addAttribute("linkBotonAvan", "/gestionWeb/facturas/pagfacturasnue?numPag=" + numPagInt + "&tpoAccion=avan" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
			
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
		
		/*	modelo.addAttribute("numPagAct1", "N" );
			modelo.addAttribute("numPagAct2", "N" );
			modelo.addAttribute("numPagAct3", "N");
			modelo.addAttribute("numPagAct4", "N" );
			modelo.addAttribute("numPagAct5", "N" ); */
			
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
			

			if ( pagFactura.isLast()  )
				{
				modelo.addAttribute("indUltPag", "S");
				}
			else
				{
				modelo.addAttribute("indUltPag", "N");
				}
			
			modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
			
			return "gestionWeb/facturas/paginacionFacturas.html";
		}

	private Map<String, Object>  validarDatosFactura(BeanFacturaWeb datosFacturaWeb, String formaPago) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> resultadoValidacion = new HashMap<>();
			BeanErrorValidacion datosErrorValidacion = new BeanErrorValidacion(new Integer(0));

			Factura facturaNueva = new Factura();
			FormaPago forPagoNueva = new FormaPago();
	

			facturaNueva.setNumFactura("2020/00" + String.valueOf(numFac + 1));
			// facturaNueva.setImpFactura(facturaWeb.getImpFacturaWeb());
			forPagoNueva.setIdForPago(Integer.parseInt(formaPago));
			facturaNueva.setFormaPago(forPagoNueva);
			
			facturaNueva.setCodDivisa( facturaWeb );


			Optional<Cliente> OptCliente = servicioJPA.buscarIdCliente(new Integer(1));
			facturaNueva.setCliente(OptCliente.get());

			resultadoValidacion.put("lineaFacturaValidacion", facturaNueva);
			resultadoValidacion.put("errorValidacion" , datosErrorValidacion);

			return resultadoValidacion;	
		}
		
		private Map<String, Object> validarLineasFactura(BeanFacturaLineas lineaFacturaWeb) {
			// SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
			Map<String, Object> resultadoValidacion = new HashMap<>();
			BeanErrorValidacion datosErrorValidacion = new BeanErrorValidacion(new Integer(0));

			FacturaLineas lineaFactura = new FacturaLineas();
			
			//lineaFacturaWeb.getIdLinFactura()
			//lineaFacturaWeb.getCantidad()
			//lineaFacturaWeb.getConcepto()
			//lineaFacturaWeb.getPorIva()
			//lineaFacturaWeb.getImpFactura()
			
			resultadoValidacion.put("facturaLineaValidacion", lineaFactura);
			resultadoValidacion.put("errorValidacion" , datosErrorValidacion);

			return resultadoValidacion;	
		}
	
}
