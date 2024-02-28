package com.green.Nolloo.restAPI.controller.kakao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Map {

    @GetMapping("/position")
    public String pos() {

        return "/content/restAPI/position";
    }

    @GetMapping("/find-items")
    public String findItems() {

        return "/content/restAPI/find-items";
    }



}
