package com.crypto.recommendation.repository;

import com.crypto.recommendation.model.Prices;
import java.util.HashMap;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepository extends JpaRepository<Prices, Long> {


    @Query(value = "select symbol, (MAX(price)-MIN(price))/MIN(price) as price from prices group by symbol", nativeQuery = true)
    public List<String> findNormalizedRange();


    @Query(value="select symbol, MAX(price) as MaxPrice, MIN(price) as MinPrice, MAX(timestamp) as Newest,\n" +
            "MIN(timestamp) oldest, MONTH(timestamp) as Month\n" +
            " from prices\n" +
            " where month(timestamp) = ?1\n" +
            "group by symbol, month(timestamp)", nativeQuery = true)
    List<String> getMonthlyStats(int currentMonthNumber);


    @Query(value="select symbol, MAX(price) as MaxPrice, MIN(price) as MinPrice, MAX(timestamp) as Newest,\n" +
            "MIN(timestamp) oldest\n" +
            " from prices\n" +
            " where symbol = ?1\n" +
            "group by symbol", nativeQuery = true)
    List<String> getMonthlyStatsByCrypto(String cryptoName);


}
