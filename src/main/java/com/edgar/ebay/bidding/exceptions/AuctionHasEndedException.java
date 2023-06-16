package com.edgar.ebay.bidding.exceptions;

public class AuctionHasEndedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuctionHasEndedException(String message) {
		super(message);
	}

}
