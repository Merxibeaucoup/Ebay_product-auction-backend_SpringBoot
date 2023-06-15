package com.edgar.ebay.bidding.services;

import java.math.BigDecimal;

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

	/** new Product **/
	public Product createProduct(Product product, User user) {
		product.setSeller(user);
		product.setSellerName(user.getFirstname());
		if (product.getShippingStatus() == ShippingStatus.FREE) {
			product.setShippingPrice(FREE_SHIPPING);
		} else {
			product.setShippingPrice(product.getShippingPrice());
		}	
		
		product.setIsAuctionEnded(false);
		product.setAuctionEndDateTime(product.getAuctionStartDateTime()
				.plusDays(product.getAuctionDurationDays()));

		return productRepository.save(product);
	}

}
