package com.solumesl.aims.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solumesl.aims.custom.exception.ValidationException;
import com.solumesl.aims.dom.CustomerDto;
import com.solumesl.aims.entity.Address;
import com.solumesl.aims.entity.Contact;
import com.solumesl.aims.entity.Customer;
import com.solumesl.aims.repository.CustomerRepository;
import com.solumesl.aims.repository.SolumUserRepository;

import jakarta.annotation.PostConstruct;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private SolumUserRepository solumUserRepository;
	@Autowired
	private AddressService addressService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private ModelMapper modelMapper;

	@PostConstruct
	public void init() {
		modelMapper.typeMap(CustomerDto.class, Customer.class).addMappings(mapper -> {
			mapper.map(src -> src.getCustomerCode(), Customer::setCustomerCode);
			mapper.map(src -> src.getCustomerName(),  Customer::setCustomerName);
			mapper.map(src -> src.getCustomerType(),  Customer::setCustomerType);
			mapper.map(src -> src.isSiPartner(),  Customer::setSIPartner);
			mapper.map(src -> src.getCurrency(),  Customer::setCurrency);
			mapper.map(src -> src.getCustomerIndustry(),  Customer::setCustomerIndustry);
			mapper.map(src -> src.getIndustryScale(),  Customer::setIndustryScale);
			mapper.map(src -> src.getCustomerDisplayName(),  Customer::setCustomerDisplayName);
			mapper.map(src -> src.getOtherInformation(),  Customer::setOtherInformation);
		});
	}
	
	@Transactional
	public Customer createCustomer(CustomerDto customer) throws ValidationException {

		
		if(customerRepository.existsByCustomerCode(customer.getCustomerCode())) {
			throw new ValidationException("Customer Code Already exists");
		}
		
		final Customer customerEntity  = modelMapper.map(customer, Customer.class);
		
		Address billingAddress = modelMapper.map(customer.getBillingAddress(), Address.class);

		Address shippingAddress = modelMapper.map(customer.getShippingAddress(), Address.class);

		customerEntity.setBillingAddress(addressService.createAddress(billingAddress));
		customerEntity.setShippingAddress(addressService.createAddress(shippingAddress));

		Contact primaryContact  = modelMapper.map(customer.getPrimaryContact(), Contact.class);
		Contact contactPerson  = modelMapper.map(customer.getContactPerson(), Contact.class);

		customerEntity.setPrimaryContact(contactService.createContact(primaryContact));
		customerEntity.setContactPerson(contactService.createContact(contactPerson));

		try {
			customerEntity.setSolumUser(solumUserRepository.findById(customer.getSolumUser()).get());
		} catch (Exception e) {
			 
		}

		return customerRepository.save(customerEntity);
	}


	public Customer updateCustomer(Customer customer, long id) {
		return customerRepository.save(customer);
	}


	public void delete(long id) {
		customerRepository.deleteById(id);

	}

	public List<Customer> findAllCustomers(){
		List<Customer> customer = new ArrayList<Customer>();
		customerRepository.findAll().forEach(customer::add);
		return customer;
	}
	public Optional<Customer> findCustomerById(Long id){
		Optional<Customer> customerData = customerRepository.findById(id);
		return customerData;
	}
}
