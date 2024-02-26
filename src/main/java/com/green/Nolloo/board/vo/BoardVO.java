package com.green.Nolloo.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
    private int boardNum;
    private String boardTitle;
    private String boardContent;
    private String boardPrice;
    private String boardDate;
    private String boardPeople;
    private int peopleCnt;
    private String boardPlace;
    private String placeDetail;
    private int cateCode;
}
