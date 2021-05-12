package com.myfactory.SBootWebProject.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfactory.SBootWebProject.beanForm.BeanClienteWeb;
import com.myfactory.SBootWebProject.beanForm.BeanErrorValidacion;
import com.myfactory.SBootWebProject.beanForm.BeanFacturaLineas;
import com.myfactory.SBootWebProject.beanForm.BeanFacturaWeb;
import com.myfactory.SBootWebProject.beanForm.BeanIdUsuario;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.common.CrearBotoneraPag;
import com.myfactory.SBootWebProject.constantes.ConstantesAplicacion;
import com.myfactory.SBootWebProject.informesjasper.GeneradorJasper;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.Empleado;
import com.myfactory.SBootWebProject.model.Factura;
import com.myfactory.SBootWebProject.model.FacturaLinea;
import com.myfactory.SBootWebProject.model.FacturaSituacion;
import com.myfactory.SBootWebProject.model.FormaPago;
import com.myfactory.SBootWebProject.model.Proyecto;
import com.myfactory.SBootWebProject.servicesJPA.ServJPA;
import com.myfactory.SBootWebProject.servicesJPA.ServJPACliente;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAEmpresa;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAFactura;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/gestionWeb/facturas")
@PropertySource("classpath:parametrosServicioMail.properties")
public class ControllerWebFacturas {

	@Autowired
	ServJPA servicioJPA;
	@Autowired
	ServJPAFactura servJPAFactura;
	@Autowired
	ServJPACliente servJPACliente;
	@Autowired
	ServJPAEmpresa servJPAEmpresa;
	@Autowired
	BeanClienteWeb facturaWeb;
	@Autowired
	CargarBeansDatos cargarBeansDatos;
	@Autowired
	BeanUsuarioSession beanUsuarioSession;
	@Autowired
	BeanIdUsuario beanIdUsuario;
	
	@Value("${path.MACOSGeneracionFacturasPDF}")
	private String pathDescargaFacturasPDFMacOS;
	
	@Value("${factura.divisa.EUR}")
	private String codDivisaEUR;
	
	@Value("${emailFactura.cuerpoMensaje}")
	private String cuerpoMensajeFactura;
	
	@Value("${emailFactura.asunto}")	
	private String asuntoMensajeFactura;
	
	@GetMapping("/formeditarfactura")
	public String formEditarFactura(Model modelo, @RequestParam(value = "idFactura", required = true) Integer idFactura)  {

		Optional<Factura> factura = servJPAFactura.buscarIdFactura( idFactura  );
		
		modelo.addAttribute("datosFacturaWeb", cargarBeansDatos.cargarBeanFactura(factura.get()) );
		modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
		//	modelo.addAttribute("situacionFactuWeb", servicioJPA.getSituacionesFactura())
		modelo.addAttribute("empresaFactuWeb", servJPAEmpresa.listEmpresasProyecto() );
		 
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
	 	
		facturaWeb.setFecAltaFacturaWeb(Calendar.getInstance());
		facturaWeb.setNumFacturaWeb("");
		facturaWeb.setCodFactura("");
		facturaWeb.setNotaFactura("");
		facturaWeb.setCodDivisaWeb(876);
		facturaWeb.setDesDivisaWeb(ConstantesAplicacion.DES_ISO_DIVISA_EUR);
		facturaWeb.setPorDescuentoWeb(10F);
		facturaWeb.setImpFacturaWeb(0F);
	 
		Iterable <FacturaSituacion> iterFacturaSitu = servicioJPA.getSituacionesFactura();
		 
		List<FacturaSituacion> listFactuSituacion = StreamSupport
					  .stream(iterFacturaSitu.spliterator(), false)
					  .collect(Collectors.toList());
		 
		facturaWeb.setSituacionesFactura(listFactuSituacion);
		
		modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
	//	modelo.addAttribute("situacionFactuWeb", servicioJPA.getSituacionesFactura())
		modelo.addAttribute("empresaFactuWeb", servJPAEmpresa.listEmpresasProyecto() );
		
		modelo.addAttribute("datosFacturaWeb", facturaWeb);
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "gestionWeb/facturas/FormAltaFactura";
	}

