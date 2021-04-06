package com.myfactory.SBootWebProject.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MENU")
public class Menu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_MENU")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMenu;
	
	@OneToMany(mappedBy="menu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 	private Set<SubMenuNivel1> subMenuNivel1 = new HashSet<>(); ;
	
//	@NotNull(message = "Debe introducir un valor numerico!")
//	@Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Debe introducir un valor numerico entre 1 y 99!")
// @Min(value = 1, message = "El rango de numero de orden es de 1 a 99!")
// @Max(value = 100, message = "El rango de numero de orden es de 1 a 99!")
	@Column(name = "NUM_ORDEN_MENU",  nullable = false)
	private Integer numOrdenMenu;
	
	@Column(name = "TEX_MENU", length = 30, nullable = false)
	private String 	textoMenu;
	
	@Column(name = "HREF_APLICACION", length = 50, nullable = true)
	private String 	hrefAplicacion;
	
	@Column(name = "IND_ACTIVO", nullable = false)
	private boolean activo;
	
	public Integer getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}
	
	public Integer getNumOrdenMenu() {
		return numOrdenMenu;
	}
	public void setNumOrdenMenu(Integer numOrdenMenu) {
		this.numOrdenMenu = numOrdenMenu;
	}
	public String getTextoMenu() {
		return textoMenu;
	}
	public void setTextoMenu(String textoMenu) {
		this.textoMenu = textoMenu;
	} 
	
	public String getHrefAplicacion() {
		return hrefAplicacion;
	}
	
	public void setHrefAplicacion(String hrefAplicacion) {
		this.hrefAplicacion = hrefAplicacion;
	}
	
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public Set<SubMenuNivel1> getSubMenuNivel1() {
		return subMenuNivel1;
	}
	public void setSubMenuNivel1(Set<SubMenuNivel1> subMenuNivel1) {
		this.subMenuNivel1 = subMenuNivel1;
	}
	
}
