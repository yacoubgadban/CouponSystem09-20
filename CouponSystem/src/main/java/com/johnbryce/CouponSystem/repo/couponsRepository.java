package com.johnbryce.CouponSystem.repo;


import java.util.List;
import java.util.Optional;

import org.hibernate.criterion.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import com.johnbryce.CouponSystem.beans.COUPONS;

public interface couponsRepository extends JpaRepository<COUPONS, Long> {

	List<COUPONS> findByCompanyId(long companyId);

	void save(long amount);

	List<COUPONS> findByCategoryId(long categoryId);

	void save(Optional<COUPONS> coup);

	COUPONS getByTitle(String title);

	

	

	


	

	

	


	

	

	

	

	

	


	

	



	

	

	


	
	
	

	  


	



	

	


	



	
	
 
	
}
