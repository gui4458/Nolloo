package com.green.Nolloo.member.controller;

import com.green.Nolloo.member.service.MemberServiceImpl;
import com.green.Nolloo.member.vo.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
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
        return "";
    }

}
