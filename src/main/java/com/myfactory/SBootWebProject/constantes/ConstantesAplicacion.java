package com.myfactory.SBootWebProject.constantes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ConstantesAplicacion {
	
	// Numero de registros mostrados por paginas
	public static final Integer REG_POR_PAGINA  = new Integer(5);
	public static final int NUM_PAGINAS_PAGINADAS  = 4;
	public static final int NUM_PAGINAS_POR_BLOQUE  = 25;

	// Nombres de servicio
	public static final String SERVICIO_PERSONA_FISICA = "repositorioDatosPersonaFisicaHost";

	// Origen Expediente REE
	public static final String ORIGEN_EXPEDIENTE_REE_SEPE = "SEPE";
	public static final String ORIGEN_EXPEDIENTE_REE_EM = "EM";

	
	// Retornos de backing beans

	public static final String RETORNO_OK = "0";
	public static final String RETORNO_EMPTY = "";
	public static final String RETORNO_ZERO = "0";
	public static final String RETORNO_NULL = null;
	public static final String RETORNO_SUCCESS = "success";
	public static final String RETORNO_ERROR = "error";
	public static final Boolean TRUE = true;
	public static final Boolean FALSE = false;
	

	// Retorno de Error
	public static final String RETORNO_ERROR_CONSULTA = "errorConsulta";



	// Tipos de movimiento
	public static final BigDecimal TIPO_MOVIMIENTO_MANUAL = BigDecimal
			.valueOf(Long.valueOf("1"));
	

	
	public static final String PATRON_FECHA_ONLY = "dd/MM/yyyy";
	public static final String PATRON_FECHA = "dd/MM/yyyy HH:mm:ss";
	public static final String PATRON_FECHA_DOC = "yyyyMMddHHmmssSSS";
	public static final String ENCODING_ES = "ISO-8859-1";


	
//	public static final Long TIPO_INTER_LONG = Long.valueOf(TIPO_INTER);
	 

	public static final String NIF_DEFECTO_REPRE = "-1";
	public static final String REPRE_SELECCION = "representante";

	public static final String ENVIO_NOTIF = "1";

	public static final Long NO_ES_ALTA_RRCC = 0L;


	// Properties JASPER
	public static final String JASPER_DOC_CONFORMIDAD = "jasper.documento_respuesta_peticiones_reembolso";
	public static final String JASPER_U1 = "jasper.u1";
	public static final String JASPER_U2 = "jasper.u2";
	
	// Constantes utilizadas para la configuración de la firma del documento
	public static final String DOMINIO_FIRMA = "D";
	public static final String PREFIJO_FIRMA = "CVE: ";
	public static final String SUFIJO_FIRMA = "\nAutenticidad verificable en: https://sede.sepe.gob.es";
//	public static final Point POSICION_FIRMA = new Point(30, 3);
	public static final int TAMANIO_FUENTE = 12;
	public static final int ANCHURA = 40;
	public static final String CONFIGURACION_DOCUMENTO_PDF = "pdfapp_4";

	// XPath
	public static final String XPATH_INSTITUCION_ORIGEN = "/documento/institucion[@procedencia='origen']";
	public static final String XPATH_INSTITUCION_DESTINO = "/documento/institucion[@procedencia='receptora']";
	

	// Máximo de caracteres para VARCHAR (4000 bytes)
	
	public static final String ID_TAG_FORM_U021 = "25";
	public static final String ID_TAG_FORM_U022 = "26";
	public static final String ID_TAG_FORM_U023 = "27";

	// Errores messages
	public static final String ERROR_GENERAL = "Ha habido un problema interno con la aplicación. Consulte con el administrador.";

	
	public static final String ERROR_ALTAEXP_VL = "El interesado tiene registros de alta en Vida Laboral. No se puede dar de alta el expediente.";

	
	public static final String ERROR_GENDOC_GUARDAR_ANEXO = "No es posible generar un documento de tipo Anexo.";


	public static final String ERROR_BUSQUEDA_ASUNTOS_UNICOS = "Error al buscar asuntos únicos";
	public static final String ERROR_BUSQUEDA_ASUNTOS_UNICOS_SEPE = "Error al iniciar la búsqueda de asuntos únicos de SEPE";

	public static final String ERROR_BUSQUEDA_EXPEDIENTES_REE = "Error al buscar expedientes de reembolso";

	
	public static final String ERROR_EDITAR_DATOS_TIPO_EXPEDIENTE_REE = "Error al editar los datos de tipo expedientes de reembolso";
	public static final String ERROR_ANULAR_EXPEDIENTE = "Se ha producido un error al anular el expediente";
	
	public static final String ERROR_MAX_REGISTROS_CENDAR = "Se ha superado el límite permitido de registros de Cotizaciones para el periodo especificado. En este caso, indique un periodo de tiempo inferior.";

	public static final String ERROR_EDITAR_ASUNTO_UNICO_GEN_DOC_CONF = "Se ha producido un error al generar el documento de conformidad";
	
	public static final String ERROR_VAL_INTERESADO = "Por favor, rellene los campos obligatorios del interesado";
	public static final String ERROR_VAL_REPRESENTANTE = "Por favor, rellene los campos obligatorios del representante";	
	
	//ERRORES CONSULTAS GTSPEE
	public static final String ERROR_GTSPEE_CONSULTA_DATOS = "Error en la consulta de datos.";
	
	
	public static final String WARNING_NO_EXISTE_EN_ENTIDADES_COMUNES="No se han encontrado datos en entidades comunes para el NIF/NIE introducido. Puede continuar con el alta.";
	public static final String WARNING_NO_PRESTACIONES="No se han encontrado prestaciones para el NIF/NIE introducido. Puede continuar con el alta.";
	
	
	// Vida laboral
	public static final String CCC_SEG_CUENTA_AJENA = "0111";
	public static final String MODALIDAD_COTIZACION_0 ="2 - SISTEMA POR JORNADAS REALES (613)1";
	public static final List<Integer> TR_VL_EMPLEO = Arrays.asList(new Integer[] { 60, 61, 62, 63, 64 });

	 

	public static final Boolean FLUJO_RETRY_XML = true;

	// Tipos Expediente
	// Control de extensiones
	public static String[] EXTENSIONES_NO_PERMITIDAS = { "ade", "adp",
		"app", "asp", "bas", "bat", "cer", "chm", "cmd", "com",
		"cpl", "crt", "csh", "der", "exe", "der", "fxp", "hlp",
		"hta", "inf", "ins", "isp", "its", "js", "jse", "ksh",
		"lnk", "mad", "maf", "mag", "mam", "maq", "mar", "mas",
		"mat", "mau", "mav", "maw", "mda", "mdb", "mde", "mdt",
		"mdw", "mdz", "msc", "msh", "msh1", "msh2", "mshxml",
		"msh1xml", "msh2xml", "msi", "msp", "mst", "ops", "pcd",
		"pif", "plg", "prf", "prg", "pst", "reg", "scf", "scr",
		"sct", "shb", "shs", "tmp", "url", "vb", "vbe", "vbs",
		"vsmacros", "vsw", "ws", "wsc", "wsf", "wsh" };
	
	public static final int COD_CATALOGO_CONF_REEM = 1286;

	public static final String COD_CATALOGO_MODELO_U020 = "U020-Solicitud de Reembolso";

	//Query para borrar asuntos únicos
	public static final String SQL_BORRAR_REL_PERSONA_DOC = "delete from RRCC_REL_EXP_PERSONA where ID_EXPEDIENTE_FK = ? and ID_PERSONA_FK = ?";
}