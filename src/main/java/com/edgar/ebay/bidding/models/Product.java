package com.edgar.ebay.bidding.models;

import java.util.List;
import java.util.Set;

import com.edgar.ebay.bidding.security.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String productName;

	@Column(columnDefinition = "TEXT", nullable = true)
	private String productDescription;

	private List<String> photoUrls;

	@NotNull
	@PositiveOrZero
	private Integer auctionDuration;

	@NotNull
	@PositiveOrZero
	private Double statringBidPrice;

	private Double finalSalePrice;

	@OneToOne
	private AboutItem about;

	@OneToOne
	private Bidding bids;

	@ManyToOne
	private User seller;

}
