package com.green.Nolloo.chat.service;

import com.green.Nolloo.chat.vo.ChatMessageVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("chatMessageService")
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 채팅 메시지 저장 메서드 구현
    @Override
    public void saveChatMessage(ChatMessageVO chatMessage) {
        sqlSession.insert("chatMapper.insertChatMessage", chatMessage);
    }

    // 채팅 메시지 검색 메서드 구현
    @Override
    public List<ChatMessageVO> getChatMessagesByRoom(String roomId) {
        return sqlSession.selectList("chatMapper.selectChatMessagesByRoom", roomId);
    }
}