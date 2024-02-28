package com.green.Nolloo.wish.controller;

import com.green.Nolloo.member.vo.MemberVO;
import com.green.Nolloo.wish.service.WishService;
import com.green.Nolloo.wish.service.WishServiceImpl;
import com.green.Nolloo.wish.vo.WishVO;
import com.green.Nolloo.wish.vo.WishViewVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/wish")
public class WishController {

    @Resource(name="wishService")
    private WishService wishService;

    @GetMapping("/goWishList")
    public String goWishList(Model model,HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        List<WishViewVO> wishList = wishService.selectWish(loginInfo.getMemberId());
        model.addAttribute("wishList",wishList);
        return "content/wish/wish_list";
    }


//  관심목록에 같은 상품이 있는지 체크
    @ResponseBody
    @PostMapping("/WishListChk")
    public List<WishViewVO> WishListChk(HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        List<WishViewVO> wishList = wishService.selectWish(loginInfo.getMemberId());
        return wishList;
    }

//  관심목록에 아이템 추가
    @GetMapping("/insertWish")
    public String insertWish(WishVO wishVO, HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        wishVO.setMemberId(loginInfo.getMemberId());
        wishService.insertWish(wishVO);
        return "redirect:/wish/goWishList";
    }

}
