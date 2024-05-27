package com.cakmak.stock.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class StockExchange implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LIVE_IN_MARKET")
    private Boolean liveInMarket = Boolean.FALSE;

    @OneToMany(mappedBy = "stockExchange")
    private Set<StockExchangeIntersect> stockExchangeIntersects;

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
}
