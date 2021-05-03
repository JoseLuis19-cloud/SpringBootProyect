package com.myfactory.SBootWebProject.informesjasper;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.PropertySource;

import org.springframework.core.io.ResourceLoader;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.myfactory.SBootWebProject.dto.EmpleadoDTO;
import com.myfactory.SBootWebProject.dto.FacturaDTO;
import com.myfactory.SBootWebProject.dto.FacturaLineaDTO;
import com.myfactory.SBootWebProject.dto.ProyectoDTO;
import com.myfactory.SBootWebProject.dto.UserDTO;
import com.myfactory.SBootWebProject.model.Empleado;
import com.myfactory.SBootWebProject.model.Factura;
import com.myfactory.SBootWebProject.model.FacturaLinea;
import com.myfactory.SBootWebProject.model.Proyecto;
import com.myfactory.SBootWebProject.model.User;
@PropertySource(value = "classpath:/parametrosaplicacion.properties", ignoreResourceNotFound = true)
public class GeneradorJasper {
	
	private static final String REPORTE_JAPSER_EMPLEADOS = "InformeEmpleados.jasper";
	private static final String REPORTE_JAPSER_USUARIOS = "/jasper/informes/InformeUsuarios.jasper";
	private static final String REPORTE_JAPSER_PROYECTO = "/jasper/informes/InformeUsuarios.jasper";
	
	@Value("${pathMACOSdescargaficheros}")
    private String pathWindows;

	// @PersistenceContext(unitName = "springboot")
	// private EntityManager entityManager;

	private java.sql.Connection conexionBBDD = null;
	protected static final Logger parentLogger = LogManager.getLogger();
	
	private static final String DATE_FORMAT = "dd/MM/yyyy";

    // @Qualifier("springboot")

	@Autowired
	ResourceLoader resourceLoader;

	// @Autowired
	// static private Environment environment;

