package com.green.Nolloo.chat.manager;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChatRoomManager {
    private Map<String, List<WebSocketSession>> chatRooms = new HashMap<>();

    public void addChatRoom(String roomId, WebSocketSession session) {
        if (!chatRooms.containsKey(roomId)) {
            chatRooms.put(roomId, new ArrayList<>());
        }
        chatRooms.get(roomId).add(session);
    }

    public void removeChatRoom(String roomId) {
        chatRooms.remove(roomId);
    }

    public List<WebSocketSession> getChatRoom(String roomId) {
        return chatRooms.get(roomId);
    }

    public void sendMessageToRoom(String roomId, String message) throws Exception {
        List<WebSocketSession> sessions = chatRooms.get(roomId);
        if (sessions != null) {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                }
            }
        }
    }

    public void removeSessionFromRoom(String roomId, WebSocketSession session) {
        List<WebSocketSession> sessions = chatRooms.get(roomId);
        if (sessions != null) {
            sessions.remove(session);
        }
    }
}