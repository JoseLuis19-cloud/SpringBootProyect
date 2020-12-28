package com.myfactory.SBootWebProject.servicesJPA.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.User;
import com.myfactory.SBootWebProject.repository.usuario.UsuarioJPABaseRepository;
import com.myfactory.SBootWebProject.repository.usuario.UsuarioJPAPagRepository;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAUsuario;

@Service
public class ServJPAUsuarioImp implements ServJPAUsuario {

	@Autowired
	UsuarioJPABaseRepository usuarioJPABaseRepository;
	@Autowired
	UsuarioJPAPagRepository usuarioJPAPagRepository;
	
	@Override
	public User getMenusUsuario(Long idUsuario){
		return usuarioJPABaseRepository.getMenusUsuario(idUsuario);
	}
	
	@Override
	public Page<User> paginacionUsuarios(Integer numPag, Integer numRegPag) {
		Page<User> restulPag = usuarioJPAPagRepository.findAll(PageRequest.of(numPag, numRegPag, Sort.by("username")));

		if (restulPag.hasContent()) {
			System.out.println("Tiene contenido la paginacion");
		}

		return restulPag;
	}
	
	@Override
	public Iterable<User> listadoUsuarios() {
		return usuarioJPABaseRepository.findAll();
	}

}