	static DataSource datasource() throws PropertyVetoException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		/*
		 * dataSource.setDriverClassName(environment.getProperty(
		 * "spring.datasource.driver-class-name"));
		 * dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		 * dataSource.setUsername(environment.getProperty("spring.datasource.username"))
		 * ;
		 * dataSource.setPassword(environment.getProperty("spring.datasource.password"))
		 * ;
		 */

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(
				"jdbc:mysql://localhost:3308/springboot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}

	public GeneradorJasper() {
		try {
			// -- EntityManagerFactory emFactory =
			// Persistence.createEntityManagerFactory("springboot");
			// -- EntityManager entityManager = emFactory.createEntityManager();
			// entityManager.getTransaction().begin();
			// conexionBBDD = entityManager.unwrap(java.sql.Connection.class); // unwraps
			// the Connection class.
			conexionBBDD = datasource().getConnection();

		} catch (Exception exp) {
			System.out.println("Se ha producido un error en la conexion con la base de datos");//
			parentLogger.error("Se ha producido un error en la conexion con la base de datos " + exp);
		}
	}

	public JasperPrint generarInformeEmpleados(Iterator<Empleado> iterEmpleados) {
		
		List<EmpleadoDTO> listEmpleadoDTO = new ArrayList<EmpleadoDTO>();
		Empleado empleado;
		
		  while(iterEmpleados.hasNext()){
			  
			  empleado = iterEmpleados.next();
			  EmpleadoDTO empleadoDTO = new EmpleadoDTO();
			//  empleadoDTO.setID_EMPLEADO( empleado.getIdEmpleado() );
			  empleadoDTO.setNOMBRE(empleado.getNombre() );
			  empleadoDTO.setAPELLIDOS(empleado.getApellidos());
			  empleadoDTO.setEMAIL(empleado.getEmail());
			  empleadoDTO.setNIF(empleado.getNif());
			  empleadoDTO.setFEC_ALTA_EMPLEADO( new java.sql.Date( empleado.getFecAltaEmplelado().getTimeInMillis() ) );
			  empleadoDTO.setTEL_MOVIL( empleado.getTelefMovil());
			  empleadoDTO.setIMP_BRUTO_ANUAL(empleado.getImpBrutoAnual() );
			  empleadoDTO.setIMP_FACTURADO_MES(empleado.getImpFacturadoMes());
			  
			  listEmpleadoDTO.add(empleadoDTO);
		    }

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listEmpleadoDTO, false);

		try {

		//	String path = resourceLoader.getResource("classpath:" + REPORTE_JAPSER_EMPLEADOS).getURI().getPath();
		//	JasperReport jasperReport = JasperCompileManager.compileReport(path);
			
			// Adding the additional parameters to the pdf.
	        final Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "javacodegeek.com");

			// Fetching the .jrxml file from the resources folder.
	        final InputStream stream = this.getClass().getResourceAsStream("/plantillasjasper/InformeEmpleados.jrxml");
	        													
	     // Compile the Jasper report from .jrxml to .japser
	        JasperReport report = JasperCompileManager.compileReport(stream);
			
			// JasperPrint reportGenerado = JasperFillManager.fillReport(jasperReport, parameters);
			 JasperPrint reportGenerado = JasperFillManager.fillReport(report, parameters, dataSource);
			 
			return reportGenerado;
			
			} 
		catch (Exception ex)
			{
				parentLogger.error("Se ha producido un error en la generacion del informe Jasper " + ex);
			} finally
			{
			// entityManager.getTransaction().commit();

			}
		return null;
	}

	public JasperPrint generarInfomeUsuarios(Iterator<User> iterUsuarios) {
		try {
		
		//	List<User> listUsuario = new ArrayList<User>();
		//	iterUsuarios.forEachRemaining(listUsuario::add);

			List<UserDTO> listUsuarioDTO = new ArrayList<UserDTO>();
			User usuario;
			
			  while(iterUsuarios.hasNext()){
				  
				  usuario = iterUsuarios.next();
				  UserDTO userDTO = new UserDTO();
				  
				  userDTO.setUser_id(usuario.getId() );
				  userDTO.setEmail(usuario.getEmail() );
				  userDTO.setFull_name( usuario.getFullName());
				  userDTO.setUsername(usuario.getUsername() );
				  
				  listUsuarioDTO.add(userDTO);
			    }

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listUsuarioDTO, false);
			
		//	String path = ClassLoader.getSystemResource("/InformeUsuarios.jrxml").toURI().getPath();
		// String path = resourceLoader.getResource("classpath:InformeUsuarios.jrxml").getURI().getPath();
		//	JasperReport jasperReport = JasperCompileManager.compileReport(path);
			
			
			// String path = resourceLoader.getResource("classpath:InformeUsuarios.jrxml").getURI().getPath();
			// JasperReport jasperReport2 = JasperCompileManager.compileReport(path);
			// System.out.println(jasperReport2);
			
			// Adding the additional parameters to the pdf.
	        final Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "javacodegeek.com");
	        
			
		// Fetching the .jrxml file from the resources folder.
	        final InputStream stream = this.getClass().getResourceAsStream("/plantillasjasper/InformeUsuarios.jrxml");
	     // Compile the Jasper report from .jrxml to .japser
	        JasperReport report = JasperCompileManager.compileReport(stream);
			
	        JasperPrint reportTerminado = JasperFillManager.fillReport(report, parameters, dataSource);
			
	        return reportTerminado;
			
		} catch (Exception ex) {

			parentLogger.error("Se ha producido un error en la generacion del informe Jasper " + ex);
		}
		return null;
	}
	
	public JasperPrint generarInfomeProyectos(Iterator<Proyecto> iterProyectos) {
		try 
		{
			List<ProyectoDTO> listProyectoDTO = new ArrayList<ProyectoDTO>();
			Proyecto proyecto;
			
			  while(iterProyectos.hasNext())
			  {  
				  proyecto = iterProyectos.next();
				  ProyectoDTO proyectoDTO = new ProyectoDTO();
				  
				  proyectoDTO.setID_PROYECTO(proyecto.getIdProyecto());
				  proyectoDTO.setNOM_PROYECTO(proyecto.getNomProyecto());

			 	  proyectoDTO.setFEC_INI_PROYECTO( new java.sql.Date( proyecto.getFecIniProyecto().getTimeInMillis() ) );
			 	  proyectoDTO.setFEC_FIN_PROYECTO( new java.sql.Date( proyecto.getFecFinProyecto().getTimeInMillis() ) );
				  proyectoDTO.setIMP_PROYECTO(proyecto.getImpProyecto());
				  proyectoDTO.setUBICACION_PROYECTO( proyecto.getUbicacionProyecto());
				  
				  listProyectoDTO.add(proyectoDTO);
			    }

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listProyectoDTO, false);
			
			// Adding the additional parameters to the pdf.
	        final Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "javacodegeek.com");
	    //    parameters.put("ROOT_DIR", "");
	    //    parameters.put("IMAGE_DIR", "");
	       
			
		// Fetching the .jrxml file from the resources folder.
	        final InputStream stream = this.getClass().getResourceAsStream("/plantillasjasper/InformeProyectos.jrxml");
	     // Compile the Jasper report from .jrxml to .japser
	        JasperReport report = JasperCompileManager.compileReport(stream);
			
	        JasperPrint reportTerminado = JasperFillManager.fillReport(report, parameters, dataSource);
			
	        return reportTerminado;
			
		} catch (Exception ex) {

			parentLogger.error("Se ha producido un error en la generacion del informe Jasper " + ex);
		}
		return null;
	}
	
	
