package com.myfactory.SBootWebProject.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "indempleado")
	private boolean indEmpleado;
	
	@Column(name = "fecaltausuario")
	@Temporal(TemporalType.DATE)
	private Calendar fecAltaUsuario;
	
	@Column(name = "fecbajausuario")
	@Temporal(TemporalType.DATE)
	private Calendar fecBajaUsuario;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<Role> roles = new HashSet<>(); 
	
	// @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JoinTable(
	//		name = "USUARIO_MENU",
	//		joinColumns = @JoinColumn(name = "ID_USER"),
	//		inverseJoinColumns = @JoinColumn(name = "ID_MENU")
//			)
//	private Set<Menu> menuUsuario = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY)
//	@MapsId
	@JoinColumn(name = "idempleadofk")
	private Empleado empleado;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isIndEmpleado() {
		return indEmpleado;
	}

	public void setIndEmpleado(boolean indEmpleado) {
		this.indEmpleado = indEmpleado;
	}
	
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	public Calendar getFecAltaUsuario() {
		return fecAltaUsuario;
	}

	public void setFecAltaUsuario(Calendar fecAltaUsuario) {
		this.fecAltaUsuario = fecAltaUsuario;
	}

	public Calendar getFecBajaUsuario() {
		return fecBajaUsuario;
	}

	public void setFecBajaUsuario(Calendar fecBajaUsuario) {
		this.fecBajaUsuario = fecBajaUsuario;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public Set<Menu> getMenuUsuario() {
//		return menuUsuario;
//	}

//	public void setMenuUsuario(Set<Menu> menuUsuario) {
//		this.menuUsuario = menuUsuario;
//	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
