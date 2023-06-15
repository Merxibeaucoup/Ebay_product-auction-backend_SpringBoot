package com.edgar.ebay.bidding.exceptions;

public class UserWithIdDoesntExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserWithIdDoesntExistException(String message) {
		super(message);
	}

}
