package com.myfactory.SBootWebProject.repository.aviso;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.myfactory.SBootWebProject.model.Aviso;

public interface AvisoJPAAvisoDao extends PagingAndSortingRepository<Aviso, Integer>, CrudRepository<Aviso, Integer>  {

	 
		  
}
