package com.myfactory.SBootWebProject.servicesJPA;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Proyecto;

@Service
public interface ServJPAProyecto {
	
	public Proyecto altaProyecto(Proyecto proyecto);
	
	public Proyecto modifProyecto(Proyecto proyecto);
	
	public Optional<Proyecto> buscarIdProyecto(Integer idProyecto);	
	
	public Page<Proyecto> pagProyectos(Integer numPag, Integer numRegPag, String buscarProyecto);
	
	public Iterable<Proyecto> listProyectos();
	
}
