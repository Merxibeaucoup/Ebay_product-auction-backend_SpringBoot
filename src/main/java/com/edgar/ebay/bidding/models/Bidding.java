package com.edgar.ebay.bidding.models;

import java.util.Set;

import com.edgar.ebay.bidding.security.user.User;

import jakarta.persistence.Entity;

@Entity
public class Bidding {

	private Set<User> bidders;

	private Integer numberOfBidders;

	private Integer numberOfBids;

}
