package com.webapp.user.services;

import java.util.List;


import com.webapp.user.payloads.ProductDto;
import com.webapp.user.payloads.ProductResponse;

public interface IProductService {

	//create
	
	ProductDto createProduct(ProductDto productDto,Integer userId);
	
	//update
	
	ProductDto updateProduct(ProductDto productDto,Integer productId);
	
	//delete
	void deleteProduct(Integer productId);
	
	//Get All Products
	ProductResponse getAllProducts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//Get single Product
	ProductDto getProductById(Integer productId);
	
	//Get Products by user
	List<ProductDto> getProductsByUser(Integer userId);
	
	//Get Products
	List<ProductDto> searchProduct(String keyword);
	
}
