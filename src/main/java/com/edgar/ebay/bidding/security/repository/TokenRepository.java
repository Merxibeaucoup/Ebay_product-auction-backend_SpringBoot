package com.edgar.ebay.bidding.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edgar.ebay.bidding.security.token.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

	
	Optional<Token> findByToken(String token);
	
	
	@Query("""
			select t from Token t inner join User u on t.user.id = u.id
			where u.id =:userId and (t.expired = false or t.revoked = false)
			""")
	List<Token> findAllValidTokensByUser(Long userId);


	
}
