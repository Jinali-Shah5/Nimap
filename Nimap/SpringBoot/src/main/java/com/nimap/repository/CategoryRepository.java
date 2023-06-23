package com.nimap.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.nimap.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>, PagingAndSortingRepository<Category,Integer>{

	List<Category> findAllById(double categoryId, Pageable pageable);
}
