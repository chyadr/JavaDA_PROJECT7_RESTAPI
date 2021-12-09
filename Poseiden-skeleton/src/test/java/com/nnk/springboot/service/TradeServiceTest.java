package com.nnk.springboot.service;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.impl.TradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class TradeServiceTest {

     @InjectMocks
     private TradeService tradeService;
     @Mock
     private TradeRepository tradeRepository;

     @Test
     public void givenBidList_whenFinAllBidList_thenReturnList() {
         when(tradeRepository.findAll()).thenReturn(ConstantsTest.trades);
         List<Trade> trades =tradeService.findAll();
         assertNotNull(trades);
         assertEquals(1, trades.size());
     }

    @Test
    public void givenBidList_whenCreateBidList_thenVerify() {
        when(tradeRepository.save(any())).thenReturn(ConstantsTest.trade);
        tradeService.createTrade(ConstantsTest.trade);
        verify(tradeRepository,times(1)).save(ConstantsTest.trade);
    }


    @Test
    public void givenBidList_whenUpdateBidList_thenVerify() {
        when(tradeRepository.save(any())).thenReturn(ConstantsTest.trade);
        tradeService.updateTrade(ConstantsTest.trade);
        verify(tradeRepository,times(1)).save(ConstantsTest.trade);
    }

    @Test
    public void givenBidList_whenUDeleteBidList_thenVerify() {
        doNothing().when(tradeRepository).delete(any());
        tradeService.deleteTrade(ConstantsTest.trade);
        verify(tradeRepository,times(1)).delete(ConstantsTest.trade);
    }
}
