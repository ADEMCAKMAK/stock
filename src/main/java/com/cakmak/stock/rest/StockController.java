package com.cakmak.stock.rest;

import com.cakmak.stock.dto.StockPrice;
import com.cakmak.stock.entity.Stock;
import com.cakmak.stock.service.StockExchangeIntersectService;
import com.cakmak.stock.service.StockService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;
    private final StockExchangeIntersectService stockExchangeIntersectService;

    public StockController(StockService stockService,
                           StockExchangeIntersectService stockExchangeIntersectService) {
        this.stockService = stockService;
        this.stockExchangeIntersectService = stockExchangeIntersectService;
    }

    @PostMapping
    public Stock createStock(@RequestBody Stock stock) {
        return stockService.saveOrUpdate(stock);
    }

    @PutMapping("/{id}")
    public Stock updateStock(@PathVariable final Long id, @Valid @RequestBody StockPrice stockPrice) {
        return stockService.updatePriceOfStock(id, stockPrice);
    }

    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable("id") final Long id) {
        stockExchangeIntersectService.deleteTheStockFromSystem(id);
    }
}
