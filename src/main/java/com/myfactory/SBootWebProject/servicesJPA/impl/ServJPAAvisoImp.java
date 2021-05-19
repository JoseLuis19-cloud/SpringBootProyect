package com.myfactory.SBootWebProject.servicesJPA.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myfactory.SBootWebProject.model.Aviso;
import com.myfactory.SBootWebProject.repository.aviso.AvisoJPADao;
import com.myfactory.SBootWebProject.servicesJPA.ServJPAAviso;

@Service
public class ServJPAAvisoImp implements ServJPAAviso {
	@Autowired
	AvisoJPADao avisoJPAAvisoDao;
	
	@Override
	public void crearAviso(Aviso aviso) {
		avisoJPAAvisoDao.save(aviso);
	}
	
	@Override
	public Iterable<Aviso> listAvisosUsuario(Long idUsuario) {
		return avisoJPAAvisoDao.listAvisosUsuario(idUsuario) ;
	}
	

}
