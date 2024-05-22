package com.solumesl.aims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solumesl.aims.entity.SolumUser;

public interface SolumUserRepository extends  JpaRepository<SolumUser, Long> {

}
