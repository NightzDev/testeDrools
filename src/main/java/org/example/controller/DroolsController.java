package org.example.controller;

import org.checkerframework.checker.units.qual.A;
import org.example.config.DroolsConfig;
import org.example.service.DroolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DroolsController {

    @Autowired
    private DroolsService droolsService;

    @Autowired
    private DroolsConfig droolsConfig;

    @GetMapping("/execute-drools/{number}")
    public void executeDrools(@PathVariable int number) {
        droolsService.executeRules(number);
    }

    @GetMapping("refreshRules")
    public void refreshController(){
        droolsConfig.refreshRules();
    }

}
