package com.myfactory.SBootWebProject.servicesJPA.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myfactory.SBootWebProject.model.Menu;
import com.myfactory.SBootWebProject.model.Role;
import com.myfactory.SBootWebProject.model.User;
import com.myfactory.SBootWebProject.repository.role.RoleJPADao;
import com.myfactory.SBootWebProject.repository.usuario.UsuarioJPABaseRepository;
import com.myfactory.SBootWebProject.repository.usuario.UsuarioJPAPagRepository;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAUsuario;

@Service
public class ServJPAUsuarioImp implements ServJPAUsuario {

	@Autowired
	UsuarioJPABaseRepository usuarioJPABaseRepository;
	@Autowired
	UsuarioJPAPagRepository usuarioJPAPagRepository;
	@Autowired
	RoleJPADao roleJPADao;
	
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
	
	@Override
	public Optional<User> findIdUsuario(Long idUsuario) {
		return usuarioJPABaseRepository.findById(idUsuario);
	}
	
	@Override
	public Iterable<Role> obtenerRoles(){
		return roleJPADao.findAll();
	}
	
	@Override
	public Optional<User> findByName(String userName){
		return usuarioJPABaseRepository.findByName(userName);
	}
	
	@Override
	public Optional<User> findByEmail(String email){
		return usuarioJPABaseRepository.findByEmail(email);
	}
	
	@Override
	public Optional<User> findByFullName(String fullName){
		return usuarioJPABaseRepository.findByFullName(fullName);
	}

}
