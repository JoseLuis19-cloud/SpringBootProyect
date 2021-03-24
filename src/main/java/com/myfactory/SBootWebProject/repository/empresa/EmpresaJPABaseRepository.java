package com.myfactory.SBootWebProject.repository.empresa;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.myfactory.SBootWebProject.model.Empresa;
import com.myfactory.SBootWebProject.model.User;

	public interface EmpresaJPABaseRepository extends CrudRepository<Empresa, Integer> {
	 
	    @Query("SELECT u FROM User u WHERE u.username = :username and u.enabled = TRUE")
	    public User getUserByUsername(@Param("username") String nomUsuario);
	    
	    @Query("SELECT u FROM User u WHERE u.id = :iduser")
	    public User getMenusUsuario(@Param("iduser") Long idUsuario);
	    
	    @Query("SELECT u FROM User u WHERE u.username = :name")
	    public Optional<User>  findByName(@Param("name") String name);
		
		@Query("SELECT u FROM User u WHERE u.email = :email")
		public Optional<User> findByEmail(@Param("email") String email);
	
		
		@Query("SELECT u FROM User u WHERE u.fullName = :fullName")
		public Optional<User> findByFullName(@Param("fullName") String fullName);

	}