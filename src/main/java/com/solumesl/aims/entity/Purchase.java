package com.solumesl.aims.entity;

import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.solumesl.aims.saas.adapter.entity.job.audit.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@EntityListeners(AuditingEntityListener.class)
@Data
@Entity
public class Purchase extends Auditable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Customer customer;
	@ManyToOne
	private Product product;

	private Double sellingPrice;
	@NotNull
	private Double quantity;

	private Double discount;

	private Double oneTimePurchasePrice;

	private Double recurringPrice;

	private Integer frequency;

	private Double payment;

	private Date execution;
	
	private Date validity;


}
