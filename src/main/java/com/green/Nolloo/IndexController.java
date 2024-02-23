package com.green.Nolloo;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {
    @GetMapping("/")
    public String maim(){
        return "redirect:/item/list";
    }
}
