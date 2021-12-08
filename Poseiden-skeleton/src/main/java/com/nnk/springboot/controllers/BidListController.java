package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.IBidListService;
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
public class BidListController {
    // TODO: Inject Bid service
    private final IBidListService bidListService;

    public BidListController(IBidListService bidListService) {
        this.bidListService = bidListService;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
        List<BidList> bids= bidListService.findAll();
        model.addAttribute("bids",bids);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if(!result.hasErrors()) {
            bidListService.createBidList(bid);
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        Optional<BidList> bidList = bidListService.findById(id);
        model.addAttribute("bidList",bidList.orElse(null));
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        if(!result.hasErrors()) {
            bidListService.updateBidList(bidList);
        }
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        Optional<BidList> bidList = bidListService.findById(id);
        bidList.ifPresent(bidListService::deleteBid);
        return "redirect:/bidList/list";
    }
}
