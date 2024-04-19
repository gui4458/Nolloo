package com.green.Nolloo.chat.service;


import com.green.Nolloo.chat.vo.ChatMessageVO;

import java.util.List;

public interface ChatMessageService {
    void saveChatMessage(ChatMessageVO chatMessage); // 채팅 메시지 저장 메서드
    List<ChatMessageVO> getChatMessagesByRoom(String roomId); // 채팅 메시지 검색 메서드
}