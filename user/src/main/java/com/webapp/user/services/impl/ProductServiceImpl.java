package com.webapp.user.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.webapp.user.entities.Product;
import com.webapp.user.entities.User;
import com.webapp.user.exceptions.ResourceNotFoundException;
import com.webapp.user.payloads.ProductDto;
import com.webapp.user.payloads.ProductResponse;
import com.webapp.user.repository.ProductRepository;
import com.webapp.user.repository.UserRepository;
import com.webapp.user.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepo;

	@Override
	public ProductDto createProduct(ProductDto productDto, Integer userId) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		Product product = modelMapper.map(productDto, Product.class);
		product.setUser(user);

		Product newProduct = productRepo.save(product);
		ProductDto newProductDto = modelMapper.map(newProduct, ProductDto.class);
		return newProductDto;
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "Product Id", productId));

		product.setProductName(productDto.getProductName());
		product.setProductDesc(productDto.getProductDesc());
		product.setProductPrice(productDto.getProductPrice());

		Product updatedProduct = productRepo.save(product);
		return modelMapper.map(updatedProduct, ProductDto.class);
	}

	@Override
	public void deleteProduct(Integer productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "Product Id", productId));

		productRepo.delete(product);

	}

	@Override
	public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

		Sort sort=(sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() :Sort.by(sortBy).descending());
				
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);

		Page<Product> pageProducts = productRepo.findAll(pageable);

		List<Product> products = pageProducts.getContent();

		List<ProductDto> productDtos = products.stream().map((product) -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());

		// customization for paging (total pages,total elements)
		ProductResponse productResponse = new ProductResponse();

		productResponse.setContent(productDtos);
		productResponse.setPageNumber(pageProducts.getNumber());
		productResponse.setPageSize(pageProducts.getSize());
		productResponse.setTotalElements(pageProducts.getTotalElements());
		productResponse.setTotalPages(pageProducts.getTotalPages());
		productResponse.setLastPage(pageProducts.isLast());

		return productResponse;

	}

	@Override
	public ProductDto getProductById(Integer productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "Product Id", productId));

		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		return productDto;
	}

	@Override
	public List<ProductDto> getProductsByUser(Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

		List<Product> products = productRepo.findByUser(user);

		List<ProductDto> productDtos = products.stream().map((product) -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());

		return productDtos;
	}

	@Override
	public List<ProductDto> searchProduct(String keyword) {
		List<Product> products=productRepo.findByProductNameContaining(keyword);
		List<ProductDto> productDtos=products.stream().map((product)->modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		
		return productDtos;
	}

}
