package com.johnbryce.CouponSystem.repo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.johnbryce.CouponSystem.beans.COMPANIES;
import com.johnbryce.CouponSystem.beans.COUPONS;
import com.johnbryce.CouponSystem.repo.companiesRepository;

@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "*")
@Service
@Transactional
public class CompanyService {
	
	@Autowired
	private companiesRepository repo;
	
    public Optional<COMPANIES> getByName(@PathVariable String name){
	    return repo.findByName(name)	;
	}


    
    
    public String saveCompany(@RequestBody COMPANIES comp) {
		repo.save(comp);
	
		return"sucssess";
	}
	
	
	public List<COMPANIES> getall() {
		return (List<COMPANIES>)repo.findAll();
	}

	
	public String deleteCompany(@PathVariable long id){
	
		repo.deleteById(id);
		return"Company deleted";		
	}
	
	
	
	public COMPANIES updateCompany(@RequestBody COMPANIES comp ) {
		
		return repo.save(comp);
	
		
	}
	
	
	public Optional<COMPANIES> getByEmail(String email) {
		return repo.findByEmail(email);
	}




	public Optional<COMPANIES> getById(long id) {
		
		return repo.findById(id);
	}




	public Optional<COMPANIES> getByEmail(COMPANIES comp) {
		// TODO Auto-generated method stub
		return null;
	}


	
	

	
}
