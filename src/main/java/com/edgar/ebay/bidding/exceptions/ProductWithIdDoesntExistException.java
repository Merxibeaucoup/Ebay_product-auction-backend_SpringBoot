package com.edgar.ebay.bidding.exceptions;

public class ProductWithIdDoesntExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductWithIdDoesntExistException(String message) {
		super(message);
	}
}
