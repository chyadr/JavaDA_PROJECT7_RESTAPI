package com.nnk.springboot.controller;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
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
@WebMvcTest(UserController.class)
@WithMockUser(username = "user", authorities = { "ADMIN" })
public class UserControllerTest {

        @Autowired
        private MockMvc mvc;
        @MockBean
        private UserService userService;
        @MockBean
        private UserServiceDetails userServiceDetails;

        @Test
        public void givenNothing_whenHome_thenReturnUserList() throws Exception {

            when(userService.findAll()).thenReturn(ConstantsTest.users);

            mvc.perform(get("/user/list")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                    .andExpect(status().isOk()).andExpect(model()
                            .attributeExists("users"))
                    .andExpect(view().name("user/list"));
        }
        @Test
        public void givenUser_whenAddBidForm_thenReturnUserAdd()throws Exception{
            mvc.perform(get("/user/add")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(view().name("user/add"));
        }

    @Test
    public void givenValidUser_whenValidate_thenReturnUserList()throws Exception{
            doNothing().when(userService).createUser(any());

        mvc.perform(post("/user/validate").flashAttr("user",ConstantsTest.user)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"));
    }

    @Test
    public void givenInvalidUser_whenValidate_thenReturnUserAdd()throws Exception{
        doNothing().when(userService).createUser(any());

        mvc.perform(post("/user/validate").flashAttr("user",new User())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    public void givenUserId_whenShowUpdateForm_thenReturnUserUpdate()throws Exception{
        when(userService.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.user));

        mvc.perform(get("/user/update/2")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk()).andExpect(model()
                        .attributeExists("user"))
                .andExpect(view().name("user/update"));
    }

    @Test
    public void givenInvalidUserAndId_whenUpdateBid_thenReturnUserUpdate()throws Exception{

        mvc.perform(post("/user/update/2")
                        .flashAttr("user",new User())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"));
    }

    @Test
    public void givenAValidUserAndId_whenUpdateBid_thenReturnUserList()throws Exception{
        doNothing().when(userService).updateUser(any());
        mvc.perform(post("/user/update/2")
                        .flashAttr("user",ConstantsTest.user)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"));
    }



    @Test
    public void givenUserId_whenDeleteBid_thenReturnUserList()throws Exception{
        when(userService.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.user));
        doNothing().when(userService).deleteUser(any());

        mvc.perform(get("/user/delete/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"));
    }


    }

