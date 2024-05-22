package com.solumesl.aims.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solumesl.aims.entity.Contact;

public interface ContactRepository extends  JpaRepository<Contact, Long> {

	boolean existsByEmailAddress(String emailAddress);

	Optional<Contact> findByEmailAddress(String emailAddress);

}
