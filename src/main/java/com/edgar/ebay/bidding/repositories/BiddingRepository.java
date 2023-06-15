package com.edgar.ebay.bidding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.ebay.bidding.models.Bidding;

@Repository
public interface BiddingRepository extends JpaRepository<Bidding, Long> {

}
