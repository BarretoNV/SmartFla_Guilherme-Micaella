package com.iff.devweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/home" )
public class PageController {
    @GetMapping("")
    public String home() {return "home";}
}
