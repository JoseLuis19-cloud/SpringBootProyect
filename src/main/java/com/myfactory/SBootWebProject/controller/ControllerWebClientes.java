package com.myfactory.SBootWebProject.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.validation.Valid;

import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfactory.SBootWebProject.beanForm.BeanCamposBusqueda;
import com.myfactory.SBootWebProject.beanForm.BeanClienteWeb;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.common.CrearBotoneraPag;
import com.myfactory.SBootWebProject.constantes.ConstantesAplicacion;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.TpoCliente;
import com.myfactory.SBootWebProject.servicesJPA.ServJPA;
import com.myfactory.SBootWebProject.servicesJPA.ServJPACliente;

@Controller
@RequestMapping("/gestionWeb/clientes")
@PropertySource("classpath:parametrosaplicacion.properties")
public class ControllerWebClientes {

	@Autowired
	ServJPA servicioJPA;
	@Autowired
	ServJPACliente servicioClienteJPA;
	@Autowired
	BeanClienteWeb clienteWeb;
	@Autowired
	CargarBeansDatos cargarBeansDatos;
	@Autowired
	BeanUsuarioSession beanUsuarioSession;
	
	// private final String UPLOAD_DIR = "./uploads/";
	@Value("${path.MACOSDescargaFicheros}")
	private String pathMacOS;

	@GetMapping("/formeditarcliente")
	public String formularioEditarCliente(Model modelo,  @RequestParam(value = "idCliente", required = false ) String idCliente)  {
	
		Iterable <TpoCliente> tipoCliente = servicioJPA.getTipoCliente();
		
		Optional<Cliente> cliente = servicioJPA.buscarIdCliente(new Integer(Integer.parseInt(idCliente)));
		
		modelo.addAttribute("clienteWeb", cargarBeansDatos.cargarBeanCliente(cliente.get()) );
		modelo.addAttribute("tpoClienteWeb", tipoCliente );
		
		return "GestionWeb/clientes/FormEditarCliente.html";
	}
		
	@RequestMapping("/forminsertarcliente")
	public String formularioInserCliente(Model modelo ) {
		
		BeanClienteWeb clienteWeb = new BeanClienteWeb ("", "", "", "", "", "", "", "", new Integer(0));
		
		modelo.addAttribute("clienteWeb", clienteWeb );
		modelo.addAttribute("tpoClienteWeb", servicioJPA.getTipoCliente() );
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionWeb/clientes/FormInsertarCliente";
	}
	
	@RequestMapping(value = "/insertarcliente", method = RequestMethod.POST)
	public String altaCliente(@Valid @ModelAttribute("formClienteWeb") BeanClienteWeb formClienteWeb, 
			BindingResult resultValidacion,
			RedirectAttributes redirectAttrs,
			Model modelo, @RequestParam(value = "tipoCliente", required = true) String tpoCliente) {
		
		  if (! resultValidacion.hasErrors()) {
			  Cliente clienteAlta = validarDatosCliente(formClienteWeb, tpoCliente);
			  Cliente cliente = servicioJPA.altaCliente(clienteAlta);

			  	if (cliente == null) {
			  		modelo.addAttribute("clienteWeb", formClienteWeb);
			  		modelo.addAttribute("ErrorBBDD", "1");
			  		}
		  		}
			   else
			   {
			  	modelo.addAttribute("clienteWeb", formClienteWeb );
			  	modelo.addAttribute("tpoClienteWeb", servicioJPA.getTipoCliente() );  
			   }
		
		return "GestionWeb/clientes/FormInsertarCliente.html";
	}
	
	
	@RequestMapping(value = "/modificarcliente", method = RequestMethod.POST)
	public String modificarCliente(@Valid @ModelAttribute("${clienteWeb}") BeanClienteWeb formClienteWeb, 
			BindingResult resultValidacion,
			RedirectAttributes redirectAttrs,
			Model modelo, @RequestParam(value = "tipoCliente", required = true) String tpoCliente) {
		
		//  if (! resultValidacion.hasErrors()) {
			  Cliente clienteModif = validarDatosCliente(formClienteWeb, tpoCliente);
			  Cliente cliente = servicioClienteJPA.modificarCliente(clienteModif);

			  	if (cliente == null)
			  		{
			  		modelo.addAttribute("clienteWeb", formClienteWeb);
			  		modelo.addAttribute("ErrorBBDD", "1");
			  		}
		  	//	}
			  else
			   {
			  	modelo.addAttribute("clienteWeb", formClienteWeb);
			  	modelo.addAttribute("tpoClienteWeb", servicioJPA.getTipoCliente() );  
			   }
		
		return "GestionWeb/clientes/FormEditarCliente.html";
	}

