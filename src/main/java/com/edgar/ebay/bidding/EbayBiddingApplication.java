package com.edgar.ebay.bidding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EbayBiddingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbayBiddingApplication.class, args);
	}

}
