package com.crypto.recommendation.utils;

import com.crypto.recommendation.model.Prices;
import com.crypto.recommendation.repository.PriceRepository;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsvService {

    @Autowired
    PriceRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Prices> prices = CsvHelper.csvToPrices(file.getInputStream());

            for (Prices price : prices) {
                try {
                    repository.save(price);
                } catch(DataIntegrityViolationException e){
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
