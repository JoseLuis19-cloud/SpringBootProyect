package com.myfactory.SBootWebProject.constantes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ConstantesAplicacion {
	
	// Numero de registros mostrados por paginas
	public static final Integer REG_POR_PAGINA  = new Integer(5);
	public static final Integer REG_POR_PAGINA_10  = new Integer(10);
	public static final int NUM_PAGINAS_PAGINADAS  = 4;
	public static final int NUM_PAGINAS_POR_BLOQUE  = 25;
	
	public static final int NUM_REG_POR_BLOQUE  = 50;
	
	public static final int FACTURAS_SECUENCIAL  = 1;
	
	public static final int COD_ISO_DIVISA_EUR  = 978;
	public static final String DES_ISO_DIVISA_EUR  = "EUR";

	// Retornos de backing beans

	public static final String RETORNO_OK = "0";
	public static final String RETORNO_EMPTY = "";
	public static final String RETORNO_ZERO = "0";
	public static final String RETORNO_NULL = null;
	public static final String RETORNO_SUCCESS = "success";
	public static final String RETORNO_ERROR = "error";
	public static final Boolean TRUE = true;
	public static final Boolean FALSE = false;
	
	public static final String PATRON_FECHA_ONLY = "dd/MM/yyyy";
	public static final String PATRON_FECHA = "dd/MM/yyyy HH:mm:ss";
	public static final String PATRON_FECHA_DOC = "yyyyMMddHHmmssSSS";
	public static final String ENCODING_ES = "ISO-8859-1";


	// Properties JASPER
	public static final String JASPER_DOC_CONFORMIDAD = "jasper.documento_respuesta_peticiones_reembolso";
	public static final String JASPER_U1 = "jasper.u1";
	public static final String JASPER_U2 = "jasper.u2";
	

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
}