package com.johnbryce.CouponSystem.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import com.johnbryce.CouponSystem.beans.COMPANIES;

public interface companiesRepository extends JpaRepository<COMPANIES, Long> {

	public List<COMPANIES> deleteByName(String name);

	public void deleteById(long id);

	public Optional<COMPANIES> findByName(String name);

	public void getByName(String name);

	public Optional<COMPANIES> findByEmail(String email);

	
	



	



	
	
	


	


	




	


     



	 

	




	



	

	

	

	


	

	

	




	

	


	

	


	
	
}
