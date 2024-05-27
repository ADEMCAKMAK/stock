package com.cakmak.stock.repository;

import com.cakmak.stock.entity.StockExchange;
import com.cakmak.stock.entity.StockExchangeIntersect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockExchangeIntersectRepository extends JpaRepository<StockExchangeIntersect, Long> {

    StockExchangeIntersect findByStockIdAndStockExchangeId(Long stockId, Long stockExchangeId);

    @Query("SELECT stock.id FROM StockExchangeIntersect WHERE stockExchange.id = :stockExchangeId")
    List<Long> findAllStockIdsByStockExchangeId(@Param("stockExchangeId") Long stockExchangeId);

    @Query("SELECT stockExchange.id FROM StockExchangeIntersect WHERE stock.id = :stockId")
    List<Long> findAllStockExchangeIdsByStockId(@Param("stockId") Long stockId);

    @Modifying
    @Query("DELETE FROM StockExchangeIntersect WHERE stock.id = :stockId")
    void deleteAllIntersectByStockId(@Param("stockId") Long stockId);

    long countByStockExchange(final StockExchange stockExchange);
}
