package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;

import java.util.List;
import java.util.Optional;

public interface IBidListService {

    void createBidList(BidList bidList);
    List<BidList> findAll();
    Optional<BidList> findById(Integer bidListId);
    void updateBidList(BidList bidList);
    void deleteBid(BidList bidList);
}
