package com.green.Nolloo.wish.controller;

import com.green.Nolloo.member.vo.MemberVO;
import com.green.Nolloo.wish.service.WishService;
import com.green.Nolloo.wish.service.WishServiceImpl;
import com.green.Nolloo.wish.vo.WishVO;
import com.green.Nolloo.wish.vo.WishViewVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wish")
public class WishController {

    @Resource(name="wishService")
    private WishService wishService;

    @GetMapping("/goWishList")
    public String goWishList(Model model,Authentication authentication){
        User user = (User)authentication.getPrincipal();

        List<WishViewVO> wishList = wishService.selectWish(user.getUsername());
        model.addAttribute("wishList",wishList);
        return "content/wish/wish_list";
    }


//  관심목록에 같은 상품이 있는지 체크 후 아이템 추가
    @ResponseBody
    @PostMapping("/insertWish")
    public List<WishViewVO> insertWish(Authentication authentication,@RequestBody WishVO wishVO){
        User user = (User)authentication.getPrincipal();
        wishVO.setMemberId(user.getUsername());
        List<WishViewVO> wishList = wishService.selectWish(user.getUsername());
        int chk = wishService.check(wishVO);
        if (chk == 0){
            wishService.insertWish(wishVO);
        }






        return wishList;
    }


//  관심목록에서 아이템 삭제
    @ResponseBody
    @PostMapping("/wishDelete")
    public int wishDelete(@RequestBody WishVO wishVO, Authentication authentication){
        User user = (User)authentication.getPrincipal();
        wishVO.setMemberId(user.getUsername());
        wishService.wishDelete(wishVO);
        return wishService.check(wishVO);
    }

}
