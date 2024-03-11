package com.green.Nolloo.chat.controller;

import com.green.Nolloo.member.service.MemberService;
import com.green.Nolloo.member.vo.MemberImageVO;
import com.green.Nolloo.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Log4j2
public class ChatController {
    @Resource(name = "memberService")
    private MemberService memberService;
    @GetMapping("/chat")
    public String chatGET(){

        log.info("@ChatController, chat GET()");

        return "content/chat/chat";
    }
    @ResponseBody
    @PostMapping("/chatProfile")
    public List<MemberImageVO> chatProfile(){
        List<MemberImageVO> allProfile = memberService.selectAllProfile();
        System.out.println(allProfile);
        return allProfile;
    }

}