	@GetMapping("/formbajacliente")
	public String formularioBajaCliente(Model modelo,  @RequestParam(value = "idCliente", required = false ) String idCliente)  {
	
		Iterable <TpoCliente> tipoCliente = servicioJPA.getTipoCliente();
		
		Optional<Cliente> cliente = servicioJPA.buscarIdCliente(new Integer(Integer.parseInt(idCliente)));
		
		modelo.addAttribute("clienteWeb", cargarBeansDatos.cargarBeanCliente(cliente.get()) );
		modelo.addAttribute("tipoClienteWeb", tipoCliente );
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
	//	return "redirect:/gestionWeb/formBajaCliente/";
		return "GestionWeb/clientes/FormBajaCliente.html";
	}
	
	
	@RequestMapping("/bajacliente")
	public String bajaCliente(Model modelo,  @RequestParam(value = "idCliente", required = false ) String idCliente)  {

		servicioJPA.bajaIdCliente(new Integer(Integer.parseInt(idCliente)));
		return "personas/lista.html";
	}
	
	@RequestMapping("/listaclientes")
	public String mostrarClientes(Model modelo) {
		modelo.addAttribute("listClientes", servicioJPA.buscarTodosClientes());
		return "GestionWeb/clientes/listaClientes";
	}
	
	@Deprecated()
	@RequestMapping("/pagclientes")
	public String paginacionClientes(Model modelo, @RequestParam(value = "numPag", required = false) String numPag,
												   @RequestParam(value = "tpoAccion", required = false) String tpoAccion,
	 											   @RequestParam(value = "numPos", required = false) String numPos)
	{
		int numPagInt = 0;

		// La primera vez
		if (numPag == null && tpoAccion == null) {
			numPagInt = 0;
			}
		else
		{
			// Ha pinchado o avance o retroceso serguro
			if (tpoAccion != null)
				{
					if (tpoAccion.equals("avan")) {
						numPagInt = Integer.parseInt(numPag) + 1;
					} else {
					numPagInt = Integer.parseInt(numPag) - 1;
					}	
				
				}
			else
			{
			// Ha pinchado el numero de pagina
			  numPagInt = Integer.parseInt(numPag);		
			}
		}

//		Iterable <Cliente> listMismoAppelido = servicioJPA.findByApellidos("Sanchez");

	 	Page<Cliente> pagCliente = servicioJPA.paginacionClientes(new Integer(numPagInt), ConstantesAplicacion.REG_POR_PAGINA, "");

		modelo.addAttribute("numPag", String.valueOf(numPagInt));
		modelo.addAttribute("numRegPag", pagCliente.getContent().size());
		modelo.addAttribute("numTotalReg", pagCliente.getTotalElements());
		modelo.addAttribute("pagGenerica", pagCliente);
		
		// Calcular numero de pagina que pinta en el boton
		int numPagWeb = 0;
		int numPosInt = 0;
		int numPosIntInicoPag = 0;
		
		if (numPagInt > 5)
			{
			if (numPos != null)
				{
				numPosInt = Integer.parseInt(numPos);
				switch (numPosInt) {
					case 1:
						numPagWeb = numPagInt - numPosInt;
						System.out.println("1");
						break;
					case 2:
						numPagWeb = numPagInt - numPosInt;
						System.out.println("2");
						break;
					case 3:
						numPagWeb = numPagInt - numPosInt;
						System.out.println("3");
						break;
					case 4:
						numPagWeb = numPagInt - numPosInt;
						System.out.println("4");
						break;
				case 5:
					numPagWeb = numPagInt - numPosInt;
					System.out.println("5");
					break;
				}
			numPagWeb = numPagWeb+1;
			}
		   else
			{
			   numPagWeb=1;
			   numPosIntInicoPag=1;	
			}
		}
		else
		{
			 numPagWeb=1;
			 numPosIntInicoPag=0;
		}
	
		modelo.addAttribute("numPagWeb1", numPagWeb);
		modelo.addAttribute("numPagWeb2", numPagWeb + 1);
		modelo.addAttribute("numPagWeb3", numPagWeb + 2);
		modelo.addAttribute("numPagWeb4", numPagWeb + 3);
		modelo.addAttribute("numPagWeb5", numPagWeb + 4);
		
		int numPag1 = numPosIntInicoPag;
		int numPag2 = numPosIntInicoPag+1;
		int numPag3 = numPosIntInicoPag+2;
		int numPag4 = numPosIntInicoPag+3;
		int numPag5 = numPosIntInicoPag+4;
		 
		modelo.addAttribute("linkBotonAnt",  "/gestionWeb/clientes/pagclientes?numPag=" + numPagInt + "&tpoAccion=ant");
		modelo.addAttribute("linkBoton1",    "/gestionWeb/clientes/pagclientes?numPag=" + numPag1 + "&numPos=1");
		modelo.addAttribute("linkBoton2",    "/gestionWeb/clientes/pagclientes?numPag=" + numPag2 + "&numPos=2");
		modelo.addAttribute("linkBoton3", 	 "/gestionWeb/clientes/pagclientes?numPag=" + numPag3 + "&numPos=3");
		modelo.addAttribute("linkBoton4", 	 "/gestionWeb/clientes/pagclientes?numPag=" + numPag4 + "&numPos=4");
		modelo.addAttribute("linkBoton5", 	 "/gestionWeb/clientes/pagclientes?numPag=" + numPag5 + "&numPos=5");
		modelo.addAttribute("linkBotonAvan", "/gestionWeb/clientes/pagclientes?numPag=" + numPagInt + "&tpoAccion=avan");
		
		if ( pagCliente.isLast()  )
			{
			modelo.addAttribute("indUltPag", "S");
			}
		else
			{
			modelo.addAttribute("indUltPag", "N");
			}
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

		return "GestionWeb/clientes/PaginacionClientes.html";
	}
	
