package com.crypto.recommendation.repository;

import com.crypto.recommendation.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Prices, Long> {
}
