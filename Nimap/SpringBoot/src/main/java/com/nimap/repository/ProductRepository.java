package com.nimap.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nimap.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	public List<Product> findByCategoryName(String categoryName);
}
