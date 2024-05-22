package com.solumesl.aims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solumesl.aims.entity.Product;

public interface ProductRepository extends  JpaRepository<Product, Long> {

}
