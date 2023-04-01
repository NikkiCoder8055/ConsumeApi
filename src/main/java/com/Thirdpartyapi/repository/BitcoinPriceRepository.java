package com.Thirdpartyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Thirdpartyapi.model.BitcoinPrice;

public interface BitcoinPriceRepository extends JpaRepository<BitcoinPrice, Integer>{
	
//	void saveBitcoinPrice(BitcoinPrice bitcoinPrice);

}
