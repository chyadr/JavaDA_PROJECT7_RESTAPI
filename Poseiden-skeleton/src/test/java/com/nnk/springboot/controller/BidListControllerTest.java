package com.nnk.springboot.controller;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.impl.BidListService;
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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BidListController.class)
@WithMockUser(username = "user", authorities = { "USER" })
public class BidListControllerTest {

        @Autowired
        private MockMvc mvc;
        @MockBean
        private BidListService bidListService;
        @MockBean
        private UserService userService;
        @MockBean
        private UserServiceDetails userServiceDetails;

        @Test
        public void givenNothing_whenHome_thenReturnBidListList() throws Exception {

            when(bidListService.findAll()).thenReturn(ConstantsTest.bids);

            mvc.perform(get("/bidList/list")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                    .andExpect(status().isOk()).andExpect(model()
                            .attributeExists("bids"))
                    .andExpect(view().name("bidList/list"));
        }
        @Test
        public void givenBidList_whenAddBidForm_thenReturnBidListAdd()throws Exception{
            mvc.perform(get("/bidList/add")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(view().name("bidList/add"));
        }

    @Test
    public void givenBidList_whenValidate_thenReturnBidListList()throws Exception{
            doNothing().when(bidListService).createBidList(any());

        mvc.perform(post("/bidList/validate").flashAttr("bidList",ConstantsTest.bidList)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bidList/list"));
    }

    @Test
    public void givenBidList_whenValidate_thenReturnBidListAdd()throws Exception{
        doNothing().when(bidListService).createBidList(ConstantsTest.invalidBidList);

        mvc.perform(post("/bidList/validate")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    public void givenBidListId_whenShowUpdateForm_thenReturnBidListUpdate()throws Exception{
        when(bidListService.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.bidList));

        mvc.perform(get("/bidList/update/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk()).andExpect(model()
                        .attributeExists("bidList"))
                .andExpect(view().name("bidList/update"));
    }

    @Test
    public void givenInvalidBidListAndId_whenUpdateBid_thenReturnBidListList()throws Exception{

        mvc.perform(post("/bidList/update/1")
                        .flashAttr("bidList",ConstantsTest.invalidBidList)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bidList/list"));
    }

    @Test
    public void givenValidBidListAndId_whenUpdateBid_thenReturnBidListList()throws Exception{
        doNothing().when(bidListService).updateBidList(any());
        mvc.perform(post("/bidList/update/1")
                        .flashAttr("bidList",ConstantsTest.bidList)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bidList/list"));
    }



    @Test
    public void givenBidListId_whenDeleteBid_thenReturnBidListList()throws Exception{
        when(bidListService.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.bidList));
        doNothing().when(bidListService).deleteBid(any());

        mvc.perform(get("/bidList/delete/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bidList/list"));
    }


    }

