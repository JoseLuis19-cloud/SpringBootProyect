package com.myfactory.SBootWebProject.repository.usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.myfactory.SBootWebProject.model.User;

	public interface UsuarioJPABaseRepository extends CrudRepository<User, Long> {
	 
	    @Query("SELECT u FROM User u WHERE u.username = :username and u.enabled = TRUE")
	    public User getUserByUsername(@Param("username") String nomUsuario);
	    
	    
	    @Query("SELECT u FROM User u WHERE u.id = :iduser")
	    public User getMenusUsuario(@Param("iduser") Long idUsuario);
	 
	}