package com.edgar.ebay.bidding.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class AboutItem {
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private ItemConditionType condition;
	
	private Integer quantity;
	
	private String color;
	
	private String brand;
	
	private String model;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CategoryType category;
	

}
