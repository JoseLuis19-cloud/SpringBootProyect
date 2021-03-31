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
import com.myfactory.SBootWebProject.beanForm.BeanEmpleadoWeb;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioWeb;
import com.myfactory.SBootWebProject.common.CrearBotoneraPag;
import com.myfactory.SBootWebProject.constantes.ConstantesAplicacion;
import com.myfactory.SBootWebProject.model.Empleado;
import com.myfactory.SBootWebProject.model.Pais;
import com.myfactory.SBootWebProject.model.PuestoTrabajo;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAEmpleado;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAUsuario;

@Controller
@RequestMapping("/gestionWeb/empleados")
@PropertySource("classpath:parametrosaplicacion.properties")
public class ControllerWebEmpleados {

	@Autowired
	ServJPAEmpleado servJPAEmpleado;
	@Autowired
	BeanEmpleadoWeb beanEmpleadoWeb;
	@Autowired
	CargarBeansDatos cargarBeansDatos;
	@Autowired
	ServJPAUsuario servJPAUsuario;
	@Autowired
	BeanUsuarioSession beanUsuarioSession;
	
	// private final String UPLOAD_DIR = "./uploads/";
	@Value("${path.MACOSDescargaFicheros}")
	private String pathMacOS;

	@GetMapping("/formeditarempleado")
	public String formularioEditarEmpleado(Model modelo,  @RequestParam(value = "idEmpleado", required = false ) String idEmpleado)  {

		byte[] blobBytes = null;
		byte[] encode =null;
		Optional<Empleado> empleado = servJPAEmpleado.buscarIdEmpleado(new Long(idEmpleado));
		  
		modelo.addAttribute("empleadoWeb", cargarBeansDatos.cargarBeanEmpleado(empleado.get() ));
        
		try {
		 	Blob blobImg = empleado.get().getImagenFotoEmpleado();
	        blobBytes = blobImg.getBytes(1, (int) blobImg.length());
	        
	        modelo.addAttribute("imgFoto", this.getImgData(blobBytes));	
			} 
		catch (Exception e) {
			System.out.println("error validacion");
		}
		
		modelo.addAttribute("empleado", empleado);
		modelo.addAttribute("objImagen", encode);
		
		BeanUsuarioWeb beanUsuarioWeb = new BeanUsuarioWeb();
		
		beanUsuarioWeb.setFecAltaUsuarioWeb(Calendar.getInstance());
		beanUsuarioWeb.setRolUsuarioWeb(servJPAUsuario.obtenerRoles());
		
		modelo.addAttribute("usuarioWeb", beanUsuarioWeb);
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionWeb/empleados/FormEditarEmpleado";
	}
	
	
	@GetMapping("/formbajaempleado")
	public String formularioBajaEmpleado(Model modelo,  @RequestParam(value = "idEmpleado", required = false ) String idEmpleado)  {

		byte[] blobBytes = null;
		byte[] encode =null;
		Optional<Empleado> empleado = servJPAEmpleado.buscarIdEmpleado(new Long(idEmpleado));
		  
		modelo.addAttribute("empleadoWeb", cargarBeansDatos.cargarBeanEmpleado(empleado.get() ));
        
		try {
		 	Blob blobImg = empleado.get().getImagenFotoEmpleado();
	        blobBytes = blobImg.getBytes(1, (int) blobImg.length());
	        
	        modelo.addAttribute("imgFoto", this.getImgData(blobBytes));	
			} 
		catch (Exception e) {
			System.out.println("error validacion");
		}
		
		modelo.addAttribute("empleado", empleado);
		modelo.addAttribute("objImagen", encode);
		
		BeanUsuarioWeb beanUsuarioWeb = new BeanUsuarioWeb();
		
		beanUsuarioWeb.setFecAltaUsuarioWeb(Calendar.getInstance());
		beanUsuarioWeb.setRolUsuarioWeb(servJPAUsuario.obtenerRoles());
		
		modelo.addAttribute("usuarioWeb", beanUsuarioWeb);
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionWeb/empleados/FormBajaEmpleado";
	}
	
