package com.green.Nolloo.chat.controller;

import com.green.Nolloo.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
public class ChatController {

    @GetMapping("/chat")
    public String chatGET(){

        log.info("@ChatController, chat GET()");

        return "content/chat/chat";
    }
    @ResponseBody
    @PostMapping("/getInfo")
    public MemberVO getInfo(HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        return loginInfo;
    }
}