package com.nimap.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.nimap.dto.CategoryDTO;
import com.nimap.exception.ApplicationException;
import com.nimap.service.CartService;

@RestController
@RequestMapping("/api")
@Validated
public class CategoryApi {

	@Autowired
	private CartService cartService;
	@Autowired
	private Environment environment;
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> getAllCategories(@RequestParam Pageable pageable)throws ApplicationException
	{
		List<CategoryDTO> list = cartService.getAllCategories();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@PostMapping("/categories")
	public ResponseEntity<String> addCategory(@RequestBody CategoryDTO categoryDTO)throws ApplicationException
	{
		Integer categoryId=cartService.addCategory(categoryDTO);
		String msg=environment.getProperty("API.CATEGORY_ADDED")+" "+ categoryId;
	    return new ResponseEntity<>(msg,HttpStatus.CREATED);
		
	}
	@GetMapping("/categories/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId) throws ApplicationException
	{
		CategoryDTO category = cartService.getCategory(categoryId);
		return new ResponseEntity<>(category, HttpStatus.OK);
		
	}
	@PutMapping("/categories/{categoryId}")
	public ResponseEntity<String> updateCategory(@PathVariable Integer categoryId,String categoryName) throws ApplicationException
	{
		cartService.updateCategory(categoryId,categoryName);
		String msg=environment.getProperty("API.CATEGORY_UPDATE_SUCCESS");
		return new ResponseEntity<>(msg,HttpStatus.OK);
		
	}
	@DeleteMapping("/categories/{categoryId}")
	public ResponseEntity<String> deleteCategory(Integer categoryId) throws ApplicationException
	{
		cartService.deleteCategory(categoryId);
		String msg=environment.getProperty("API.CATEGORY_DELETE_SUCCESS");
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
}
