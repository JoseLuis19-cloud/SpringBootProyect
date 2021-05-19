package com.myfactory.SBootWebProject.controller;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.myfactory.SBootWebProject.beanForm.BeanErrorValidacion;
import com.myfactory.SBootWebProject.beanForm.BeanFicheroSO;
import com.myfactory.SBootWebProject.beanForm.BeanTareaWeb;
import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.beanForm.CargaBeansDatos;
import com.myfactory.SBootWebProject.model.Aviso;
import com.myfactory.SBootWebProject.model.TpoFrecuRepeticion;
import com.myfactory.SBootWebProject.model.User;
import com.myfactory.SBootWebProject.servicesJPA.ServComunesAplicacionJPA;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAAviso;
import com.myfactory.SBootWebProject.servicesJPA.ServJPACliente;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAUsuario;

@Controller
@RequestMapping("/administracion")
@PropertySource("classpath:parametrosAplicacion.properties")
public class ControllerWebAdministracion {

	@Autowired
	ServComunesAplicacionJPA servicioJPA;
	@Autowired
	ServJPACliente servicioClienteJPA;
	@Autowired
	ServJPAUsuario servJPAUsuario;
	@Autowired
	ServJPAAviso servJPAAviso;
	@Autowired
	CargaBeansDatos cargarBeansDatos;
	@Autowired
	BeanUsuarioSession beanUsuarioSession;

	protected static final Logger parentLogger = LogManager.getLogger();

	@Value("${path.MACOSDescargaFicheros}")
	private String pathMacOS;
	
	@Value("${path.PathMACOSCopiaSeguridad}")
	private String pathMacOSCopiaSeguridad;
		
	@Value("${path.PathWindowsCopiaSeguridad}")
	private String pathWindowsOSCopiaSeguridad;	
	
	@Value("${path.PathMACOSMySQLDump}")
	private String pathMacOSMySQLDump;
		
	@Value("${path.PathWindowsMySQLDump}")
	private String pathWindowsOSMySQLDump;
		

	@GetMapping("/formcopiaseguridad")
	public String formCopiaSeguridad(Model modelo) {
		
	//	Scanner sc = new Scanner(System.in);
	//	System.out.println("Escribe la ruta: ");
	//  String ruta = sc.nextLine();
	//  System.out.println("Escribe la extension: ");
	//  String ext = sc.nextLine();
	
		List<BeanFicheroSO> listFicheros = new ArrayList<BeanFicheroSO>();
	    
	    File carpeta = new File("/Users/UsuarioJoseLuis/Documents/");
	    File[] archivos;
	    if(carpeta.exists()) {
	        if(carpeta.isDirectory()) {
	            archivos = carpeta.listFiles();
	            
	            Arrays.sort(archivos, Comparator.comparingLong(File::lastModified).reversed());
	            
	            for(int i=0; i<archivos.length; i++) {
	                if(archivos[i].getName().toLowerCase().endsWith(".sql")) 
	                {
		               BeanFicheroSO ficheroSO = new BeanFicheroSO();
		               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		                  
		               try 
		                 {
		                  Path fileObj = Paths.get("/Users/UsuarioJoseLuis/Documents/" + archivos[i].getName());
		                  BasicFileAttributes attr = Files.readAttributes(fileObj, BasicFileAttributes.class);
		                  
		                  ficheroSO.setNomFichero(archivos[i].getName());
		                  ficheroSO.setFechaCreacion(sdf.format( attr.creationTime().toMillis() ));
							
					      listFicheros.add(ficheroSO);
		                  }
		                 catch (Exception e)
		                  {
		                   parentLogger.error("Se ha producido un error al recuperar la lista de copias de seguridad" ); 
		                  }
	                }
	                // lo Metemos en un list
	            }
	        }
	    }
	    
	    modelo.addAttribute("pathDestinoCopia", pathMacOSCopiaSeguridad);
	    modelo.addAttribute("listFicheros", listFicheros);
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	    
		return "GestionWeb/administracion/FormCopiaSeguridad";
	}

