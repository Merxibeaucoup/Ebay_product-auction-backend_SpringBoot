package com.edgar.ebay.bidding.requests;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class BidRequest {

	private Long productId;

	private Long userId;

	private BigDecimal bidAmount;

}
