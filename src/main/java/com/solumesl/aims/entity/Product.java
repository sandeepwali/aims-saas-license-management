package com.solumesl.aims.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.solumesl.aims.saas.adapter.entity.job.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
public class Product extends Auditable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private long productCode;
	private String productName;
	@NotNull
	private String productType;
	@NotNull
	private String size;
	@NotNull
	private String currency;
	@NotNull
	private Double pricePerUnit;
	private String comments;
	@NotNull
	private BaseProduct baseProduct;

}
