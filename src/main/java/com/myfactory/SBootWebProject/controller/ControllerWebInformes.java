package com.myfactory.SBootWebProject.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.myfactory.SBootWebProject.beanForm.BeanUsuarioSession;
import com.myfactory.SBootWebProject.informesjasper.GeneradorJasper;
import com.myfactory.SBootWebProject.model.Empleado;
import com.myfactory.SBootWebProject.model.Proyecto;
import com.myfactory.SBootWebProject.model.User;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAEmpleado;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAProyecto;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAUsuario;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/informes")
@PropertySource("classpath:parametrosAplicacion.properties")
public class ControllerWebInformes {

	@Autowired
	private ServJPAUsuario servJPAUsuario;
	
	@Autowired
	private ServJPAProyecto servJPAProyecto;
	
	@Autowired
	private ServJPAEmpleado servJPAEmpleado;
	
	@Autowired
	BeanUsuarioSession beanUsuarioSession;

	protected static final Logger parentLogger = LogManager.getLogger();
	
	// private final String UPLOAD_DIR = "./uploads/";
	@Value("${path.MACOSGeneracionPDF}")
	private String pathDescargaPDFMacOS;
	
	@RequestMapping("/forminformeproyectos")
	public String formInformeProyectos(Model modelo) {
		
	  modelo.addAttribute("datosInformes", "1");
	  modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	 return "GestionWeb/informes/FormInformeProyectos.html";
	}
	
	
	@RequestMapping("/forminformeempleados")
	public String formInformeEmpleados(Model modelo) {
		
	  modelo.addAttribute("datosInformes", "1");
	  modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	 return "GestionWeb/informes/FormInformeEmpleados.html";
	}
	
	@RequestMapping("/forminformeusuarios")
	public String formInformeUsuarios(Model modelo) {
		
	  modelo.addAttribute("datosInformes", "1");
	  modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
	 return "GestionWeb/informes/FormInformeUsuarios.html";
	}
 
	@RequestMapping("/informeusuarios")
	public String informeUsuarios(Model modelo) {
	   try
	    {
	    Iterable<User> usuarios = servJPAUsuario.listadoUsuarios();
		Iterator<User> iterUsuarios = usuarios.iterator();
		
	    GeneradorJasper genInfoJasper = new GeneradorJasper();
	     
	    JasperPrint reportGenerado = genInfoJasper.generarInfomeUsuarios(iterUsuarios);
	    JasperExportManager.exportReportToPdfFile(reportGenerado, pathDescargaPDFMacOS + "InformeUsuarios.pdf");
	    // JasperViewer viewer = new JasperViewer(reportGenerado);
	    // viewer.setVisible(true);
	    
	    } catch (JRException ex) {
        	parentLogger.error("Se ha producido un error en la generacion del informe Jasper " + ex);
        }
	   
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

		return "gestionWeb/informes/InformesAplicacion.html";
	}
	
	@RequestMapping("/informeempleado")
	public String informeEmpleado(Model modelo) {
	   try
	    {
	    Iterable<Empleado> empleado = servJPAEmpleado.obtenerListEmpleados();
		Iterator<Empleado> iterEmpleado = empleado.iterator();
		
	    GeneradorJasper genInfoJasper = new GeneradorJasper();
	     
	    JasperPrint reportGenerado = genInfoJasper.generarInformeEmpleados(iterEmpleado);
	    JasperExportManager.exportReportToPdfFile(reportGenerado, pathDescargaPDFMacOS + "InformeEmpleados.pdf");
	    // JasperViewer viewer = new JasperViewer(reportGenerado);
	    // viewer.setVisible(true);
	    
	    } catch (JRException ex) {
        	parentLogger.error("Se ha producido un error en la generacion del informe Jasper " + ex);
        }
	   
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());
		
		return "gestionWeb/informes/InformesAplicacion.html";
	}
	
	@RequestMapping("/informeproyectos")
	public String informeProyectos(Model modelo) {
	   try
	    {
	     Iterable<Proyecto> proyectos = servJPAProyecto.listProyectos() ;
		 Iterator<Proyecto> iterProyecto = proyectos.iterator();
		
	     GeneradorJasper genInfoJasper = new GeneradorJasper();
	     
	     JasperPrint reportGenerado = genInfoJasper.generarInfomeProyectos(iterProyecto);
	     JasperExportManager.exportReportToPdfFile(reportGenerado, pathDescargaPDFMacOS + "InformeProyectos.pdf");
	     
	    // JasperViewer viewer = new JasperViewer(reportGenerado);
	    // viewer.setVisible(true);
	    
	    } 
	   catch (JRException ex)
	     {
          parentLogger.error("Se ha producido un error en la generacion del informe Jasper " + ex);
         }
	   
		modelo.addAttribute("opcionesMenuUsuario", beanUsuarioSession.getListBeanMenuUsuarioSession());

		return "gestionWeb/informes/InformesAplicacion.html";
	}
	
	@RequestMapping(value = "downloadFile1", method = RequestMethod.GET)
    public StreamingResponseBody getSteamingFile1(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
      //  response.setHeader("Content-Disposition", "attachment; filename=\"demo.pdf\"");
        InputStream inputStream = new FileInputStream(new File(pathDescargaPDFMacOS + "InformeProyectos.pdf"));
        
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                System.out.println("Writing some bytes..");
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
        
    }
	
	@RequestMapping(value = "downloadFile2", method = RequestMethod.GET)
    public StreamingResponseBody getSteamingFile2(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
      //  response.setHeader("Content-Disposition", "attachment; filename=\"demo.pdf\"");
        InputStream inputStream = new FileInputStream(new File(pathDescargaPDFMacOS + "InformeProyectos.pdf"));
        
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                System.out.println("Writing some bytes..");
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
        
    }
	
	@RequestMapping(value = "downloadFile3", method = RequestMethod.GET)
    public StreamingResponseBody getSteamingFile3(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
      //  response.setHeader("Content-Disposition", "attachment; filename=\"demo.pdf\"");
        InputStream inputStream = new FileInputStream(new File(pathDescargaPDFMacOS + "InformeEmpleados.pdf"));
        
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                System.out.println("Writing some bytes..");
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
        
    }
 
}
