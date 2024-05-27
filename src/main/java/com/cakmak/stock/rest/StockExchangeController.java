package com.cakmak.stock.rest;

import com.cakmak.stock.dto.StockExchangeDTO;
import com.cakmak.stock.entity.StockExchange;
import com.cakmak.stock.service.StockExchangeIntersectService;
import com.cakmak.stock.service.StockExchangeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock-exchange")
public class StockExchangeController {

    private final StockExchangeService stockExchangeService;
    private final StockExchangeIntersectService stockExchangeIntersectService;

    public StockExchangeController(StockExchangeService stockExchangeService,
                                   StockExchangeIntersectService stockExchangeIntersectService) {
        this.stockExchangeService = stockExchangeService;
        this.stockExchangeIntersectService = stockExchangeIntersectService;
    }

    @GetMapping("/{name}")
    public StockExchangeDTO listTheStockExchangeWithAllStocks(@PathVariable("name") final String stockExchangeName) {
        return stockExchangeIntersectService.listTheStockExchangeWithAllStocks(stockExchangeName);
    }

    @PostMapping
    public StockExchange createStockExchange(@RequestBody StockExchange stockExchange) {
        return stockExchangeService.saveOrUpdate(stockExchange);
    }

    @PutMapping("/{id}/stock/{name}")
    public void addTheStockToTheStockExchange(@PathVariable("id") final Long id,
                                                       @PathVariable("name") final String name) {
        stockExchangeIntersectService.addTheStockToTheStockExchange(id, name);
    }

    @DeleteMapping("/{id}/stock/{name}")
    public void deleteTheStockFromTheStockExchange(@PathVariable("id") final Long id,
                                                   @PathVariable("name") final String name) {
        stockExchangeIntersectService.deleteTheStockToTheStockExchange(id, name);
    }

}
