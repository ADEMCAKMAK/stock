package com.cakmak.stock.mapper;

import com.cakmak.stock.dto.StockExchangeDTO;
import com.cakmak.stock.entity.StockExchange;
import org.springframework.stereotype.Component;

@Component
public class StockExchangeMapper {

    public StockExchangeDTO fromEntityToDTO(final StockExchange stockExchange){
        return new StockExchangeDTO(stockExchange.getId(), stockExchange.getName(),
                stockExchange.getDescription(), stockExchange.getLiveInMarket());
    }
}
