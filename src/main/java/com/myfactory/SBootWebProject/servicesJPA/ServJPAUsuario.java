package com.myfactory.SBootWebProject.servicesJPA;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.myfactory.SBootWebProject.model.Role;
import com.myfactory.SBootWebProject.model.User;


@Service
public interface ServJPAUsuario {

	public User getMenusUsuario(Long idUsuario);

	public Page<User> paginacionUsuarios(Integer numPag, Integer numRegPag);
	
	public Iterable<User> listadoUsuarios();
	
	public Optional<User>  findIdUsuario(Long idUsuario);
	
	public Iterable<Role>  obtenerRoles();
	
	public Optional<User>  findByName(String userName);
	
	public Optional<User>  findByEmail(String userEmail);
	
	public Optional<User>  findByFullName(String userFullName);

}
