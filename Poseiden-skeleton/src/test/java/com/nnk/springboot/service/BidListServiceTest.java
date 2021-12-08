package com.nnk.springboot.service;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.impl.BidListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class BidListServiceTest {

     @InjectMocks
     private BidListService bidListService;
     @Mock
     private BidListRepository bidListRepository;

     @Test
     public void givenBidList_whenFinAllBidList_thenReturnList() {
         when(bidListRepository.findAll()).thenReturn(ConstantsTest.bids);
         List<BidList> bids =bidListService.findAll();
         assertNotNull(bids);
         assertEquals(1, bids.size());
     }

    @Test
    public void givenBidList_whenCreateBidList_thenVerify() {
        when(bidListRepository.save(any())).thenReturn(ConstantsTest.bidList);
        bidListService.createBidList(ConstantsTest.bidList);
        verify(bidListRepository,times(1)).save(ConstantsTest.bidList);
    }


    @Test
    public void givenBidList_whenUpdateBidList_thenVerify() {
        when(bidListRepository.save(any())).thenReturn(ConstantsTest.bidList);
        bidListService.updateBidList(ConstantsTest.bidList);
        verify(bidListRepository,times(1)).save(ConstantsTest.bidList);
    }

    @Test
    public void givenBidList_whenUDeleteBidList_thenVerify() {
        doNothing().when(bidListRepository).delete(any());
        bidListService.deleteBid(ConstantsTest.bidList);
        verify(bidListRepository,times(1)).delete(ConstantsTest.bidList);
    }
}
