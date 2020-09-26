package com.johnbryce.CouponSystem.repo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.johnbryce.CouponSystem.beans.CATEGORIES;
import com.johnbryce.CouponSystem.repo.CategoriesRepository;

@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "*")
@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoriesRepository repo;
	
	
	public List<CATEGORIES> getall() {
		return (List<CATEGORIES>)repo.findAll();
	}
	
	
	public String saveCategory(@RequestBody CATEGORIES cate) {
		repo.save(cate);
	
		return"sucssess";
	}
	
	public String deleteCategory(@PathVariable long id){
		
		repo.deleteById(id);
		return"Category deleted";		
	}
	
	 public Optional<CATEGORIES> getById(@PathVariable long id){
		    return repo.findById(id);
		}
	 
	 
	 public Optional<CATEGORIES> getByName(@PathVariable String name){
		    return repo.findByName(name)	;
		}
	 
}
