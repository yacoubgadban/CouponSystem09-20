package com.johnbryce.CouponSystem.dao;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.johnbryce.CouponSystem.beans.CATEGORIES;
import com.johnbryce.CouponSystem.repo.companiesRepository;
import com.johnbryce.CouponSystem.repo.services.CategoryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping("/getall")
	public List<CATEGORIES> getAllCustomer() {
		return service.getall();
	}

	@PostMapping("/add")
	public ResponseEntity<String> saveCategory(@RequestBody CATEGORIES cate) {
		Optional<CATEGORIES> cate2 = service.getByName(cate.getName());

		if (cate2.isEmpty()) {

			service.saveCategory(cate);
            
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}

		return new ResponseEntity<String>("this category is already created", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/delete/{id}")

	public String deleteCategory(@PathVariable long id) {

		service.deleteCategory(id);
		return "Company deleted";
	}

	@GetMapping("/byid/{id}")
	public Optional<CATEGORIES> getById(@PathVariable long id) {
		return service.getById(id);
	}

	@GetMapping("/byname/{name}")
	public Optional<CATEGORIES> getByName(@PathVariable String name) {
		return service.getByName(name);
	}
}
