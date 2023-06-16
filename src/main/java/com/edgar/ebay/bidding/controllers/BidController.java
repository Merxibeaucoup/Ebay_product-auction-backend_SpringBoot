package com.edgar.ebay.bidding.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.ebay.bidding.models.Bidding;
import com.edgar.ebay.bidding.requests.BidRequest;
import com.edgar.ebay.bidding.security.user.User;
import com.edgar.ebay.bidding.services.BiddingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/bid")
@EnableTransactionManagement
@RequiredArgsConstructor
public class BidController {

	public final BiddingService biddingService;

	/* will use web socket later !*/
	@PostMapping("/new/{productId}")
	public ResponseEntity<Bidding> newBidOffer(@PathVariable Long productId, @RequestBody BidRequest bidRequest,
			@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(biddingService.makeBidOffer(productId, bidRequest, user));

	}

}
