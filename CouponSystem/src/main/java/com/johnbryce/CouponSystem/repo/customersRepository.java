package com.johnbryce.CouponSystem.repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.johnbryce.CouponSystem.beans.COMPANIES;
import com.johnbryce.CouponSystem.beans.COUPONS;
import com.johnbryce.CouponSystem.beans.CUSTOMERS;

public interface customersRepository extends JpaRepository<CUSTOMERS, Long>{

	

	public void deleteById(long id);

	

	

	public Optional<CUSTOMERS> findByEmail(String email);

	

	public Optional<CUSTOMERS> findByFirstName(String firstName);





	public Optional<CUSTOMERS> findByPassword(String password);





	




	










	





	







}
