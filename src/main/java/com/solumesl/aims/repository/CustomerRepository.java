package com.solumesl.aims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solumesl.aims.entity.Customer;

public interface CustomerRepository extends  JpaRepository<Customer, Long> {

	boolean existsByCustomerCode(String customerCode);

}
