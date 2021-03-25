package com.myfactory.SBootWebProject.servicesJPA.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Proyecto;
import com.myfactory.SBootWebProject.repository.proyecto.ProyectoJPABaseRepository;
import com.myfactory.SBootWebProject.repository.proyecto.ProyectoJPAPagRepository;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAProyecto;

@Service
public class ServJPAProyectoImpl implements ServJPAProyecto {

	@Autowired
	ProyectoJPABaseRepository proyectoJPARepository;
	
	@Autowired
	ProyectoJPAPagRepository proyectoJPAPagRepository;

	public Proyecto altaProyecto(Proyecto proyecto){
		return proyectoJPARepository.save(proyecto);
	}
	
	public Proyecto modifProyecto(Proyecto proyecto){
		return proyectoJPARepository.save(proyecto);
	}

	@Override
	public Optional<Proyecto> buscarIdProyecto(Integer idProyecto) {
		return proyectoJPAPagRepository.findById(idProyecto);
	}

	@Override
	public Page<Proyecto> pagProyectos(Integer numPag, Integer numRegPag, String buscarProyecto) {
		Page<Proyecto> restulPag;
		
		 if (buscarProyecto.equals("")  )
		 	{		
		 	 restulPag = proyectoJPAPagRepository.findAll(PageRequest.of(numPag, numRegPag, Sort.by("nomProyecto")));
		 	}
	 	else
	 	{
			 restulPag = proyectoJPAPagRepository.findMayorProyecto(PageRequest.of(numPag, numRegPag, Sort.by("nomProyecto")), buscarProyecto);
	 		}

		if (restulPag.hasContent()) {
			System.out.println("Tiene contenido la paginacion");
		}
		return restulPag;
	}
}
