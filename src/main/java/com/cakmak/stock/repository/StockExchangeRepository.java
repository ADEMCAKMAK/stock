package com.cakmak.stock.repository;

import com.cakmak.stock.entity.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange, Long> {
    StockExchange findByName(String name);


}
