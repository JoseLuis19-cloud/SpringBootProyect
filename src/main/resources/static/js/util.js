/*
* Abrir ventana
*/
function openWindow(html,x,y)
{
var ventana = window.open(html,"ventana2","width="+x+",height="+y+""); 
ventana.focus();
}

/*
* Sacar la fecha
*/
function getDate()
{
dows = new Array("Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado");
months = new Array("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
now = new Date();
dow = now.getDay();
d = now.getDate();
m = now.getMonth();
h = now.getTime();
y = now.getYear();
if (y < 100) y += 2000;
if (y > 100&&y < 2000) y += 1900;
document.write(dows[dow]+" "+d+" de "+months[m]+" de "+y);
}


/***********************************************
* Gradual Highlight image script- � Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

var baseopacity=30

function slowhigh(which2) {
	imgobj=which2
	browserdetect=which2.filters? "ie" : typeof which2.style.MozOpacity=="string"? "mozilla" : ""
	instantset(baseopacity)
	highlighting=setInterval("gradualfade(imgobj)",50)
}

function slowlow(which2) {
	cleartimer()
	instantset(baseopacity)
}

function instantset(degree) {
	if (browserdetect=="mozilla")
		imgobj.style.MozOpacity=degree/100
	else if (browserdetect=="ie")
		imgobj.filters.alpha.opacity=degree
}

function cleartimer() {
	if (window.highlighting) clearInterval(highlighting)
}

function gradualfade(cur2) {
	if (browserdetect=="mozilla" && cur2.style.MozOpacity<1)
		cur2.style.MozOpacity=Math.min(parseFloat(cur2.style.MozOpacity)+0.1, 0.99)
	else if (browserdetect=="ie" && cur2.filters.alpha.opacity<100)
		cur2.filters.alpha.opacity+=10
	else if (window.highlighting)
		clearInterval(highlighting)
}

/**
 *
 */	 
function cambiarColorBotones(evt) {
  var boton = this;     
     
  if (navigator.appName.indexOf("Explorer") != -1) {
  	var type = "string" == typeof(event.type) ? event.type : evt.type;             
  	boton.style.background = type == "mouseover" ? "#655B47" : "#D4D0C8";
  	boton.style.color = type == "mouseover" ? "white" : "black";
  } else {
  	boton.style.background = ((evt.type.toString() == "mouseover") ? "#655B47" : "#D4D0C8");
  	boton.style.color = ((evt.type.toString() == "mouseover") ? "white" : "black");
  }	
}

/**
 * Paginacion
 */
var path;
function pagMouseOver() {
    var cn = this.className;
    if(cn != "selected") {
        if(cn.indexOf("out") > -1)
            this.className = cn.replace("out", "over");
        else
            this.className += " over";
    }
    var child = this.childNodes[0].childNodes[0];
    if(child.tagName) {
        if(child.src!=null)
        child.src = path+"paginacion_" + (child.src.indexOf("siguiente") > -1 ? "siguiente" : "anterior") + "_over.gif";    
    }    
}

function pagMouseOut() {
    if(this.className != "selected")
        this.className = this.className.replace("over", "rest");
    var child = this.childNodes[0].childNodes[0];
    if(child.tagName) {
        if(child.src!=null)
        child.src = path+"paginacion_" + (child.src.indexOf("siguiente") > -1 ? "siguiente" : "anterior") + "_out.gif";    
    }    
}

var pagClickProcessed = false;
function pagMouseClick() {
    if (!pagClickProcessed) {
        var children = this.childNodes
        var numChildren = children.length;
        for (var i=0; i<numChildren; i++) {
            if (children[i].nodeName == "A") {
                pagClickProcessed = true;
                //children[i].click();
                location.href = children[i].href;
            }
        }
    }
}
