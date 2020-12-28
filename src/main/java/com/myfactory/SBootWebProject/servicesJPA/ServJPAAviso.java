package com.myfactory.SBootWebProject.servicesJPA;


import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Aviso;

@Service
public interface ServJPAAviso {
 
	public Iterable<Aviso> listAvisos(Integer idAvisos);

}
