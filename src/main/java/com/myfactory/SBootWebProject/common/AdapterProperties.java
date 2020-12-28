package com.myfactory.SBootWebProject.common;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * <p>
 * Titulo: AdapterProperties
 * </p>
 * <p>
 * Descripcion: Lector ficheros de propiedades
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Empresa:
 * </p>
 * 
 * @author Xurxo
 * @version 2.0
 */

public class AdapterProperties {

    private static final String PATH_PROPERTIES = "/properties/";

    /**
     * Metodo encargado de recuperar todas las propiedades de un fichero.
     * 
     * @param fic
     *                String con el nombre del fichero de propiedades del que se
     *                va a leer la propiedad. Este nombre de fichero es relativo
     *                a la propiedad pathProperties que se encuentra en el
     *                fichero com.cesce.arquitectura.adapter.adaptador
     * @return HashMap<String, String> con las propiedades leidas
     * @throws Exception
     */
    public static HashMap<String, String> recuperarTodasProps(String fic) throws Exception {

	HashMap<String, String> hMapProps = new HashMap<String, String>();

	Properties ficPropiedades = new Properties();

	InputStream is = null;

	try {

	    is = AdapterProperties.class.getResourceAsStream(PATH_PROPERTIES + fic);
	    ficPropiedades.load(is);

	    for (Enumeration<?> e = ficPropiedades.propertyNames(); e.hasMoreElements();) {
		String nomItem = (String) e.nextElement();
		hMapProps.put(nomItem, ficPropiedades.getProperty(nomItem));
	    }

	} finally {
	    try {
		is.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	return hMapProps;
    }

    /**
     * Metodo encargado de recuperar una propiedades de un fichero.
     * 
     * @param fic
     *                String con el nombre del fichero de propiedades del que se
     *                va a leer la propiedad. Este nombre de fichero es relativo
     *                a la propiedad pathProperties
     * @param nomPropiedad
     *                String con el nombre de la propiedad a leer
     * @return String contenido de la propiedad leida
     * @throws Exception
     */
    public static String recuperarProp(String fic, String nomPropiedad) throws Exception {

	Properties ficPropiedades = new Properties();

	InputStream is = null;
	String result = "";

	try {

	    is = AdapterProperties.class.getResourceAsStream(PATH_PROPERTIES + fic);
	    ficPropiedades.load(is);
	    result = ficPropiedades.getProperty(nomPropiedad);

	} finally {
	    try {
		is.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return result;
    }

}// Fin-clase
