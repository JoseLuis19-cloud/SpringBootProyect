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
import com.myfactory.SBootWebProject.beanForm.BeanEmpleadoWeb;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.common.CrearBotoneraPag;
import com.myfactory.SBootWebProject.constantes.ConstantesAplicacion;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.Empleado;
import com.myfactory.SBootWebProject.model.TpoCliente;
import com.myfactory.SBootWebProject.servicesJPA.ServJPA;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAEmpleado;

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
	BeanUsuarioSession beanUsuarioSession;
	
	// private final String UPLOAD_DIR = "./uploads/";
	@Value("${path.MACOSDescargaFicheros}")
	private String pathMacOS;

	@GetMapping("/formeditarempleado")
//	public String formularioEditarCliente(Model modelo,  @RequestParam(value = "idEmpleado", required = false ) String idEmpleado)  {
		public String formularioEditarCliente(Model modelo)  {

	// Optional<Empleado> empleado = servJPAEmpleado.buscarIdEmpleado(new Integer(idEmpleado));

		byte[] blobBytes = null;
		byte[] encode =null;
		Optional<Empleado> empleado = servJPAEmpleado.buscarIdEmpleado(new Long(1));
		
		modelo.addAttribute("empleadoWeb", cargarBeansDatos.cargarBeanEmpleado(empleado.get()) );
        
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

		return "GestionWeb/empleados/FormEditarEmpleado";
	}
		
	@RequestMapping("/forminsertarempleado")
	public String formularioInserEmpleado(Model modelo ) {
		
		// modelo.addAttribute("empleadoWeb", empleadoWeb );
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionWeb/empleados/FormEditarEmpleado";
	}
	
	@RequestMapping(value = "/insertarempleado", method = RequestMethod.POST)
	public String altaCliente(@Valid @ModelAttribute("formClienteWeb") BeanClienteWeb formClienteWeb, 
			BindingResult resultValidacion,
			RedirectAttributes redirectAttrs,
			Model modelo, @RequestParam(value = "tipoCliente", required = true) String tpoCliente) {
		
	/*	  if (! resultValidacion.hasErrors()) {
			  Cliente clienteAlta = validarDatosEmpleado(formEmpleadoWeb);
			  Cliente cliente = servJPAEmpleado.altaEmpleado(clienteAlta);

			  	if (cliente == null) {
			  		modelo.addAttribute("clienteWeb", formClienteWeb);
			  		modelo.addAttribute("ErrorBBDD", "1");
			  		}
		  		}
			   else
			   {
			  	modelo.addAttribute("clienteWeb", formClienteWeb );
			   
			   } */
		
		return "GestionWeb/empleados/FormInsertarEmpleado.html";
	}

	@GetMapping("/formbajaempleado")
	public String formularioBajaCliente(Model modelo,  @RequestParam(value = "idEmpleado", required = false ) String idEmpleado)  {
 
		
		// modelo.addAttribute("clienteWeb", cargarBeansDatos.cargarBeanCliente(cliente.get()) );
 
		
		//  modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
	//	return "redirect:/gestionWeb/formBajaCliente/";
		return "GestionWeb/clientes/FormBajaCliente.html";
	}
	
	@RequestMapping("/bajaempleado")
	public String bajaEmpleado(Model modelo,  @RequestParam(value = "idCliente", required = false ) String idCliente)  {

	//	servJPAEmpleado.bajaIdEmpleado(new Integer(Integer.parseInt(idCliente)));
		return "personas/lista.html";
	}

	
	@GetMapping("/formuploadfichero")
	public String formUploadFichero(Model modelo)  {
	

	//	return "redirect:/gestionWeb/formBajaCliente/";
		return "GestionWeb/empleados/FormUploadImagenEmpleado.html";
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

	//	private Cliente validarDatosEmpleado(BeanEmpleadoWeb clienteWeb, String tpoCliente) {

	//	Cliente clienteNuevo = new Cliente();

		//	clienteNuevo.setNombre(clienteWeb.getNombreWeb());
		//	clienteNuevo.setApellidos(clienteWeb.getApellidosWeb());
		//	clienteNuevo.setDireccion(clienteWeb.getDireccionWeb());
		//	clienteNuevo.setDNI(clienteWeb.getDNIWeb());
		//	clienteNuevo.setDireccion(clienteWeb.getDireccionWeb() );
		//	clienteNuevo.setDirEmail(clienteWeb.getDirEmailWeb());
		// clienteNuevo.setFecAltaCliente( )  );
		//	clienteNuevo.setPais(clienteWeb.getPaisWeb());
		//	clienteNuevo.setTelefono(clienteWeb.getTelefonoWeb());
		
		//	TpoCliente tipClienteNue = new TpoCliente();
		//	tipClienteNue.setIdTpoCliente(Integer.parseInt(tpoCliente));
		//	clienteNuevo.setTpoCliente(tipClienteNue);

		//	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
		//	try {
		//		clienteNuevo.setFecNacimiento(
		//				new java.sql.Date((dateFormat.parse(clienteWeb.getFecNacimientoWeb())).getTime()));
					//	} catch (Exception e) {
		//		System.out.println("error validacion");
			//	}

		//	return clienteNuevo;
	//	}
	
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
