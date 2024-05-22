package com.solumesl.aims.dom;

import com.solumesl.aims.entity.CustomerType;

import lombok.Data;

@Data
public class CustomerDto {
	 
	 
	private String customerCode;
	
	private CustomerType customerType;
	
	private boolean isSiPartner;
	
	private String currency;
	
	private String customerIndustry;
	
	private String industryScale;

	 
	private ContactDto primaryContact;
	
	 
	private ContactDto contactPerson;
 
	private String customerName;
	 
	private String customerDisplayName;
	

	
 
    private AddressDto shippingAddress;
 
    private AddressDto billingAddress;
	
	private String otherInformation;
 
  
	private Long solumUser;
}
