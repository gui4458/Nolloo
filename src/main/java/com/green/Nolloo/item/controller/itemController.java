package com.green.Nolloo.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class itemController {
    @GetMapping("/list")
    public String list (){
        return "content/main";
    }

}
