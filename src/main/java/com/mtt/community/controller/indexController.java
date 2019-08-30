package com.mtt.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController
{
    @GetMapping(value = "/")
    public String index(){
        return "index";
    }
}
