package com.nnk.springboot.service;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.impl.CurvePointService;
import com.nnk.springboot.services.impl.RatingService;
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
public class RatingServiceTest {

     @InjectMocks
     private RatingService ratingService;
     @Mock
     private RatingRepository ratingRepository;

     @Test
     public void givenBidList_whenFinAllBidList_thenReturnList() {
         when(ratingRepository.findAll()).thenReturn(ConstantsTest.ratings);
         List<Rating> ratings =ratingService.findAll();
         assertNotNull(ratings);
         assertEquals(1, ratings.size());
     }

    @Test
    public void givenBidList_whenCreateBidList_thenVerify() {
        when(ratingRepository.save(any())).thenReturn(ConstantsTest.rating);
        ratingService.createRating(ConstantsTest.rating);
        verify(ratingRepository,times(1)).save(ConstantsTest.rating);
    }


    @Test
    public void givenBidList_whenUpdateBidList_thenVerify() {
        when(ratingRepository.save(any())).thenReturn(ConstantsTest.rating);
        ratingService.updateRating(ConstantsTest.rating);
        verify(ratingRepository,times(1)).save(ConstantsTest.rating);
    }

    @Test
    public void givenBidList_whenUDeleteBidList_thenVerify() {
        doNothing().when(ratingRepository).delete(any());
        ratingService.deleteRating(ConstantsTest.rating);
        verify(ratingRepository,times(1)).delete(ConstantsTest.rating);
    }
}
