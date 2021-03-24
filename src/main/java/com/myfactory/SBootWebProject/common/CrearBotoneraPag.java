package com.myfactory.SBootWebProject.common;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.ui.Model;

import com.myfactory.SBootWebProject.constantes.ConstantesAplicacion;

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
 * @author Jose Luis Bueno
 * @version 1.0
 */

public class CrearBotoneraPag {

    /**
     * Metodo encargado de gestionar lo paginacion y los valores de la botonera.
     * 
     * @param fic
     *                String con el nombre del fichero de propiedades del que se
     *                va a leer la propiedad. Este nombre de fichero es relativo
     *                a la propiedad pathProperties que se encuentra en el
     *                fichero  
     * @return HashMap<String, String> con las propiedades leidas
     * @throws Exception
     */
    public static HashMap<String, Integer> calculaNumPagBotonera(int numPagInt, String tpoAccion, String numPos, long numRegistros) throws Exception {
    	
    	HashMap<String, Integer> paramBotoneraPag = new HashMap<String, Integer>();

     // Calcular numero de pagina que pinta en el boton
    	int numPagWeb = 0;
    	int numPosInt = 0;
    	int numPaginaIniReal = 0;
    	int numPosDentroPag = 0;
    
    	if (numPos != null) 
    		{
    		  numPosDentroPag = Integer.valueOf(numPos).intValue();
    		}
    		else
    		{
    		  numPosDentroPag = 0;
    		}
    	 
    	if (numPagInt > ConstantesAplicacion.NUM_PAGINAS_PAGINADAS)
    		{ // Bloque 2 o mas
    		if (tpoAccion != null) 
    			{
    			if (tpoAccion.equals("avan") )
    				{
    				numPagWeb = numPagInt - 4;
    				numPaginaIniReal =  numPagInt ;
    				}
    			else
    				{
    				numPagWeb = numPagInt - 4;
    				numPaginaIniReal =  numPagInt  - 5 ;    			
    				}
    			}
    		else
    			{
    			switch ( numPosDentroPag ) {
    				case 1:
    					numPagWeb = numPagInt - numPosInt;
    					numPaginaIniReal = numPagInt - numPosInt - 1;
    					break;
    				case 2:
    					numPagWeb = numPagInt - numPosInt;
    					numPaginaIniReal = numPagInt - numPosInt - 1;
    					break;
    				case 3:
    					numPagWeb = numPagInt - numPosInt;
    					numPaginaIniReal = numPagInt - numPosInt - 1;
    					break;
    				case 4:
    					numPagWeb = numPagInt - numPosInt;
    					break;
    				case 5:
    					numPagWeb = numPagInt - numPosInt;
    					numPaginaIniReal = numPagInt - numPosInt - 1;
    					break;
    				}
    				numPagWeb = numPagWeb + 1 ;
    				}
    			}
    		else
    		{ // Primer bloque de paginas
    			if (tpoAccion != null) 
    				{
    				if (tpoAccion.equals("ant") )
    					{
    		    		 numPagWeb = 1;
        				 numPaginaIniReal = numPagInt - 5;
    					}
    				else
    					{
    					 numPagWeb = 1;
        				 numPaginaIniReal = 0;
    					}
    				}
    			  else
    				{
    				numPagWeb = 1;
  		    		numPaginaIniReal = 0;
    				}
    		}
    	
    	Long numPagVisibles ;
    	
		if (numPagInt > 4)
			{
			numPagVisibles = (numRegistros -  new Long(25));
			}
		else
			{
			if (numPagInt > 9)
				{
				numPagVisibles = (numRegistros -  new Long(50));
				}
			else
				{
				numPagVisibles = numRegistros; 
				}
			}	

			paramBotoneraPag.put("numPagVisibles", new Integer(numPagVisibles.intValue()));
    	
    		paramBotoneraPag.put("numPagWeb1", numPagWeb);
    		paramBotoneraPag.put("numPagWeb2", numPagWeb + 1);
    		paramBotoneraPag.put("numPagWeb3", numPagWeb + 2);
    		paramBotoneraPag.put("numPagWeb4", numPagWeb + 3);
    		paramBotoneraPag.put("numPagWeb5", numPagWeb + 4);
    		
    		List<Integer> listnumPag = new ArrayList<Integer>();
    			
    		listnumPag.add(new Integer(numPaginaIniReal));
    		listnumPag.add(new Integer(numPaginaIniReal + 1));
    		listnumPag.add(new Integer(numPaginaIniReal + 2));
    		listnumPag.add(new Integer(numPaginaIniReal + 3));
    		listnumPag.add(new Integer(numPaginaIniReal + 4));

    		paramBotoneraPag.put("numPaginaReal1", 	(Integer) listnumPag.get(0));
    		paramBotoneraPag.put("numPaginaReal2", 	(Integer) listnumPag.get(1));
    		paramBotoneraPag.put("numPaginaReal3",	(Integer) listnumPag.get(2));	
    		paramBotoneraPag.put("numPaginaReal4",  (Integer) listnumPag.get(3));
    		paramBotoneraPag.put("numPaginaReal5",  (Integer) listnumPag.get(4));

	return paramBotoneraPag;
    }
    
