package com.Thirdpartyapi.service;

import java.util.List;

import com.Thirdpartyapi.model.BitcoinPrice;

public interface BitcoinPriceService {
	
	public BitcoinPrice getBitcoinPrice();
	
	public List<BitcoinPrice> getBitcoinPrices();

}
