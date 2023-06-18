package com.edgar.ebay.bidding.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.edgar.ebay.bidding.models.Product;
import com.edgar.ebay.bidding.models.enums.ShippingStatus;
import com.edgar.ebay.bidding.repositories.ProductRepository;
import com.edgar.ebay.bidding.security.user.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
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
		
		
		
		return productRepository.save(product);
	}
	
	//runs every 1 second and checks if auction ended before "now"
	@Scheduled(cron ="*/1 * * * * *")
	public void ProductAuctionEndedScheduler() {	
		LocalDateTime now = LocalDateTime.now();
		
		List<Product> listOfEndedProducts = productRepository.findAll()
				.stream()
				.filter(p -> p.getAuctionEndDateTime() != null 
				&& p.getIsAuctionEnded()==false
				&&p.getAuctionEndDateTime().isBefore(now)
						)
				.collect(Collectors.toList());
		
		if (listOfEndedProducts.size() > 0) {

			listOfEndedProducts.forEach(l -> {
				Product product = productRepository.findById(l.getId()).get();

				product.setIsAuctionEnded(true);
				product.setFinalSalePrice(product.getWinnigBid());
				productRepository.save(product);
			});
			
			log.info("---------------- product with ended auction found updating product isAuctionEnded to true--------------");
			

		}
		
		log.info("----------------Product Scheduler Running--------------");

	}

}
