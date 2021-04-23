package com.myfactory.SBootWebProject.servicesJPA;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.FacturacionProyecto;
import com.myfactory.SBootWebProject.model.TpoCliente;

@Service
public interface ServJPAFacturacionProyecto {
	
	public Optional<FacturacionProyecto> buscarIdProyectoFacturacion(Integer idFacturaProyecto); 
}
