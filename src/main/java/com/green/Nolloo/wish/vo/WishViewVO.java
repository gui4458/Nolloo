package com.green.Nolloo.wish.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WishViewVO {
    private int boardNum;
    private String boardTitle;
    private String boardContent;
    private int boardPrice;
    private String boardDate;
    private int boardPeople;
    private int peopleCnt;
    private String boardPlace;
    private String placeDetail;
    private int cateCode;
    private String memberId;
    private String memberName;
    private String address;
    private String memberBirth;
    private String gender;
    private String memberEmail;
    private String memberTel;
    private String joinDate;
    private int wishCode;
    private String wishDate;
}
