package com.green.Nolloo.chat.hendler;

import com.green.Nolloo.chat.manager.ChatRoomManager;
import com.green.Nolloo.chat.service.ChatMessageService;
import com.green.Nolloo.chat.vo.ChatMessageVO;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

@Component
@Log4j2
public class ChatHandler extends TextWebSocketHandler {

    @Autowired
    private ChatRoomManager chatRoomManager;

    @Resource(name="chatMessageService")
    private ChatMessageService chatMessageService;

    private String extractRoomId(WebSocketSession session) {
        String roomId = session.getUri().toString();
        return roomId.substring(roomId.lastIndexOf("/") + 1);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("핸들러텍스트메세지");
        String payload = message.getPayload();
        log.info(payload);

        String roomId = extractRoomId(session);
        List<WebSocketSession> sessions = chatRoomManager.getChatRoom(roomId);

        for (WebSocketSession sess : sessions) {
            if (sess.isOpen()) {
                sess.sendMessage(message);
            }
        }

        // 메시지를 데이터베이스에 저장
        saveMessageToDatabase(roomId, payload);
    }

    private void saveMessageToDatabase(String roomId, String payload) {
        String[] parts = payload.split(":", 2); // 사용자명과 메시지 분리
        String sender = parts[0];
        String message = parts[1];

        // ChatMessage 객체 생성 및 저장
        ChatMessageVO chatMessage = new ChatMessageVO();
        chatMessage.setRoomId(roomId);
        chatMessage.setSender(sender);
        chatMessage.setMessage(message);

        // 데이터베이스에 저장
        chatMessageService.saveChatMessage(chatMessage);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String roomId = extractRoomId(session);
        chatRoomManager.addChatRoom(roomId, session);
        log.info(session + " 클라이언트 접속");
        System.out.println("핸들러끝");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String roomId = extractRoomId(session);
        chatRoomManager.removeSessionFromRoom(roomId, session);
        log.info(session + " 클라이언트 접속 해제");
    }
}