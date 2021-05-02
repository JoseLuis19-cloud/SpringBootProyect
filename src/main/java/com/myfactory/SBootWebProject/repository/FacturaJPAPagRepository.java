package com.myfactory.SBootWebProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.myfactory.SBootWebProject.model.Factura;

public interface FacturaJPAPagRepository extends PagingAndSortingRepository<Factura, Integer> {
	
//	@Query("SELECT fa from Factura fa order by fa.numFactura")
//	Page<Factura> findAllOrderNumFactura(Pageable pageable) ;

	
}
