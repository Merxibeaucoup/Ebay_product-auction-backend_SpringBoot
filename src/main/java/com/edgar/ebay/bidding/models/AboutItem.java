package com.edgar.ebay.bidding.models;

import com.edgar.ebay.bidding.models.enums.CategoryType;
import com.edgar.ebay.bidding.models.enums.ItemConditionType;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutItem {

	@NotNull
	@Enumerated(EnumType.STRING)
	private ItemConditionType condition;

	@NotNull
	private Integer quantity;

	@NotNull
	private String color;

	@NotNull
	private String brand;

	@NotNull
	private String model;

	@NotNull
	@Enumerated(EnumType.STRING)
	private CategoryType category;

}
