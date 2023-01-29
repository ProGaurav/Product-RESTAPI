package com.webapp.user.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.user.config.AppConstants;
import com.webapp.user.payloads.ApiResponse;
import com.webapp.user.payloads.ProductDto;
import com.webapp.user.payloads.ProductResponse;
import com.webapp.user.services.IProductService;

import jakarta.validation.Valid;
import lombok.Getter;


@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private IProductService productService;
	
	//create
	@PostMapping("/user/{userId}/products")
	public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto,@PathVariable Integer userId)
	{
		ProductDto createProductDto=productService.createProduct(productDto, userId);
		
		return new ResponseEntity<ProductDto>(createProductDto,HttpStatus.CREATED);
	}
	
	//get Product By user
	@GetMapping("/user/{userId}/products")
	public ResponseEntity<List<ProductDto>> getProductsByUser(@PathVariable Integer userId)
	{
		List<ProductDto> productDtos=productService.getProductsByUser(userId);
		
		return new ResponseEntity<List<ProductDto>>(productDtos,HttpStatus.OK);
	}
	
	//get all products
	@GetMapping("/products/")
	public ResponseEntity<ProductResponse> getAllProducts(
			@RequestParam(value = "pageNumber",defaultValue =AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue =AppConstants.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue =AppConstants.SORT_DIR,required = false)String sortDir)
	
			
			
	{
		ProductResponse productResponse=productService.getAllProducts(pageNumber,pageSize,sortBy,sortDir);
		
		return new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK);
	}
	
	//Get Product By Id
	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Integer productId)
	{
		ProductDto productDto=productService.getProductById(productId);
		
		return new ResponseEntity<ProductDto>(productDto,HttpStatus.OK);
	}
	//delete product by id
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer productId)
	{
		productService.deleteProduct(productId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Product deleted successfully",true),HttpStatus.OK);
	}
	//update product by id
	@PutMapping("/products/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto,@PathVariable Integer productId)
	{
		ProductDto updatedProductDto=productService.updateProduct(productDto, productId);
		
		return new ResponseEntity<ProductDto>(updatedProductDto,HttpStatus.OK);
	}
	
	//search product 
	@GetMapping("/products/search/{keyword}")
	public ResponseEntity<List<ProductDto>> searchProductByKeyword(@PathVariable String keyword)
	{
		List<ProductDto> productDto=productService.searchProduct(keyword);
		
		return new ResponseEntity<List<ProductDto>>(productDto,HttpStatus.OK);
	}
}

