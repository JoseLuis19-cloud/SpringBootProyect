package com.myfactory.SBootWebProject.repository;

import org.springframework.data.repository.CrudRepository;
import com.myfactory.SBootWebProject.model.Cliente;


public interface ClienteJPABaseRepository extends CrudRepository<Cliente, Integer>{ 

	  
}
