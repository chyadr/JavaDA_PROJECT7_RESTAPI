package com.nnk.springboot.controller;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.services.impl.RuleNameService;
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
@WebMvcTest(RuleNameController.class)
@WithMockUser(username = "user", authorities = { "USER" })
public class RuleNameControllerTest {

        @Autowired
        private MockMvc mvc;
        @MockBean
        private RuleNameService ruleNameService;
        @MockBean
        private UserService userService;
        @MockBean
        private UserServiceDetails userServiceDetails;

        @Test
        public void givenNothing_whenHome_thenReturnRuleNameList() throws Exception {

            when(ruleNameService.findAll()).thenReturn(ConstantsTest.ruleNames);

            mvc.perform(get("/ruleName/list")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                    .andExpect(status().isOk()).andExpect(model()
                            .attributeExists("ruleNames"))
                    .andExpect(view().name("ruleName/list"));
        }
        @Test
        public void givenRuleName_whenAddBidForm_thenReturnRuleNameAdd()throws Exception{
            mvc.perform(get("/ruleName/add")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(view().name("ruleName/add"));
        }

    @Test
    public void givenRuleName_whenValidate_thenReturnRuleNameList()throws Exception{
            doNothing().when(ruleNameService).createRuleName(any());

        mvc.perform(post("/ruleName/validate").flashAttr("ruleName",ConstantsTest.ruleName)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"));
    }

    @Test
    public void givenRuleName_whenValidate_thenReturnRuleNameAdd()throws Exception{
        doNothing().when(ruleNameService).createRuleName(ConstantsTest.invalidRuleName);

        mvc.perform(post("/ruleName/validate")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    public void givenRuleNameId_whenShowUpdateForm_thenReturnRuleNameUpdate()throws Exception{
        when(ruleNameService.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.ruleName));

        mvc.perform(get("/ruleName/update/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk()).andExpect(model()
                        .attributeExists("ruleName"))
                .andExpect(view().name("ruleName/update"));
    }

    @Test
    public void givenInvalidRuleNameAndId_whenUpdateBid_thenReturnRuleNameList()throws Exception{

        mvc.perform(post("/ruleName/update/1")
                        .flashAttr("ruleName",ConstantsTest.invalidRuleName)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"));
    }

    @Test
    public void givenValidRuleNameAndId_whenUpdateBid_thenReturnRuleNameList()throws Exception{
        doNothing().when(ruleNameService).updateRuleName(any());
        mvc.perform(post("/ruleName/update/1")
                        .flashAttr("ruleName",ConstantsTest.ruleName)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"));
    }



    @Test
    public void givenRuleNameId_whenDeleteBid_thenReturnRuleNameList()throws Exception{
        when(ruleNameService.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.ruleName));
        doNothing().when(ruleNameService).deleteRuleName(any());

        mvc.perform(get("/ruleName/delete/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"));
    }


    }

