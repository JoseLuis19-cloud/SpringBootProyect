package com.myfactory.SBootWebProject.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUBMENU_NIVEL_1")
public class SubMenuNivel1 implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_SUBMENU_NIVEL_1")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSubmenuNivel1;

	@ManyToOne(fetch = FetchType.LAZY)
 	@JoinColumn(name = "ID_MENU_FK", nullable = false, updatable = false)
 	private Menu menu;
	
	@Column(name = "NUM_ORDEN_MENU", nullable = false)
	private Integer numOrdenMenu;

	@Column(name = "TEX_MENU_NIVEL_1", length = 30, nullable = false)
	private String 	textoSubMenuN1;
	
	@Column(name = "HREF_APLICACION_N1", length = 50, nullable = true)
	private String 	hrefAplicacionN1;
	
	@Column(name = "IND_ACTIVO", nullable = false)
	private boolean activo;
	
	public Integer getIdSubmenuNivel1() {
		return idSubmenuNivel1;
	}
	public void setIdSubmenuNivel1(Integer idSubmenuNivel1) {
		this.idSubmenuNivel1 = idSubmenuNivel1;
	}
	public Integer getNumOrdenMenu() {
		return numOrdenMenu;
	}
	public void setNumOrdenMenu(Integer numOrdenMenu) {
		this.numOrdenMenu = numOrdenMenu;
	}
	
	public String getTextoSubMenuN1() {
		return textoSubMenuN1;
	}
	public void setTextoSubMenuN1(String textoSubMenuN1) {
		this.textoSubMenuN1 = textoSubMenuN1;
	}
	
	public String getHrefAplicacionN1() {
		return hrefAplicacionN1;
	}
	public void setHrefAplicacionN1(String hrefAplicacionN1) {
		this.hrefAplicacionN1 = hrefAplicacionN1;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
