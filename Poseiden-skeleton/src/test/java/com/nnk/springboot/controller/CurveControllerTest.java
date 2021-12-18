package com.nnk.springboot.controller;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.services.impl.CurvePointService;
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
@WebMvcTest(CurveController.class)
@WithMockUser(username = "user", authorities = { "USER" })
public class CurveControllerTest {

        @Autowired
        private MockMvc mvc;
        @MockBean
        private CurvePointService curvePointService;
        @MockBean
        private UserService userService;
        @MockBean
        private UserServiceDetails userServiceDetails;

        @Test
        public void givenNothing_whenHome_thenReturnCurveList() throws Exception {

            when(curvePointService.findAll()).thenReturn(ConstantsTest.curvePoints);

            mvc.perform(get("/curvePoint/list")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                    .andExpect(status().isOk()).andExpect(model()
                            .attributeExists("curvePoints"))
                    .andExpect(view().name("curvePoint/list"));
        }
        @Test
        public void givenCurve_whenAddBidForm_thenReturnCurveAdd()throws Exception{
            mvc.perform(get("/curvePoint/add")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(view().name("curvePoint/add"));
        }

    @Test
    public void givenCurve_whenValidate_thenReturnCurveList()throws Exception{
            doNothing().when(curvePointService).createCurvePoint(any());

        mvc.perform(post("/curvePoint/validate").flashAttr("curvePoint",ConstantsTest.curvePoint)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }

    @Test
    public void givenCurve_whenValidate_thenReturnCurveAdd()throws Exception{
        doNothing().when(curvePointService).createCurvePoint(ConstantsTest.invalidCurvePoint);

        mvc.perform(post("/curvePoint/validate")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    public void givenCurveId_whenShowUpdateForm_thenReturnCurveUpdate()throws Exception{
        when(curvePointService.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.curvePoint));

        mvc.perform(get("/curvePoint/update/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk()).andExpect(model()
                        .attributeExists("curvePoint"))
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    public void givenInvalidCurveAndId_whenUpdateBid_thenReturnCurveList()throws Exception{

        mvc.perform(post("/curvePoint/update/1")
                        .flashAttr("curvePoint",ConstantsTest.invalidCurvePoint)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }

    @Test
    public void givenValidCurveAndId_whenUpdateBid_thenReturnCurveList()throws Exception{
        doNothing().when(curvePointService).updateCurvePoint(any());
        mvc.perform(post("/curvePoint/update/1")
                        .flashAttr("curvePoint",ConstantsTest.curvePoint)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }



    @Test
    public void givenCurveId_whenDeleteBid_thenReturnCurveList()throws Exception{
        when(curvePointService.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.curvePoint));
        doNothing().when(curvePointService).deleteCurvePoint(any());

        mvc.perform(get("/curvePoint/delete/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }


    }

