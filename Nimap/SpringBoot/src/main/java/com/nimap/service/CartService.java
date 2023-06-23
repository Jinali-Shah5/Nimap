package com.nimap.service;

import java.util.List;

import com.nimap.dto.CategoryDTO;
import com.nimap.dto.ProductDTO;
import com.nimap.exception.ApplicationException;

public interface CartService {

	
	public List<CategoryDTO> getAllCategories()  throws ApplicationException;
	public Integer addCategory(CategoryDTO categoryDTO)  throws ApplicationException;
	public CategoryDTO getCategory(Integer categoryId)  throws ApplicationException;
	public void updateCategory(Integer categoryId,String categoryName)  throws ApplicationException;
	public void deleteCategory(Integer categoryId)  throws ApplicationException;
	
	public List<ProductDTO> getAllProducts()  throws ApplicationException;
	public Integer addProduct(ProductDTO productDTO)  throws ApplicationException;
	public ProductDTO getProduct(Integer productId)  throws ApplicationException;
	public void updateProduct(Integer productId,String productName)  throws ApplicationException;
	public void deleteProduct(Integer productId)  throws ApplicationException;
}
