package com.johnbryce.CouponSystem.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnbryce.CouponSystem.beans.CATEGORIES;
import com.johnbryce.CouponSystem.beans.COMPANIES;

public interface CategoriesRepository extends JpaRepository<CATEGORIES, Long> {

	Optional<CATEGORIES> findByName(String name);

}
