package com.webapp.user.payloads;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {


	private int Id;
	
	@NotEmpty
	@Size(min=4,message ="First Name must be minimum of four characters")
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	private String mobileNo;
	
	@Email(message = "Email address is not valid")
	private String emailId;
	
	@NotEmpty
	private String password;
	
}
