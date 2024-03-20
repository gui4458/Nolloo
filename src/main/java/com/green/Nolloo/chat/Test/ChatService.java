//package com.green.Nolloo.chat.Test;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import groovy.util.logging.Slf4j;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Import;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class ChatService {
//
//    private final ObjectMapper objectMapper;
//    private Map<String, ChatRoom> chatRooms;
//
//    @PostConstruct
//    private void init() {
//        chatRooms = new HashMap<>();
//    }
//
//    public List<ChatRoom> findAllRoom() {
//        System.out.println("findAllRoom ~");
//        System.out.println(chatRooms.values());
//        return new ArrayList<>(chatRooms.values());
//    }
//
//    public ChatRoom findRoomById(String roomId) {
//        System.out.println("findRoomById ~");
//        System.out.println(chatRooms);
//        System.out.println(roomId);
//        return chatRooms.get(roomId);
//    }
//
//    public ChatRoom createRoom(String name) {
//        String randomId = UUID.randomUUID().toString();
//        ChatRoom chatRoom = ChatRoom.builder()
//                .roomId(randomId)
//                .name(name)
//                .build();
//        chatRooms.put(randomId, chatRoom);
//        return chatRoom;
//    }
//}
