package com.johnbryce.CouponSystem.dao;

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
import com.johnbryce.CouponSystem.beans.COMPANIES;
import com.johnbryce.CouponSystem.beans.COUPONS;
import com.johnbryce.CouponSystem.repo.services.CompanyService;
import com.johnbryce.CouponSystem.repo.services.CouponService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/companylist")
public class CompanyController {

	@Autowired
	private CompanyService service;
	
	@Autowired CouponService coupService;

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateCompany(@RequestBody COMPANIES comp, @PathVariable long id) {


		if(comp.getName()==null || comp.getEmail()==null) {
			return new ResponseEntity<String>("Error: 'company name and email cant be empty ' "
					, HttpStatus.BAD_REQUEST);
		}
		if(comp.getPassword().length()<6) {
			return new ResponseEntity<String>("Error: 'password must be at least 6  ' "
					, HttpStatus.BAD_REQUEST);
		}
		
		Optional<COMPANIES> comp2 = service.getByName(comp.getName());

		if (comp2.isEmpty()) {
			return new ResponseEntity<String>("cant change company name", HttpStatus.BAD_REQUEST);
		}
		service.updateCompany(comp);
		return new ResponseEntity<String>("update success", HttpStatus.OK);

	}

	@PostMapping("/add")
	public ResponseEntity<?> saveCompany(@RequestBody COMPANIES comp) {

		if(comp.getName()==null || comp.getEmail()==null) {
			return new ResponseEntity<String>("Error: 'company name and email cant be empty ' "
					, HttpStatus.BAD_REQUEST);
		}
		if(comp.getPassword().length()<4) {
			return new ResponseEntity<String>("Error: 'password must be at least 6  ' "
					, HttpStatus.BAD_REQUEST);
		}
		Optional<COMPANIES> comp2 = service.getByEmail(comp.getEmail());
		Optional<COMPANIES> comp3 = service.getByName(comp.getName());

		if (comp2.isEmpty() && comp3.isEmpty()) {

			service.saveCompany(comp);
			return new ResponseEntity<String>("seccess !", HttpStatus.OK);
		} else {

			return new ResponseEntity<String>("Error: 'this company is already added' "
					, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/getall")
	public List<COMPANIES> getAllCompanies() {
		return service.getall();
	}

	@GetMapping("/byemail/{email}")
	public Optional<COMPANIES> getByEmail(@PathVariable String email) {
		return service.getByEmail(email);
	}

	@GetMapping("/byname/{name}")
	public Optional<COMPANIES> getByName(@PathVariable String name) {
		return service.getByName(name);
	}

	@GetMapping("/byid/{id}")
	public Optional<COMPANIES> getByid(@PathVariable long id) {
		return service.getById(id);
	}

	@DeleteMapping("/delete/{id}")

	public String deleteCompany(@PathVariable long id) {

		List<COUPONS> coupons=coupService.getByCompanyId(id);
		service.deleteCompany(id);
		coupService.deleteAllCoupons(coupons);
		return "Company deleted";
	}

}
