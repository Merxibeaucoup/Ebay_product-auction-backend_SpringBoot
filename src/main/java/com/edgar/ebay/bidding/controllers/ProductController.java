package com.edgar.ebay.bidding.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.ebay.bidding.models.Product;
import com.edgar.ebay.bidding.security.user.User;
import com.edgar.ebay.bidding.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
	
	
	private final ProductService productService;
	
	@PostMapping("/new")
	public ResponseEntity<Product> createProduct(@RequestBody Product product, @AuthenticationPrincipal User user){
		return ResponseEntity.ok(productService.createProduct(product, user));
		
	}

}
