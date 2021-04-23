package com.myfactory.SBootWebProject.repository.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.myfactory.SBootWebProject.model.User;

	public interface UsuarioJPABaseRepository extends CrudRepository<User, Long> {
	 
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
		
		@Query("SELECT u FROM User u WHERE u.enabled = FALSE")
	    public Iterable<User>  listaUsuariosHistorico();
	 
		@Query("SELECT u FROM User u ORDER BY u.username")
	    public Iterable<User>  listUsuariosOrderByName();

	}