	@RequestMapping(value = "/insertarfactura", method = RequestMethod.POST)
	public String altaFactura(@ModelAttribute("datosFacturaWeb") @Valid BeanFacturaWeb datosFacturaWeb,
		BindingResult resultValidacion, 
		RedirectAttributes redirectAttrs,
		Model modelo, @RequestParam(value = "formaPago", required = true) String formaPago,
					  @RequestParam(value = "sitFactura", required = true) String sitFactura,
					  @RequestParam(value = "clienteFactura", required = true) String clienteFactura) {
		
		Factura factura;
		BeanErrorValidacion datosError = null;
		BeanErrorValidacion datosErrorLinea  = null;
		 
		Set<FacturaLinea> lineasFactu = new HashSet<FacturaLinea>();

	 	if (! resultValidacion.hasErrors()) {
		
	 		Map<String, Object> resultValFactura;
	 		resultValFactura = validarDatosFactura(datosFacturaWeb, formaPago, sitFactura, clienteFactura );
		
	 		datosError = (BeanErrorValidacion) resultValFactura.get("errorValidacion");
			
	 		if (datosError.getCodError().intValue() != 0 ) 
	 			{
	 			modelo.addAttribute("errorValidacion" , true);
	 			modelo.addAttribute("mensajeError", datosError.getCodError().toString() + ", " + datosError.getDesError() );
	 			  	modelo.addAttribute("datosFacturaWeb", datosFacturaWeb);
	 		 	
	 			  	modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
	 			 	modelo.addAttribute("empresaFactuWeb", servJPAEmpresa.listEmpresasProyecto() );	
	 			 	modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());			
	 			 //	redirectAttrs.addFlashAttribute("mensaje", "Agregado correctamente").addFlashAttribute("clase", "success");

	 			}
	 		  else
	 			{
	 			  for (BeanFacturaLineas elemenLinFactura : datosFacturaWeb.getBeanFacturaLineas())
	   				  {
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
						FacturaLinea factuLinea = (FacturaLinea) resultValLineaFactura.get("facturaLineaValidacion" );
						factuLinea.setFactura( (Factura) resultValFactura.get("facturaValidacion"));
						lineasFactu.add(factuLinea); 
					 	}
	   				  }
	 			  
	 			factura = (Factura) resultValFactura.get("facturaValidacion");
	 			
	 			factura.setFacturaLineas(lineasFactu);
	 			Integer idFactura = null;	
	 			// Si no existe la factura todavía
	 		//	if (datosFacturaWeb.getIdFacturaWeb() == null )
	 		//		{
	 				factura.setPorIva(21);
	 				factura.setNumFactura(servJPAFactura.asignarNumFactura(ConstantesAplicacion.FACTURAS_SECUENCIAL) );
	 			 // Dar de alta en cascada 
	 				idFactura = servJPAFactura.altaFactura(factura);
	 				
	 				datosFacturaWeb.setIdFacturaWeb(idFactura);
	 				datosFacturaWeb.setNumFacturaWeb(factura.getNumFactura());
	 				
	 				servJPAFactura.incrementarNumFactura("2021", new Integer ( factura.getNumFactura().substring(5, factura.getNumFactura().length() ) ) );
	 				
	 				redirectAttrs.addAttribute("idFactura", idFactura) ;
					 
					modelo.addAttribute("errorValidacion" , false);
					modelo.addAttribute("mensajeError", "" );
					 
					return "redirect:/gestionWeb/facturas/formeditarfactura";
	 				
	 		//		}
	 		//	  else
	 		//		{
	 		//		 factura.setNumFactura(datosFacturaWeb.getNumFacturaWeb());
	 		//	     servJPAFactura.modifFactura(factura);	
	 		//		}
		    }
		}
	 	return "gestionWeb/facturas/FormAltaFactura";
	}
	 	
	 //	datosFacturaWeb.getBeanFacturaLineas().add(new BeanFacturaLineas());
	 	
	 //	modelo.addAttribute("datosFacturaWeb", datosFacturaWeb);
	 	
	 //	modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
	//	modelo.addAttribute("empresaFactuWeb", servJPAEmpresa.listEmpresasProyecto() );	
	 
			
	//	modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
			
	//	redirectAttrs.addFlashAttribute("mensaje", "Agregado correctamente").addFlashAttribute("clase", "success");

