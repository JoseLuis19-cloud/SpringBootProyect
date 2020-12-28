package com.myfactory.SBootWebProject.repository.usuario;


import org.springframework.data.repository.PagingAndSortingRepository;
import com.myfactory.SBootWebProject.model.User;


public interface UsuarioJPAPagRepository extends PagingAndSortingRepository<User, Long> {

	
}
