package com.solumesl.aims.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.solumesl.aims.saas.adapter.entity.job.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@EntityListeners(AuditingEntityListener.class)
@Data
@Entity
public class Customer  extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Customer Code is mandatory")
	@Column(unique=true)
	private String customerCode;
	private CustomerType customerType;
	private boolean isSIPartner;
	private String currency;
	private String customerIndustry;
	private String industryScale;

	@ManyToOne
	private Contact primaryContact;
	
	@ManyToOne
	private Contact contactPerson;
	@NotBlank(message = "Customer Name is mandatory")
	private String customerName;
	 
	private String customerDisplayName;
	

	
	@ManyToOne
    private Address shippingAddress;
	@ManyToOne
    private Address billingAddress;
	
	private String otherInformation;
	@ManyToOne
    @JoinColumn(name="user_id", nullable=true)
	private SolumUser solumUser;

}
