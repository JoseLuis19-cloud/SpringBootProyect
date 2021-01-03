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
@Table(name = "MENUS_USUARIO")
public class MenusUsuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_MENU", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMenu;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO_FK", nullable = false)
	private User user;
	
 	@Column(name = "NUM_ORDEN", nullable = false)
 	@JoinColumn(name = "ID_USUARIO_FK", nullable = false)
 	private Integer numOrden;
	
 	@ManyToOne(fetch = FetchType.LAZY)
 	@JoinColumn(name = "COD_NIVEL_1_FK", nullable = false)
	// @Column(name = "COD_NIVEL_1_FK")
	private Menu menu;
	
 	@ManyToOne(fetch = FetchType.LAZY)
 	@JoinColumn(name = "COD_NIVEL_2_FK", nullable = false)
//	@Column(name = "COD_NIVEL_2_FK")
 	private SubMenuNivel1 subMenu1;

	public Integer getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getNumOrden() {
		return numOrden;
	}

	public void setNumOrden(Integer numOrden) {
		this.numOrden = numOrden;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public SubMenuNivel1 getSubMenu1() {
		return subMenu1;
	}

	public void setSubMenu1(SubMenuNivel1 subMenu1) {
		this.subMenu1 = subMenu1;
	}


}
