package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class BeanPrueba1  implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private String nombreWeb;
	private String apellidosWeb;
	

	public BeanPrueba1() {
	
	}


	public String getNombreWeb() {
		return nombreWeb;
	}


	public void setNombreWeb(String nombreWeb) {
		this.nombreWeb = nombreWeb;
	}


	public String getApellidosWeb() {
		return apellidosWeb;
	}


	public void setApellidosWeb(String apellidosWeb) {
		this.apellidosWeb = apellidosWeb;
	}

		
}
