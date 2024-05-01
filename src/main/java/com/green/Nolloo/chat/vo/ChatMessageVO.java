package com.green.Nolloo.chat.vo;

import lombok.Data;

@Data
public class ChatMessageVO {
    private String roomId;
    private String sender;
    private String message;
    private int roomCode;
    // 추가적인 필드들이 필요할 경우 여기에 추가할 수 있습니다.
}