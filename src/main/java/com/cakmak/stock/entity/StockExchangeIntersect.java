package com.cakmak.stock.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class StockExchangeIntersect implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STOCK_ID")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "STOCK_EXCHANGE_ID")
    private StockExchange stockExchange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public StockExchange getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
    }
}
