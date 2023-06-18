package com.edgar.ebay.bidding.services;

import java.math.BigDecimal;
import java.util.Comparator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edgar.ebay.bidding.exceptions.AuctionHasEndedException;
import com.edgar.ebay.bidding.exceptions.BidIsLowException;
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
	
	
	@Transactional
	public Bidding makeBidOffer(Long productId, BidRequest bidRequest, User user) {
			
		Bidding bid = new Bidding();
		
		Product product = productRepository.findById(productId)
				.orElseThrow(()-> new ProductWithIdDoesntExistException("product with id :: "+productId+" doesnt exist"));
		
		User bidder = userRepository.findById(user.getId())
				.orElseThrow(()-> new UserWithIdDoesntExistException(" user with id :: "+user.getId()+ " doesnt exist"));
		
		BigDecimal highestBid = product.getBids().stream().map(Bidding::getBid).max(Comparator.naturalOrder()).orElse(product.getStatringBidPrice());
	
		if(product.getIsAuctionEnded() == true) {
			throw new AuctionHasEndedException(" cant bid on product, auction has aleady ended");
		}
		
		if(bidRequest.getBidAmount().compareTo(highestBid) == 1 ) {
			product.setWinnigBid(bidRequest.getBidAmount());
			product.setNumberOfBids(product.getNumberOfBids()+1);
			product.getBids().add(bid);	
			product.getBidders().add(bidder);	
			product.setNumberOfBidders(product.getBidders().size());
			
			/* will fix with dto later */
			product.setWinner(bidder);
			product.setWinnerName(bidder.getFirstname());
			
			bid.setProduct(product);
			bid.setUser(bidder);
			bid.setBid(bidRequest.getBidAmount());	
			
			productRepository.save(product);
			return biddingRepository.save(bid);	
		}
		else 
			throw new BidIsLowException("Bid higher ! current Highest Bid price :: "+ highestBid);
	
	}

}
