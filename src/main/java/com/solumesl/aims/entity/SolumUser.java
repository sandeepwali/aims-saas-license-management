package com.solumesl.aims.entity;

import java.util.Set;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.solumesl.aims.saas.adapter.entity.job.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SolumUser extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String emailAddress;
	private String firstName;
	private String lastName;
	
	private Role role;
	private Integer userLevel;
	private boolean approved;
	@OneToMany(mappedBy="solumUser")
	private Set<Customer> mappedCustomers;
}
