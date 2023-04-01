package com.Thirdpartyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Thirdpartyapi.model.BitcoinPrice;
import com.Thirdpartyapi.repository.BitcoinPriceRepository;
import com.Thirdpartyapi.service.BitcoinPriceService;

@RestController
@RequestMapping("/")
public class BitcoinPriceController {

	@Autowired
	private BitcoinPriceService bitPriceService;
	
	@Autowired
	private BitcoinPriceRepository repository;
	
	 @GetMapping("/bitcoin")
	    public BitcoinPrice getAndSaveBitcoinPrice() {
	        BitcoinPrice bitcoinPrice = bitPriceService.getBitcoinPrice();
	        repository.save(bitcoinPrice);
	        return bitcoinPrice;
	    }
	 @GetMapping("/all")
	 public ResponseEntity<List<BitcoinPrice>> getAllBitcoinPrices(){
		 List<BitcoinPrice> bitcoinPrices = this.bitPriceService.getBitcoinPrices();
		 return new ResponseEntity<List<BitcoinPrice>>(bitcoinPrices,HttpStatus.OK);
	 }
	
}
