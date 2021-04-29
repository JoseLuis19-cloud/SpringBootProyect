package com.myfactory.SBootWebProject.repository.aviso;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.myfactory.SBootWebProject.model.Aviso;
import com.myfactory.SBootWebProject.model.User;

public interface AvisoJPAAvisoDao extends CrudRepository<Aviso, Integer>  {

//	@Query("SELECT a FROM Aviso a WHERE u.email = :email")
//	public Optional<User> findByEmail(@Param("idUsuario") Long idUsuario);
	
	
	@Query("SELECT a from Aviso a"
 		 	+ " JOIN a.usuario us"
			+ " WHERE us.id = :idUsuario")
	public Iterable<Aviso> listAvisosUsuario(Long idUsuario);
	
}
