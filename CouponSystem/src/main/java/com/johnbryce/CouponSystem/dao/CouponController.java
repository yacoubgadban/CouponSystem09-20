package com.johnbryce.CouponSystem.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.johnbryce.CouponSystem.beans.COMPANIES;
import com.johnbryce.CouponSystem.beans.COUPONS;
import com.johnbryce.CouponSystem.beans.CUSTOMERS;
import com.johnbryce.CouponSystem.repo.services.CompanyService;
import com.johnbryce.CouponSystem.repo.services.CouponService;

import net.bytebuddy.asm.Advice.Return;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/coupon")
public class CouponController {

	@Autowired
	public CouponService service;

	@Autowired
	CompanyService compService;

	@PostMapping("/add")
	public ResponseEntity<?> addCoupon(@RequestBody COUPONS coup) {
		if (coup.getAmount() < 1) {
			return new ResponseEntity<String>("The coupon Amount is not valid!"
					, HttpStatus.BAD_REQUEST);
		}
		if (coup.getPrice() <= 0) {
			return new ResponseEntity<String>("The coupon Price is not valid!"
					, HttpStatus.BAD_REQUEST);
		}
		if (coup.getStart_date().before(new Date())) {
			return new ResponseEntity<String>("The coupon Start Date is not valid!"
					, HttpStatus.BAD_REQUEST);
		}
		if (coup.getEnd_date().before(coup.getStart_date())) {
			return new ResponseEntity<String>("The coupon End Date is not valid!"
					, HttpStatus.BAD_REQUEST);
		}
		if (coup.getTitle() == null) {
			return new ResponseEntity<String>("The coupon title is not valid!"
					, HttpStatus.BAD_REQUEST);
		}
		if (coup.getDescription() == null) {
			return new ResponseEntity<String>("The coupon Description is not valid!"
					, HttpStatus.BAD_REQUEST);
		}
		
		List<COUPONS> coupons = service.getByCompanyId(coup.getCompanyId());
		for (COUPONS newCoup : coupons) {
			if (newCoup.getTitle().equalsIgnoreCase(coup.getTitle())) {
				return new ResponseEntity<String>("There is another coupon with same Title!!"
						, HttpStatus.BAD_REQUEST);
			}
		}
		
		service.addCoupon(coup);

		return new ResponseEntity<String>("coupon has been added", HttpStatus.OK);
	}

	@GetMapping("/getall")
	public List<COUPONS> getAll() {
		return service.getAll();

	}

	@DeleteMapping("/delete/{id}")

	public String deleteCompany(@PathVariable long id) {
		service.deleteCoupon(id);
		return "Company deleted";
	}

	@GetMapping("/company/{companyId}")
	public List<COUPONS> getByCompanyId(@PathVariable long companyId) {
		return service.getByCompanyId(companyId);
	}

	@GetMapping("/category/{categoryId}")
	public List<COUPONS> getByCategoryId(@PathVariable long categoryId) {
		return service.getByCategoryId(categoryId);
	}

	@PutMapping("/update/{id}")
	public COUPONS updateCoupon(@RequestBody COUPONS coup) {

		return service.updateCoupon(coup);

	}

	 @GetMapping("/title/{companyId}/{title}")
	 public COUPONS getByTitle(@PathVariable String title ) {
		   return service.getByTitle(title);
	   }
}