	@RequestMapping("/pagclientesNue")
	public String paginacionClientesNue(Model modelo, @RequestParam(value = "numPag", required = false) String numPag,
												      @RequestParam(value = "tpoAccion", required = false) String tpoAccion,
	 											      @RequestParam(value = "numPos", required = false) String numPos,
	 											      @RequestParam(value = "numBloquePag", required = false) Integer numBloquePag,
	 											      @RequestParam(value = "apellidosBus", required = false) String apellidosBus,
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

		if (busquedaCampo.getApellidosBusqueda() == null)
			{
			busquedaCampo = new BeanCamposBusqueda();
			
			if (apellidosBus == null)
				{
				busquedaCampo.setApellidosBusqueda("");
				}
			  else
				{
				  busquedaCampo.setApellidosBusqueda(apellidosBus);
				}
			}
			else
			{
				busquedaCampo.setApellidosBusqueda(busquedaCampo.getApellidosBusqueda());
			}
			
		
		modelo.addAttribute("objBusqueda", busquedaCampo);

		Page<Cliente> pagCliente = servicioJPA.paginacionClientes(new Integer(numPagInt), ConstantesAplicacion.REG_POR_PAGINA, busquedaCampo.getApellidosBusqueda().trim());
		modelo.addAttribute("pagGenerica", pagCliente);
		modelo.addAttribute("numPag", String.valueOf(numPagInt));
		modelo.addAttribute("numRegPag", pagCliente.getContent().size());
		
	// 	modelo.addAttribute("numTotalReg", pagCliente.getTotalElements());
	
		try
		 {
		   botoneraPag = new CrearBotoneraPag();
		   paramBotonera = CrearBotoneraPag.calculaNumPagBotoneraNue(numPagInt, tpoAccion,  numPos, pagCliente.getTotalElements(), new Double(numBloquePag.intValue()) );
		 }
		catch (Exception exp)
		 {
			exp.printStackTrace();
		 }
		
		String URLPag = "/gestionWeb/clientes/pagclientesNue?numPag=" ;
		
		CrearBotoneraPag.montarEnlacesBotonera(paramBotonera, modelo, numPagInt, URLPag, busquedaCampo.getApellidosBusqueda().trim());
	
		/* modelo.addAttribute("numPagVisibles", paramBotonera.get("numPagVisibles") );
		
		modelo.addAttribute("numPagWeb1", paramBotonera.get("numPagWeb1") );
		modelo.addAttribute("numPagWeb2", paramBotonera.get("numPagWeb2") );
		modelo.addAttribute("numPagWeb3", paramBotonera.get("numPagWeb3") );
		modelo.addAttribute("numPagWeb4", paramBotonera.get("numPagWeb4") );
		modelo.addAttribute("numPagWeb5", paramBotonera.get("numPagWeb5") );

		modelo.addAttribute("linkBotonAnt",  "/gestionWeb/clientes/pagclientesNue?numPag=" + numPagInt + "&tpoAccion=ant" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
		modelo.addAttribute("linkBoton1",    "/gestionWeb/clientes/pagclientesNue?numPag=" + paramBotonera.get("numPaginaReal1") + "&numPos=1" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
		modelo.addAttribute("linkBoton2",    "/gestionWeb/clientes/pagclientesNue?numPag=" + paramBotonera.get("numPaginaReal2") + "&numPos=2" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
		modelo.addAttribute("linkBoton3", 	 "/gestionWeb/clientes/pagclientesNue?numPag=" + paramBotonera.get("numPaginaReal3") + "&numPos=3" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
		modelo.addAttribute("linkBoton4", 	 "/gestionWeb/clientes/pagclientesNue?numPag=" + paramBotonera.get("numPaginaReal4") + "&numPos=4" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
		modelo.addAttribute("linkBoton5", 	 "/gestionWeb/clientes/pagclientesNue?numPag=" + paramBotonera.get("numPaginaReal5") + "&numPos=5" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
		modelo.addAttribute("linkBotonAvan", "/gestionWeb/clientes/pagclientesNue?numPag=" + numPagInt + "&tpoAccion=avan" + "&numBloquePag=" + paramBotonera.get("numBloquePag")); */
		
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
		

		if ( pagCliente.isLast()  )
			{
			modelo.addAttribute("indUltPag", "S");
			}
		else
			{
			modelo.addAttribute("indUltPag", "N");
			}
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

		return "GestionWeb/clientes/PaginacionClientes.html";
	}
	
	
	@GetMapping("/formuploadfichero")
	public String formUploadFichero(Model modelo)  {
	

	//	return "redirect:/gestionWeb/formBajaCliente/";
		return "GestionWeb/clientes/FormUploadFichero.html";
	}
	
