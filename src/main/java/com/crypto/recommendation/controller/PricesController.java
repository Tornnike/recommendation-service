package com.crypto.recommendation.controller;

import com.crypto.recommendation.model.Prices;
import com.crypto.recommendation.repository.PriceRepository;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendation-service")
public class PricesController {

    @Autowired
    PriceRepository priceRepository;

    @GetMapping("/normal-range")
    public ResponseEntity<List<String>> normalizedRange (){
        List<String> prices = priceRepository.findNormalizedRange();
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    @GetMapping("/monthly/{month}")
    public ResponseEntity<List<String>> monthlyStats(@PathVariable int month){
        List<String> monthlyStatsList = priceRepository.getMonthlyStats(month);
        return new ResponseEntity<>(monthlyStatsList, HttpStatus.OK);
    }

    @GetMapping("/crypto/{crypto}")
    public ResponseEntity<List<String>> monthlyStats(@PathVariable String crypto){
        List<String> monthlyStatsList = priceRepository.getMonthlyStatsByCrypto(crypto);
        return new ResponseEntity<>(monthlyStatsList, HttpStatus.OK);
    }
}
