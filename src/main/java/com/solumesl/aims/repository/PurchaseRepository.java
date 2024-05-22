package com.solumesl.aims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solumesl.aims.entity.Purchase;

public interface PurchaseRepository extends  JpaRepository<Purchase, Long> {

}
