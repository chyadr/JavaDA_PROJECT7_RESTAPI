package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.IBidListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService implements IBidListService {

    private final BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    @Override
    public void createBidList(BidList bidList) {
        bidListRepository.save(bidList);
    }

    @Override
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    @Override
    public Optional<BidList> findById(Integer bidListId) {
        return bidListRepository.findById(bidListId);
    }

    @Override
    public void updateBidList(BidList bidList) {
        bidListRepository.save(bidList);
    }

    @Override
    public void deleteBid(BidList bidList) {
        bidListRepository.delete(bidList);
    }
}
