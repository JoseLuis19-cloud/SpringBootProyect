package com.myfactory.SBootWebProject.servicesJPA.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfactory.SBootWebProject.model.FacturacionProyecto;
import com.myfactory.SBootWebProject.repository.facturacionProyecto.FacturacionProyectoJPADao;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAFacturacionProyecto;


@Service
public class ServJPAFacturacionProyectoImpl implements ServJPAFacturacionProyecto  {

	@Autowired
	FacturacionProyectoJPADao facturacionProyectoJPADao;

	@Override
	public Optional<FacturacionProyecto> buscarIdProyectoFacturacion(Integer idFacturaProyecto) {
		return facturacionProyectoJPADao.findById(idFacturaProyecto);
	}

}
