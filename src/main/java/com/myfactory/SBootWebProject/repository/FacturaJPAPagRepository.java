package com.myfactory.SBootWebProject.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import com.myfactory.SBootWebProject.model.Factura;



public interface FacturaJPAPagRepository extends PagingAndSortingRepository<Factura, Integer> {

	
}
