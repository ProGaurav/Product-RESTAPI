package com.webapp.user.payloads;




import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ProductDto {

	private int productId;
	@NotEmpty
	@Size(min = 4,message = "minimum size of product name is 4")
	private String productName;
	
	@NotEmpty
	private String productDesc;
	
	@NotNull
	private double productPrice;
	
	private UserDto user;
}
