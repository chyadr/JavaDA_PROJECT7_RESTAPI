package com.nnk.springboot.controller;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.services.impl.TradeService;
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
@WebMvcTest(TradeController.class)
@WithMockUser(username = "user", authorities = { "USER" })
public class TradeControllerTest {

        @Autowired
        private MockMvc mvc;
        @MockBean
        private TradeService tradeService;
        @MockBean
        private UserService userService;
        @MockBean
        private UserServiceDetails userServiceDetails;

        @Test
        public void givenNothing_whenHome_thenReturnTradeList() throws Exception {

            when(tradeService.findAll()).thenReturn(ConstantsTest.trades);

            mvc.perform(get("/trade/list")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                    .andExpect(status().isOk()).andExpect(model()
                            .attributeExists("trades"))
                    .andExpect(view().name("trade/list"));
        }
        @Test
        public void givenTrade_whenAddBidForm_thenReturnTradeAdd()throws Exception{
            mvc.perform(get("/trade/add")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(view().name("trade/add"));
        }

    @Test
    public void givenTrade_whenValidate_thenReturnTradeList()throws Exception{
            doNothing().when(tradeService).createTrade(any());

        mvc.perform(post("/trade/validate").flashAttr("trade",ConstantsTest.trade)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/trade/list"));
    }

    @Test
    public void givenTrade_whenValidate_thenReturnTradeAdd()throws Exception{
        doNothing().when(tradeService).createTrade(ConstantsTest.invalidTrade);

        mvc.perform(post("/trade/validate")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    @Test
    public void givenTradeId_whenShowUpdateForm_thenReturnTradeUpdate()throws Exception{
        when(tradeService.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.trade));

        mvc.perform(get("/trade/update/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk()).andExpect(model()
                        .attributeExists("trade"))
                .andExpect(view().name("trade/update"));
    }

    @Test
    public void givenInvalidTradeAndId_whenUpdateBid_thenReturnTradeList()throws Exception{

        mvc.perform(post("/trade/update/1")
                        .flashAttr("trade",ConstantsTest.invalidTrade)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/trade/list"));
    }

    @Test
    public void givenValidTradeAndId_whenUpdateBid_thenReturnTradeList()throws Exception{
        doNothing().when(tradeService).updateTrade(any());
        mvc.perform(post("/trade/update/1")
                        .flashAttr("trade",ConstantsTest.trade)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/trade/list"));
    }



    @Test
    public void givenTradeId_whenDeleteBid_thenReturnTradeList()throws Exception{
        when(tradeService.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.trade));
        doNothing().when(tradeService).deleteTrade(any());

        mvc.perform(get("/trade/delete/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/trade/list"));
    }


    }

