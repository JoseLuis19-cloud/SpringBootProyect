package com.myfactory.SBootWebProject.dto;

import java.io.Serializable;

 
public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long user_id;
	private String full_name;
	private String username;
	private String email;
	private Boolean enabled;
	private Boolean indempleado;
	private String fecaltausuario;
	private String fecbajausuario;
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getIndempleado() {
		return indempleado;
	}
	public void setIndempleado(Boolean indempleado) {
		this.indempleado = indempleado;
	}
	public String getFecaltausuario() {
		return fecaltausuario;
	}
	public void setFecaltausuario(String fecaltausuario) {
		this.fecaltausuario = fecaltausuario;
	}
	public String getFecbajausuario() {
		return fecbajausuario;
	}
	public void setFecbajausuario(String fecbajausuario) {
		this.fecbajausuario = fecbajausuario;
	}
	
	
}
