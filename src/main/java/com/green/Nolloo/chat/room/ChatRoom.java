package com.green.Nolloo.chat.room;

import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private String id;
    @Getter
    private List<WebSocketSession> sessions = new ArrayList<>();

    public ChatRoom(String id) {
        this.id = id;
    }

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

}