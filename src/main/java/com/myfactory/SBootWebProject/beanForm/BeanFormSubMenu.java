package com.myfactory.SBootWebProject.beanForm;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.myfactory.SBootWebProject.model.SubMenuNivel1;

@Component
@RequestScope
public class BeanFormSubMenu implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
 
	private ArrayList<SubMenuNivel1> beanSubMenuAplicacionWeb ;

	public ArrayList<SubMenuNivel1> getBeanSubMenuAplicacionWeb() {
		return beanSubMenuAplicacionWeb;
	}

	public void setBeanSubMenuAplicacionWeb(ArrayList<SubMenuNivel1> BeanSubMenuAplicacionWeb) {
		beanSubMenuAplicacionWeb = BeanSubMenuAplicacionWeb;
	}

}
