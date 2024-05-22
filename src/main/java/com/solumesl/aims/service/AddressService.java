package com.solumesl.aims.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solumesl.aims.entity.Address;
import com.solumesl.aims.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;


	public Address createAddress(Address address) {
		return addressRepository.save(address);
	}


	public Address updateAddress(Address address, long id) {
		return addressRepository.save(address);
	}


	public void delete(long id) {
		addressRepository.deleteById(id);

	}

	public List<Address> findAllAddress(){
		List<Address> addressList = new ArrayList<Address>();
		addressRepository.findAll().forEach(addressList::add);
		return addressList;
	}
	public Optional<Address> findAddressById(Long id){
		Optional<Address> data = addressRepository.findById(id);
		return data;
	}
}
