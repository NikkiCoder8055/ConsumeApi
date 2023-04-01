package com.Thirdpartyapi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Thirdpartyapi.model.BitcoinPrice;
import com.Thirdpartyapi.repository.BitcoinPriceRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BitcoinPriceServiceImpl implements BitcoinPriceService {
	
	
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public BitcoinPrice getBitcoinPrice() {
		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
		
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode bpiNode = root.path("bpi");
            Iterator<Map.Entry<String, JsonNode>> fields = bpiNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String currency = entry.getKey();
                String rate = entry.getValue().path("rate").asText();
                return new BitcoinPrice(currency,rate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	public List<BitcoinPrice> getBitcoinPrices() {
        String apiUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<BitcoinPrice> bitcoinPrices = new ArrayList<>();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode bpiNode = root.path("bpi");
            Iterator<Map.Entry<String, JsonNode>> fields = bpiNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String currency = entry.getKey();
                String rate = entry.getValue().path("rate").asText();
                BitcoinPrice bitcoinPrice = new BitcoinPrice(currency, rate);
                bitcoinPrices.add(bitcoinPrice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitcoinPrices;
	}

}
