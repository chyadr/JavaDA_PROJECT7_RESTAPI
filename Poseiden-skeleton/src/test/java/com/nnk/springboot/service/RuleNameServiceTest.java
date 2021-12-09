package com.nnk.springboot.service;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.impl.RuleNameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.nnk.springboot.ConstantsTest.ruleName;
import static com.nnk.springboot.ConstantsTest.ruleNames;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class RuleNameServiceTest {

    @InjectMocks
    private RuleNameService ruleNameService;
    @Mock
    private RuleNameRepository ruleNameRepository;
    @Test
    public void givenRuleName_whenFinAllRuleName_thenReturnList() {
        when(ruleNameRepository.findAll()).thenReturn(ruleNames);
        List<RuleName> ruleNames =ruleNameService.findAll();
        assertNotNull(ruleNames);
        assertEquals(1, ruleNames.size());
    }

    @Test
    public void givenBidList_whenCreateBidList_thenVerify() {
        when(ruleNameRepository.save(any())).thenReturn(ConstantsTest.ruleName);
        ruleNameService.createRuleName(ruleName);
        verify(ruleNameRepository,times(1)).save(ruleName);
    }


    @Test
    public void givenBidList_whenUpdateBidList_thenVerify() {
        when(ruleNameRepository.save(any())).thenReturn(ruleName);
        ruleNameService.updateRuleName(ruleName);
        verify(ruleNameRepository,times(1)).save(ruleName);
    }

    @Test
    public void givenBidList_whenUDeleteBidList_thenVerify() {
        doNothing().when(ruleNameRepository).delete(any());
        ruleNameService.deleteRuleName(ruleName);
        verify(ruleNameRepository,times(1)).delete(ruleName);
    }
}


