package com.myfactory.SBootWebProject.servicesJPA;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.FacturacionProyecto;

@Service
public interface ServJPAFacturacionProyecto {
	
	public Optional<FacturacionProyecto> buscarIdProyectoFacturacion(Integer idFacturaProyecto); 
}
