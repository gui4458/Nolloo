package com.green.Nolloo.chat.Test;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;


    @RequestMapping("/chat/chatList")
    public String chatList(Model model){
        List<ChatRoom> roomList = chatService.findAllRoom();
        model.addAttribute("roomList",roomList);
        return "content/chat/test1/chatList";
    }


    @PostMapping("/chat/createRoom")  //방을 만들었으면 해당 방으로 가야지.
    public String createRoom(Model model, @RequestParam(name="name") String name
                            , Authentication authentication) {
        ChatRoom room = chatService.createRoom(name);
        User user = (User)authentication.getPrincipal();
        System.out.println("memberId = " + user.getUsername());

        model.addAttribute("room",room);
        model.addAttribute("memberId",user.getUsername());
        return "content/chat/test1/chatRoom";  //만든사람이 채팅방 1빠로 들어가게 됩니다
    }

    @GetMapping("/chat/chatRoom")
    public String chatRoom(Model model, @RequestParam(name="roomId") String roomId){
        System.out.println("챗룸");
        ChatRoom room = chatService.findRoomById(roomId);

        model.addAttribute("room",room);   //현재 방에 들어오기위해서 필요한데...... 접속자 수 등등은 실시간으로 보여줘야 돼서 여기서는 못함
        return "content/chat/test1/chatRoom";
    }
}
