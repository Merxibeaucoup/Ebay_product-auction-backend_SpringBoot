package com.edgar.ebay.bidding.services;

import java.math.BigDecimal;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.edgar.ebay.bidding.exceptions.ProductWithIdDoesntExistException;
import com.edgar.ebay.bidding.exceptions.UserWithIdDoesntExistException;
import com.edgar.ebay.bidding.models.Bidding;
import com.edgar.ebay.bidding.models.Product;
import com.edgar.ebay.bidding.repositories.BiddingRepository;
import com.edgar.ebay.bidding.repositories.ProductRepository;
import com.edgar.ebay.bidding.requests.BidRequest;
import com.edgar.ebay.bidding.security.repository.AppUserRepository;
import com.edgar.ebay.bidding.security.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BiddingService {

	private final BiddingRepository biddingRepository;
	
	private final ProductRepository productRepository;
	
	private final AppUserRepository userRepository;
	
	
	public Bidding makeBidOffer(BidRequest bidRequest) {
		
		Product product = productRepository.findById(bidRequest.getProductId())
				.orElseThrow(()-> new ProductWithIdDoesntExistException("product with id :: "+bidRequest.getProductId()+" doesnt exist"));
		
		User user = userRepository.findById(bidRequest.getUserId())
				.orElseThrow(()-> new UserWithIdDoesntExistException(" user with id :: "+ bidRequest.getUserId()+ " doesnt exist"));
		
		BigDecimal highestBid = product.getBids().stream().map(Bidding::getBid).max(Comparator.naturalOrder()).orElse(product.getStatringBidPrice());
		
		Bidding bid = new Bidding();
		
		//not done finish bidding !!!
		
		return biddingRepository.save(bid);	
	}

}
