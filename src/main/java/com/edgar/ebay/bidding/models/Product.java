package com.edgar.ebay.bidding.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.edgar.ebay.bidding.models.enums.ShippingStatus;
import com.edgar.ebay.bidding.security.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	private Integer auctionDurationDays;

	private LocalDateTime auctionStartDateTime;

	private LocalDateTime auctionEndDateTime;

	@NotNull
	@PositiveOrZero
	private BigDecimal statringBidPrice;

	private BigDecimal finalSalePrice;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ShippingStatus shippingStatus;

	private BigDecimal shippingPrice;

	@Embedded
	private AboutItem about;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Bidding> bids;

//	@OneToMany(cascade = CascadeType.ALL) will fix later 
//	private Set<User> bidders;

	private Integer numberOfBidders;

	private Integer numberOfBids;
	
	private BigDecimal winnigBid;

	@OneToOne
	@JsonIgnore
	private User winner;

	private String winnerName;

	@ManyToOne
	@JsonIgnore
	private User seller;

	private String sellerName;

	private Boolean isAuctionEnded;
}
