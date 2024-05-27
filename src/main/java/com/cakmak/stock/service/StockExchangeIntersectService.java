package com.cakmak.stock.service;

import com.cakmak.stock.dto.StockExchangeDTO;
import com.cakmak.stock.entity.Stock;
import com.cakmak.stock.entity.StockExchange;
import com.cakmak.stock.entity.StockExchangeIntersect;
import com.cakmak.stock.mapper.StockExchangeMapper;
import com.cakmak.stock.repository.StockExchangeIntersectRepository;
import com.cakmak.stock.repository.StockExchangeRepository;
import com.cakmak.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockExchangeIntersectService {

    private final StockRepository stockRepository;
    private final StockExchangeRepository stockExchangeRepository;
    private final StockExchangeIntersectRepository stockExchangeIntersectRepository;
    private final StockExchangeMapper stockExchangeMapper;
    private final LiveInMarketService liveInMarketService;

    public StockExchangeIntersectService(StockRepository stockRepository,
                                         StockExchangeRepository stockExchangeRepository,
                                         StockExchangeIntersectRepository stockExchangeIntersectRepository,
                                         StockExchangeMapper stockExchangeMapper,
                                         LiveInMarketService liveInMarketService) {
        this.stockRepository = stockRepository;
        this.stockExchangeRepository = stockExchangeRepository;
        this.stockExchangeIntersectRepository = stockExchangeIntersectRepository;
        this.stockExchangeMapper = stockExchangeMapper;
        this.liveInMarketService = liveInMarketService;
    }

    public StockExchangeDTO listTheStockExchangeWithAllStocks(final String stockExchangeName){
        StockExchange stockExchange = stockExchangeRepository.findByName(stockExchangeName);
        StockExchangeDTO stockExchangeDTO = stockExchangeMapper.fromEntityToDTO(stockExchange);
        List<Long> stockIds = stockExchangeIntersectRepository.findAllStockIdsByStockExchangeId(stockExchange.getId());
        stockExchangeDTO.setStocks(stockRepository.findAllById(stockIds));
        return stockExchangeDTO;
    }

    @Transactional
    public void addTheStockToTheStockExchange(final Long stockExchangeId, final String stockName){
        Stock stock = stockRepository.findByName(stockName);
        StockExchange stockExchange = stockExchangeRepository.findById(stockExchangeId).get();
        StockExchangeIntersect stockExchangeIntersect = new StockExchangeIntersect();
        stockExchangeIntersect.setStock(stock);
        stockExchangeIntersect.setStockExchange(stockExchange);
        stockExchangeIntersectRepository.save(stockExchangeIntersect);
        liveInMarketService.toggleLiveInMarket(List.of(stockExchangeId));
    }

    @Transactional
    public void deleteTheStockToTheStockExchange(final Long stockExchangeId, final String stockName){
        Stock stock = stockRepository.findByName(stockName);
        StockExchangeIntersect stockExchangeIntersect = stockExchangeIntersectRepository
                .findByStockIdAndStockExchangeId(stock.getId(), stockExchangeId);
        stockExchangeIntersectRepository.delete(stockExchangeIntersect);
        liveInMarketService.toggleLiveInMarket(List.of(stockExchangeId));
    }

    @Transactional
    public void deleteTheStockFromSystem(final Long stockId){
        List<Long> stockExchangeIds = stockExchangeIntersectRepository.findAllStockExchangeIdsByStockId(stockId);
        stockExchangeIntersectRepository.deleteAllIntersectByStockId(stockId);
        stockRepository.deleteById(stockId);
        liveInMarketService.toggleLiveInMarket(stockExchangeIds);
    }
}
