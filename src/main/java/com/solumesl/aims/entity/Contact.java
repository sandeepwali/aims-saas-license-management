package com.solumesl.aims.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@EntityListeners(AuditingEntityListener.class)
@Data
@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Phone is mandatory")
	private String phone;
	@NotBlank(message = "Position is mandatory")
	private String position;
	@NotBlank(message = "First Name is mandatory")
	private String firstName;
	@Column(unique=true)
	@NotBlank(message = "Email Address is mandatory")
	private String emailAddress;
	@NotBlank(message = "First Name is mandatory")
	private String lastName;
	
	private String designation;
	
	private String mobile;
	
	private String workPhone;



}
