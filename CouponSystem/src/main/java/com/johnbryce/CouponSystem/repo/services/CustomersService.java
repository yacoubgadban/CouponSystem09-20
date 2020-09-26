package com.johnbryce.CouponSystem.repo.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.johnbryce.CouponSystem.beans.COMPANIES;
import com.johnbryce.CouponSystem.beans.COUPONS;
import com.johnbryce.CouponSystem.beans.CUSTOMERS;
import com.johnbryce.CouponSystem.repo.companiesRepository;
import com.johnbryce.CouponSystem.repo.couponsRepository;
import com.johnbryce.CouponSystem.repo.customersRepository;

import ch.qos.logback.core.joran.conditional.ElseAction;

@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "*")
@Service
@Transactional
public class CustomersService {

	@Autowired
	private customersRepository repo;
	
	@Autowired
	private couponsRepository repository;
	
    public Optional<CUSTOMERS> getByFirstName(@PathVariable String firstName){
	    return repo.findByFirstName(firstName);
	}

    public Optional<CUSTOMERS> getById(@PathVariable long id){
	    return repo.findById(id);
	}
    
    
    public String saveCustomer(@RequestBody CUSTOMERS custo) {
		repo.save(custo);
	
		return"sucssess";
	}
	
	
	public List<CUSTOMERS> getall() {
		return (List<CUSTOMERS>)repo.findAll();
	}

	
	public String deleteCustomer(@PathVariable long id){
	
		repo.deleteById(id);
		return"Company deleted";		
	}
	
	
	
	public CUSTOMERS updateCustomer(@RequestBody CUSTOMERS custo ) {
		
		return repo.save(custo);
	
		
	}
	
	
	public Optional<CUSTOMERS> getByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	
	public String buyCoupon(@PathVariable long customers_id ,@PathVariable long coupon_id) {
		
		Optional<CUSTOMERS> cousto=repo.findById(customers_id); 
		Optional<COUPONS> coup=repository.findById(coupon_id);
		
		
		long newAmount=coup.get().getAmount();
		if(newAmount>0) {
			cousto.get().getCoupon().add(coup.get());
			repo.save(cousto.get());
			coup.get().setAmount(coup.get().getAmount() - 1);
			repository.save(coup.get());
		
		return"success";
		}
		else {
			
			return  "failed";
		
		}
		
	}
	
	
	
}
	
	
	