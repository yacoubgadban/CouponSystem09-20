package com.johnbryce.CouponSystem.repo.services;


import java.util.Date;
import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.johnbryce.CouponSystem.beans.COUPONS;
import com.johnbryce.CouponSystem.repo.couponsRepository;
@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "*")

@Service
@Transactional
public class CouponService {

	@Autowired
	public couponsRepository repo;

	
	
	
	
	
	

	
	
	public String addCoupon(@RequestBody COUPONS coup) {

		repo.save(coup);
		return "Coupon has been added" ;

	}

	public List<COUPONS>  getAll() {
		return    repo.findAll();
	}

	public String deleteCoupon(@PathVariable long id){
		
		repo.deleteById(id);
		return"Company deleted";		
	}
	
	
	public List<COUPONS> getByCompanyId(long companyId){
		return repo.findByCompanyId(companyId);
	}

	public List<COUPONS> getByCategoryId(long categoryId){
		return repo.findByCategoryId(categoryId);
	}
	
    public COUPONS updateCoupon(@RequestBody COUPONS coup ) {
		
		return repo.save(coup);
	
		
	}

   public COUPONS getByTitle(String title) {
	   return repo.getByTitle(title);
   }


	public void findAll() {
		// TODO Auto-generated method stub
		
	}




	public void deleteAllCoupons(List<COUPONS> coup) {
		repo.deleteAllInBatch();
	}








	
	
}