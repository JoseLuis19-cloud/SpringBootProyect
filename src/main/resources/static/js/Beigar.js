
 var spinnerVisible = false;
 
 function showProgress() {
     if (!spinnerVisible) {
         $("div#spinner").fadeIn("fast");
         spinnerVisible = true;
     }
 };
 
 function hideProgress() {
     if (spinnerVisible) {
         var spinner = $("div#spinner");
         spinner.stop();
         spinner.fadeOut("fast");
         spinnerVisible = false;
     }
 };
 
 
 /*
 * Funci�n que rellena con ceros un campo hasta su longitud maxima
 * 
 * param objeto - campo del formulario
 * param longitud - tama�o del campo
 */
 function completaCeros(objeto, longitud)
 { 
   for (x=objeto.value.length ; x<longitud ; x++)
    {
 	objeto.value= "0"+objeto.value;
    }
    return objeto.value;
 }	


 //------------------------------------------------------------
 // Rutina de validaci�n del codigo cuenta cliente de un banco
 // Justino Martinez, 2003
 // http://www.webviva.com
 // http://www.webviva.com/biblioteca
 //------------------------------------------------------------

 // Funcion que chequea los dos digitos de control
 // Creada originalmente por Daniel Rodriguez y Joaquin
 // Bravo y publicada en 
 // http://programacion.com/html/articulo/tw_ccc/
 function DigitoControl(cadena){
 	var cifras = new Array(1,2,4,8,5,10,9,7,3,6);
     var chequeo = 0;
     for (var i=0; i < cifras.length; i++){
 		chequeo += parseInt(cadena.charAt(i)) * cifras[i];
 	}
     chequeo = 11 - (chequeo % 11);
 	if (chequeo == 11) {chequeo = 0;}
 	if (chequeo == 10) {chequeo = 1;}
     return chequeo;
 }

 // Funcion que comprueba que "valor" es un numero entero
 function EsNumeroEntero(valor)
 {
     var cadena = valor.toString();
 	var longitud = cadena.length;
 	if (longitud == 0){return false;}
 	var ascii = null;
     for (var i=0; i<longitud; i++) 
     {
 		ascii = cadena.charCodeAt(i);
         if (ascii < 48 || ascii > 57){return false;}
     }
 	return true;
 }

 // Funcion que valida el codigo de cuenta cliente
 function ValidarCCC(entidad,oficina,dc,nc) {

 	// Comprobamos que solo hemos introducido numeros
 	if (!EsNumeroEntero(entidad)){
 		alert("Debe introducir el n�mero de entidad bancaria");
 		return false;
 	}

 	if (!EsNumeroEntero(oficina)){
 		alert("Debe introducir el n�mero de oficina");
 		return false;
 	}
 	
 	if (!EsNumeroEntero(dc)){
 		alert("Debe introducir los dos d�gitos de control");
 		return false;
 	}

 	if (!EsNumeroEntero(nc)){
 		alert("Debe introducir el n�mero de cuenta");
 		return false;
 	}

 	// Comprobamos el primer digito de control
 	

 	var primer_control="00"+entidad+oficina;
 	var primer_digito=DigitoControl(primer_control);
 	if (primer_digito != dc.charAt(0)){
 		alert("El c�digo de cuenta cliente proporcionado no es v�lido");
 		return false;
 	}

 	// Comprobamos el segundo digito de control
 	var segundo_control=nc;
 	var segundo_digito=DigitoControl(segundo_control);
 	if (segundo_digito != dc.charAt(1)){
 		alert("El c�digo de cuenta cliente proporcionado no es v�lido");
 		return false;
 	}

 	// Si todo es correcto
 	return true;
 }        


 /*******************************
  Validaci�n del cif/nif.
 *******************************/

 function ValidarCifNif(cif)
 {
 	var par = 0;
 	var non = 0;
 	var letras = "ABCDEFGHKLMNPQS";
 	var let = cif.value.charAt(0);

 	if (!isNaN(let))
   {
   	var nif=cif;
   	return validarLetra(nif);
   }

 	if (cif.value.length!=9)
   {
   	alert('El Cif debe tener 9 d�gitos');
         cif.focus();
   	return false;
   }

 	if (letras.indexOf(let.toUpperCase())==-1)
   {
   	alert("El comienzo del Cif no es v�lido");
   	cif.focus();
   	return false;
   }

 	for (zz=2;zz<8;zz+=2)
   {
   	par = par+parseInt(cif.value.charAt(zz));
   }

 	for (zz=1;zz<9;zz+=2)
   {
   	nn = 2*parseInt(cif.value.charAt(zz));
   	if (nn > 9) nn = 1+(nn-10);
   	non = non+nn;
 	}

 	parcial = par + non;

 	control = (10 - ( parcial % 10));

 	if (control!=cif.value.charAt(8))
   {
   	alert("El Cif no es v�lido");
   	cif.focus();
   	return false;
   }
   
 	return true;
 }

 function validarLetra(nif)
 {
 	var dni=nif.value.substring(0,nif.value.length-1);
 	var let=nif.value.charAt(nif.value.length-1);
 	
 	if (!isNaN(let))
   {
 	  alert('Falta la letra');
 	  nif.focus();
 	  return false;
 	}
 	else
 	{
 	  var cadena="TRWAGMYFPDXBNJZSQVHLCKET";
 	  var posicion = dni % 23;
 	  var letra = cadena.substring(posicion,posicion+1);
 	  
 	  if (letra!=let.toUpperCase())
 	  {
 	    alert("Nif no v�lido");
 	    nif.focus();
 	    return false;
 	  }
 	}
 	
 	return true;
 }

  function validaImporte(importe)
   {
   if (importe.search(new RegExp("^[0-9]{1,15}([,]{1,1}[0-9]{1,3}){0,1}$")) == -1)
     {
       return false;
     }
    else
    {
     return true;
    } 
  }
  
  
  function controlMaximoCaracteres(valorMaximo, obtTextAreaValue)
 	{
 	 numCarac = obtTextAreaValue.value.length;

      if (numCarac > valorMaximo)
          {
           valorMaximo = valorMaximo + 1;
           alert("Ha excedido el valor de " + valorMaximo + " car�cteres permitido para este campo");
           cadTexto = obtTextAreaValue.value;
           obtTextAreaValue.value = cadTexto.substring(0,valorMaximo);
           obtTextAreaValue.focus();
          }
 	}
 	
 	
   function ponerPorcentaje(uriHref)
   {
    var url = uriHref + "negocioAJAX?method=porentajeComisionComer&codcomercial=" + document.getElementById("obProyecto.codEmpleado").value;
    ajax=getObjetus();		
    ajax.open("GET", url, true);
    ajax.onreadystatechange=function()
 	 {
 	
 	  if (ajax.readyState==4) 
 	     { 
 		  var resultado = ajax.responseText;
   		  document.getElementById("obProyecto.porComision").value = resultado;
  	     }
      }
    ajax.send(null);
   }
