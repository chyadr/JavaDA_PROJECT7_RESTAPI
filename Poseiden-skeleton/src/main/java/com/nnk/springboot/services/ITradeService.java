package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;

import java.util.List;
import java.util.Optional;

public interface ITradeService {

    void createTrade(Trade trade);
    List<Trade> findAll();
    Optional<Trade> findById(Integer id);
    void updateTrade(Trade trade);
    void deleteTrade(Trade trade);
}
