package com.cakmak.stock.service;

import com.cakmak.stock.entity.StockExchange;
import com.cakmak.stock.repository.StockExchangeRepository;
import org.springframework.stereotype.Service;

@Service
public class StockExchangeService {

    private final StockExchangeRepository stockExchangeRepository;

    public StockExchangeService(StockExchangeRepository stockExchangeRepository) {
        this.stockExchangeRepository = stockExchangeRepository;
    }

    public StockExchange saveOrUpdate(StockExchange stockExchange){
        return stockExchangeRepository.save(stockExchange);
    }

    public void deleteStock(final Long id){
        stockExchangeRepository.deleteById(id);
    }

}
