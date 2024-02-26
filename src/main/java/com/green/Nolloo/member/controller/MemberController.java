package com.green.Nolloo.member.controller;

import com.green.Nolloo.member.service.MemberServiceImpl;
import com.green.Nolloo.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
@Resource(name="memberService")
    private MemberServiceImpl memberService;

    //회원가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        memberService.join(memberVO);
        return "redirect:/item/list";
    }

    //로그인 화면으로 이동
    @GetMapping("/loginPage")
    public String loginPage(){
        return "/content/member/login";
    }

    //로그인
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){
    MemberVO loginInfo = memberService.login(memberVO);

    if(loginInfo != null){
        session.setAttribute("loginInfo",loginInfo);

    }
    return "content/member/login_result";

    }

    //로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session){
    session.removeAttribute("loginInfo");
        return "redirect:/item/list";
    }

}
