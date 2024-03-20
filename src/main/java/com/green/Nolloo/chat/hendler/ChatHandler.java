package com.green.Nolloo.chat.hendler;

import com.green.Nolloo.chat.manager.ChatRoomManager;
import com.green.Nolloo.chat.room.ChatRoom;
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

    private String extractRoomId(WebSocketSession session) {
        String roomId = session.getUri().toString();
        return roomId.substring(roomId.lastIndexOf("/") + 1);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload : " + payload);

        String roomId = extractRoomId(session);
        List<WebSocketSession> sessions = chatRoomManager.getChatRoom(roomId);

        for (WebSocketSession sess : sessions) {
            if (sess.isOpen()) {
                sess.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String roomId = extractRoomId(session);
        chatRoomManager.addChatRoom(roomId, session);
        log.info(session + " 클라이언트 접속");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String roomId = extractRoomId(session);
        chatRoomManager.removeSessionFromRoom(roomId, session);
        log.info(session + " 클라이언트 접속 해제");
    }
}