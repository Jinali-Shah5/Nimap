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


import com.nimap.dto.ProductDTO;
import com.nimap.exception.ApplicationException;
import com.nimap.service.CartService;


@RestController
@RequestMapping("/api")
@Validated
public class ProductApi {
	@Autowired
	private CartService cartService;
	@Autowired
	private Environment environment;
@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> getAllProducts (@RequestParam Pageable pageable) throws ApplicationException
{
	List<ProductDTO> list=cartService.getAllProducts();
	return new ResponseEntity<>(list,HttpStatus.OK);
	
}

@PostMapping("/products")
public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO)throws ApplicationException
{
	Integer productId=cartService.addProduct(productDTO);
	String msg=environment.getProperty("API.PRODUCT_ADDED")+" "+ productId;
    return new ResponseEntity<>(msg,HttpStatus.CREATED);
	
}
@GetMapping("/products/{productId}") 
public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer productId) throws ApplicationException
{
	ProductDTO product = cartService.getProduct(productId);
	return new ResponseEntity<>(product, HttpStatus.OK);
}
@PutMapping("/products/{productId}")  
public ResponseEntity<String> updateProduct(Integer productId,String productName) throws ApplicationException
{
	cartService.updateProduct(productId,productName);
	String msg=environment.getProperty("API.PRODUCT_UPDATE_SUCCESS");
	return new ResponseEntity<>(msg,HttpStatus.OK);
}
@DeleteMapping("/products/{productId}")
public ResponseEntity<String>deleteProduct(Integer productId) throws ApplicationException
{
	cartService.deleteProduct(productId);
	String msg=environment.getProperty("API.PRODUCT_DELETE_SUCCESS");
	return new ResponseEntity<>(msg,HttpStatus.OK);
}
}
