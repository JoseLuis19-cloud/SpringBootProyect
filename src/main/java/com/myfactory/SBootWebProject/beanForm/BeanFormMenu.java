package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.myfactory.SBootWebProject.model.Menu;

@Component
@RequestScope
public class BeanFormMenu implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
 
	private ArrayList<Menu> beanMenuAplicacionWeb ;

	public ArrayList<Menu> getBeanMenuAplicacionWeb() {
		return beanMenuAplicacionWeb;
	}

	public void setBeanMenuAplicacionWeb(ArrayList<Menu> BeanMenuAplicacionWeb) {
		beanMenuAplicacionWeb = BeanMenuAplicacionWeb;
	}

}