public JasperPrint generarPDFFactura( Factura factura) {
		
		JasperPrint reportGenerado = null;
		FacturaDTO facturaDTO = new FacturaDTO();
	
	//	List<FacturaDTO> listFacturaDTO = new ArrayList<FacturaDTO>();
		List<FacturaLineaDTO> listLineasFacturaDTO = new ArrayList<FacturaLineaDTO>();
		 
	//	facturaDTO.setFEC_ALTA_FACTURA(new java.sql.Date( factura.getFecFactura().getTimeInMillis() ) );
		facturaDTO.setCOD_USUARIO(factura.getCodUsuario());
		facturaDTO.setCOD_DIVISA( factura.getCodDivisa());
		if (factura.getFecEmisionFactura()!= null)
			{
			facturaDTO.setFEC_EMISION_FACTURA(new java.sql.Date(factura.getFecEmisionFactura().getTimeInMillis() ) );
			}
		facturaDTO.setNUM_FACTURA( factura.getNumFactura() );
		facturaDTO.setPOR_IVA(factura.getPorIva());
		
	//	listFacturaDTO.add(facturaDTO);
		
		Set<FacturaLinea> lineasFactura = factura.getFacturaLineas();
		
		Iterator <FacturaLinea> iterFacturaLinea = lineasFactura.iterator();
		
		// Iterator <FacturaLinea> iterFacturaSitu = factura.getFacturaLineas().iterator();
		 
	//	List<BeanFacturaLineas> listFactuLineas = StreamSupport
//					  .stream(lineasFactura.spliterator(), false)
//					  .collect(Collectors.toList());
		
		
		for(FacturaLinea elemenLinFactura : lineasFactura){
			 
	//	while (lineasFactura.iterator().hasNext()) {
//			FacturaLinea elemenLinFactura  = lineasFactura.iterator().next();
	      
		// for (BeanFacturaLineas elemenLinFactura : lineasFactura.iterator())
			//  {
			 FacturaLineaDTO facturaLineaDTO = new FacturaLineaDTO();
			 
			 facturaLineaDTO.setCANTIDAD( elemenLinFactura.getCantidad());
			 facturaLineaDTO.setCONCEPTO( elemenLinFactura.getConcepto());
			 facturaLineaDTO.setIMP_LIN_FACTURA(elemenLinFactura.getImpLinFactura());
			 facturaLineaDTO.setPOR_IVA(elemenLinFactura.getPorIva());
			  
			 listLineasFacturaDTO.add(facturaLineaDTO);
		}
 
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listLineasFacturaDTO, false);

		try 
		{
		 // Adding the additional parameters to the pdf.
	        final Map<String, Object> parameters = new HashMap<>();
	        parameters.put("numFactura", factura.getNumFactura()  );
	        
	        // Datos Factura
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
	        Instant instant =  (new java.util.Date( factura.getFecFactura().getTimeInMillis() )).toInstant();
	        	 
	        LocalDateTime ldt = instant
	        	 .atZone(ZoneId.of("CET"))
	        	 .toLocalDateTime();
	        	 
	        

	        parameters.put("fecFactura", ldt.format(formatter));
	        
	        // Datos Empresa Beigar
	        parameters.put("CIFBeigar", "01925222N");
	        parameters.put("dirEmpBeigar", "Maria de Molina, 23");
	        parameters.put("telefEmpBeigar", "606443214");
	        
	        // Datos Cliente
	        parameters.put("nomEmpCliente", factura.getCliente().getApellidos());
	        parameters.put("CIFEmpCliente", factura.getCliente().getDNI() );
	        parameters.put("calleEmpCliente", factura.getCliente().getDireccion());
	        
			// Fetching the .jrxml file from the resources folder.
	        final InputStream stream = this.getClass().getResourceAsStream("/plantillasjasper/FacturaBeigar.jrxml");
	        													
	        // Compile the Jasper report from .jrxml to .japser
	        JasperReport report = JasperCompileManager.compileReport(stream);
			
			// JasperPrint reportGenerado = JasperFillManager.fillReport(jasperReport, parameters);
			 reportGenerado = JasperFillManager.fillReport(report, parameters, dataSource);
			 
			return reportGenerado;
			
			} 
		catch (Exception ex)
			{
				parentLogger.error("Se ha producido un error en la generacion del PDF de la factura " + ex);
				return null;
			} 
	}

}
	
