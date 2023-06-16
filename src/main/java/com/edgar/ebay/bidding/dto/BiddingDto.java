package com.edgar.ebay.bidding.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class BiddingDto {

	private String productName;

	private String userName;

	private BigDecimal bidAmount;

}
