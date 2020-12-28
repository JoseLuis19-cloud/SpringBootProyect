package com.myfactory.SBootWebProject.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUBMENU_NIVEL_2")
public class SubMenuNivel2 implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_SUBMENU_NIVEL_2")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSubmenuNivel2;

	//	@Column(name = "ID_SUBMENU_NIVEL_1_FK")
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private SubMenuNivel1 subMenuNivel1;
	
	@Column(name = "NUM_ORDEN_MENU",  nullable = false)
	private Integer numOrdenMenu;
	
	@Column(name = "TEX_MENU_NIVEL_2", length = 30, nullable = false)
	private String 	textoSubMenuN2;
	
	@Column(name = "HREF_APLICACION_N2", length = 50, nullable = true)
	private String 	hrefAplicacionN2;
	

	public Integer getNumOrdenMenu() {
		return numOrdenMenu;
	}
	public void setNumOrdenMenu(Integer numOrdenMenu) {
		this.numOrdenMenu = numOrdenMenu;
	}
	
	public String getTextoSubMenuN2() {
		return textoSubMenuN2;
	}
	public void setTextoSubMenuN2(String textoSubMenuN2) {
		this.textoSubMenuN2 = textoSubMenuN2;
	}
	
	public String getHrefAplicacionN2() {
		return hrefAplicacionN2;
	}
	public void setHrefAplicacionN2(String hrefAplicacionN2) {
		this.hrefAplicacionN2 = hrefAplicacionN2;
	}
	
	
}