	@RequestMapping("/generarcopiaseguridad")
	public String generarCopiaSeguridad(Model modelo) {
		
		copsegMySQLBueno();

		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		return "GestionWeb/administracion/FormResulCopiaSeguridad";
	}

	@GetMapping("/formrestaurarcopiaseg")
	public String formRestaurarCopSeg(Model modelo) {
		 List<BeanFicheroSO> listFicheros = new ArrayList<BeanFicheroSO>();
	 
		 File carpeta = new File("/Users/UsuarioJoseLuis/Documents/");
		 File[] archivos;
		 if (carpeta.exists()) {
		    if(carpeta.isDirectory()) {
		        
		       archivos = carpeta.listFiles();
		       Arrays.sort(archivos, Comparator.comparingLong(File::lastModified).reversed());
		            
		            for(int i=0; i<archivos.length; i++) {
		                if(archivos[i].getName().toLowerCase().endsWith(".sql")) 
		                 {
		                  BeanFicheroSO ficheroSO = new BeanFicheroSO();
				          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		                 
		                  try 
		                  {
		                   Path fileObj = Paths.get("/Users/UsuarioJoseLuis/Documents/" + archivos[i].getName());
		                   BasicFileAttributes attr = Files.readAttributes(fileObj, BasicFileAttributes.class);
		                  
		                   ficheroSO.setNomFichero(archivos[i].getName());
		                   ficheroSO.setFechaCreacion(sdf.format( attr.creationTime().toMillis() ));
							
					       listFicheros.add(ficheroSO);
		                  }
		                  catch (Exception e)
		                  {
		                   parentLogger.error("Se ha producido un error al recuperar la lista de copias de seguridad a restaurar" ); 
		                  }
		                }
		                // lo Metemos en un list
		            }
		        }
		    }
		    
		 modelo.addAttribute("pathDestinoCopia", pathMacOSCopiaSeguridad);
		 modelo.addAttribute("listFicheros", listFicheros);
		 modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		 
		return "GestionWeb/administracion/FormRestauraCopSeg";
	}

	@RequestMapping("/generarrestauracioncopseg")
	public String generarRestaurarCopSeg(Model modelo) {

		restoreMySQLBueno("");

		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		return "GestionWeb/administracion/FormResulRestaurarCopSeg";
	}

	@GetMapping("/formcreartarea")
	public String formCrearTarea(Model modelo) {

	 BeanTareaWeb datosTareaWeb = new BeanTareaWeb();

	 datosTareaWeb.setFecCreacionAviso(Calendar.getInstance());
	 datosTareaWeb.setTpoFrecuRepeticion(servicioJPA.getTpoFrecRepeticion());
	 datosTareaWeb.setUsuario(servJPAUsuario.getUsuarios());

	 modelo.addAttribute("datosTareaWeb", datosTareaWeb);
	 modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

	 return "GestionWeb/administracion/FormCrearTarea";
	}
	
	@GetMapping("/forminfotecnologias")
	public String formInformacionTecnologias(Model modelo) {
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		return "GestionWeb/administracion/InfoTecnologias";
	}
	
	@GetMapping("/pruebas")
	public String pruebas(Model modelo) {
	 System.out.println("12345");	
	 modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

	 return "GestionWeb/administracion/Prueba1";
	}
	
