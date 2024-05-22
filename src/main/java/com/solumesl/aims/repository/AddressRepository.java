package com.solumesl.aims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solumesl.aims.entity.Address;

public interface AddressRepository extends  JpaRepository<Address, Long> {

}