//		return "gestionWeb/facturas/FormAltaFactura";
	
	@RequestMapping(value = "/modiffactura", method = RequestMethod.POST)
	public String modifFactura(@ModelAttribute("datosFacturaWeb") @Valid BeanFacturaWeb datosFacturaWeb,
			BindingResult resultValidacion, 
			Model  modelo,
			@RequestParam(value = "formaPago", required = true) String formaPago,
			@RequestParam(value = "sitFactura", required = true) String sitFactura,
			@RequestParam(value = "clienteFactura", required = true) String clienteFactura) {

		Factura factura = new Factura();
		BeanErrorValidacion datosError = null;
		BeanErrorValidacion datosErrorLinea  = null;
	
		Set<FacturaLinea> lineasFactu = new HashSet<FacturaLinea>();

	 	if (! resultValidacion.hasErrors()) {
	
	 		modelo.addAttribute("formasPagoWeb", servicioJPA.getFormasPago());
		
	 		Map<String, Object> resultValFactura;
			resultValFactura = validarDatosFactura(datosFacturaWeb, formaPago, sitFactura, clienteFactura );
		
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
				  	FacturaLinea lineasFactura = new FacturaLinea();
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
						FacturaLinea factuLinea = (FacturaLinea) resultValLineaFactura.get("facturaLineaValidacion" );
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

			Page<Factura> pagFactura= servJPAFactura.paginacionFacturas(new Integer(numPagInt), ConstantesAplicacion.REG_POR_PAGINA);
			
			
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
			
			return "gestionWeb/facturas/PagFacturas";
		}

	private Map<String, Object> validarDatosFactura(BeanFacturaWeb datosFacturaWeb, String formaPago, String sitFactura, String clienteFactura) {

			Map<String, Object> resultadoValidacion = new HashMap<>();
			BeanErrorValidacion datosErrorValidacion = new BeanErrorValidacion(new Integer(0));

			Factura factura = new Factura();
			
		 	factura.setImpFactura(datosFacturaWeb.getImpFacturaWeb() );
			factura.setFecFactura(datosFacturaWeb.getFecAltaFacturaWeb() );
			factura.setCodDivisa(new Integer(codDivisaEUR));
			datosFacturaWeb.setPorIvaWeb(21F);
			factura.setPorIva(  datosFacturaWeb.getImpFacturaWeb().intValue() );
			factura.setNotaFactura(datosFacturaWeb.getNotaFactura());
			
			factura.setCodUsuario(beanIdUsuario.getIdUsuario());
			factura.setPorDescuento(datosFacturaWeb.getPorDescuentoWeb() );
			FormaPago forPagoNueva = new FormaPago();
			forPagoNueva.setIdForPago(Integer.parseInt(formaPago));
			factura.setFormaPago(forPagoNueva);

			Cliente cliente = new Cliente();
			cliente.setIdCliente(new Integer(clienteFactura));
			factura.setCliente(cliente); 
			
			FacturaSituacion facturaSit = new FacturaSituacion();
			facturaSit.setCodSitFactura( new Integer(sitFactura));
			factura.setFacturaSituacion(facturaSit);

			resultadoValidacion.put("facturaValidacion", factura);
			resultadoValidacion.put("errorValidacion" , datosErrorValidacion);

			return resultadoValidacion;	
		}
		
		private Map<String, Object> validarLineasFactura(BeanFacturaLineas lineaFacturaWeb) {
		
			Map<String, Object> resultadoValidacion = new HashMap<>();
			BeanErrorValidacion datosErrorValidacion = new BeanErrorValidacion(new Integer(0));

			FacturaLinea factuLinea = new FacturaLinea();
			
			factuLinea.setCantidad(lineaFacturaWeb.getCantidad() ); 
			factuLinea.setConcepto(lineaFacturaWeb.getConcepto() ); 
			factuLinea.setPorIva(lineaFacturaWeb.getPorIva());
			factuLinea.setImpLinFactura(lineaFacturaWeb.getImpLinFactura());
			 
			factuLinea.setFecactualizacion(new Date (Calendar.getInstance().getTimeInMillis() ) );
			factuLinea.setPorDescuento(lineaFacturaWeb.getPorDescuento());
	
			resultadoValidacion.put("facturaLineaValidacion", factuLinea);
			resultadoValidacion.put("errorValidacion" , datosErrorValidacion);

			return resultadoValidacion;	
		}
		
		
		@RequestMapping("/generarpdffactura")
		public String generarPDFFActura(Model modelo, @RequestParam(value = "idFactura", required = true) Integer idFactura,
													  @RequestParam(value = "numFactura", required = true) String numFactura ) {
		   try
		    {
		     Factura factura = servJPAFactura.buscarIdFactura( idFactura).get() ;
			 
		     GeneradorJasper genInfoJasper = new GeneradorJasper();
		     
		     JasperPrint reportGenerado = genInfoJasper.generarPDFFactura(factura );
		     // Concatenar el numero de factura en el nombre numFactura
		     JasperExportManager.exportReportToPdfFile(reportGenerado, pathDescargaFacturasPDFMacOS + "FacturaBeigar" + numFactura.replace("/", "_") + ".pdf");
		     
		    // JasperViewer viewer = new JasperViewer(reportGenerado);
		    // viewer.setVisible(true);
		     
		     modelo.addAttribute("numFactura", numFactura);
				
		    } 
		   catch (JRException ex)
		     {
	         // parentLogger.error("Se ha producido un error en la generacion del informe Jasper " + ex);
	         }
		   
			modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

			return "gestionWeb/facturas/PDFFacturaGenerada";
		}
		
		@RequestMapping(value = "downloadFilePDFFactura", method = RequestMethod.GET
				)
	    public StreamingResponseBody getSteamingFile1(HttpServletResponse response, @RequestParam(value = "numFactura", required = true) String numFactura) throws IOException {
	        response.setContentType("application/pdf");
	      //  response.setHeader("Content-Disposition", "attachment; filename=\"demo.pdf\"");
	        InputStream inputStream = new FileInputStream(new File(pathDescargaFacturasPDFMacOS + "FacturaBeigar" + numFactura.replace("/", "_") + ".pdf"));
	        
	        return outputStream -> {
	            int nRead;
	            byte[] data = new byte[1024];
	            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
	                System.out.println("Writing some bytes..");
	                outputStream.write(data, 0, nRead);
	            }
	            inputStream.close();
	            
	       //     enviarEmail(numFactura, "FacturaBeigar" + numFactura.replace("/", "_") + ".pdf");
	        };
	        
	    }
		
		
	@RequestMapping("/enivaremailpdffactura")
	public String enivarEmailPDFFactura(Model modelo) {
			
		 enviarEmail("1", pathDescargaFacturasPDFMacOS + "FacturaBeigar2023_1.pdf");

		 return "gestionWeb/facturas/PDFFacturaGenerada";
	}
		
	private void enviarEmail(String numFactura, String nomFichero)
	{		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("jlbuenome.andro@gmail.com");
	    mailSender.setPassword("19buenomendez70");
	    
	    
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 
		Session session = Session.getInstance(props,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication("jlbuenome.andro@gmail.com", "19buenomendez70");
	                    }
	                });
	        
	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("jlbuenome.andro@gmail.com"));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse("jlbuenome@gmail.com")
	            );
	            
	          String asuntoMensaje = asuntoMensajeFactura.replace("%1" , numFactura) ;
	         //   String cuerpoMensaje = cuerpoMensajeFactura.replaceAll("%1", numFactura + "\n\n");
	            
	            Multipart multipart = new MimeMultipart();
	            
	            BodyPart messageBodyPart0 = new MimeBodyPart();
	         // Fill the message
	            messageBodyPart0.setText("This is message body");
	            multipart.addBodyPart(messageBodyPart0);
	            // Create a multipar message
	           // Multipart multipart0 = new MimeMultipart();

	            // Set text message part
	     //       multipart0.addBodyPart(messageBodyPart0);
	            
	            
	            
	          //  MimeBodyPart messageBodyPart = new MimeBodyPart();
	            
	            BodyPart messageBodyPart1 = new MimeBodyPart();
	            String filename = pathDescargaFacturasPDFMacOS + "FacturaBeigar2023_1.pdf";  
	            DataSource source = new FileDataSource(filename);  
	            messageBodyPart1.setDataHandler(new DataHandler(source));  
	            messageBodyPart1.setFileName(filename);
	            
	            multipart.addBodyPart(messageBodyPart1); 
	            
	            
	         //   String filePath = pathDescargaFacturasPDFMacOS ;
	         //   String nomFichero2 = "FacturaBeigar2023_1.pdf";
	            
	         
	          //  File file = new File(pathDescargaFacturasPDFMacOS + "FacturaBeigar2023_1");
	             
	          //  DataSource dataSource = new ByteArrayDataSource(new FileInputStream(file), "application/pdf");
	           //  message.addAttachment("FacturaBeigar2023_1.pdf", dataSource);  //         
	            
	          //  DataSource source = new FileDataSource(filePath);
	         //   messageBodyPart.setDataHandler(new DataHandler(source));
	         //   messageBodyPart.setFileName(nomFichero2);
	          //  multipart.addBodyPart(messageBodyPart);

	            message.setContent(multipart); 
	            
	           message.setSubject(asuntoMensaje);
	         //   message.setText(cuerpoMensaje);
	           
	            Transport.send(message);
	            
	            System.out.println("Done");
	            
	             
	           

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	        
	        catch (Exception e2) {
	            e2.printStackTrace();
	        }

		
	}
}
