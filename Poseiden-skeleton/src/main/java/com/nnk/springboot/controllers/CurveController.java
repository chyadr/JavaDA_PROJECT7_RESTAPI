package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.ICurvePointService;
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
public class CurveController {
    // TODO: Inject Curve Point service
    private final ICurvePointService curvePointService;

    public CurveController(ICurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model
        List<CurvePoint> curvePoints = curvePointService.findAll();
        model.addAttribute("curvePoints",curvePoints);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        if(!result.hasErrors()) {
            curvePointService.createCurvePoint(curvePoint);
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        Optional<CurvePoint> curvePoint = curvePointService.findById(id);
        model.addAttribute("curvePoint",curvePoint.orElse(null));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        if(!result.hasErrors()) {
            curvePointService.updateCurvePoint(curvePoint);
        }
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        Optional<CurvePoint> curvePoint = curvePointService.findById(id);
        curvePoint.ifPresent(curvePointService::deleteCurvePoint);
        return "redirect:/curvePoint/list";
    }
}
