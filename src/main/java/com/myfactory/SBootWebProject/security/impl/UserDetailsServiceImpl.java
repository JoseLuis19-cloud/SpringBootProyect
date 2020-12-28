package com.myfactory.SBootWebProject.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myfactory.SBootWebProject.beanForm.BeanIdUsuario;
import com.myfactory.SBootWebProject.model.User;
import com.myfactory.SBootWebProject.repository.usuario.UsuarioJPABaseRepository;
import com.myfactory.SBootWebProject.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioJPABaseRepository userRepository;
	
	@Autowired
	private BeanIdUsuario beanIdUsusario;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		
		beanIdUsusario.setIdUsuario( user.getId());
		
		if (user == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		return new MyUserDetails(user);
	}

}
