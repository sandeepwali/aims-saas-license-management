package com.solumesl.aims.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Address {
	 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Country is mandatory")
	private String country;
	private String region;
	private String city;
	private String state;
	@NotBlank(message = "ZipCode is mandatory")
	private String zipCode;
	
	private String phone;
	
	private String fax;
	
	private String address;
 
}
