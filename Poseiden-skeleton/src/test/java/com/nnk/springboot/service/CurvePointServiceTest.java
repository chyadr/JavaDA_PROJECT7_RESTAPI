package com.nnk.springboot.service;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.impl.BidListService;
import com.nnk.springboot.services.impl.CurvePointService;
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
public class CurvePointServiceTest {

     @InjectMocks
     private CurvePointService curvePointService;
     @Mock
     private CurvePointRepository curvePointRepository;

     @Test
     public void givenBidList_whenFinAllBidList_thenReturnList() {
         when(curvePointRepository.findAll()).thenReturn(ConstantsTest.curvePoints);
         List<CurvePoint> curvePoints =curvePointService.findAll();
         assertNotNull(curvePoints);
         assertEquals(1, curvePoints.size());
     }

    @Test
    public void givenBidList_whenCreateBidList_thenVerify() {
        when(curvePointRepository.save(any())).thenReturn(ConstantsTest.curvePoint);
        curvePointService.createCurvePoint(ConstantsTest.curvePoint);
        verify(curvePointRepository,times(1)).save(ConstantsTest.curvePoint);
    }


    @Test
    public void givenBidList_whenUpdateBidList_thenVerify() {
        when(curvePointRepository.save(any())).thenReturn(ConstantsTest.curvePoint);
        curvePointService.updateCurvePoint(ConstantsTest.curvePoint);
        verify(curvePointRepository,times(1)).save(ConstantsTest.curvePoint);
    }

    @Test
    public void givenBidList_whenUDeleteBidList_thenVerify() {
        doNothing().when(curvePointRepository).delete(any());
        curvePointService.deleteCurvePoint(ConstantsTest.curvePoint);
        verify(curvePointRepository,times(1)).delete(ConstantsTest.curvePoint);
    }
}
