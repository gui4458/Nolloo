package com.green.Nolloo.chat.controller;

import com.green.Nolloo.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
public class ChatController {

    @GetMapping("/chat")
    public String chatGET(Model model,HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        System.out.println(loginInfo);
        model.addAttribute("loginInfo",loginInfo);
        log.info("@ChatController, chat GET()");

        return "content/chat/chat";
    }

}