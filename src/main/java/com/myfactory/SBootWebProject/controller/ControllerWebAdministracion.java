package com.myfactory.SBootWebProject.controller;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.Valid;

import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.myfactory.SBootWebProject.beanForm.BeanTareaWeb;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.common.CrearBotoneraPag;
import com.myfactory.SBootWebProject.constantes.ConstantesAplicacion;
import com.myfactory.SBootWebProject.model.Cliente;
import com.myfactory.SBootWebProject.model.TpoCliente;
import com.myfactory.SBootWebProject.servicesJPA.ServJPA;
import com.myfactory.SBootWebProject.servicesJPA.ServJPACliente;
 
@Controller
@RequestMapping("/administracion")
@PropertySource("classpath:parametrosaplicacion.properties")
public class ControllerWebAdministracion {

	@Autowired
	ServJPA servicioJPA;
	@Autowired
	ServJPACliente servicioClienteJPA;
	
	@Autowired
	ServJPAUsuario servJPAUsuario;
	@Autowired
	BeanClienteWeb clienteWeb;
	@Autowired
	CargarBeansDatos cargarBeansDatos;
	@Autowired
	BeanUsuarioSession beanUsuarioSession;
	
	protected static final Logger parentLogger = LogManager.getLogger();
	
 // private final String UPLOAD_DIR = "./uploads/";
	@Value("${path.MACOSDescargaFicheros}")
	private String pathMacOS;

	@GetMapping("/formcopiaseguridad")
	public String formCopiaSeguiradad(Model modelo) {
		modelo.addAttribute("datosCopSeg", "1" );
		
		return "GestionWeb/administracion/FormCopiaSeguridad";
	}
		
	@RequestMapping("/generarcopiaseguridad")
	public String generarCopiaSeguridad(Model modelo) 
	{
	 backupBDMySQL();
		
	 modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());	
	 return "GestionWeb/administracion/FormResulCopiaSeguridad";
	}
	
	@GetMapping("/formrestaurarcopiaseg")
	public String formRestaurarCopSeg(Model modelo, @RequestParam(value = "idCliente", required = false ) String idCliente)  {
	
		modelo.addAttribute("datosCopSeg", "2" );
		
		return "GestionWeb/administracion/FormRestaurarCopSeg";
	}
		
	@RequestMapping("/generarcrestaurarcioncopseg")
	public String generarRestaurarCopSeg(Model modelo) {
		
		restaurarCopiSegMySQL();
	
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "GestionWeb/administracion/FormResultRestaurarCopSeg";
	}
	
	
	@GetMapping("/formcreartarea")
	public String formCrearTarea(Model modelo) {
		
		BeanTareaWeb datosTareaWeb = new BeanTareaWeb();
		
		datosTareaWeb.setTpoFrecuRepeticion(servicioJPA.getTpoFrecRepeticion());
		datosTareaWeb.setUsuario(usuario);
		
		modelo.addAttribute("datosTareaWeb", datosTareaWeb );
		
		return "GestionWeb/administracion/FormCrearTarea";
	}
	
	private static void backupBDMySQL() {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
        String backupPath = String.format("%s/%s.%s", "/Users/UsuarioJoseLuis/Documents", currentDate, "sql");
        File backupFile = new File(backupPath);
       // if (!backupFile.exists()) {
            try {
            backupFile.createNewFile();
         //   String mysqlCom=String.format("/Applications/XAMPP/xamppfiles/bin/mysqldump -u%s -p%s %s","root","","springboot");
            
            String mysqlCom2=String.format("/Applications/XAMPP/xamppfiles/bin/mysqldump -u root -p springboot > /Users/UsuarioJoseLuis/Documents/copseg2.sql");
          //  String[] command = new String[] { "/bin/bash", "-c" , "echo 19mendez70| sudo -S",  mysqlCom};
            
           // String[] command = new String[] { "/bin/bash", "-c" , "ls > /Users/UsuarioJoseLuis/Documents/lista12.txt"};
            
          //  String[] command = new String[] { "/bin/bash", "-c" , "echo 19mendez70| sudo -S /sbin/ifconfig > /Users/UsuarioJoseLuis/Documents/lista15.txt" };
            String[] command2 = new String[] { "/bin/bash", "-c" , "echo 19mendez70| sudo -S ", mysqlCom2 };
   
            
            ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(command2));
            processBuilder.redirectError(Redirect.INHERIT);
            processBuilder.redirectOutput(Redirect.to(backupFile));
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("Se ha realizado la backup de la BBDD SpringBoot");//
			// parentLogger.error("Se ha realizado la backup de la BBDD SpringBoot");
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	   private static void restaurarCopiSegMySQL() {
           String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
           String backupPath = String.format("%s/%s.%s", "/Users/UsuarioJoseLuis/Documents", currentDate, "sql");
           File backupFile = new File(backupPath);
           if (!backupFile.exists()) {
               try {
               backupFile.createNewFile();
               String mysqlCom=String.format("/Applications/XAMPP/xamppfiles/bin/mysql -u%s -p%s %s","root","","springboot", " < 2020_12_28.sql");
             
               String[] command = new String[] { "/bin/bash", "-c" , "echo 19mendez70 | sudo -S ",  mysqlCom};
               ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(command));
               processBuilder.redirectError(Redirect.INHERIT);
               processBuilder.redirectOutput(Redirect.to(backupFile));
               Process process = processBuilder.start();
               process.waitFor();
               System.out.println("Se ha realizado la backup de la BBDD SpringBoot");//
   			//parentLogger.error("Se ha realizado la backup de la BBDD SpringBoot");
           } catch (IOException e1) {
               e1.printStackTrace();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

       } else {
       	parentLogger.error("Se realizo la copia de seguridad ya");
       }
}
 
	 
}
