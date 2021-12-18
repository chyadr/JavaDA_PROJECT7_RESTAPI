package com.nnk.springboot.controller;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.services.impl.RatingService;
import com.nnk.springboot.services.impl.UserService;
import com.nnk.springboot.services.impl.UserServiceDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RatingController.class)
@WithMockUser(username = "user", authorities = { "USER" })
public class RatingControllerTest {

        @Autowired
        private MockMvc mvc;
        @MockBean
        private RatingService ratingService;
        @MockBean
        private UserService userService;
        @MockBean
        private UserServiceDetails userServiceDetails;

        @Test
        public void givenNothing_whenHome_thenReturnRatingList() throws Exception {

            when(ratingService.findAll()).thenReturn(ConstantsTest.ratings);

            mvc.perform(get("/rating/list")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                    .andExpect(status().isOk()).andExpect(model()
                            .attributeExists("ratings"))
                    .andExpect(view().name("rating/list"));
        }
        @Test
        public void givenRating_whenAddBidForm_thenReturnRatingAdd()throws Exception{
            mvc.perform(get("/rating/add")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(view().name("rating/add"));
        }

    @Test
    public void givenRating_whenValidate_thenReturnRatingList()throws Exception{
            doNothing().when(ratingService).createRating(any());

        mvc.perform(post("/rating/validate").flashAttr("rating",ConstantsTest.rating)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"));
    }

    @Test
    public void givenRating_whenValidate_thenReturnRatingAdd()throws Exception{
        doNothing().when(ratingService).createRating(ConstantsTest.invalidRating);

        mvc.perform(post("/rating/validate")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    @Test
    public void givenRatingId_whenShowUpdateForm_thenReturnRatingUpdate()throws Exception{
        when(ratingService.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.rating));

        mvc.perform(get("/rating/update/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk()).andExpect(model()
                        .attributeExists("rating"))
                .andExpect(view().name("rating/update"));
    }

    @Test
    public void givenInvalidRatingAndId_whenUpdateBid_thenReturnRatingList()throws Exception{

        mvc.perform(post("/rating/update/1")
                        .flashAttr("rating",ConstantsTest.invalidRating)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"));
    }

    @Test
    public void givenValidRatingAndId_whenUpdateBid_thenReturnRatingList()throws Exception{
        doNothing().when(ratingService).updateRating(any());
        mvc.perform(post("/rating/update/1")
                        .flashAttr("rating",ConstantsTest.rating)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"));
    }



    @Test
    public void givenRatingId_whenDeleteBid_thenReturnRatingList()throws Exception{
        when(ratingService.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.rating));
        doNothing().when(ratingService).deleteRating(any());

        mvc.perform(get("/rating/delete/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"));
    }


    }