	@RequestMapping(value = "/creartarea", method = RequestMethod.POST)
	public String crearTarea(Model modelo, 
			@Valid  @ModelAttribute("datosTareaWeb") BeanTareaWeb beanTareaWeb,
			BindingResult resultValidacion,
			@RequestParam(value = "frecuencia", required = true) String codFrecuencia,
 			@RequestParam(value = "codUsuario", required = true) String idUsuario) {
		
		BeanErrorValidacion datosError = null;
		Aviso aviso = new Aviso();

		if (! resultValidacion.hasErrors()) 
			{
			try 
			{
			Map<String, Object> resultValTarea;
			resultValTarea = validarDatosTarea(beanTareaWeb, codFrecuencia, idUsuario);

			datosError = (BeanErrorValidacion) resultValTarea.get("errorValidacion");

			if (datosError.getCodError().intValue() != 0) {
				modelo.addAttribute("errorValidacion", true);
				modelo.addAttribute("mensajeError",
				datosError.getCodError().toString() + ", " + datosError.getDesError());
			    }
			   else
				{
					aviso = (Aviso) resultValTarea.get("tareaValidacion");

					// Dar de alta Empleado
					servJPAAviso.crearAviso(aviso);

					modelo.addAttribute("errorValidacion", false);
					modelo.addAttribute("mensajeError", "");
					modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

					return "GestionWeb/administracion/FormCrearTarea";

				}
			} 
			catch (Exception e)
			{
			modelo.addAttribute("errorValidacion", true);
			modelo.addAttribute("mensajeError",
			datosError.getCodError().toString() + ", " + datosError.getDesError());
		    parentLogger.error("Se ha producido un error al crear la tarea para el usuario: " + idUsuario ); 
			}

			modelo.addAttribute("datosTareaWeb", beanTareaWeb);
			modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

			return "GestionWeb/administracion/FormCrearTarea";
		}
		else 
		{
		//	datosTareaWeb.setTpoFrecuRepeticion(servicioJPA.getTpoFrecRepeticion());
		//	datosTareaWeb.setUsuario(servJPAUsuario.getUsuarios());

			modelo.addAttribute("datosTareaWeb", beanTareaWeb);
			return "GestionWeb/administracion/FormCrearTarea";
		}
	}
	
