package com.green.Nolloo.chat.service;

import com.green.Nolloo.chat.vo.ChatVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("chatService")
public class ChatServiceImpl implements ChatService{
    @Autowired
    private SqlSessionTemplate sqlSession;


}
