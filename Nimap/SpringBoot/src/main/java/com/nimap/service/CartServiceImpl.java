package com.nimap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.nimap.dto.CategoryDTO;
import com.nimap.dto.ProductDTO;
import com.nimap.entity.Category;
import com.nimap.entity.Product;
import com.nimap.exception.ApplicationException;
import com.nimap.repository.CategoryRepository;
import com.nimap.repository.ProductRepository;

@Service(value="CartService")
public class CartServiceImpl implements CartService{

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public List<ProductDTO> getAllProducts() throws ApplicationException 
	{
		Iterable<Product> i = productRepository.findAll();
		List<ProductDTO> list1 = new ArrayList<ProductDTO>();
		i.forEach(list2 -> {
			ProductDTO prod = new ProductDTO();
			prod.setPrice(list2.getPrice());
			prod.setProductId(list2.getProductId());
			prod.setProductName(list2.getProductName());
			list1.add(prod);
		});
		if (list1.isEmpty())
			throw new ApplicationException("Service.PRODUCTS_NOT_FOUND");
		
		return list1;
		
	}
	@Override
	public Integer addProduct(ProductDTO product) throws ApplicationException {
		Product productEntity = new Product();
		productEntity.setProductId(product.getProductId());
		productEntity.setProductName(product.getProductName());;
		productEntity.setPrice(product.getPrice());
		
		Product productEntity2 =productRepository.save(productEntity);
		return productEntity2.getProductId();
	}

	@Override
	public void updateProduct(Integer productId,String productName)  throws ApplicationException {
	
	Optional<Product> product = productRepository.findById(productId);
	Product p = product.orElseThrow(() -> new ApplicationException("Service.PRODUCT_NOT_FOUND"));
	p.setProductName(productName);
	}
	@Override
	public void deleteProduct(Integer productId) throws ApplicationException {
		
		
		
		productRepository.deleteById(productId);
		}
	@Override	
	public ProductDTO getProduct(Integer productId)  throws ApplicationException
	{
		Optional<Product> optional = productRepository.findById(productId);
		Product product = optional.orElseThrow(() -> new ApplicationException("Service.CUSTOMER_NOT_FOUND"));
		ProductDTO product2 = new ProductDTO();
		product2.setProductId(productId);
		product2.setPrice(product.getPrice());;
		product2.setProductName(product.getProductName());
		
		return product2;
	}
@Override
	public List<CategoryDTO> getAllCategories() throws ApplicationException
	{
		
		Iterable<Category> i = categoryRepository.findAll();
		List<CategoryDTO> list1 = new ArrayList<CategoryDTO>();
		i.forEach(list2 -> {
			CategoryDTO cate = new CategoryDTO();
			cate.setCategoryId(list2.getCategoryId());
			cate.setCategoryName(list2.getCategoryName());
			list1.add(cate);
		});
		if (list1.isEmpty())
			throw new ApplicationException("Service.CATEGORIES_NOT_FOUND");
		
		return list1;	
		
	}
	
	@Override
	public Integer addCategory(CategoryDTO category) throws ApplicationException {
		Category categoryEntity = new Category();
		List<Product> products=new ArrayList<>();
		Product prod=new Product();
		categoryEntity.setCategoryId(category.getCategoryId());
		categoryEntity.setCategoryName(category.getCategoryName());;
		category.getProductDTO().stream().forEach(list2 ->{
			
			prod.setProductId(list2.getProductId());
			prod.setProductName(list2.getProductName());
			prod.setPrice(list2.getPrice());
			products.add(prod);
		}
		);
		categoryEntity.setProducts(products);;
	
		
		Category categoryEntity2 =categoryRepository.save(categoryEntity);
		return categoryEntity2.getCategoryId();
	}
	@Override
	public void updateCategory(Integer categoryId,String categoryName)  throws ApplicationException {
	
	Optional<Category> category = categoryRepository.findById(categoryId);
	Category c = category.orElseThrow(() -> new ApplicationException("Service.CATEGORY_NOT_FOUND"));
	c.setCategoryName(categoryName);
	}
	@Override
	public void deleteCategory(Integer categoryId)  throws ApplicationException {
	
	
	categoryRepository.deleteById(categoryId);
	}
	
	@Override	
	public CategoryDTO getCategory(Integer categoryId)  throws ApplicationException
	{
		Optional<Category> optional = categoryRepository.findById(categoryId);
		Category category = optional.orElseThrow(() -> new ApplicationException("Service.CATEGORY_NOT_FOUND"));
		CategoryDTO category2 = new CategoryDTO();
		category2.setCategoryId(categoryId);
		category2.setCategoryName(category.getCategoryName());
		
		
		return category2;
	}

}
