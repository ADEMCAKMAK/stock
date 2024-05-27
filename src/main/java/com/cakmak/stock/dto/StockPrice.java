package com.cakmak.stock.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class StockPrice implements Serializable {

    @NotNull
    private Long price;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