   public static HashMap<String, Integer> calculaNumPagBotoneraNue(int numPagInt, String tpoAccion, String numPos, long numRegistros, Double numBloquePagAnt) throws Exception {
    	
    	HashMap<String, Integer> paramBotoneraPag = new HashMap<String, Integer>();
    	
     	int numPagWebIni = 0;
    	int numPaginaIniReal = 0;
    	int numPosDentroPag = 0;
    	
    	if (numPos != null) 
			{
    		numPosDentroPag = Integer.valueOf(numPos).intValue();
			}
 
    	double numBloque = (numPagInt * 5) / ConstantesAplicacion.NUM_PAGINAS_POR_BLOQUE;
     	double parteDecimal = numBloque % 1; // Lo que sobra de dividir al número entre 1
      	double numBloqueEntero = numBloque - parteDecimal; // Le quitamos la parte decimal usando una resta
    	
    	
    	if (numBloquePagAnt  != numBloqueEntero) {
    		// Comprobar si ha dado avance de pagina o retroceso y cambia de bloque (5 paginas)
    		if (tpoAccion != null) 
    			{
    			if (tpoAccion.equals("avan") )
    				{
    				numPagWebIni = numPagInt + 1;
    				numPaginaIniReal =numPagInt;
    				}
    			else
    				{
    				numPagWebIni = numPagInt - 3 ;
    				numPaginaIniReal = numPagWebIni - 1;
    				}	
    			}
    		numBloquePagAnt  = numBloqueEntero;
    		 
    		}
    		else
    		{
    	 // Ha pinchado un numero de boton del bloque inicial
		     if (numPagInt == 0)
		     	{
		    	 numPagWebIni = 1;
			     numPaginaIniReal = 0; 
		     	}
		     else
		    	{
		    //	double numPosicion = numPagInt / ConstantesAplicacion.REG_POR_PAGINA;
	    	 //   double numPosDecimal = numPosicion % 1; // Lo que sobra de dividir al número entre 1
	    	 //   double numPosEntero = numPosicion - numPosDecimal; // Le quitamos la parte decimal usando una res
	    	    	
		     // Ha pinchado avance o retroceso dentro del mismo bloque de pag
		    	if (tpoAccion != null) 
 				   {
 
	    	    	if (numBloquePagAnt == 0) {
	    	    		numPagWebIni =  1;
		    	    	numPaginaIniReal = numPagWebIni - 1; 
		    		}
	    	    	
	    	    	if (numBloquePagAnt == 1) {
	    	    		numPagWebIni =  6;
		    	    	numPaginaIniReal = numPagWebIni - 1; 
		    		}
	    	    	
	    	    	if (numBloquePagAnt == 2) {
	    	    		numPagWebIni =  11;
		    	    	numPaginaIniReal = numPagWebIni - 1; 
		    		}
	    	    	
	    	    	if (numBloquePagAnt == 3 ) {
	    	    		numPagWebIni =  16;
		    	    	numPaginaIniReal = numPagWebIni - 1; 
		    		}
	    	    	
	    	    	if (numBloquePagAnt == 4 ) {
	    	    		numPagWebIni =  21;
		    	    	numPaginaIniReal = numPagWebIni - 1; 
		    		}
	    	    	
 				  }
		    	 else
		    	// Ha pinchado número de pagina directamente pero  no sabemos que bloque es
		    	  {
		    		if (numBloquePagAnt == 0) {
		    			numPagWebIni =  1;
		    	    	numPaginaIniReal = numPagWebIni - 1; 
		    		}
		    	    if (numBloquePagAnt == 1) {
		    	    	numPagWebIni =  6;
		    	    	numPaginaIniReal = numPagWebIni - 1; 
		    			}
		    	    if (numBloquePagAnt == 2) {
		    	    	numPagWebIni =  11;
		    	    	numPaginaIniReal = numPagWebIni - 1; 
		    			}
		    	    if (numBloquePagAnt == 3) {
		    	    	numPagWebIni =  16;
		    	    	numPaginaIniReal = numPagWebIni - 1; 
		    			}
		    	    if (numBloquePagAnt == 4 ) {
	    	    		numPagWebIni =  21;
		    	    	numPaginaIniReal = numPagWebIni - 1; 
		    		}
		    	    
		    		}
		         }
		    	 
    		}
    
    	Long numPagVisibles ;
    	
		if (numPagInt > 4)
			{
			 numPagVisibles = (numRegistros -  new Long(25));
			}
		   else
			{
			 if (numPagInt > 9)
				{
				 numPagVisibles = (numRegistros -  new Long(50));
				}
				else
				{
				 if (numPagInt > 14)
					{
					numPagVisibles = (numRegistros -  new Long(75));
					}
					else
					{
						if (numPagInt > 19) 
						{
							numPagVisibles = (numRegistros -  new Long(100));
							}
							else
							{	
							numPagVisibles = numRegistros; 
							}
					}
				}
			}	

			paramBotoneraPag.put("numPagVisibles", new Integer(numPagVisibles.intValue()));
    	
    		paramBotoneraPag.put("numPagWeb1", numPagWebIni);
    		paramBotoneraPag.put("numPagWeb2", numPagWebIni + 1);
    		paramBotoneraPag.put("numPagWeb3", numPagWebIni + 2);
    		paramBotoneraPag.put("numPagWeb4", numPagWebIni + 3);
    		paramBotoneraPag.put("numPagWeb5", numPagWebIni + 4);
    		
    		List<Integer> listnumPag = new ArrayList<Integer>();
    			
    		listnumPag.add(new Integer(numPaginaIniReal));
    		listnumPag.add(new Integer(numPaginaIniReal + 1));
    		listnumPag.add(new Integer(numPaginaIniReal + 2));
    		listnumPag.add(new Integer(numPaginaIniReal + 3));
    		listnumPag.add(new Integer(numPaginaIniReal + 4));

    		paramBotoneraPag.put("numPaginaReal1", 	(Integer) listnumPag.get(0));
    		paramBotoneraPag.put("numPaginaReal2", 	(Integer) listnumPag.get(1));
    		paramBotoneraPag.put("numPaginaReal3",	(Integer) listnumPag.get(2));	
    		paramBotoneraPag.put("numPaginaReal4",  (Integer) listnumPag.get(3));
    		paramBotoneraPag.put("numPaginaReal5",  (Integer) listnumPag.get(4));	
    	 
    		paramBotoneraPag.put("numBloquePag",  new Integer( numBloquePagAnt.intValue() )) ;	
    		
    	return paramBotoneraPag;
   } 
   
