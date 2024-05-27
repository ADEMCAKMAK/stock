package com.cakmak.stock.service;


import com.cakmak.stock.dto.StockPrice;
import com.cakmak.stock.entity.Stock;
import com.cakmak.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public Stock saveOrUpdate(Stock stock){
        return stockRepository.save(stock);
    }

    @Transactional
    public void deleteStock(final Long id){
        stockRepository.deleteById(id);
    }

    @Transactional
    public Stock updatePriceOfStock(final Long id, final StockPrice stockPrice) {
        Stock stock = this.stockRepository.getReferenceById(id);
        stock.setCurrentPrice(stockPrice.getPrice());
        return this.saveOrUpdate(stock);
    }
}
