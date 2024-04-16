package com.green.Nolloo.admin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class NoticeVO {
    private int noticeCode;
    private String noticeWriter;
    private String noticeTitle;
    private String noticeContent;
    private String noticeDate;
    private int readCnt;
    private List<NoticeImgVO> noticeImgList;
}
