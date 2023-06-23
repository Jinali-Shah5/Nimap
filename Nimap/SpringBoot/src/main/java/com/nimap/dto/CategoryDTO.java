package com.nimap.dto;

import java.util.List;

public class CategoryDTO {

	private Integer categoryId;
	private String categoryName;
	private List <ProductDTO> productDTO;
	private ProductDTO product;
	
	public CategoryDTO()
	{
		super();
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public CategoryDTO(Integer categoryId, String categoryName, List<ProductDTO> productDTO) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.productDTO = productDTO;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<ProductDTO> getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(List<ProductDTO> productDTO) {
		this.productDTO = productDTO;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	
	

}
