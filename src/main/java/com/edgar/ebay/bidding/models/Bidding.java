package com.edgar.ebay.bidding.models;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.edgar.ebay.bidding.security.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bidding {
	
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;

	@ManyToOne(cascade = CascadeType.ALL)
	private User user; /* user placing bid  -> user can place many bids*/
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Product product; /* product bidding on  -> product can have many bids */
	
	private BigDecimal bid; /* bid placed */

}
