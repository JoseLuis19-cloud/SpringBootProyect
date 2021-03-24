package com.myfactory.SBootWebProject.repository.empresa;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.myfactory.SBootWebProject.model.Empresa;

public interface EmpresaJPAPagRepository extends PagingAndSortingRepository<Empresa, Integer> {

	
}
