package com.myfactory.SBootWebProject.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Optional;

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
import com.myfactory.SBootWebProject.beanForm.BeanFacturaWeb;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.common.CrearBotoneraPag;
import com.myfactory.SBootWebProject.constantes.ConstantesAplicacion;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.Factura;
import com.myfactory.SBootWebProject.model.FormaPago;
import com.myfactory.SBootWebProject.servicesJPA.ServJPA;

@Controller
@RequestMapping("/gestionWeb/facturas")
public class ControllerWebFacturas {

	@Autowired
	ServJPA servicioJPA;
	@Autowired
	BeanClienteWeb facturaWeb;
	@Autowired
	CargarBeansDatos cargarBeansDatos;
	@Autowired
	BeanUsuarioSession beanUsuarioSession;
	
	private static final int numFac = 22;
	
	@GetMapping("/formeditarfactura")
	public String formularioEditarFactura(Model modelo,  @RequestParam(value = "idFactura", required = false ) String idFactura)  {
	
	// Iterable <TpoCliente> tipoCliente = servicioJPA.getTipoCliente();
		
		Optional<Factura> factura = servicioJPA.buscarIdFactura(new Integer(Integer.parseInt(idFactura)));
		
		modelo.addAttribute("facturaWeb", cargarBeansDatos.cargarBeanFactura(factura.get()) );
	//	modelo.addAttribute("tipoClienteWeb", tipoCliente );
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "gestionWeb/facturas/FormEditarFactura";
	}
	
	
	@GetMapping("/formaltafactura")
	public String formularioAltaFactura(Model modelo,  @RequestParam(value = "idFactura", required = false ) String idFactura)  {
	
	// Iterable <TpoCliente> tipoCliente = servicioJPA.getTipoCliente();
		
	//	Optional<Factura> factura = servicioJPA.buscarIdFactura(new Integer(Integer.parseInt(idFactura)));
		
		BeanFacturaWeb facturaWeb = new BeanFacturaWeb (new Integer(0), "", "", "", "", new Integer(0));

	 
		modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
		modelo.addAttribute("facturaWeb", facturaWeb);
	//	modelo.addAttribute("tipoClienteWeb", tipoCliente );
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "gestionWeb/facturas/FormInsertarFactura";
	}

	@RequestMapping(value = "/altafactura", method = RequestMethod.GET)
	public String altaFactura(@ModelAttribute("facturaWeb") @Valid BeanFacturaWeb facturaWeb,
			BindingResult resultValidacion, 
			 Model modelo, @RequestParam(value = "formaPago", required = true) String formaPago) {

		if (resultValidacion.hasErrors()) {
			modelo.addAttribute("facturaWeb", facturaWeb);
			modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
			modelo.addAttribute("CODERROR", "1");

			return "gestionWeb/facturas/FormInsertarFactura.html";
		}

		modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
		Factura factura = validarDatosFactura(facturaWeb, formaPago);

		factura = servicioJPA.altaFactura(factura);

		if (factura == null) {
			modelo.addAttribute("facturaWeb", facturaWeb);
			modelo.addAttribute("CODERROR", "2");
		} else {
			modelo.addAttribute("facturaWeb", facturaWeb);
			modelo.addAttribute("CODERROR", "0");
		}

		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
	//	redirectAttrs.addFlashAttribute("mensaje", "Agregado correctamente").addFlashAttribute("clase", "success");

		return "gestionWeb/facturas/FormInsertarFactura";
	}
	
	@RequestMapping(value = "/modiffactura", method = RequestMethod.GET)
	public String modifFactura(@ModelAttribute("facturaWeb") @Valid BeanFacturaWeb facturaWeb,
			BindingResult resultValidacion, 
			 Model modelo, @RequestParam(value = "formaPago", required = true) String formaPago) {

		if (resultValidacion.hasErrors()) {
			modelo.addAttribute("facturaWeb", facturaWeb);
			modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
			modelo.addAttribute("CODERROR", "1");

			return "gestionWeb/facturas/FormEditarFactura";
		}

		modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
		Factura factura = validarDatosFactura(facturaWeb, formaPago);

		factura = servicioJPA.altaFactura(factura);

		if (factura == null) {
			modelo.addAttribute("facturaWeb", facturaWeb);
			modelo.addAttribute("CODERROR", "2");
		} else {
			modelo.addAttribute("facturaWeb", facturaWeb);
			modelo.addAttribute("CODERROR", "0");
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
		
			modelo.addAttribute("numPagAct1", "N" );
			modelo.addAttribute("numPagAct2", "N" );
			modelo.addAttribute("numPagAct3", "N");
			modelo.addAttribute("numPagAct4", "N" );
			modelo.addAttribute("numPagAct5", "N" );
			
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

		private Factura validarDatosFactura(BeanFacturaWeb facturaWeb, String formaPago) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			Factura facturaNueva = new Factura();
			FormaPago forPagoNueva = new FormaPago();

			facturaNueva.setNumFactura("2020/00" + String.valueOf(numFac + 1));
			facturaNueva.setPorIva(Integer.valueOf(facturaWeb.getPorIvaWeb().trim()));
			facturaNueva.setConcepto(facturaWeb.getConceptoWeb());
			facturaNueva.setImpFactura(new Float(facturaWeb.getImpFacturaWeb()));
			forPagoNueva.setIdForPago(Integer.parseInt(formaPago));

			facturaNueva.setFormaPago(forPagoNueva);

			try {
				// SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
				// String dateInString = "31-08-1982 10:20:56";

				facturaNueva.setFecFactura(new java.sql.Date((dateFormat.parse(facturaWeb.getFecFacturaWeb())).getTime()));
			} catch (Exception e) {
				facturaNueva = null;
			}

			Optional<Cliente> OptCliente = servicioJPA.buscarIdCliente(new Integer(1));
			facturaNueva.setCliente(OptCliente.get());

			return facturaNueva;
		}
	
}
