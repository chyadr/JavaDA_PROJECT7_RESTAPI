package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;

import java.util.List;
import java.util.Optional;

public interface IRuleNameSevice {
    void createRuleName(RuleName ruleName);
    List<RuleName> findAll();
    Optional<RuleName> findById(Integer id);
    void updateRuleName(RuleName ruleName);
    void deleteRuleName(RuleName ruleName);
}
