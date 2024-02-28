package com.green.Nolloo.restAPI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")
public class KakaoMap {

    @GetMapping("/kakaoMap")
    public String kakaoMap() {
        return "/content/restAPI/map";
    }

}
