package com.edgar.ebay.bidding.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.edgar.ebay.bidding.models.Product;
import com.edgar.ebay.bidding.models.enums.ShippingStatus;
import com.edgar.ebay.bidding.repositories.ProductRepository;
import com.edgar.ebay.bidding.security.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	
	private final BigDecimal FREE_SHIPPING = new BigDecimal(0.00);

	/** new Product   -> will use a DTO later **/
	public Product createProduct(Product product, User user) {
		product.setSeller(user);
		product.setSellerName(user.getFirstname());
		if (product.getShippingStatus() == ShippingStatus.FREE) {
			product.setShippingPrice(FREE_SHIPPING);
		} else {
			product.setShippingPrice(product.getShippingPrice());
		}	
		
		product.setIsAuctionEnded(false);
		product.setNumberOfBids(0);
		product.setNumberOfBidders(0);
		product.setAuctionStartDateTime(LocalDateTime.now());
		product.setAuctionEndDateTime(product.getAuctionStartDateTime()
				.plusDays(product.getAuctionDurationDays()));
		
		
		/* will use a scheduler later */
		if(product.getAuctionEndDateTime().isAfter(LocalDateTime.now())) {
			product.setFinalSalePrice(product.getWinnigBid());
			product.setIsAuctionEnded(true);
		}
		
		
		

		return productRepository.save(product);
	}

}
