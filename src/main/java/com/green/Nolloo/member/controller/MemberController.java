package com.green.Nolloo.member.controller;

import com.green.Nolloo.member.service.MemberServiceImpl;
import com.green.Nolloo.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
@Resource(name="memberService")
    private MemberServiceImpl memberService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    //회원가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        memberVO.setMemberPw(encoder.encode(memberVO.getMemberPw()));
        memberService.join(memberVO);
        return "redirect:/item/list";
    }

    //로그인 화면으로 이동
    @GetMapping("/loginPage")
    public String loginPage(){
        return "/content/member/login";
    }

    //로그인
//    @PostMapping("/login")
//    public String login(MemberVO memberVO, HttpSession session){
//    MemberVO loginInfo = memberService.login(memberVO);
//
//    if(loginInfo != null){
//        session.setAttribute("loginInfo",loginInfo);
//
//    }
//    return "content/member/login_result";
//
//    }

//    //로그아웃
//    @GetMapping("logout")
//    public String logout(HttpSession session){
//    session.removeAttribute("loginInfo");
//        return "redirect:/item/list";
//    }

    @GetMapping("/myPage")
    public String myPage(MemberVO memberVO, Model model, HttpSession session){
        //세션에 있는 로그인한 사람의 id를 가져온다.
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        memberVO.setMemberId(loginInfo.getMemberId());


        MemberVO memberInfo = memberService.memberInfo(memberVO);
        model.addAttribute("memberInfo",memberInfo);

        return "content/member/my_page";
    }

    @PostMapping("/myPage")
    public String myPage1(MemberVO memberVO,HttpSession session,Model model){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        memberVO.setMemberId(loginInfo.getMemberId());
        memberService.revise(memberVO);

//        MemberVO memberInfo = memberService.memberInfo(memberVO);
//        model.addAttribute("memberInfo",memberInfo);
//        return "content/member/my_page";

        return "redirect:/member/myPage";

    }
    @GetMapping("deleteMember")
    public String deleteMember(MemberVO memberVO,HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        memberVO.setMemberId(loginInfo.getMemberId());

        memberService.deleteMember(memberVO);
        session.removeAttribute("loginInfo");

        return "redirect:/item/list";
    }

}
