package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.IRuleNameSevice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class RuleNameController {
    // TODO: Inject RuleName service
    private final IRuleNameSevice ruleNameSevice;

    public RuleNameController(IRuleNameSevice ruleNameSevice) {
        this.ruleNameSevice = ruleNameSevice;
    }

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        // TODO: find all RuleName, add to model
        List<RuleName> ruleNames = ruleNameSevice.findAll();
        model.addAttribute("ruleNames", ruleNames);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list
        if (!result.hasErrors()) {
            ruleNameSevice.createRuleName(ruleName);
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form
        Optional<RuleName> ruleName = ruleNameSevice.findById(id);
        model.addAttribute("ruleName", ruleName.orElse(null));
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        if (!result.hasErrors()) {
            ruleNameSevice.updateRuleName(ruleName);
        }
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName (@PathVariable("id") Integer id, Model model){
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        Optional<RuleName> ruleName = ruleNameSevice.findById(id);
        ruleName.ifPresent(ruleNameSevice::deleteRuleName);
        return "redirect:/ruleName/list";
    }

}