package com.example.laba1.controller;

import com.example.laba1.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private MainService mainService;

    public MainController() {

    }

    @PostMapping("/calculate")
    public String calculateIntegral(Model model, @RequestParam("threads") int numberOfThreads, @RequestParam("intervals") int numberOfIntervals) {

        mainService = new MainService();
        mainService.calculate(numberOfIntervals,numberOfThreads);
        long time = mainService.getTime();
        double result = mainService.getTotalSum();

        System.out.println("numberOfThreads = " + numberOfThreads);
        System.out.println("numberOfIntervals = " + numberOfIntervals);

        model.addAttribute("time",time);
        model.addAttribute("result",result);
        return "result";
    }
}