	public static void backupOpcion2() {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
        String backupPath = String.format("%s/%s.%s", "/Users/UsuarioJoseLuis/Documents", "BeigarBD" + currentDate, "sql");
        File backupFile = new File(backupPath);
       // if (!backupFile.exists()) {
            try {
            backupFile.createNewFile();
            String mysqlCom2=String.format("/Applications/XAMPP/xamppfiles/bin/mysqldump -u root -p springboot > /Users/UsuarioJoseLuis/Documents/copseg__2.sql");
         // String[] command = new String[] { "/bin/bash", "-c" , "echo 19mendez70| sudo -S",  mysqlCom};    
         // String[] command = new String[] { "/bin/bash", "-c" , "ls > /Users/UsuarioJoseLuis/Documents/lista12.txt"};
         //String[] command = new String[] { "/bin/bash", "-c" , "echo 19mendez70| sudo -S /sbin/ifconfig > /Users/UsuarioJoseLuis/Documents/lista15.txt" };
            String[] command2 = new String[] { "/bin/bash", "-c" , "echo 19mendez70| sudo -S ", mysqlCom2 };
   
            
            ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(command2));
            processBuilder.redirectError(Redirect.INHERIT);
            processBuilder.redirectOutput(Redirect.to(backupFile));
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("Se ha realizado la backup de la BBDD SpringBoot");//
			// parentLogger.error("Se ha realizado la backup de la BBDD SpringBoot");
        } catch (IOException e1) {
            parentLogger.error("Se ha producido un error en el fichero al restaurar la copia de seguridad"); 
            e1.printStackTrace();
        } catch (InterruptedException e) {
            parentLogger.error("Se ha producido un error en el proceso de recuperación la copia de seguridad a restaurar" ); 
            e.printStackTrace();
        }
	}
	
	
	public static void copsegMySQLBueno() {
         // String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));

       try 
         {
           String[] comandoUnixArr2 = new String[] { "sh", "/Users/UsuarioJoseLuis/Documents/copsegbeigar.sh"};
          
           ProcessBuilder pb = new ProcessBuilder(comandoUnixArr2);
         //   Map<String, String> env = pb.environment();
         //   env.put("VAR1", "myValue");
         //   env.remove("OTHERVAR");
         //   env.put("VAR2", env.get("VAR1") + "suffix");
         //   pb.directory(new File("myDir"));
            Process p = pb.start();
            p.waitFor();
              
        } 
       catch (IOException e1) 
          {
            e1.printStackTrace();
            parentLogger.error("Se ha producido un error en el fichero al restaurar la copia de seguridad"); 
         } 
       catch (InterruptedException e)
         {
            e.printStackTrace();
            parentLogger.error("Se ha producido un error en el proceso de recuperación la copia de seguridad a restaurar" ); 
         }
	}

	public static void restoreMySQLBueno(String nomFicheroSQL) {
       // String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
       // String backupPath = String.format("%s/%s.%s", "/Users/UsuarioJoseLuis/Documents", "BeigarBD" + currentDate, "sql");
       // File backupFile = new File(backupPath);
       // if (!backupFile.exists()) {
          try {
       //  backupFile.createNewFile();
          String[] comandoUnixArr2 = new String[] { "sh", "/Users/UsuarioJoseLuis/Documents/restorecopsegbeigar.sh"};
           
          ProcessBuilder pb = new ProcessBuilder(comandoUnixArr2);
       //   Map<String, String> env = pb.environment();
       //   env.put("VAR1", "myValue");
       //   env.remove("OTHERVAR");
       //   env.put("VAR2", env.get("VAR1") + "suffix");
       //   pb.directory(new File("myDir"));
            Process p = pb.start();
            p.waitFor();
            
      } catch (IOException e1) {
          e1.printStackTrace();
          parentLogger.error("Se ha producido un error en el proceso de recuperación la copia de seguridad a restaurar" ); 
      } catch (InterruptedException e) {
          e.printStackTrace();
          parentLogger.error("Se ha producido un error en el proceso de recuperación la copia de seguridad a restaurar" ); 
      }
	}
	
	private static void restaurarCopiSegMySQL() {
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
		String backupPath = String.format("%s/%s.%s", "/Users/UsuarioJoseLuis/Documents", currentDate, "sql");
		File backupFile = new File(backupPath);
		if (!backupFile.exists()) {
			try {
				backupFile.createNewFile();
				String mysqlCom = String.format("/Applications/XAMPP/xamppfiles/bin/mysql -u%s -p%s %s", "root", "",
						"springboot", " < 2020_12_28.sql");

				String[] command = new String[] { "/bin/bash", "-c", "echo 19mendez70 | sudo -S ", mysqlCom };
				ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(command));
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

		} else {
			parentLogger.error("Se realizo la copia de seguridad ya");
		}
	}

	private Map<String, Object> validarDatosTarea(BeanTareaWeb datosTareaWeb, String codFrecuencia, String idUsuario) {

		Map<String, Object> resultadoValidacion = new HashMap<>();
		BeanErrorValidacion datosErrorValidacion = new BeanErrorValidacion(new Integer(0));

		Aviso aviso = new Aviso();

		aviso.setDesTarea(datosTareaWeb.getDesTarea());
		aviso.setDirEnlaceProceso(datosTareaWeb.getDirEnlaceProceso());
		aviso.setFecCreacionAviso(datosTareaWeb.getFecCreacionAviso());
		aviso.setFecLimiteAviso(datosTareaWeb.getFecLimiteAviso());
		
		aviso.setIndLeido(false);
		
		User usuario = new User();
		usuario.setId(new Long(idUsuario) );

		aviso.setUsuario(usuario);

		TpoFrecuRepeticion tpoFrecuRepeticion = new TpoFrecuRepeticion();
		tpoFrecuRepeticion.setIdFrecuRepeticion( new Integer(codFrecuencia) ); 
		aviso.setTpoFrecuRepeticion(tpoFrecuRepeticion);

		resultadoValidacion.put("tareaValidacion", aviso);
		resultadoValidacion.put("errorValidacion", datosErrorValidacion);

		return resultadoValidacion;
	}

}
