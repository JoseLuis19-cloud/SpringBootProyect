package com.myfactory.SBootWebProject.constantes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ConstantesErroresAplicacion {

	// Errores messages
	public static final String ERROR_FORMATO_FECHA = "Ha habido un problema interno con la aplicación. Consulte con el administrador.";

	
	public static final String ERROR_ALTAEXP_VL = "El interesado tiene registros de alta en Vida Laboral. No se puede dar de alta el expediente.";

	
	public static final String ERROR_GENDOC_GUARDAR_ANEXO = "No es posible generar un documento de tipo Anexo.";


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