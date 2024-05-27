package com.cakmak.stock.dto;

import com.cakmak.stock.entity.Stock;

import java.io.Serializable;
import java.util.List;

public class StockExchangeDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Boolean liveInMarket;

    private List<Stock> stocks;

    public StockExchangeDTO() {
    }

    public StockExchangeDTO(Long id, String name, String description, Boolean liveInMarket) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.liveInMarket = liveInMarket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getLiveInMarket() {
        return liveInMarket;
    }

    public void setLiveInMarket(Boolean liveInMarket) {
        this.liveInMarket = liveInMarket;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
