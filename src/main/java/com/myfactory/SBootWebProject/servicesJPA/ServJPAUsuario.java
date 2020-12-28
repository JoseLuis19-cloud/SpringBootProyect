package com.myfactory.SBootWebProject.servicesJPA;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.User;


@Service
public interface ServJPAUsuario {

	public User getMenusUsuario(Long idUsuario);

	public Page<User> paginacionUsuarios(Integer numPag, Integer numRegPag);
	
	
	public Iterable<User> listadoUsuarios();

}
