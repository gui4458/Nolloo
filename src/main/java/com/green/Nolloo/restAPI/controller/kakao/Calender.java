package com.green.Nolloo.restAPI.controller.kakao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Calender {

    @GetMapping("/kakaoCal")
    public String Cal() {

        return "/content/restAPI/kakaoCal";
    }
}
