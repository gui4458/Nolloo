package com.green.Nolloo.wish.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WishViewVO {
    private int itemCode;
    private String itemTitle;
    private String itemContent;
    private int itemPrice;
    private String itemDate;
    private int itemPeople;
    private int peopleCnt;
    private String itemPlace;
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
    private String attachedFileName;
}
