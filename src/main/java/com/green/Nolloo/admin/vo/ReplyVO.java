package com.green.Nolloo.admin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReplyVO {
    private int replyCode;
    private String content;
    private String createDate;
    private String writer;
    private int noticeCode;
}
