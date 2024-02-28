package com.green.Nolloo.wish.controller;

import com.green.Nolloo.wish.service.WishService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wish")
public class WishController {

    @Resource(name="wishService")
    private WishService wishService;

    @GetMapping("/goWishList")
    public String goWishList(){

        return "content/wish/wish_list";
    }

    @GetMapping("/insertWish")
    public String insertWish(){
        wishService.insertWish();
        return "redirect:/wish/goWishList";
    }

}
