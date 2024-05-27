package com.cakmak.stock.service;

import com.cakmak.stock.entity.StockExchange;
import com.cakmak.stock.repository.StockExchangeIntersectRepository;
import com.cakmak.stock.repository.StockExchangeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LiveInMarketService {

    private static final Integer LIMIT = 5;
    private final StockExchangeRepository stockExchangeRepository;
    private final StockExchangeIntersectRepository stockExchangeIntersectRepository;

    public LiveInMarketService(StockExchangeRepository stockExchangeRepository, StockExchangeIntersectRepository stockExchangeIntersectRepository) {
        this.stockExchangeRepository = stockExchangeRepository;
        this.stockExchangeIntersectRepository = stockExchangeIntersectRepository;
    }

    @Transactional
    public void toggleLiveInMarket(List<Long> stockExchangeIds){
        for(Long id : stockExchangeIds){
            toggleLiveInMarket(id);
        }
    }

    private void toggleLiveInMarket(Long stockExchangeId){
        List<Long> stockIds = stockExchangeIntersectRepository.findAllStockIdsByStockExchangeId(stockExchangeId);
        StockExchange stockExchange = stockExchangeRepository.findById(stockExchangeId).get();
        Boolean liveInMarket = stockIds.size() > LIMIT;
        if(!Objects.equals(liveInMarket, stockExchange.getLiveInMarket())) {
            stockExchange.setLiveInMarket(liveInMarket);
            stockExchangeRepository.save(stockExchange);
        }
    }
}