   public static void montarEnlacesBotonera(HashMap<String, Integer>  paramBotonera, Model modelo, int numPagInt, String URLPag, String empresaBusqueda)  {
	   
	    modelo.addAttribute("numPagVisibles", paramBotonera.get("numPagVisibles") );
		
		modelo.addAttribute("numPagWeb1", paramBotonera.get("numPagWeb1") );
		modelo.addAttribute("numPagWeb2", paramBotonera.get("numPagWeb2") );
		modelo.addAttribute("numPagWeb3", paramBotonera.get("numPagWeb3") );
		modelo.addAttribute("numPagWeb4", paramBotonera.get("numPagWeb4") );
		modelo.addAttribute("numPagWeb5", paramBotonera.get("numPagWeb5") );

		modelo.addAttribute("linkBotonAnt",  URLPag + numPagInt + "&tpoAccion=ant" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
		modelo.addAttribute("linkBoton1",    URLPag + paramBotonera.get("numPaginaReal1") + "&numPos=1" + "&numBloquePag=" + paramBotonera.get("numBloquePag") + "&apellidosBus=" + empresaBusqueda);
		modelo.addAttribute("linkBoton2",    URLPag + paramBotonera.get("numPaginaReal2") + "&numPos=2" + "&numBloquePag=" + paramBotonera.get("numBloquePag") + "&apellidosBus=" + empresaBusqueda);
		modelo.addAttribute("linkBoton3", 	 URLPag + paramBotonera.get("numPaginaReal3") + "&numPos=3" + "&numBloquePag=" + paramBotonera.get("numBloquePag") + "&apellidosBus=" + empresaBusqueda);
		modelo.addAttribute("linkBoton4", 	 URLPag + paramBotonera.get("numPaginaReal4") + "&numPos=4" + "&numBloquePag=" + paramBotonera.get("numBloquePag") + "&apellidosBus=" + empresaBusqueda);
		modelo.addAttribute("linkBoton5", 	 URLPag + paramBotonera.get("numPaginaReal5") + "&numPos=5" + "&numBloquePag=" + paramBotonera.get("numBloquePag") + "&apellidosBus=" + empresaBusqueda);
		modelo.addAttribute("linkBotonAvan", URLPag + numPagInt + "&tpoAccion=avan" + "&numBloquePag=" + paramBotonera.get("numBloquePag"));
		
		modelo.addAttribute("numPagAct1", "N" );
		modelo.addAttribute("numPagAct2", "N" );
		modelo.addAttribute("numPagAct3", "N");
		modelo.addAttribute("numPagAct4", "N" );
		modelo.addAttribute("numPagAct5", "N" );
	
	   
   }
   
}