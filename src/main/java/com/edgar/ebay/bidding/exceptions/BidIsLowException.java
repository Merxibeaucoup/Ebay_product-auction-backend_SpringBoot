package com.edgar.ebay.bidding.exceptions;

public class BidIsLowException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BidIsLowException(String message) {
		super(message);
	}

}
