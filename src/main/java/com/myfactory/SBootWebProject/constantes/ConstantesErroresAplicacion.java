package com.myfactory.SBootWebProject.constantes;

import java.util.Arrays;
import java.util.List;

public class ConstantesErroresAplicacion {

	// Errores messages
	public static final int COD_ERROR_FOR_FECHA = 10;
	public static final String ERROR_FORMATO_FECHA = "El formato de la fecha introducida no es correcta";
	
	public static final int COD_ERROR_USUARIO_DUPLICADO = 20;
	public static final String ERROR_USUARIO_DUPLICADO = "Error username del Usuario esta es duplicado";
	
	public static final int COD_ERROR_FULLNAME_DUPLICADO = 21;
	public static final String ERROR_FULLNAME_DUPLICADO = "Error fullname del Usuario esta es duplicado";
	
	public static final int COD_ERROR_EMAIL_DUPLICADO = 30;
	public static final String ERROR_EMAIL_DUPLICADO = "El email ya esta asignado a otro usuario";

	public static final List<Integer> TR_VL_EMPLEO = Arrays.asList(new Integer[] { 60, 61, 62, 63, 64 });
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