package com.edgar.ebay.bidding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.ebay.bidding.models.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

}