	@RequestMapping(value = "/bajaempleado", method = RequestMethod.POST)
	public String bajaempleado(Model modelo,  @Valid @ModelAttribute("formEmpleadoWeb") BeanEmpleadoWeb datosEmpleadoWeb)  { 
		
		Optional<Empleado> empleado = servJPAEmpleado.buscarIdEmpleado(new Long(datosEmpleadoWeb.getIdEmpleadoWeb()));
		
		empleado.get().setFecBajaEmplelado(datosEmpleadoWeb.getFecBajaEmpleladoWeb());
		empleado.get().setIndbajaEmpleado(true); 
		
		servJPAEmpleado.bajaEmpleado(empleado.get());
	
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "redirect:/gestionWeb/empleados/" + "pagempleadosNue";
	}
	
	
	@GetMapping("/formaltaempleado")
	public String formularioaltaEmpleado(Model modelo)  {

	// Optional<Empleado> empleado = servJPAEmpleado.buscarIdEmpleado(new Integer(idEmpleado));

	//	byte[] blobBytes = null;
	//	byte[] encode =null;
	//	Optional<Empleado> empleado = servJPAEmpleado.buscarIdEmpleado(new Long(1));
		
		BeanEmpleadoWeb datosEmpleado = new BeanEmpleadoWeb() ;
		  
		datosEmpleado.setImpBrutoAnual2Web("0");
		modelo.addAttribute("empleadoWeb", datosEmpleado   );
        
	/*	try {
		 	Blob blobImg = empleado.get().getImagenFotoEmpleado();
	        blobBytes = blobImg.getBytes(1, (int) blobImg.length());
	        
	        modelo.addAttribute("imgFoto", this.getImgData(blobBytes));	
			} 
		catch (Exception e) {
			System.out.println("error validacion");
		} */
		
	// 	modelo.addAttribute("empleado", empleado);
	//	modelo.addAttribute("objImagen", encode);
		
		BeanUsuarioWeb beanUsuarioWeb = new BeanUsuarioWeb();
		
		beanUsuarioWeb.setFecAltaUsuarioWeb(Calendar.getInstance());
		beanUsuarioWeb.setRolUsuarioWeb(servJPAUsuario.obtenerRoles());
		
		BeanEmpleadoWeb beanEmpleadoWeb = new BeanEmpleadoWeb();
		beanEmpleadoWeb.setPaisWeb(servJPAEmpleado.obtenerPaises()); 
		beanEmpleadoWeb.setPuestoTrabajoWeb(servJPAEmpleado.obtenerPuestoTrabajo() ); 
		modelo.addAttribute("empleadoWeb", beanEmpleadoWeb);	 
		   
		modelo.addAttribute("usuarioWeb", beanUsuarioWeb);
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionWeb/empleados/FormAltaEmpleado";
	}

	@RequestMapping(value = "/altaempleado", method = RequestMethod.POST)
	public String altaEmpleado(@Valid @ModelAttribute("formEmpleadoWeb") BeanEmpleadoWeb datosEmpleadoWeb, 
					BindingResult resultValidacion,
					RedirectAttributes redirectAttrs,
					Model modelo, 
			 		@RequestParam(value = "paisEmpleado", required = true) String codPais,
		 			@RequestParam(value = "puestoTrabajoEmpleado", required = true) String codPuestoTrabajo)   {
		
		Empleado empleado = validarDatosEmpleado(datosEmpleadoWeb, codPais, codPuestoTrabajo );
		
		servJPAEmpleado.altaEmpleado(empleado);
		
		BeanUsuarioWeb beanUsuarioWeb = new BeanUsuarioWeb();
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "redirect:/gestionWeb/empleados/" + "pagempleadosNue";	 
	}
	
	@RequestMapping(value = "/modifempleado", method = RequestMethod.POST)
	public String modifEmpleado(@Valid @ModelAttribute("formEmpleadoWeb") BeanEmpleadoWeb datosEmpleadoWeb, 
					BindingResult resultValidacion,
					RedirectAttributes redirectAttrs,
					Model modelo, 
					@RequestParam(value = "paisEmpleado", required = true) String codPais,
					@RequestParam(value = "puestoTrabajo", required = true) String codPuestoTrabajo) {
	
		Empleado empleado = validarDatosEmpleado(datosEmpleadoWeb, codPais, codPuestoTrabajo );
		empleado.setIdEmpleado( datosEmpleadoWeb.getIdEmpleadoWeb( ) );
		
		servJPAEmpleado.modifEmpleado(empleado);
		
		BeanUsuarioWeb beanUsuarioWeb = new BeanUsuarioWeb();
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionWeb/empleados/FormEditarEmpleado";
	}
	
	@GetMapping("/formuploadfichero")
	public String formUploadFichero(Model modelo)  {
	//	return "redirect:/gestionWeb/formBajaCliente/";
		return "GestionWeb/empleados/FormUploadImagenEmpleado";
	}
	
	@PostMapping("/uploadimagenempleado")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

     // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Seleccione una imagen a enviar.");
            return "redirect:/";
        }

     // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Optional<Empleado> empleado = servJPAEmpleado.buscarIdEmpleado(new Long(1));
        
		try
		 {
			byte[] arrayBytesImagen = file.getBytes();
			java.sql.Blob blobImagen = new javax.sql.rowset.serial.SerialBlob(arrayBytesImagen);	
			empleado.get().setImagenFotoEmpleado(blobImagen);
		 }
		catch (Exception exp) {
			System.out.println("Error conversion");
		}
        
		servJPAEmpleado.grabarImagen(empleado.get());
        
        try {
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

	
	@RequestMapping("/cargarImagenEmpleado")
	public String cargarImagenEmpleado(Model modelo) {
		
		byte[] blobBytes = null;
		byte[] encode =null;
		Optional<Empleado> empleado = servJPAEmpleado.buscarIdEmpleado(new Long(1));
        
		try {
		 	Blob blobImg = empleado.get().getImagenFotoEmpleado();
	        blobBytes = blobImg.getBytes(1, (int) blobImg.length());
	        
	        modelo.addAttribute("imgFoto", this.getImgData(blobBytes));	
			} 
		catch (Exception e) {
			System.out.println("error validacion");
		}
	
		modelo.addAttribute("empleado", empleado);
		modelo.addAttribute("objImagen", encode);
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
 
	 	return "GestionWeb/empleados/FormVerFotoEmpleado";
	}
	
	
	@RequestMapping("/pagempleadosNue")
	public String paginacionEmpleadosNue(Model modelo, @RequestParam(value = "numPag", required = false) String numPag,
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

		Page<Empleado> pagEmpleados = servJPAEmpleado.pagEmpleados(new Integer(numPagInt), ConstantesAplicacion.REG_POR_PAGINA, busquedaCampo.getApellidosBusqueda().trim());
		modelo.addAttribute("pagGenerica", pagEmpleados);
		modelo.addAttribute("numPag", String.valueOf(numPagInt));
		modelo.addAttribute("numRegPag", pagEmpleados.getContent().size());
	
		try
		 {
		   botoneraPag = new CrearBotoneraPag();
		   paramBotonera = CrearBotoneraPag.calculaNumPagBotoneraNue(numPagInt, tpoAccion,  numPos, pagEmpleados.getTotalElements(), new Double(numBloquePag.intValue()) );
		 }
		catch (Exception exp)
		 {
			exp.printStackTrace();
		 }
		
		String URLPag = "/gestionWeb/empleados/pagempleadosNue?numPag=" ;
		
		CrearBotoneraPag.montarEnlacesBotonera(paramBotonera, modelo, numPagInt, URLPag, busquedaCampo.getApellidosBusqueda().trim());

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

		if ( pagEmpleados.isLast()  )
			{
			modelo.addAttribute("indUltPag", "S");
			}
		else
			{
			modelo.addAttribute("indUltPag", "N");
			}
		
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

		return "GestionWeb/empleados/PagEmpleados";
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
	
	 
	private Empleado validarDatosEmpleado(BeanEmpleadoWeb datosEmpleadoWeb, String codPais, String codPuestoTrabajo) {
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Empleado empleado = new Empleado();
		Pais pais = new Pais();
		PuestoTrabajo  puestoTrabajo = new PuestoTrabajo();
		 
		empleado.setApellidos(  datosEmpleadoWeb.getApellidosWeb( ) ); 
		empleado.setNombre( datosEmpleadoWeb.getNombreWeb() );
		empleado.setDireccion(   datosEmpleadoWeb.getDireccionWeb() );
		empleado.setEmail(datosEmpleadoWeb.getEmailWeb());
		if (datosEmpleadoWeb.getImpBrutoAnual2Web() != "" && datosEmpleadoWeb.getImpBrutoAnual2Web() != null)
				{
			empleado.setImpBrutoAnual(new Float(datosEmpleadoWeb.getImpBrutoAnual2Web()));
		
				}
		 
		empleado.setCodPostal(datosEmpleadoWeb.getCodPostalWeb());
		
	 	pais.setIdPais(new Integer(codPais ) );
      	puestoTrabajo.setIdPuestoTrabajo( new Integer( codPuestoTrabajo) );
		
		empleado.setPais(pais);
		empleado.setPuestoTrabajo(puestoTrabajo);

		if (datosEmpleadoWeb.getFecAltaEmplelado2Web() != null)
			{
			try {
				// SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
				// String dateInString = "31-08-1982 10:20:56";

			//	empleado.setFecFactura(datosEmpleadoWeb.getFecAltaEmplelado2Web());
				} catch (Exception e) {
			 
				}
			}
		return empleado;
	}
 	
}
