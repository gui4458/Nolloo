package com.green.Nolloo.restAPI.controller.kakao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Pay {
    @GetMapping("/kakaoPay")
    public String kakaoPay() {

        return "/content/restAPI/kakaoPay";
    }
}
