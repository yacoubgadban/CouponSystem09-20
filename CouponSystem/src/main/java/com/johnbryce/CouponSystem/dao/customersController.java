package com.johnbryce.CouponSystem.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnbryce.CouponSystem.beans.COUPONS;
import com.johnbryce.CouponSystem.beans.CUSTOMERS;
import com.johnbryce.CouponSystem.repo.couponsRepository;
import com.johnbryce.CouponSystem.repo.services.CustomersService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clients")

public class customersController {

	@Autowired
	public CustomersService service;

	@Autowired
	public couponsRepository serv;

	
	
	@GetMapping("/byemail/{email}")
	public Optional<CUSTOMERS> getByEmail(@PathVariable String email) {
		return service.getByEmail(email);
	}

	@GetMapping("/byid/{id}")
	public Optional<CUSTOMERS> getById(@PathVariable long id) {
		return service.getById(id);
	}

	@GetMapping("/byname/{name}")
	public Optional<CUSTOMERS> getByName(@PathVariable String firstName) {
		return service.getByFirstName(firstName);
	}

	@PostMapping("/add")
	public ResponseEntity<?> saveCustomer(@RequestBody CUSTOMERS custo) {
		if(custo.getFirstName()==null&&custo.getLastName()==null&&custo.getPassword()==null) {
			return new ResponseEntity<String>("Error: 'first name and and last name amd email cant be empty ' "
					, HttpStatus.BAD_REQUEST);

		}
		if(custo.getPassword().length()<4) {
			return new ResponseEntity<String>("Error: 'password must be at least 6  ' "
					, HttpStatus.BAD_REQUEST);

		}
		Optional<CUSTOMERS> cust2 = service.getByEmail(custo.getEmail());

		if (cust2.isEmpty()) {
			service.saveCustomer(custo);

			return new ResponseEntity<String>("sucssess", HttpStatus.OK);
		}

		return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/buy/{customers_id}/{coupon_id}")
	public ResponseEntity<String> buyCoupon(@PathVariable long customers_id
			, @PathVariable long coupon_id) {

		Optional<COUPONS> coup = serv.findById(coupon_id);
		Optional<CUSTOMERS> cousto = service.getById(customers_id);
		List<COUPONS> customerCoupons = (List<COUPONS>) cousto.get().getCoupon();
		for (COUPONS c : customerCoupons) {
			if (c.getId() == coupon_id) {
				return new ResponseEntity<String>(" err this customer had already bought this coupon."
						, HttpStatus.BAD_REQUEST);
			}
		}
		long newAmount = coup.get().getAmount();
			if (newAmount == 0) {
			
			return new ResponseEntity<String>("amount 0", HttpStatus.BAD_REQUEST);
				
		} 
		
		 if (coup.get().getEnd_date().before(new Date())) {
			return new ResponseEntity<String>("Coupon date expired.", HttpStatus.BAD_REQUEST);
		}
		
		
			cousto.get().getCoupon().add(coup.get());
			coup.get().setAmount(coup.get().getAmount() - 1);
			serv.save(coup.get());
			return new ResponseEntity<String>("success", HttpStatus.OK);

	
		
	}

	@GetMapping("/getall")

	public List<CUSTOMERS> getAllCustomer() {
		return service.getall();

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateCompany(@RequestBody CUSTOMERS custo) {

		service.updateCustomer(custo);

		return new ResponseEntity<String>("sucssess", HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")

	public String deleteCustomer(@PathVariable long id) {

	
	      Optional<CUSTOMERS> cust=service.getById(id);
	      Collection<COUPONS> cupons=cust.get().getCoupon();
		service.deleteCustomer(id);
		
		return "Company deleted";
	}

//	@GetMapping("/get/{email}/{paswword}")

}
