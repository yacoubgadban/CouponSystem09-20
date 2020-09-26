package com.johnbryce.CouponSystem;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.johnbryce.CouponSystem.beans.COUPONS;
import com.johnbryce.CouponSystem.repo.services.CouponService;



@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "*")

@RestController
public class CleanCoupons {

	
	
	@Autowired
	CouponService service;
	
	boolean start=true;
	
	public CleanCoupons() {
	
	   Thread t1=new Thread(new Runnable() {
		   
		@Override
		public void run() {
			while (start) {
				
				
				
					try {
						Thread.sleep(60*1000*60*12);
						List<COUPONS> coup2=service.getAll();
						for(COUPONS c : coup2) {
							
							if( c.getEnd_date().before(new Date())) {
								
							service.deleteCoupon(c.getId());
							
						}
							
						}
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					
				
			}
			
			
		
		}
		
	   });
	   t1.start();
	  	}
}