	@PostMapping("/uploadfile")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

     // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }

     // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Optional<Cliente> cliente = servicioJPA.buscarIdCliente(new Integer(1));
        
		try
		 {
			byte[] arrayBytesImagen = file.getBytes();
       
			java.sql.Blob blobImagen = new javax.sql.rowset.serial.SerialBlob(arrayBytesImagen);
        		
			cliente.get().setImagenFotoCliente(blobImagen);
		 }
		catch (Exception exp) {
			System.out.println("Error conversion");
		}
        
        servicioJPA.grabarImagen(cliente.get());
        
        try {
        // String pathWidows = "C:\\Documents and Settings\\Manoj\\Desktop";
        //	String pathMacOs = "/Users/UsuarioJoseLuis/Documents/"   ;
        //	String pathW  = pathWidows.replace("\\", "/");
        	// or
        	// path = path.replaceAll("\\\\", "/");
        	 
        // save the file on the local file system
            Path path = Paths.get(this.getPathMacOS() + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        return "redirect:/";
    }

	
	@RequestMapping("/cargarImagen")
	public String cargarImagenCliente(Model modelo) {
		
		byte[] blobBytes = null;
		byte[] encode =null;
		Optional<Cliente> cliente = servicioJPA.buscarIdCliente(new Integer(1));
        
		try {
		 	Blob blobImg = cliente.get().getImagenFotoCliente();
	        blobBytes = blobImg.getBytes(1, (int) blobImg.length());
	        
	        modelo.addAttribute("imgFoto", this.getImgData(blobBytes));	
			} 
		catch (Exception e) {
			System.out.println("error validacion");
		}
	
		modelo.addAttribute("cliente", cliente);
		modelo.addAttribute("objImagen", encode);
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
 
	//	return "GestionWeb/clientes/FormPruebaMenu";
	 	return "GestionWeb/clientes/FormVerFotoCliente";
	}

	private Cliente validarDatosCliente(BeanClienteWeb clienteWeb, String tpoCliente) {

		Cliente clienteNuevo = new Cliente();
		
		clienteNuevo.setIdCliente(clienteWeb.getIdClienteWeb());
		clienteNuevo.setNombre(clienteWeb.getNombreWeb());
		clienteNuevo.setApellidos(clienteWeb.getApellidosWeb());
		clienteNuevo.setDireccion(clienteWeb.getDireccionWeb());
		clienteNuevo.setDNI(clienteWeb.getDNIWeb());
		clienteNuevo.setDireccion(clienteWeb.getDireccionWeb() );
		clienteNuevo.setDirEmail(clienteWeb.getDirEmailWeb());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			 clienteNuevo.setFecNacimiento(
			 			new java.sql.Date((dateFormat.parse(clienteWeb.getFecNacimiento2Web())).getTime()));
			} catch (Exception e) {
			 System.out.println("error validacion");
		 	}

		clienteNuevo.setFecAltaCliente(Calendar.getInstance() )  ;
		 
		clienteNuevo.setPais(clienteWeb.getPaisWeb());
		clienteNuevo.setTelefono(clienteWeb.getTelefonoWeb());
		
		TpoCliente tipClienteNue = new TpoCliente();
		tipClienteNue.setIdTpoCliente(Integer.parseInt(tpoCliente));
		clienteNuevo.setTpoCliente(tipClienteNue);

		return clienteNuevo;
	}
	
	public String getPathMacOS() {
		return pathMacOS;
	}


	public void setPathMacOS(String pathMacOS) {
		this.pathMacOS = pathMacOS;
	}
	
	 
	public String getImgData(byte[] byteData) {
	     return Base64.getMimeEncoder().encodeToString(byteData);
	}
 
		
}
