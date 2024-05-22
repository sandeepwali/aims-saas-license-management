package com.solumesl.aims.controller;

import java.util.List;
import java.util.Optional;

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

import com.solumesl.aims.dom.CustomerDto;
import com.solumesl.aims.entity.Customer;
import com.solumesl.aims.service.CustomerService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition( info = @Info(
		title = "Aims Saas License Management",
		version = "0.1",description = "License Management"
		),security =@SecurityRequirement(name = "bearerAuth"))
@SecurityScheme(  type = SecuritySchemeType.HTTP,in = SecuritySchemeIn.HEADER, name="bearerAuth", bearerFormat="JWT",scheme = "bearer") 
@RestController
@RequestMapping(path = "/api")
@Tag(name="Customer", description = "License Management")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getCustomers(@RequestParam(required = false) String title) {
		try {
			List<Customer> customer =  customerService.findAllCustomers();


			if (customer.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomerByCode(@PathVariable("id") long id) {
		Optional<Customer> customerData = customerService.findCustomerById(id);
		if (customerData.isPresent()) {
			return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customer) {
		try {

			return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {

		try {

			return new ResponseEntity<>( customerService.updateCustomer(customer, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Customer/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id) {
		try {
			customerService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
