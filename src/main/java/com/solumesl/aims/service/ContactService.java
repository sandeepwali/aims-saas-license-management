package com.solumesl.aims.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solumesl.aims.entity.Contact;
import com.solumesl.aims.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;


 

	public Contact createContact(Contact primaryContact) {
		final Optional<Contact> contact = contactRepository.findByEmailAddress(primaryContact.getEmailAddress());
		if(contact.isPresent()) {
			return contact.get();
		} 
		return contactRepository.save(primaryContact);
	}
}