/*	public static String generarPathExpRee(String rutaPrimaria) throws MKDIRException
	{
			Calendar now = Calendar.getInstance();
			String year =  new SimpleDateFormat("yyyy").format(now.getTime());
			String month = new SimpleDateFormat("MM").format(now.getTime());

			String strRutaDinamicaFichero =  year + "/" + month + "/";

			File file = new File(rutaPrimaria + File.separator + "reembolsos_RINA"); */
			
			/*---Se crea el nuevo directorio---*/
	/* if(!file.exists() || !file.isDirectory()) {
				if(!file.mkdir()) {
					//logger.log(Level.SEVERE, MKDIR + "[" + file.getAbsolutePath() + "]");
					throw new MKDIRException("[" + file.getAbsolutePath() + "]");
				}	
			}
			 file = new File(file.getAbsolutePath() + File.separator + year);*/
			
			/*---Se crea el nuevo directorio---*/
	/*	if(!file.exists() || !file.isDirectory()) {
				if(!file.mkdir()) {
					//logger.log(Level.SEVERE, MKDIR + "[" + file.getAbsolutePath() + "]");
					throw new MKDIRException("[" + file.getAbsolutePath() + "]");
				}	
			}
			
			file = new File(file.getAbsolutePath() + File.separator + month);
			if(!file.exists() || !file.isDirectory()) {
				if(!file.mkdir()) {
					
					throw new MKDIRException("[" + file.getAbsolutePath() + "]");
				}	
			}
			
			
			 return file.getAbsolutePath();
	  }*/
/* } */


/* String ruta = "/inem/sappweb/genDocs/apps/prestaciones/REGCOM/listados/";
String xmlRina=null;
List<RinaNuevoRegistroDTO> listRegistroConsultaDTO = new ArrayList<RinaNuevoRegistroDTO>();

final CatalogoPaises catalogoPaises = new CatalogoGTSPEEPaises();

String nombreFicheroREE = UtilidadDatos.nomFicheroExpREE(codPaisISO.obtenerCodISO(camposBusqueda.getFiltrosExp().getPaisDestino()) );
String rutaSalidaExport = UtilidadDatos.generarPathExpRee(ruta);
String reimbursementRequestID  = "XML"+ nombreFicheroREE+".XML"; */
