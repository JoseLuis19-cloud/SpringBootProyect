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

import com.myfactory.SBootWebProject.informesjasper.GeneradorJasper;
import com.myfactory.SBootWebProject.model.User;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAUsuario;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
 

@Controller
@RequestMapping("/informes")
@PropertySource("classpath:parametrosaplicacion.properties")
public class ControllerWebInformes {

	@Autowired
	private ServJPAUsuario servJPAUsuario;

	protected static final Logger parentLogger = LogManager.getLogger();
	
	// private final String UPLOAD_DIR = "./uploads/";
	@Value("${path.MACOSGeneracionPDF}")
	private String pathDescargaPDFMacOS;
	
	@RequestMapping("/informeempleados")
	public String paginacionUsuarios(Model modelo) {
	    try
	    {
	     GeneradorJasper genInfoJasper = new GeneradorJasper();
	     JasperPrint reportGenerado = genInfoJasper.generarInformeEmpleados();
	     JasperExportManager.exportReportToPdfFile(reportGenerado, "InformeEmpleados.pdf");
	     JasperViewer viewer = new JasperViewer(reportGenerado);
	     viewer.setVisible(true);
	    
	    } catch (JRException ex) {
        	parentLogger.error("Se ha producido un error en la generacion del informe Jasper " + ex);
        }
	 
		return "gestionWeb/informes/InformeEmpleados.html";
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

		return "gestionWeb/informes/InformesAplicacion.html";
	}
	
	
	@RequestMapping(value = "downloadFile", method = RequestMethod.GET)
    public StreamingResponseBody getSteamingFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
      //  response.setHeader("Content-Disposition", "attachment; filename=\"demo.pdf\"");
        InputStream inputStream = new FileInputStream(new File(pathDescargaPDFMacOS + "InformeUsuarios.pdf"));
        
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
