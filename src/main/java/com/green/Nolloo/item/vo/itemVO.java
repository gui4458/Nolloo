package com.green.Nolloo.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class itemVO {
    private int itemCode;
    private String itemTitle;
    private String itemContent;
    private int itemPrice;
    private int itemDate;
    private int itemPeople;
    private int itemPlace;
    private int placeDetail;
    private int cateCode;
}
